package com.cgi.recruitment.fx.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.models.FxPerson;
import com.cgi.recruitment.util.PersonValidator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

	private Stage dialogStage;
	private FxPerson person;

	private String[] lookingForList = { "Baan", "Stage" };
	private String[] workingLocationList = { "Heerlen", "Groningen", "Rotterdam" };

	@FXML
	private void initialize() {
		lookingForChc.setItems(FXCollections.observableArrayList(Arrays.asList(lookingForList)));
		workingLocationChc.setItems(FXCollections.observableArrayList(Arrays.asList(workingLocationList)));
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	public void addPerson(ActionEvent event) {

		System.err.println("Button Clicked" + event.getSource().toString());
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

	private void validateGeneric(boolean condition, ObservableList<String> style) {
		if (condition) {
			if (style.contains("error"))
				style.removeAll("error");
		} else {
			if (!style.contains("error"))
				style.add("error");
		}
	}

}
