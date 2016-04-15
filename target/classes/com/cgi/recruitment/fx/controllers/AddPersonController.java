package com.cgi.recruitment.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private ComboBox<String> workingLocationFld;
	@FXML
	private DatePicker availablePerFld;
	@FXML
	private TextArea commentsArea;
	
}
