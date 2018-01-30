package com.cyh.functional._interface;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class FunctionalInterfaceMain01 {

    public static void main(String[] args) {
        new FunctionalInterfaceMain01().run();
    }

    private void run() {
        wayA();
        wayB();
        wayC();
    }

    private void wayA() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted); // 123
    }

    private void wayB() {
        //通过静态方法引用来表示
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted); // 123
    }

    private void wayC() {
        /*
         * 但是和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确：
         * 不过这里的num必须不可被后面的代码修改（即隐性的具有final的语义）
         * 在lambda表达式中试图修改num同样是不允许的。
         */
        final int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String result = stringConverter.convert(2);// 3
        System.out.println(result);
    }

}


@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
