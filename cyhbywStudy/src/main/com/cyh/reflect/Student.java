package com.cyh.reflect;

/**
 * Created by yanhuche on 4/10/2017.
 */
public class Student extends People implements InterfaceForStudent {
    /*private*/int grade;
    public String hobby;

    public Student(String name, int age) {
        super(name, age);
    }

    public Student(String name, int age, int grade) {
        super(name, age);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    /*private*/void learn(String course) {
        System.out.println(name + " learn " + course);
    }
}
