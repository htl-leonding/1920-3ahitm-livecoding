package at.htl.exercise.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

     public void createTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "CREATE TABLE PERSON (" +
                    "ID INT GENERATED ALWAYS AS IDENTITY " +
                    "CONSTRAINT PK_PERSON PRIMARY KEY, " +
                    "FIRST_NAME VARCHAR(100)," +
                    "LAST_NAME VARCHAR(100)," +
                    "EMAIL VARCHAR(100)" +
                    ")";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Created table PERSON.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "DROP TABLE PERSON";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Dropped table PERSON.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'PERSON'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPerson(Person person) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, EMAIL) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getEmail());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Insert into PERSON failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    person.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Insert into PERSON failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Read all persons from database

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void removePerson(Person person) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Remove person in database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
