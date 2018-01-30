package _08;

/**
 * 方法静态分派演示
 *
 * Human：静态类型
 * Man：实际类型
 *
 * VM（Javac）在重载时是通过参数的<b>静态类型</b>而不是<b>实际类型</b>作为判定依据的
 * 并且静态类型是编译期可知的
 * 因此，在编译阶段，Javac编译器会根据参数的<b>静态类型</b>决定使用哪个重载版本
 */
public class _06_StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        _06_StaticDispatch sr = new _06_StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
