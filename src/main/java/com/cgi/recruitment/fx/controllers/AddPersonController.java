package com.cgi.recruitment.fx.controllers;

import java.time.LocalDate;
import java.util.Arrays;

import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.services.EventPersistService;
import com.cgi.recruitment.util.converters.LastNameConverter;
import com.cgi.recruitment.util.converters.PhoneNumberConverter;
import com.cgi.recruitment.util.vallidators.PersonValidator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
@PropertySources({
	@PropertySource(value = "classpath:recruitment.properties"),
	@PropertySource(value = "${recruitment.config}",ignoreResourceNotFound=true)
})
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
	private Label validatorLbl;
	@FXML
	private GridPane gridPane;

	@FXML
	private ComboBox<String> testBox;

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

	@FXML
	public void addPerson(ActionEvent event) {
		if (validateAll()) {
			addPersonToModel();
			persistService.persistEvent(personOverviewModel.getFxEvent());
			validatorLbl.setText("");
			log.info("Person added to model and saved to file");
		} else {
			validatorLbl.setText("Vul alle velden in!");
		}
	}

	public void setPersonOverviewModel(PersonOverviewModel model) {
		this.personOverviewModel = model;
	}

	private void addPersonToModel() {
		FxPerson person = new FxPerson();

		person.setFirstName(firstNameFld.getText());
		firstNameFld.setText("");

		person.setLastName(LastNameConverter.convertLastName(lastNameFld.getText()));
		lastNameFld.setText("");

		person.setEmailAddress(emailAddressFld.getText());
		emailAddressFld.setText("");

		person.setPhoneNumber(PhoneNumberConverter.formatPhoneNumber(phoneNumberFld.getText()));
		phoneNumberFld.setText("");

		person.setStudy(studyFld.getText());
		studyFld.setText("");

		person.setGraduationDate(graduationDateFld.getValue());
		graduationDateFld.setValue(null);

		person.setInterestedIn(interestedInChc.getCheckModel().getCheckedItems().toString());
		interestedInChc.getCheckModel().clearChecks();

		person.setRegion(regionChc.getCheckModel().getCheckedItems().toString());
		interestedInChc.getCheckModel().clearChecks();

		person.setPrefStartDate(prefStartDateDap.getValue());
		prefStartDateDap.setValue(null);

		person.setCareerLevel(carreerLevelChc.getValue());
		carreerLevelChc.setValue(null);

		person.setSpecialism(comboSkill.getCheckModel().getCheckedItems().toString());
		comboSkill.getCheckModel().clearChecks();

		person.setBranch(comboBranch.getCheckModel().getCheckedItems().toString());
		comboBranch.getCheckModel().clearChecks();

		person.setRole(comboRole.getCheckModel().getCheckedItems().toString());
		comboRole.getCheckModel().clearChecks();

		person.setComments(commentsArea.getText());
		commentsArea.setText("");

		if (personOverviewModel != null)
			personOverviewModel.getPersonData().add(person);
		else
			log.error("Overview model is null!!");
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
		allFieldsCorrect &= validateNotRequired(PersonValidator.validateNotRequired(carreerLevelChc.getValue()));
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
