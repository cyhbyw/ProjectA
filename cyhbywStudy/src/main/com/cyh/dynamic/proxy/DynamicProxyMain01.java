package com.cyh.dynamic.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by yanhuche on 4/10/2017.
 */

/**
 * http://www.cnblogs.com/absfree/p/5392639.html
 */
public class DynamicProxyMain01 {

    public static void main(String[] args) {
        //创建中介类实例
        DynamicProxy proxy = new DynamicProxy(new Vendor());
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取代理类实例sell
        Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[] {Sell.class}, proxy);
        //通过代理类对象调用代理类方法，实际上会转到invoke方法调用
        sell.sell();
        sell.ad();
    }


}


/**
 * 但是，JDK的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理了。
 */
