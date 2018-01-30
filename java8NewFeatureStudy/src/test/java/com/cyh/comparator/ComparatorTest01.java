package com.cyh.comparator;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import com.cyh.pojo.Person;

/**
 * Created by yanhuche on 3/31/2017.
 */
public class ComparatorTest01 {



    @Test
    public void test_comparator() {
        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        assertTrue(comparator.compare(p1, p2) > 0);
        assertTrue(comparator.reversed().compare(p1, p2) < 0);
    }



}
