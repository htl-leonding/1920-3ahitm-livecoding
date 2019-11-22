package at.htl.jdbcprimer.model;

public class Person {

    private String name;
    private String city;
    private String house;

    public Person(String name, String city, String house) {
        this.name = name;
        this.city = city;
        this.house = house;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
