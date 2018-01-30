package com.cyh.test;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        new Test2().run();
    }

    private void run() {
        List<Person> personList = new ArrayList<>();

        Person person = new Person("a");
        personList.add(person);

        person = new Person("a");
        person.setName("b");
        personList.add(person);

        System.out.println(personList);

    }


}
