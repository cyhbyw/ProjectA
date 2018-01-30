package com.cyh.staticFinalInnerClass;

/**
 * Created by yanhuche on 4/28/2016.
 */
public class Main {

    public static void main(String[] args) {
        Main cyh = new Main();
        cyh.run();
    }

    private void run() {
        int maxThreadCount = 20;
        for (int i = 0; i < maxThreadCount; i++) {
            new StaticFinalInnerClassTest().put("key" + i, i);
        }

        for (int i = 0; i < maxThreadCount + maxThreadCount; i++) {
            System.out.println(new StaticFinalInnerClassTest().get("key" + i));
        }

    }

}
