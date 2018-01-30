package com.cyh.__java.lang;

/**
 * Created by yanhuche on 6/6/2016.
 */
public class StringSplitTest {

    public static void main(String[] args) {
        new StringSplitTest().run();
    }

    private void run() {
        String strA = "MessageId.MSG0554, destination.getAbsolutePath()";
        for (String str : strA.split(",")) {
            System.out.println(str.trim());
        }
    }


}
