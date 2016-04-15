package com.cgi.recruitment.fx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.BootFxRecApplication;
import com.cgi.recruitment.fx.models.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.shared.DateConverter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
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
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label studyLabel;
    @FXML
    private Label graduationLabel;
    @FXML
    private Label lookingForLabel;
    @FXML
    private Label workLocationLabel;
    @FXML
    private Label prefStartDateLabel;
    @FXML
    private Label commentsLabel;
    
    @Autowired
    private PersonOverviewModel personModel;
    

    // Reference to the main application.
    private BootFxRecApplication fxApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    	System.out.println("PersonOverviewController created");
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
        
        showPersonDetails(null);
        
        personModel.fillListWithTestData();
        
        personTable.setItems(personModel.getPersonData());
        
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
            emailLabel.setText(person.getEmailAddress());
            phoneLabel.setText(person.getPhoneNumber());
            studyLabel.setText(person.getStudy());
            graduationLabel.setText(DateConverter.format(person.getGraduationDate()));
            lookingForLabel.setText(person.getLookingFor());
            workLocationLabel.setText(person.getWorkLocation());
            prefStartDateLabel.setText(DateConverter.format(person.getWorkStartDate()));
            commentsLabel.setText(person.getComments());

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailLabel.setText("");
            phoneLabel.setText("");
            studyLabel.setText("");
            graduationLabel.setText("");
            lookingForLabel.setText("");
            workLocationLabel.setText("");
            prefStartDateLabel.setText("");
            commentsLabel.setText("");
        }
    }
    /**
     * If Button new is clicked.
     */
    @FXML
    private void handleNewPerson () {
    	System.out.println("Button New.. Clicked");
    	if (fxApp == null) {
    		System.err.println("FxApp is not set!");
    	}
    	fxApp.showAddPersonDialog();
    }
}
