package com.cyh.hash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 从 Dubbo 源码 org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance 中取出来的
 *
 * 其实是 Ketama Hash 算法
 */
public class ConsistentHash {

    private static final ConcurrentMap<String, ConsistentHashSelector> selectors = new ConcurrentHashMap<>();

    public static Invoker doSelect(List<Invoker> invokers, Object[] args) {
        String key = "com.cyh.dubbo.DemoService" + "." + "sayHello";
        ConsistentHashSelector selector = selectors.get(key);
        if (selector == null) {
            selector = new ConsistentHashSelector(invokers);
            selectors.put(key, selector);
        }
        return selector.select(args);
    }

    private static final class ConsistentHashSelector {
        // 使用 TreeMap 存储 Invoker 虚拟节点
        private final TreeMap<Long, Invoker> virtualInvokers;
        private final int[] argumentIndex;

        /**
         * ConsistentHashSelector 的构造方法执行了一系列的初始化逻辑，
         * 比如从配置中获取虚拟节点数以及参与 hash 计算的参数下标，默认情况下只使用第一个参数进行 hash
         */
        ConsistentHashSelector(List<Invoker> invokers) {
            this.virtualInvokers = new TreeMap<>();
            // 获取虚拟节点数，默认为160
            int replicaNumber = 160;
            // 获取参与 hash 计算的参数下标值，默认对第一个参数进行 hash 运算
            String[] index = {"0"};
            argumentIndex = new int[index.length];
            for (int i = 0; i < index.length; i++) {
                argumentIndex[i] = Integer.parseInt(index[i]);
            }
            for (Invoker invoker : invokers) {
                String address = invoker.getAddress();
                for (int i = 0; i < replicaNumber / 4; i++) {
                    // 对 address + i 进行 md5 运算，得到一个长度为16的字节数组
                    byte[] digest = md5(address + i);
                    // 对 digest 部分字节进行4次 hash 运算，得到四个不同的 long 型正整数
                    for (int h = 0; h < 4; h++) {
                        long m = hash(digest, h);
                        // 将 hash 到 invoker 的映射关系存储到 virtualInvokers 中，
                        // virtualInvokers 需要提供高效的查询操作，因此选用 TreeMap 作为存储结构
                        virtualInvokers.put(m, invoker);
                    }
                }
            }
        }

        public Invoker select(Object[] args) {
            // 将参数转为 key
            String key = toKey(args);
            // 对参数 key 进行 md5 运算
            byte[] digest = md5(key);
            // 取 digest 数组的前四个字节进行 hash 运算，再将 hash 值传给 selectForKey 方法，寻找合适的 Invoker
            long hash = hash(digest, 0);
            return selectForKey(hash);
        }

        private String toKey(Object[] args) {
            StringBuilder buf = new StringBuilder();
            for (int index : argumentIndex) {
                if (index >= 0 && index < args.length) {
                    buf.append(args[index]);
                }
            }
            return buf.toString();
        }

        private Invoker selectForKey(long hash) {
            // 到 TreeMap 中查找第一个节点值大于或等于当前 hash 的 Invoker
            Map.Entry<Long, Invoker> entry = virtualInvokers.ceilingEntry(hash);
            // 如果 hash 大于 Invoker 在圆环上最大的位置，此时 entry = null，需要将 TreeMap 的头节点赋值给 entry
            if (entry == null) {
                entry = virtualInvokers.firstEntry();
            }
            return entry.getValue();
        }

        private long hash(byte[] digest, int number) {
            return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                    | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                    | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                    | (digest[number * 4] & 0xFF))
                    & 0xFFFFFFFFL;
        }

        private byte[] md5(String value) {
            MessageDigest md5;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            md5.reset();
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            md5.update(bytes);
            return md5.digest();
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Invoker {
        private String address;
    }

}
