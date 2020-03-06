package at.htl.exercise.repository;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Person {
    private int id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public Person(String firstName, String lastName, String email) {
        this(0, firstName, lastName, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public StringProperty firstNameProperty() {
        return this.firstName;
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    public StringProperty lastNameProperty() {
        return this.lastName;
    }

    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public StringProperty emailProperty() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) &&
                getEmail().equals(person.getEmail());
    }
}
