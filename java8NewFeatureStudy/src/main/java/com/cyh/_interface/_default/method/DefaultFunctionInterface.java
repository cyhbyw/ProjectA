package com.cyh._interface._default.method;

/**
 * Created by yanhuche on 3/29/2017.
 */

@FunctionalInterface
public interface DefaultFunctionInterface {

    void method();

    default String defaultFunction() {
        return "java 8's default function";
    }


}
