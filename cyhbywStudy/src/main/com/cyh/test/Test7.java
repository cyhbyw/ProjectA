package com.cyh.test;

import java.math.BigDecimal;

/**
 * Created by yanhuche on 3/22/2016.
 */
public class Test7 {

    public static void main(String[] args) {
        Object[] objects = {"name", 1, 1.32, 1.32d, 1.32f};
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];

            System.out.print(obj + " is: ");
            if (obj instanceof Double) {
                System.out.println("Double");
            } else if (obj instanceof Float) {
                System.out.println("Float");
            } else if (obj instanceof BigDecimal) {
                System.out.println("BigDecimal");
            } else {
                System.out.println("NaN");
            }
        }
    }



    void fun() {}



}
