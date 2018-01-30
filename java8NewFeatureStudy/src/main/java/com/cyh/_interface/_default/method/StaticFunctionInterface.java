package com.cyh._interface._default.method;

/**
 * Created by yanhuche on 3/29/2017.
 */

@FunctionalInterface
public interface StaticFunctionInterface {

    void method();

    static String staticFunction() {
        return "java 8's static function";
    }


}
