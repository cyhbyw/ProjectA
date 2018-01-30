package com.cyh.functional._interface;

import com.cyh.pojo.Person;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class PersonMain01 {

    public static void main(String[] args) {
        //我们只需要使用 Person::new 来获取Person类构造函数的引用，
        //Java编译器会自动根据PersonFactory.create方法的签名来选择合适的构造函数。
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.getFirstName());
    }

}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
