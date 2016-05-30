package com.cgi.recruitment.fx.controllers;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Lazy
public class EditPersonController {

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
	private TextField educationLevelChc; // Required
	@FXML
	private TextField interestedInChc;
	@FXML
	private TextField regionChc;
	@FXML
	private DatePicker prefStartDateDap;
	@FXML
	private TextField carreerLevelChc;
	@FXML
	private TextField comboSkill;
	@FXML
	private TextField comboBranch;
	@FXML
	private TextField comboRole;
	@FXML
	private TextArea commentsArea;
	@FXML
	private TextArea commentsCGIArea;
	@FXML
	private TextField spokenWithFld;
	@FXML
	private TextField newsletterFld;
	@FXML
	private DatePicker applyDate;

	private FXApp fxApp;
	private FxPerson person;
	private FxRecruitmentEvent event;

	@FXML
	private void initialize() {
		if (person != null) {
			initFieldsWithPerson ();
		}
	}

	public void setMainApp(FXApp fxApp) {
		this.fxApp = fxApp;
	}

	public void setFxPerson(FxPerson person) {
		this.person = person;
		initFieldsWithPerson ();

	}

	public void setFxRecruitmentEvent(FxRecruitmentEvent event) {
		this.event = event;
	}

	@FXML
	private void backToPersonOverview() {
		this.fxApp.showPersonOverview(this.event);
	}
	
	private void initFieldsWithPerson () {
		firstNameFld.setText(person.getFirstName());
		lastNameFld.setText(person.getLastName());
		emailAddressFld.setText(person.getEmailAddress());
		phoneNumberFld.setText(person.getPhoneNumber());
		studyFld.setText(person.getStudy());
		graduationDateFld.setValue(person.getGraduationDate());
		educationLevelChc.setText(person.getEducationLevel());
		interestedInChc.setText(person.getInterestedIn());
		regionChc.setText(person.getRegion());
		prefStartDateDap.setValue(person.getPrefStartDate());
		carreerLevelChc.setText(person.getCareerLevel());
		comboSkill.setText(person.getSpecialism());
		comboBranch.setText(person.getBranch());
		comboRole.setText(person.getRole());
		commentsArea.setText(person.getComments());
		commentsCGIArea.setText(person.getCommentsCGI());
		spokenWithFld.setText(person.getSpokenWith());
		newsletterFld.setText(person.getNewsLetter());
		applyDate.setValue(person.getEventDate());
	}
	
	@FXML
	public void updatePerson () {
		Optional<ButtonType> result = fxApp.showConfirmationDialog("Persoon bijwerken?",
				"Wilt u persoon met naam " + person.getFirstName() + " "
						+ person.getLastName() + " bijwerken?");

		if (result.get() == ButtonType.OK) {
			clonePerson ();
		} else {
			return;
		}
		backToPersonOverview();
	}
	
	private void clonePerson () {
		person.setFirstName(firstNameFld.getText());
		person.setLastName(lastNameFld.getText());
		person.setEmailAddress(emailAddressFld.getText());
		person.setPhoneNumber(phoneNumberFld.getText());
		person.setStudy(studyFld.getText());
		person.setGraduationDate(graduationDateFld.getValue());
		person.setEductionLevel(educationLevelChc.getText());
		person.setInterestedIn(interestedInChc.getText());
		person.setRegion(regionChc.getText());
		person.setPrefStartDate(prefStartDateDap.getValue());
		person.setCareerLevel(carreerLevelChc.getText());
		person.setSpecialism(comboSkill.getText());
		person.setBranch(comboBranch.getText());
		person.setRole(comboRole.getText());
		person.setComments(commentsArea.getText());
		person.setCommentsCGI(commentsCGIArea.getText());
		person.setSpokenWith(spokenWithFld.getText());
		person.setNewsLetter(newsletterFld.getText());
		person.setApplyDate(applyDate.getValue());
		
		log.info("Person updated!");
	}

}
