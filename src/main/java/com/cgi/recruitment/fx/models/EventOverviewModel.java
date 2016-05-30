package com.cgi.recruitment.fx.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEventFileName;
import com.cgi.recruitment.services.EventPersistService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventOverviewModel {
	
	@Autowired
	private EventPersistService eventPersistService;
	
	private FxRecruitmentEvent selectedEvent;
	
	//Holds a list of RecruitmentEvent file names (The XML files in the appdata folder)
	private ObservableList<FxRecruitmentEventFileName> eventData;
	
	public EventOverviewModel () {
		eventData = FXCollections.observableArrayList();
	}

	public ObservableList<FxRecruitmentEventFileName> getEventData () {
		return this.eventData;
	}
	
	public void fillEventData () {
		eventData.clear();
		for (String eventFileName : eventPersistService.getEventFileNames()) {
			eventData.add(new FxRecruitmentEventFileName(eventFileName));
		}
	}
	
	public void deleteEvent (String eventFileName) {
		eventPersistService.deleteEvent(eventFileName);
		eventData.remove(eventFileName);
		fillEventData ();
		this.selectedEvent = null;
		log.info("Event with filename " + eventFileName + " deleted");
	}
	
	//When user clicks an event filename, load the event based on the filename
	public void setSelectedEvent(String eventFileName) {
		this.selectedEvent = eventPersistService.getPersistedEvent(eventFileName);
	}
	
	public FxRecruitmentEvent getSelectedEvent () {
		return this.selectedEvent;
	}
}
