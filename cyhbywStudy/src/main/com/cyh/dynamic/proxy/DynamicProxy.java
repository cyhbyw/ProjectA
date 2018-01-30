package com.cyh.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yanhuche on 4/10/2017.
 */
public class DynamicProxy implements InvocationHandler {

    private Object obj; //obj为委托类对象；

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }
}
