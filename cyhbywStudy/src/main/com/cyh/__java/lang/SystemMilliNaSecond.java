package com.cyh.__java.lang;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class SystemMilliNaSecond {

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        System.out.printf("%d\n%d\n%f\n", currentTimeMillis, nanoTime, (0d + nanoTime) / currentTimeMillis);
    }


}
