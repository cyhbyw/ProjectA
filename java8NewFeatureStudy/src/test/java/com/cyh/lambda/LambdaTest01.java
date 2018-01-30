package com.cyh.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class LambdaTest01 {


    @Test
    public void test01() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    @Test
    public void test02() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    @Test
    public void test03() {
        List<String> stringList = Arrays.asList("abc", "d", "efg", "h");
        System.out.println(stringList);
        List<String> collect = stringList.stream().filter((x) -> x.length() > 2).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test04() {
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }



}
