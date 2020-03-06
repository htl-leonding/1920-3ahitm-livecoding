package at.htl.exercise.repository;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
class PersonRepositoryTest {

    PersonRepository repository;

    @BeforeEach
    void beforeEach(){
        repository = new PersonRepository();

        if (repository.tableExists()) {
            repository.dropTable();
        }

        repository.createTable();

        Person[] persons = new Person[] {
                new Person("John", "Smith", "j.smith@gmail.com"),
                new Person("Jane", "Doe", "jane.doe@outlook.com"),
                new Person("Richard", "Roe", "richi90@hotmail.com")
        };

        for (Person p : persons) {
            repository.addPerson(p);
        }
    }

    @Test
    @Order(10)
    void getAllPersons() {
        Person[] expectedPersons = new Person[] {
                new Person(1, "John", "Smith", "j.smith@gmail.com"),
                new Person(2, "Jane", "Doe", "jane.doe@outlook.com"),
                new Person(3, "Richard", "Roe", "richi90@hotmail.com")
        };
        List<Person> persons = repository.getAllPersons();

        assertThat(persons).contains(expectedPersons);
    }

    @Test
    @Order(20)
    void removePerson() {
        Person[] expectedPersons = new Person[] {
                new Person(1, "John", "Smith", "j.smith@gmail.com"),
                new Person(3, "Richard", "Roe", "richi90@hotmail.com")
        };

        Person p = new Person(2, "Jane", "Doe", "jane.doe@outlook.com");
        repository.removePerson(p);

        List<Person> persons = repository.getAllPersons();
        assertThat(persons).contains(expectedPersons);
    }
}