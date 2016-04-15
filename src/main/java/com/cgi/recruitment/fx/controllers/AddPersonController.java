package com.cgi.recruitment.fx.controllers;

import com.cgi.recruitment.fx.models.FxPerson;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private ComboBox<String> lookingForFld;
	@FXML
	private ChoiceBox<String> workingLocationFld;
	@FXML
	private DatePicker availablePerFld;
	@FXML
	private TextArea commentsArea;
	
	private Stage dialogStage;
	private FxPerson person;
	
	
	@FXML
	private void initialize () {
		
	}
	
	public void setDialogStage (Stage dialogStage) {
		this.dialogStage=dialogStage;
	}
	
	public void addPerson () {
		
	}
	
}
