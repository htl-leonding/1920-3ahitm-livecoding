package at.htl.exercise;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ContactsController controller;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("contacts"));
        stage.setScene(scene);
        stage.setTitle("Address Book");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        return parent;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        controller.savePersons();
        super.stop();
    }
}