package com.cgi.recruitment.fx.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.services.EventPersistService;
import com.cgi.recruitment.util.converters.CheckListConverter;
import com.cgi.recruitment.util.converters.LastNameConverter;
import com.cgi.recruitment.util.converters.PhoneNumberConverter;
import com.cgi.recruitment.util.vallidators.PersonValidator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Lazy
public class AddPersonController {

	@FXML
	private TextField firstNameFld; // Required
	@FXML
	private TextField lastNameFld; // Required
	@FXML
	private TextField emailAddressFld; // Required
	@FXML
	private TextField phoneNumberFld;
	@FXML
	private TextField studyFld; // Required
	@FXML
	private DatePicker graduationDateFld;
	@FXML
	private ChoiceBox<String> educationLevelChc; // Required
	@FXML
	private CheckComboBox<String> interestedInChc;
	@FXML
	private CheckComboBox<String> regionChc;
	@FXML
	private DatePicker prefStartDateDap;
	@FXML
	private ChoiceBox<String> carreerLevelChc;
	@FXML
	private CheckComboBox<String> comboSkill;
	@FXML
	private CheckComboBox<String> comboBranch;
	@FXML
	private CheckComboBox<String> comboRole;
	@FXML
	private TextArea commentsArea;
	@FXML
	private CheckBox akkoordCheckBox;

	@FXML
	private Label validatorLbl;
	@FXML
	private GridPane gridPane;

	@Autowired
	private EventPersistService persistService;

	// Values are in the application.properties file (under resources)
	@Value("${recruitment.values.interesse}")
	private String[] lookingForList;

	@Value("${recruitment.values.regio}")
	private String[] workingLocationList;

	@Value("${recruitment.values.opleidingsniveau}")
	private String[] educationLevelList;

	@Value("${recruitment.values.werkveld}")
	private String[] brancheList;

	@Value("${recruitment.values.vaardigheden}")
	private String[] skillsList;

	@Value("${recruitment.values.rol}")
	private String[] rolesList;

	@Value("${recruitment.values.ervaring}")
	private String[] experienceList;

	private PersonOverviewModel personOverviewModel;

	private FXApp fxApp;

	@FXML
	private void initialize() {
		interestedInChc.getItems().addAll(Arrays.asList(lookingForList));
		regionChc.getItems().addAll(Arrays.asList(workingLocationList));
		educationLevelChc.setItems(FXCollections.observableArrayList(Arrays.asList(educationLevelList)));
		carreerLevelChc.setItems(FXCollections.observableArrayList(Arrays.asList(experienceList)));
		comboSkill.getItems().addAll(Arrays.asList(skillsList));
		comboRole.getItems().addAll(Arrays.asList(rolesList));
		comboBranch.getItems().addAll(Arrays.asList(brancheList));
		addDateCellFactory();
		addListeners();
	}

	public void setMainApp(FXApp fxApp) {
		this.fxApp = fxApp;
	}

	@FXML
	public void addPerson(ActionEvent event) {
		if (validateAll()) {

			fxApp.showAddPersonCGIDialog(addPersonToModel());

			persistService.persistEvent(personOverviewModel.getFxEvent());
			
			emptyAllFields();
			
			log.info("Person added to model and saved to file");
		} else {
			validatorLbl.setText("Vul alle velden (correct) in!");
		}
	}

	public void setPersonOverviewModel(PersonOverviewModel model) {
		this.personOverviewModel = model;
	}

	private void emptyAllFields() {
		firstNameFld.setText("");
		lastNameFld.setText("");
		emailAddressFld.setText("");
		phoneNumberFld.setText("");
		studyFld.setText("");
		graduationDateFld.setValue(null);
		educationLevelChc.setValue(null);
		interestedInChc.getCheckModel().clearChecks();
		regionChc.getCheckModel().clearChecks();
		prefStartDateDap.setValue(null);
		carreerLevelChc.setValue(null);
		comboSkill.getCheckModel().clearChecks();
		comboBranch.getCheckModel().clearChecks();
		comboRole.getCheckModel().clearChecks();
		commentsArea.setText("");
		akkoordCheckBox.setSelected(false);
		validatorLbl.setText("");
	}

