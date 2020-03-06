package at.htl.exercise;

import at.htl.exercise.repository.Person;
import at.htl.exercise.repository.PersonRepository;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> emailColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button addButton;

    PersonRepository repository;
    ObservableList<Person> persons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // configure tableView
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // read persons from database
        repository = new PersonRepository();
        persons = FXCollections.observableList(repository.getAllPersons());
        tableView.setItems(persons);

        // set event listeners
        addButton.setOnAction(actionEvent -> addPerson());
//        firstNameField.setOnKeyTyped(event -> validateTextFields());
//        lastNameField.setOnKeyTyped(event -> validateTextFields());
//        emailField.setOnKeyTyped(event -> validateTextFields());

        BooleanBinding disable = firstNameField.textProperty().isEmpty()
                                        .or(lastNameField.textProperty().isEmpty())
                                        .or(emailField.textProperty().isEmpty());
        addButton.disableProperty().bind(disable);

        tableView.setOnKeyReleased(event -> {
            if (tableView.getEditingCell() == null) {
                if (event.getCode() == KeyCode.DELETE) {
                    removePerson();
                }
            }
        });

        // make first name editable
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            String oldValue = event.getOldValue();
            Person person = event.getRowValue();
            person.setFirstName(event.getNewValue());

            if (event.getNewValue().length() == 0) {
                showInvalidInputAlert("Please enter a valid first name.",
                        "First names must not be empty.");
                person.setFirstName(oldValue);
            }
        });

        // make last name editable
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event -> {
            String oldValue = event.getOldValue();
            Person person = event.getRowValue();
            person.setLastName(event.getNewValue());

            if (event.getNewValue().length() == 0) {
                showInvalidInputAlert("Please enter a valid last name.",
                        "Last names must not be empty.");
                person.setLastName(oldValue);
            }
        });

        // make last name editable
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            String oldValue = event.getOldValue();
            Person person = event.getRowValue();
            person.setEmail(event.getNewValue());

            if (!validateEmail(event.getNewValue())) {
                showInvalidInputAlert("Please enter a valid email address.",
                        "Email addresses must match the following pattern:\n<local>@<domain> (e.g. j.smith@example.com).");
                person.setEmail(oldValue);
            }
        });
    }

    private void addPerson() {
        if (validateEmail(emailField.getText())) {

            Person person = new Person(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText()
            );

            persons.add(person);
            repository.addPerson(person);

            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
        } else {
            showInvalidInputAlert("Please enter a valid email address.",
                    "Email addresses must match the following pattern:\n<local>@<domain> (e.g. j.smith@example.com).");
        }
    }

    private void removePerson() {
        Person person = tableView.getSelectionModel().getSelectedItem();
        if (person != null) {
            persons.remove(person);
            repository.removePerson(person);
        }
    }

    private void validateTextFields() {
        boolean disabled = firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty();
        addButton.setDisable(disabled);
    }

    private boolean validateEmail(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        } else {
            return true;
        }
    }

    private void showInvalidInputAlert(String headerText, String contentText) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void savePersons() {
        for (Person p : persons) {
            repository.savePerson(p);
        }
    }
}
