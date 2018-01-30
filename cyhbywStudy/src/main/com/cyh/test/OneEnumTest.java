package com.cyh.test;

/**
 * Created by yanhuche on 4/22/2016.
 */
public enum OneEnumTest {

    minute("min", 1) {
        @Override
        void concat() {

        }
    },
    second("sec", 2) {
        @Override
        void concat() {

        }
    };


    private String des;
    private int xx;

    OneEnumTest(String des, int xx) {
        this.des = des;
        this.xx = xx;
    }

    abstract void concat();
}
