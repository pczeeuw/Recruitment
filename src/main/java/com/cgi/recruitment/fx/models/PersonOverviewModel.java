package com.cgi.recruitment.fx.models;

/**
 * Model to hold the RecruitmentEvent data
 *  
 */

import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;

import javafx.collections.ObservableList;

@Component
public class PersonOverviewModel {
	
	//private ObservableList<FxPerson> personData;
	
	private FxRecruitmentEvent fxEvent;
	
	public PersonOverviewModel () {
	}
	
	public PersonOverviewModel (FxRecruitmentEvent fxEvent) {
		this.fxEvent = fxEvent;
	}
	
	public ObservableList<FxPerson> getPersonData () {
		return this.fxEvent.getPersonList();
	}
	
	public void setFxEvent (FxRecruitmentEvent fxEvent) {
		this.fxEvent = fxEvent;
	}
	
	public FxRecruitmentEvent getFxEvent () {
		return this.fxEvent;
	}
}
