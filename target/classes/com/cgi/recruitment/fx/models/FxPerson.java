package com.cgi.recruitment.fx.models;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class FxPerson {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private  StringProperty emailAddress;
	private  IntegerProperty phoneNumber;
	private  StringProperty study;
	private ObjectProperty<LocalDate> graduationDate;
	private  StringProperty lookingFor;
	private  StringProperty workLocation;
	private ObjectProperty<LocalDate> workStartDate;
	private  StringProperty comments;

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
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
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

	public IntegerProperty getPhoneNumberProperty () {
		return phoneNumber;
	}
	
	public Integer getPhoneNumber () {
		return this.phoneNumber.get();
	}
	
	public void setPhoneNumber(Integer phoneNumber) {
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

	public StringProperty getLookingForProperty() {
		return lookingFor;
	}
	
	public String getLookingFor() {
		return lookingFor.get();
	}
	
	public void setLookingFor(String lookingFor) {
		this.lookingFor.set(lookingFor);
	}

	public StringProperty getWorkLocationProperty() {
		return workLocation;
	}
	
	public String getWorkLocation() {
		return workLocation.get();
	}
	
	public void setWorkLocation(String workLocation) {
		this.workLocation.set(workLocation);
	}

	public ObjectProperty<LocalDate> getWorkStartDateProperty() {
		return workStartDate;
	}
	
	public LocalDate getWorkStartDate() {
		return workStartDate.get();
	}
	
	public void setWorkStartDate(LocalDate startDate) {
		this.workStartDate.set(startDate);
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