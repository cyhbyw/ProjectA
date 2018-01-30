package com.cyh.repeatable.annotation;

import java.lang.annotation.Repeatable;

/**
 * Created by yanhuche on 4/1/2017.
 */
public class RepeatingAnnotations2 {

    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint); // null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println("hints1 == null: " + (hints1 == null));
        if (hints1 != null) {
            System.out.println(hints1.value().length); // 2
        }

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length); // 2
    }


    @interface Hints {
        Hint[] value();
    }

    @Repeatable(Hints.class)
    @interface Hint {
        String value();
    }

    @Hint("hint1")
    @Hint("hint2")
    class Person {
    }


}
