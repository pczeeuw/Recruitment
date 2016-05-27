package com.cgi.recruitment.fx.domain;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 */
public class FxPerson {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty emailAddress;
	private final StringProperty phoneNumber;
	private final StringProperty study;
	private final ObjectProperty<LocalDate> graduationDate;
	private final StringProperty educationLevel;
	private final StringProperty interestedIn;
	private final StringProperty region;
	private final ObjectProperty<LocalDate> prefStartDate;
	private final StringProperty careerLevel;
	private final StringProperty specialism;
	private final StringProperty branch;
	private final StringProperty role;
	private final StringProperty comments;
	private final StringProperty spokenWith;
	private final StringProperty commentsCGI;
	private final StringProperty newsLetter;
	private final ObjectProperty<LocalDate> applyDate;
	private final StringProperty eventName;
	private final StringProperty eventLocation;
	private final ObjectProperty<LocalDate> eventDate;

	/**
	 * Default constructor.
	 */
	public FxPerson() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public FxPerson(String firstName, String lastName) {
		this(firstName, lastName, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public FxPerson(String firstName, String lastName, String emailAddress, String phoneNumber, String study,
			LocalDate graduationDate, String educationLevel, String lookingFor, String workLocation,
			LocalDate prefStartDate, String comments, String careerLevel, String specialism, String branch,
			String role, String spokenWith, String commentsCGI, String newsLetter, LocalDate applyDate,
			String eventName, String eventLocation, LocalDate eventDate) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.emailAddress = new SimpleStringProperty(emailAddress);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.study = new SimpleStringProperty(study);
		this.graduationDate = new SimpleObjectProperty<>(graduationDate);
		this.educationLevel = new SimpleStringProperty(educationLevel);
		this.interestedIn = new SimpleStringProperty(lookingFor);
		this.region = new SimpleStringProperty(workLocation);
		this.prefStartDate = new SimpleObjectProperty<>(prefStartDate);
		this.comments = new SimpleStringProperty(comments);
		this.careerLevel = new SimpleStringProperty(careerLevel);		
		this.specialism = new SimpleStringProperty(specialism);		
		this.branch = new SimpleStringProperty(branch);		
		this.role = new SimpleStringProperty(role);
		this.spokenWith = new SimpleStringProperty(spokenWith);
		this.commentsCGI = new SimpleStringProperty(commentsCGI);
		this.newsLetter = new SimpleStringProperty(newsLetter);
		this.applyDate = new SimpleObjectProperty<> (applyDate);
		this.eventName = new SimpleStringProperty(eventName);
		this.eventLocation = new SimpleStringProperty(eventLocation);
		this.eventDate = new SimpleObjectProperty<>(eventDate);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public StringProperty emailAddressProperty() {
		return emailAddress;
	}

	public String getEmailAddress() {
		return this.emailAddress.get();
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress.set(emailAddress);
	}

	public StringProperty getPhoneNumberProperty() {
		return phoneNumber;
	}

	public String getPhoneNumber() {
		return this.phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public StringProperty getStudyProperty() {
		return study;
	}

	public String getStudy() {
		return this.study.get();
	}

	public void setStudy(String study) {
		this.study.set(study);
	}

	public ObjectProperty<LocalDate> getGraduationDateProperty() {
		return graduationDate;
	}

	public LocalDate getGraduationDate() {
		return graduationDate.get();
	}

	public void setGraduationDate(LocalDate gradDate) {
		this.graduationDate.set(gradDate);
	}

	public StringProperty getInterestedInProperty() {
		return interestedIn;
	}

	public String getInterestedIn() {
		return interestedIn.get();
	}

	public void setInterestedIn(String lookingFor) {
		this.interestedIn.set(lookingFor);
	}

	public StringProperty getRegionProperty() {
		return region;
	}

	public String getRegion() {
		return region.get();
	}

	public void setRegion(String workLocation) {
		this.region.set(workLocation);
	}

	public ObjectProperty<LocalDate> getPrefStartDateProperty() {
		return prefStartDate;
	}

	public LocalDate getPrefStartDate() {
		return prefStartDate.get();
	}

	public void setPrefStartDate(LocalDate startDate) {
		this.prefStartDate.set(startDate);
	}

	public StringProperty getCommentsProperty() {
		return comments;
	}

	public String getComments() {
		return comments.get();
	}

	public void setComments(String comments) {
		this.comments.set(comments);
	}

	public StringProperty getCareerLevelProperty() {
		return this.careerLevel;
	}

	public String getCareerLevel() {
		return careerLevel.get();
	}

	public void setCareerLevel(String careerLevel) {
		this.careerLevel.set(careerLevel);
	}

	public StringProperty getSpecialismProperty() {
		return this.specialism;
	}

	public String getSpecialism() {
		return specialism.get();
	}

	public void setSpecialism(String specialism) {
		this.specialism.set(specialism);
	}

	public StringProperty getBranchProperty() {
		return this.branch;
	}

	public String getBranch() {
		return branch.get();
	}

	public void setBranch(String branch) {
		this.branch.set(branch);
	}

	public StringProperty getRoleProperty() {
		return this.role;
	}

	public String getRole() {
		return role.get();
	}

	public void setRole(String role) {
		this.role.set(role);
	}

	public StringProperty getEducationLevelProperty() {
		return this.educationLevel;
	}

	public String getEducationLevel() {
		return this.educationLevel.get();
	}

	public void setEductionLevel(String educationLevel) {
		this.educationLevel.set(educationLevel);
	}
	
	public StringProperty getSpokenWithProperty() {
		return this.spokenWith;
	}

	public String getSpokenWith() {
		return this.spokenWith.get();
	}

	public void setSpokenWith(String spokenWith) {
		this.spokenWith.set(spokenWith);
	}
	
	public StringProperty getCommentsCGIProperty() {
		return this.commentsCGI;
	}

	public String getCommentsCGI() {
		return this.commentsCGI.get();
	}

	public void setCommentsCGI(String commentsCGI) {
		this.commentsCGI.set(commentsCGI);
	}
	
	public StringProperty getNewsLetterProperty() {
		return this.newsLetter;
	}

	public String getNewsLetter() {
		return this.newsLetter.get();
	}

	public void setNewsLetter(String newsLetter) {
		this.newsLetter.set(newsLetter);
	}
	
	public ObjectProperty<LocalDate> getApplyDateProperty() {
		return applyDate;
	}

	public LocalDate getApplyDate() {
		return applyDate.get();
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate.set(applyDate);
	}
	
	public StringProperty getEventNameProperty() {
		return this.eventName;
	}

	public String getEventName() {
		return this.eventName.get();
	}

	public void setEventName(String eventName) {
		this.eventName.set(eventName);
	}
	
	public StringProperty getEventLocationProperty() {
		return this.eventLocation;
	}

	public String getEventLocation() {
		return this.eventLocation.get();
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation.set(eventLocation);
	}
	
	public ObjectProperty<LocalDate> getEventDateProperty() {
		return eventDate;
	}

	public LocalDate getEventDate() {
		return eventDate.get();
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate.set(eventDate);
	}

}