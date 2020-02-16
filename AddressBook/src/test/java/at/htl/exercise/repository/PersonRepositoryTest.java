package at.htl.exercise.repository;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
                new Person(0, "John", "Smith", "j.smith@gmail.com"),
                new Person(0, "Jane", "Doe", "jane.doe@outlook.com"),
                new Person(0, "Richard", "Roe", "richi90@hotmail.com")
        };

        for (Person p : persons) {
            repository.addPerson(p);
        }
    }

    @Test
    @Order(10)
    void getAllPersons() {
        // TODO: Implemement test
        Fail.fail("Not implemented yet.");
    }

    @Test
    @Order(20)
    void removePerson() {
        // TODO: Implemement test
        Fail.fail("Not implemented yet.");
    }
}