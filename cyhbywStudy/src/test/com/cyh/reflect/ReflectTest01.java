package com.cyh.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * Created by yanhuche on 4/10/2017.
 */
public class ReflectTest01 {


    @Test
    public void test01() {
        Class<People> peopleClass = People.class;
        System.out.println("peopleClass: " + peopleClass);
    }

    @Test
    public void test02() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.cyh.reflect.People");
        System.out.println("aClass: " + aClass);
    }

    @Test
    public void test03() {
        People people = new People("Bill", 18);
        Class<?> peopleClass = people.getClass();
        System.out.println("peopleClass_2: " + peopleClass);
    }

    @Test
    public void test04_Construct() throws Exception {
        Class<People> pClass = People.class;
        System.out.println(pClass);
        Constructor<People> constructor = pClass.getConstructor(String.class, int.class);
        // 设置 constructor 的 Accessible属性为ture以取消Java的访问检查
        constructor.setAccessible(true);
        People people = constructor.newInstance("Bill_CYH", 18);
        people.speak();
    }

    @Test
    public void test05_showDeclaredMethods() {
        Student student = new Student("Bill23854", 18);
        Class<? extends Student> studentClass = student.getClass();
        Method[] methods = studentClass.getDeclaredMethods(); //all methods in Student, public + protected + default + private
        for (Method method : methods) {
            System.out.println("declared method name: " + method.getName());
        }

        try {
            Method learnMethod = studentClass.getDeclaredMethod("learn", String.class);
            Class<?>[] paramClasses = learnMethod.getParameterTypes();
            for (Class<?> klass : paramClasses) {
                System.out.println("learn方法的参数: " + klass.getName());
            }
            System.out
                    .println(learnMethod.getName() + " is private: " + Modifier.isPrivate(learnMethod.getModifiers()));
            learnMethod.invoke(student, "Java Reflection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test06_showMethods() {
        Student student = new Student("CYHq8235", 99);
        Class<? extends Student> studentClass = student.getClass();
        Method[] methods = studentClass.getMethods();// 获取所有public方法(包括Student本身的和从父类继承来的）
        for (Method method : methods) {
            System.out.println("method name: " + method.getName());
        }
        try {
            //注意，通过 getMethod只能获取public方法，若尝试获取private方法则会抛出异常
            Method learnMethod = studentClass.getMethod("learn", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test07_showDeclaredFields() {
        Student student = new Student("Billasg8iasdg", 18, 33);
        Class<? extends Student> studentClass = student.getClass();
        Field[] fields = studentClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("declared field name: " + field.getName());
        }

        try {
            Field gradeField = studentClass.getDeclaredField("grade");
            System.out.println("The grade is: " + gradeField.getInt(student));
            gradeField.set(student, 10);
            System.out.println("The grade is: " + gradeField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test08_showFields() {
        Student student = new Student("Bill", 18);
        Field[] publicFields = student.getClass().getFields();// 获取当前类和父类的所有public属性并显示
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }
    }

    @Test
    public void test09_getSuperClass() {
        Student student = new Student("Bill", 18);
        Class<?> superClass = student.getClass().getSuperclass();
        System.out.println("Student's super class is: " + superClass.getName());
    }

    @Test
    public void test10_showInterfaces() {
        Student student = new Student("Bill", 19);
        Class<?>[] interfaces = student.getClass().getInterfaces();
        for (Class<?> _interface : interfaces) {
            System.out.println("Implements interface: " + _interface.getName());
        }
    }
}
