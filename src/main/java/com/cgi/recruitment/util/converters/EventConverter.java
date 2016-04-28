package com.cgi.recruitment.util.converters;

import java.util.Set;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;

public class EventConverter {

	public static RecruitmentEvent convertToRecruitmentEvent (FxRecruitmentEvent fxEvent) {
		RecruitmentEvent event = new RecruitmentEvent(fxEvent.getEventName(),fxEvent.getEventLocation(),fxEvent.getEventDate());
		Set<Person> personSet = PersonListConverter.convertToPersonSet(fxEvent.getPersonList());		
		event.setPersonSet(personSet);		
		return event;
	}
	
	public static FxRecruitmentEvent convertToFxRecruitmentEventg (RecruitmentEvent event) {
		FxRecruitmentEvent fxEvent = new FxRecruitmentEvent ();
		fxEvent.setEventName(event.getEventName());
		fxEvent.setEventLocation(event.getEventLocation());
		fxEvent.setEventDate(event.getEventDate());
		fxEvent.setPersonList(PersonListConverter.convertToPersonObservableList(event.getPersonSet()));
		return fxEvent;
	}
}
