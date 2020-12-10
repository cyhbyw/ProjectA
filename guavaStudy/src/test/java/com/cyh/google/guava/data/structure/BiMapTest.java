package com.cyh.google.guava.data.structure;

import com.google.common.collect.HashBiMap;
import org.junit.Test;

public class BiMapTest {

    /**
     * 双向 Map，比如用户ID和用户邮箱
     */
    @Test
    public void test() {
        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("1001", "chenyanhua@qq.com");
        biMap.put("1002", "12035@qq.com");
        System.out.println(biMap.get("1001"));
        System.out.println(biMap.inverse().get("12035@qq.com"));
    }

}
