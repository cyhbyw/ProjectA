package _02;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Created by yanhuche on 3/21/2017.
 */
public class Main0209DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }


}
