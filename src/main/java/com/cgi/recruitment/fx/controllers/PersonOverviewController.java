package com.cgi.recruitment.fx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.services.EventPersistService;
import com.cgi.recruitment.util.converters.DateConverter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    
    private PersonOverviewModel personModel;
    
   
    @Autowired
    private EventPersistService service;
    

    // Reference to the main application.
    private FXApp fxApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    	log.info("PersonOverviewController created");
    }
    
    public void setPersonOverviewModel (PersonOverviewModel model) {
    	this.personModel = model;
    	initAfterModel();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	log.info("Initialize of Controller called");

        
    }
    
    private void initAfterModel () {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        showPersonDetails(null);
        
        //personModel.fillListWithTestData();
        
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
    public void setMainApp(FXApp fxApp) {
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
    	fxApp.showAddPersonDialog(personModel);
    }
    
    @FXML
    private void editPerson () {
//    	recruitmentEvent.setEventName("Testdagen");
//    	recruitmentEvent.setEventLocation("Groningen");
//    	recruitmentEvent.setEventDate(LocalDate.now());
//    	recruitmentEvent.setPersonList(personModel.getPersonData());
//
//    	log.info("Persisting Model");
//    	service.persistEvent(recruitmentEvent);
    }
    
    @FXML
    private void deletePerson () {
    	log.info(personTable.getSelectionModel().getSelectedItem().getFirstName());
    	if (personModel.getPersonData().remove(personTable.getSelectionModel().getSelectedItem())) {
    		log.info("Selected Person deleted from model");
    		service.persistEvent(personModel.getFxEvent());
    	}
    }
    
    @FXML
    private void backToEvents () {
    	service.persistEvent(personModel.getFxEvent());
    	this.fxApp.showEventOverview();
    }
    
    
    
    
}
