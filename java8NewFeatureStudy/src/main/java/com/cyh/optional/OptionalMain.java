package com.cyh.optional;

import java.util.Optional;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class OptionalMain {


    public static void main(String[] args) {
        new OptionalMain().run();
    }

    private void run() {
        fun01();
        fun02();
    }

    private void fun01() {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    private void fun02() {
        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }


}
