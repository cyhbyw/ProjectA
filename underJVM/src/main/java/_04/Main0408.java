package _04;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanhuche on 10/12/2016.
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class Main0408 {

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
            System.out.println(i);
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }


    /**
     * 内存占位符对象，一个OOMObject大约占64K
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }


}
