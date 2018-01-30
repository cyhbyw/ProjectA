package com.cyh.consumer;

import java.util.function.Consumer;

import org.junit.Test;

import com.cyh.pojo.Person;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class ConsumerTest01 {


    @Test
    public void test() {
        Consumer<Person> greeter = p -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));
    }



}
