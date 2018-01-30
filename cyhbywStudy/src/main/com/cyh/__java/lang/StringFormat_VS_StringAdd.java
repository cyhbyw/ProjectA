package com.cyh.__java.lang;

/**
 * Created by yanhuche on 4/6/2016.
 */
public class StringFormat_VS_StringAdd {

    int maxRow = 1000;
    int maxColumn = 100;

    public static void main(String[] args) {
        StringFormat_VS_StringAdd cyh = new StringFormat_VS_StringAdd();
        cyh.run();
    }

    private void run() {
        long st = System.nanoTime();
        stringAdd();
        long en = System.nanoTime();
        System.out.println("stringAdd    cost time: " + (en - st));

        st = System.nanoTime();
        stringFormat();
        en = System.nanoTime();
        System.out.println("stringFormat cost time: " + (en - st));
    }

    private void stringAdd() {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                String str = "i==" + i + ", j==" + j;
                // System.out.println(str);
            }
        }
    }

    private void stringFormat() {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                String str = String.format("i==%d, j==%d", i, j);
                // System.out.println(str);
            }
        }
    }


}
