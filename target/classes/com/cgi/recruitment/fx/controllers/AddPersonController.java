package com.cgi.recruitment.fx.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.models.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.util.PersonValidator;
import com.cgi.recruitment.util.PhoneNumberConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

@Component
public class AddPersonController {

	@FXML
	private TextField firstNameFld;
	@FXML
	private TextField lastNameFld;
	@FXML
	private TextField emailAddressFld;
	@FXML
	private TextField phoneNumberFld;
	@FXML
	private TextField studyFld;
	@FXML
	private DatePicker graduationDateFld;
	@FXML
	private ChoiceBox<String> lookingForChc;
	@FXML
	private ChoiceBox<String> workingLocationChc;
	@FXML
	private DatePicker availablePerFld;
	@FXML
	private TextArea commentsArea;
	@FXML
	private Label validatorLbl;

	//private Stage dialogStage;
		
	@Autowired
	private PersonOverviewModel personOverviewModel;

	private final String[] lookingForList = { "Baan", "Stage" };
	private final String[] workingLocationList = { "Arnhem", "Eindhoven", "Groningen", "Heerlen", "Hoofddrop",
			"Rotterdam" };

	@FXML
	private void initialize() {
		lookingForChc.setItems(FXCollections.observableArrayList(Arrays.asList(lookingForList)));
		workingLocationChc.setItems(FXCollections.observableArrayList(Arrays.asList(workingLocationList)));
		lookingForChc.setValue("Baan");
		workingLocationChc.setValue("Arnhem");
	}

//	public void setDialogStage(Stage dialogStage) {
//		//this.dialogStage = dialogStage;
//	}

	@FXML
	public void addPerson(ActionEvent event) {
		if (validateAll ()) {
			addPersonToModel ();
			validatorLbl.setText("");
		} else {
			validatorLbl.setText("Vul alle velden in!");
		}
	}
	
	private void addPersonToModel () {
		FxPerson person = new FxPerson ();
		
		person.setFirstName(firstNameFld.getText());
		firstNameFld.setText("");
		person.setLastName(lastNameFld.getText());
		lastNameFld.setText("");
		person.setEmailAddress(emailAddressFld.getText());
		emailAddressFld.setText("");
		person.setPhoneNumber(PhoneNumberConverter.formatPhoneNumber(phoneNumberFld.getText()));
		phoneNumberFld.setText("");
		person.setStudy(studyFld.getText());
		studyFld.setText("");
		person.setGraduationDate(graduationDateFld.getValue());
		graduationDateFld.setValue(null);
		person.setLookingFor(lookingForChc.getValue());
		lookingForChc.setValue("Baan");
		person.setWorkLocation(workingLocationChc.getValue());
		workingLocationChc.setValue("Arnhem");
		person.setWorkStartDate(availablePerFld.getValue());
		availablePerFld.setValue(null);
		person.setComments(commentsArea.getText());	
		commentsArea.setText("");
		
		personOverviewModel.getPersonData().add(person);
	}
	
	private boolean validateAll () {
		boolean allFieldsCorrect = true;
		
		allFieldsCorrect &= validateGeneric(PersonValidator.validateNotEmpty(firstNameFld.getText()), firstNameFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validateNotEmpty(lastNameFld.getText()), lastNameFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validateEmailAddress(emailAddressFld.getText()), emailAddressFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validatePhoneNumber(phoneNumberFld.getText()), phoneNumberFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validateNotEmpty(studyFld.getText()), studyFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validateLocalDate(graduationDateFld.getValue()), graduationDateFld.getStyleClass());
		allFieldsCorrect &= validateGeneric(PersonValidator.validateLocalDate(availablePerFld.getValue()), availablePerFld.getStyleClass());
		
		return allFieldsCorrect;
	}
	
	@FXML
	public void validatePhoneNumber (KeyEvent event) {
		TextField source = (TextField) event.getSource();
		
		validateGeneric(PersonValidator.validatePhoneNumber(source.getText()),source.getStyleClass());
	}
	
	@FXML 
	public void validateDate (Event event) {
				
		DatePicker source = (DatePicker) event.getSource();
		
		validateGeneric(PersonValidator.validateLocalDate(source.getValue()), source.getStyleClass());
	} 

	@FXML
	public void validateEmailAddress(KeyEvent event) {
		TextField source = (TextField) event.getSource();

		validateGeneric(PersonValidator.validateEmailAddress(source.getText()), source.getStyleClass());
	}

	@FXML
	public void validateNotEmpty(KeyEvent event) {
		TextField source = (TextField) event.getSource();

		validateGeneric(PersonValidator.validateNotEmpty(source.getText()), source.getStyleClass());
	}

	private boolean validateGeneric(boolean condition, ObservableList<String> style) {
		if (condition) {
			if (style.contains("error"))
				style.removeAll("error");
		} else {
			if (!style.contains("error"))
				style.add("error");
		}
		return condition;
	}
}
