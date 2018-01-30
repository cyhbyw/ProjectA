package com.cyh.reflect;

/**
 * Created by yanhuche on 4/10/2017.
 */
public class People implements InterfaceForPeople {
    String name;
    int age;

    public double weight;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(getName() + " " + getAge());
    }
}
