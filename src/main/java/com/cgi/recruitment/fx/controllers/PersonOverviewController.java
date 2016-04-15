package com.cgi.recruitment.fx.controllers;

import com.cgi.recruitment.BootFxRecApplication;
import com.cgi.recruitment.fx.models.FxPerson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
    @FXML
    private TableView<FxPerson> personTable;
    @FXML
    private TableColumn<FxPerson, String> firstNameColumn;
    @FXML
    private TableColumn<FxPerson, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private BootFxRecApplication fxApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	System.err.println("Initialize of Controller called");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        fillPersonTable();
        showPersonDetails(null);
        
        personTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> showPersonDetails(newValue)
        		);
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(BootFxRecApplication fxApp) {
        this.fxApp = fxApp;


    }
    
    private void fillPersonTable () {
        ObservableList<FxPerson> personData = FXCollections.observableArrayList();
        
        personData.add(new FxPerson("Hans", "Muster"));
        personData.add(new FxPerson("Ruth", "Mueller"));
        personData.add(new FxPerson("Heinz", "Kurz"));
        personData.add(new FxPerson("Cornelia", "Meier"));
        personData.add(new FxPerson("Werner", "Meyer"));
        personData.add(new FxPerson("Lydia", "Kunz"));
        personData.add(new FxPerson("Anna", "Best"));
        personData.add(new FxPerson("Stefan", "Meier"));
        personData.add(new FxPerson("Martin", "Mueller"));
        // Add observable list data to the table
        personTable.setItems(personData);
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(FxPerson person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
}
