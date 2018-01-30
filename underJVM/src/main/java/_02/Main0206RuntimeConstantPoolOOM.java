package _02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanhuche on 3/21/2017.
 */
public class Main0206RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }


}
