package _07;

/**
 * Created by yanhuche on 3/23/2017.
 */

/**
 * 类加载---->类初始化
 *
 * 类初始化是执行<clinit>方法的过程。
 *
 * <clinit>方法是邮编译器自动收集类中的所有类变量的赋值动作和静态语句块static{}中的语句合并产生的，由源文件中定义的顺序决定，静态语句块只能访问到在其之前的变量；
 * <clinit>方法与实构造器<init>方法不同，它不需要显示地调用父类构造器，VM会保证在子类的<clinit>方法执行之前，父类的<clinit>方法已经执行完毕，故第一个被执行<clinit>方法的是java.lang.Object；
 * 由于父类的<clinit>方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作，如此例所示。
 */

class Parent {
    public static int A = 1;
    static {
        A = 2;
    }
}


class Sub extends Parent {
    public static int B = A;
}


public class _06_ClassLoader_ClassInitiatation {

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }

}
