package com.cyh.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yanhuche on 4/10/2017.
 */

/**
 * http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
 */
public class DynamicProxyMain02 {

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
    }

}


interface BookFacade {
    void addBook();
}


class BookFacadeImpl implements BookFacade {

    @Override
    public void addBook() {
        System.out.println("增加图书方法。。。");
    }

}


class BookFacadeProxy implements InvocationHandler {
    private Object target;


    public Object bind(Object target) {
        this.target = target;
        /*        
         * 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("事物开始");
        Object result = method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }

}
