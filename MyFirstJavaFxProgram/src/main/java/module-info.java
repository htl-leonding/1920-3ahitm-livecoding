module at.htl {
    requires javafx.controls;
    requires javafx.fxml;

    opens at.htl to javafx.fxml;
    exports at.htl;
}