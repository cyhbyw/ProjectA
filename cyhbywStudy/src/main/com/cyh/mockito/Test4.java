package com.cyh.mockito;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by yanhuche on 3/10/2016.
 */
public class Test4 {

    @Test
    public void capturing_args() {
        PersonDao personDao = mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        personService.update(1, "jack");
        verify(personDao).update(argument.capture());
        assertEquals(1, argument.getValue().getId());
        assertEquals("jack", argument.getValue().getName());
    }

    class Person {
        private int id;
        private String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface PersonDao {
        void update(Person person);
    }

    class PersonService {
        private PersonDao personDao;

        PersonService(PersonDao personDao) {
            this.personDao = personDao;
        }

        public void update(int id, String name) {
            personDao.update(new Person(id, name));
        }
    }

}
