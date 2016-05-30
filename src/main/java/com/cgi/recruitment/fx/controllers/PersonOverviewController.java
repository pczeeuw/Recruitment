package com.cgi.recruitment.fx.controllers;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.services.EventPersistService;
import com.cgi.recruitment.util.converters.DateConverter;
import com.cgi.recruitment.util.converters.DateTimeConverter;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Lazy
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
	private Label educationLevelLabel;
	@FXML
	private Label interestedInLabel;
	@FXML
	private Label workLocationLabel;
	@FXML
	private Label prefStartDateLabel;
	@FXML
	private Label carreerLevelLabel;;
	@FXML
	private Label specialismLabel;
	@FXML
	private Label branchLabel;
	@FXML
	private Label roleLabel;
	@FXML
	private Label commentsLabel;
	@FXML
	private Label spokenWithLabel;
	@FXML
	private Label commentsCGILabel;
	@FXML
	private Label newsLetterLabel;
	@FXML
	private Label applyDateLabel;

	private PersonOverviewModel personModel;

	@Autowired
	private EventPersistService service;

	// Reference to the main application.
	private FXApp fxApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PersonOverviewController() {
		log.info("PersonOverviewController created");
	}

	public void setPersonOverviewModel(PersonOverviewModel model) {
		this.personModel = model;
		initAfterModel();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	private void initAfterModel() {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		personTable.setRowFactory(new Callback<TableView<FxPerson>, TableRow<FxPerson>>() {
			@Override
			public TableRow<FxPerson> call(TableView<FxPerson> tableView) {
				final TableRow<FxPerson> row = new TableRow<FxPerson>() {
					@Override
					protected void updateItem(FxPerson person, boolean empty) {
						super.updateItem(person, empty);
						if (person != null) {
							if (person.getSpokenWith() == null || person.getSpokenWith().length() < 1) {
								if (!getStyleClass().contains("highlight")) {
									getStyleClass().add("highlight");
								}
							} else {
								getStyleClass().removeAll(Collections.singleton("highlight"));
							}
						}
					}
				};

				return row;
			}
		});

		showPersonDetails(null);

		// personModel.fillListWithTestData();

		personTable.setItems(personModel.getPersonData());

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
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
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person
	 *            the person or null
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
			educationLevelLabel.setText(person.getEducationLevel());
			interestedInLabel.setText(person.getInterestedIn());
			workLocationLabel.setText(person.getRegion());
			prefStartDateLabel.setText(DateConverter.format(person.getPrefStartDate()));
			carreerLevelLabel.setText(person.getCareerLevel());
			specialismLabel.setText(person.getSpecialism());
			branchLabel.setText(person.getBranch());
			roleLabel.setText(person.getRole());
			commentsLabel.setText(person.getComments());
			spokenWithLabel.setText(person.getSpokenWith());
			commentsCGILabel.setText(person.getCommentsCGI());
			newsLetterLabel.setText(person.getNewsLetter());
			applyDateLabel.setText(DateTimeConverter.format(person.getApplyDate()));

		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
			phoneLabel.setText("");
			studyLabel.setText("");
			graduationLabel.setText("");
			educationLevelLabel.setText("");
			interestedInLabel.setText("");
			workLocationLabel.setText("");
			prefStartDateLabel.setText("");
			carreerLevelLabel.setText("");
			specialismLabel.setText("");
			branchLabel.setText("");
			roleLabel.setText("");
			commentsLabel.setText("");
			spokenWithLabel.setText("");
			commentsCGILabel.setText("");
			newsLetterLabel.setText("");
			applyDateLabel.setText("");
		}
	}

	/**
	 * If Button 'Nieuwe Aanmelding' is clicked.
	 */
	@FXML
	private void handleNewPerson() {
		fxApp.showAddPersonDialog(personModel);
		personTable.refresh();
	}

	/**
	 * If button 'Edit' is clicked:
	 */
	@FXML
	private void editPerson() {
		if (personTable.getSelectionModel().getSelectedItem() == null)
			return;
		fxApp.showEditPersonScreen(personTable.getSelectionModel().getSelectedItem(),personModel.getFxEvent());
		service.persistEvent(this.personModel.getFxEvent());
		personTable.refresh();
	}

	/**
	 * If button 'Delete' is clicked
	 */
	@FXML
	private void deletePerson() {
		if (personTable.getSelectionModel().getSelectedItem() == null)
			return;

		Optional<ButtonType> result = fxApp.showConfirmationDialog("Persoon verwijderen?",
				"Wilt u persoon met naam " + personTable.getSelectionModel().getSelectedItem().getFirstName() + " "
						+ personTable.getSelectionModel().getSelectedItem().getLastName() + " verwijderen?");

		if (result.get() == ButtonType.OK) {
			if (personModel.getPersonData().remove(personTable.getSelectionModel().getSelectedItem())) {
				log.info("Selected Person deleted from model");
				service.persistEvent(personModel.getFxEvent());
			}
		} else {
			return;
		}

	}

	/**
	 * If button 'Terug' is clicked
	 */
	@FXML
	private void backToEvents() {
		service.persistEvent(personModel.getFxEvent());
		this.fxApp.showEventOverview();
	}

}