	private FxPerson addPersonToModel() {
		FxPerson person = new FxPerson();

		person.setFirstName(firstNameFld.getText());
		person.setLastName(LastNameConverter.convertLastName(lastNameFld.getText()));
		person.setEmailAddress(emailAddressFld.getText());
		person.setPhoneNumber(PhoneNumberConverter.formatPhoneNumber(phoneNumberFld.getText()));
		person.setStudy(studyFld.getText());
		person.setGraduationDate(graduationDateFld.getValue());
		person.setEductionLevel(educationLevelChc.getValue());
		person.setInterestedIn(
				CheckListConverter.normalizeArray(interestedInChc.getCheckModel().getCheckedItems().toString()));
		person.setRegion(CheckListConverter.normalizeArray(regionChc.getCheckModel().getCheckedItems().toString()));
		person.setPrefStartDate(prefStartDateDap.getValue());
		person.setCareerLevel(carreerLevelChc.getValue());
		person.setSpecialism(
				CheckListConverter.normalizeArray(comboSkill.getCheckModel().getCheckedItems().toString()));
		person.setBranch(CheckListConverter.normalizeArray(comboBranch.getCheckModel().getCheckedItems().toString()));
		person.setRole(CheckListConverter.normalizeArray(comboRole.getCheckModel().getCheckedItems().toString()));
		person.setComments(commentsArea.getText());
		person.setNewsLetter(getCheckBoxValue());
		person.setApplyDate(LocalDateTime.now());
		
		if (personOverviewModel != null) {
			person.setEventName(personOverviewModel.getFxEvent().getEventName());
			person.setEventLocation(personOverviewModel.getFxEvent().getEventLocation());
			person.setEventDate(personOverviewModel.getFxEvent().getEventDate());

			personOverviewModel.getPersonData().add(person);
		} else {
			log.error("Overview model is null!!");
		}
		return person;
	}

	private String getCheckBoxValue() {
		if (akkoordCheckBox.isSelected())
			return "Akkoord";
		else
			return "Niet Akkoord";
	}

	private boolean validateAll() {
		boolean allFieldsCorrect = true;

		allFieldsCorrect &= validateRequired(PersonValidator.validateNotEmpty(firstNameFld.getText()),
				firstNameFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateNotEmpty(lastNameFld.getText()),
				lastNameFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateEmailAddress(emailAddressFld.getText()),
				emailAddressFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validatePhoneNumber(phoneNumberFld.getText()),
				phoneNumberFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateNotEmpty(studyFld.getText()),
				studyFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateLocalDate(graduationDateFld.getValue()),
				graduationDateFld.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateLocalDate(prefStartDateDap.getValue()),
				prefStartDateDap.getStyleClass());
		allFieldsCorrect &= validateRequired(PersonValidator.validateNotEmpty(educationLevelChc.getValue()),
				educationLevelChc.getStyleClass());
		allFieldsCorrect &= validateNotRequired(
				PersonValidator.validateNotRequired(interestedInChc.getCheckModel().getCheckedItems().toString()));
		allFieldsCorrect &= validateRequired(PersonValidator.validateNotEmpty(carreerLevelChc.getValue()),
				carreerLevelChc.getStyleClass());
		allFieldsCorrect &= validateNotRequired(
				PersonValidator.validateNotRequired(comboSkill.getCheckModel().getCheckedItems().toString()));
		allFieldsCorrect &= validateNotRequired(
				PersonValidator.validateNotRequired(comboBranch.getCheckModel().getCheckedItems().toString()));
		allFieldsCorrect &= validateNotRequired(
				PersonValidator.validateNotRequired(comboRole.getCheckModel().getCheckedItems().toString()));
		return allFieldsCorrect;
	}

	@FXML
	public void validatePhoneNumber(KeyEvent event) {
		TextField source = (TextField) event.getSource();

		validateRequired(PersonValidator.validatePhoneNumber(source.getText()), source.getStyleClass());
	}

	@FXML
	public void validateDate(Event event) {
		DatePicker source = (DatePicker) event.getSource();

		validateRequired(PersonValidator.validateLocalDate(source.getValue()), source.getStyleClass());
	}

	@FXML
	public void validateEmailAddress(KeyEvent event) {
		TextField source = (TextField) event.getSource();

		validateRequired(PersonValidator.validateEmailAddress(source.getText()), source.getStyleClass());
	}

	@FXML
	public void validateNotEmpty(KeyEvent event) {
		TextField source = (TextField) event.getSource();

		validateRequired(PersonValidator.validateNotEmpty(source.getText()), source.getStyleClass());
	}

	private void addListeners() {
		interestedInChc.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> change) {
				if (change.next()) {
					for (String str : change.getList()) {
						if (str.equals("Nvt")) {
							prefStartDateDap.setDisable(true);
						} else {
							prefStartDateDap.setDisable(false);
						}
					}
				}
			}
		});
	}

	private void addDateCellFactory() {
		prefStartDateDap.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
						}
					}
				};
			}
		});
	}

	private boolean validateRequired(boolean condition, ObservableList<String> style) {
		if (condition) {
			if (style.contains("error"))
				style.removeAll("error");
		} else {
			if (!style.contains("error"))
				style.add("error");
		}
		return condition;
	}

	private boolean validateNotRequired(boolean condition) {
		return condition;
	}
}
