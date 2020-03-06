module at.htl.exercise {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;

    opens at.htl.exercise to javafx.fxml;
    exports at.htl.exercise;
    exports at.htl.exercise.repository;
}