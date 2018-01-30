package com.cyh.lambda.expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class LambdaStudy01 {

    public static void main(String[] args) {
        new LambdaStudy01().run();
    }

    private void run() {
        Arrays.asList("p", "k", "u", "f", "o", "r", "k").forEach(e -> System.out.println(e));

        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));

        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });

        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
        Arrays.asList("a", "b", "d").forEach(System.out::println);

        func02();

        funcSort();
        funcSort2();
    }

    private void func02() {
        final String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
    }

    private void funcSort() {
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }

    private void funcSort2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }


}
