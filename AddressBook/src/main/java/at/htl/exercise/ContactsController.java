package at.htl.exercise;

import at.htl.exercise.repository.Person;
import at.htl.exercise.repository.PersonRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {

    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, String> emailColumn;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private Button addButton;

    PersonRepository repository;
    ObservableList<Person> persons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // configure tableView
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // TODO: read persons from database instead of using a hardcoded list
        persons = FXCollections.observableList(new ArrayList<Person>());
        persons.add(new Person(1, "John", "Smith", "j.smith@gmail.com"));
        persons.add(new Person(2, "Jane", "Doe", "j.doe@gmail.com"));
        persons.add(new Person(3, "Richie", "Rich", "richi90@gmail.com"));
        tableView.setItems(persons);

        addButton.setOnAction(actionEvent -> addPerson());
        tableView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removePerson();
            }
        });
        firstNameField.setOnKeyTyped(event -> validateTextFields());
        lastNameField.setOnKeyTyped(event -> validateTextFields());
        emailField.setOnKeyTyped(event -> validateTextFields());
    }

    private void addPerson() {
        Person person = new Person(firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText());
        persons.add(person);

        // TODO: Add person to database
    }

    private void removePerson() {
        Person p = tableView.getSelectionModel().getSelectedItem();

        if (p != null) {
            persons.remove(p);
        }

        // TODO: Remove person from database
    }

    private void validateTextFields(){
        boolean disabled = firstNameField.getText().isEmpty()
                || lastNameField.getText().isBlank()
                || emailField.getText().isEmpty();
        addButton.setDisable(disabled);
    }
}
