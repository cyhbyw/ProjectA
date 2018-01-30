package com.cyh.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        new Test1().run();
    }

    private void run() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("test"));
        System.out.println("personList " + personList);

        constructorCopy(personList);
        System.out.println("personList " + personList);

        collectionCopy(personList);
        System.out.println("personList " + personList);

        constructorCopy2(personList);
        System.out.println("personList " + personList);

        collectionCopy2(personList);
        System.out.println("personList " + personList);
    }

    private void constructorCopy(List<Person> personList) {
        List<Person> cloneList = new ArrayList<>(personList);
        for (Person person : cloneList) {
            person.setName(person.getName() + "A");
        }
        System.out.println("cloneList A " + cloneList);
    }

    private void collectionCopy(List<Person> personList) {
        List<Person> cloneList = new ArrayList<>(Arrays.asList(new Person[personList.size()]));
        Collections.copy(cloneList, personList);
        for (Person person : cloneList) {
            person.setName(person.getName() + "B");
        }
        System.out.println("cloneList B " + cloneList);
    }

    private void constructorCopy2(List<Person> personList) {
        List<Person> cloneList = new ArrayList<>();
        for (Person person : personList) {
        }
    }

    private void collectionCopy2(List<Person> personList) {

    }


}
