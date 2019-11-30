package at.htl.jdbcprimer.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.Alphanumeric.class)
class JdbcTest {

    private static final String url = "jdbc:derby://localhost:1527/db;create=true";
    private static final String username = "app";
    private static final String password = "app";

    @Test
    void test001_createConnection() {
        /**
         * 1. Schritt: Erstellen einer Connection
         * Deese Connection wird in einer try-with-Resource geöffnet.
         * Damit muss man sich nicht darum kümmern, dass die Connection
         * wieder geschlossen wird
         *
         * try (RESSOURCE) {
         *   ...
         * }
         * RESSOURCE wird automatisch geschlossen
         */
        // tag::create-connection[]
        try (Connection conn = DriverManager.getConnection( // <1>
                url,
                username,
                password)) {
            try (Statement stmt = conn.createStatement()) {  // <1>
                String sql = "CREATE TABLE person (" +
                        "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "city VARCHAR(255)," +
                        "house VARCHAR(255)" +
                        ")";
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // end::create-connection[]
        fail("not yet implemented");
    }

    @Test
    void test002_insertRecord() {
        // tag::insert[]
        try (Connection conn = DriverManager.getConnection(
                url,
                username,
                password)) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "INSERT INTO person (NAME, CITY, HOUSE) " +
                        "VALUES ('Tyrion','Kingslanding','Lannister')";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // end::insert[]
        fail("not yet implemented");
    }

    @Test
    void test003_queryDatabase() {
        try (Connection conn = DriverManager.getConnection(
                url,
                username,
                password)) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT id, name, city, house " +
                        "FROM PERSON";
                final ResultSet rs = stmt.executeQuery(sql);

                rs.next();
                assertThat(rs.getString("NAME")).isEqualTo("Tyrion");
                assertThat(rs.getString("HOUSE")).isEqualTo("Lannister");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    void test004_queryDatabaseWithLoop() {
        // tag::query[]
        try (Connection conn = DriverManager.getConnection(
                url,
                username,
                password)) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT id, name, city, house " +
                        "FROM PERSON";
                final ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("ID") + " - " +
                            rs.getString("NAME") + " - " +
                            rs.getString("HOUSE")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // end::query[]

    }

}