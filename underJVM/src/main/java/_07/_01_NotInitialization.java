package _07;


/**
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 **/
class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}


class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }
}


/**
 * 非主动使用类字段演示
 **/
public class _01_NotInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }

}
