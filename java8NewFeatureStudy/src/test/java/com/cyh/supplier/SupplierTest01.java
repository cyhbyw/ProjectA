package com.cyh.supplier;

import java.util.function.Supplier;

import org.junit.Test;

import com.cyh.pojo.Person;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class SupplierTest01 {



    @Test
    public void test() {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();// new Person
        person.setLastName("a");
        System.out.println(person.getLastName());
    }



}
