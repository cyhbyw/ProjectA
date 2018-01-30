package com.cyh.__java.lang;

/**
 * Created by yanhuche on 6/7/2016.
 */
public class StringConstructorTest {



    public static void main(String[] args) {
        new StringConstructorTest().run();

    }

    private void run() {
        String originalString = "test";
        System.out.println(originalString);

        String newString = new String(originalString);
        newString += "xx";
        System.out.println(newString);


        System.out.println(originalString);
    }


}
