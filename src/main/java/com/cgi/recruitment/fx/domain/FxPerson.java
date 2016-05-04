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
	private final StringProperty interestedIn;
	private final StringProperty region;
	private final ObjectProperty<LocalDate> prefStartDate;
	
	
	
	private final StringProperty comments;

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
		this (firstName, lastName, null,null,null,null,null,null,null,null);
	}
	
	public FxPerson(String firstName, String lastName, String emailAddress, String phoneNumber, String study, LocalDate graduationDate, String lookingFor, String workLocation, LocalDate prefStartDate, String comments) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.emailAddress = new SimpleStringProperty(emailAddress);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.study = new SimpleStringProperty(study);
		this.graduationDate = new SimpleObjectProperty<>(graduationDate);
		this.interestedIn = new SimpleStringProperty(lookingFor);
		this.region = new SimpleStringProperty(workLocation);
		this.prefStartDate = new SimpleObjectProperty<>(prefStartDate);
		this.comments = new SimpleStringProperty(comments);
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
	
	public String getEmailAddress () {
		return this.emailAddress.get();
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress.set(emailAddress);
	}

	public StringProperty getPhoneNumberProperty () {
		return phoneNumber;
	}
	
	public String getPhoneNumber () {
		return this.phoneNumber.get();
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}


	public StringProperty getStudyProperty() {
		return study;
	}
	
	public String getStudy () {
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

	public ObjectProperty<LocalDate> getWorkStartDateProperty() {
		return prefStartDate;
	}
	
	public LocalDate getWorkStartDate() {
		return prefStartDate.get();
	}
	
	public void setWorkStartDate(LocalDate startDate) {
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


}