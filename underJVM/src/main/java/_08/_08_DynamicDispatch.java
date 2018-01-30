package _08;

/**
 * 方法动态分派演示
 *
 * invokevirtual的多态查找
 * invokevirtual指令执行的第一步就是在运行期确定接收者的<b>实际类型</b>
 * 所以两次调用中的invokevirtual指令把常量池中的类方法符号引用解析到了不同的直接引用上
 *
 * 这就是Java语言中重写的本质。
 */
public class _08_DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
