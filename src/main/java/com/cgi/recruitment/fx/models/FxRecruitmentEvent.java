package com.cgi.recruitment.fx.models;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class FxRecruitmentEvent {
	
	private final StringProperty eventName;
	private final StringProperty eventLocation;
	private final ObjectProperty<LocalDate> eventDate;
	private ObservableList<FxPerson> personList;
	
	public FxRecruitmentEvent () {
		this ("TestEvent","Groningen");
	}
	
	public FxRecruitmentEvent (String eventName, String eventLocation) {
		this (eventName, eventLocation, null);
	}
	
	public FxRecruitmentEvent (String eventName, String eventLocation, LocalDate eventDate) {
		this.eventName = new SimpleStringProperty(eventName);
		this.eventLocation = new SimpleStringProperty(eventLocation);
		this.eventDate = new SimpleObjectProperty<>(eventDate);
		this.personList = FXCollections.observableArrayList();
	}
	
	public StringProperty eventNameProperty () {
		return this.eventName;
	}
	
	public void setEventName (String eventName) {
		this.eventName.set(eventName);
	}
	
	public String getEventName () {
		return this.eventName.get();
	}
	
	public StringProperty eventLocationProperty () {
		return this.eventLocation;
	}
	
	public void setEventLocation (String eventLocation) {
		this.eventLocation.set(eventLocation);
	}
	
	public String getEventLocation () {
		return this.eventLocation.get();
	}
	
	public ObjectProperty<LocalDate> getEventDateProperty () {
		return this.eventDate;
	}
	
	public void setEventDate (LocalDate eventDate) {
		this.eventDate.set(eventDate);
	}
	
	public LocalDate getEventDate () {
		return this.eventDate.get();
	}
	
	public ObservableList<FxPerson> getPersonList () {
		return this.personList;
	}
	
	public void setPersonList(ObservableList<FxPerson> personList) {
		this.personList = personList;
	}

}
