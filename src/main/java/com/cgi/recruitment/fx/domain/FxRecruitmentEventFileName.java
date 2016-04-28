package com.cgi.recruitment.fx.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FxRecruitmentEventFileName {
	StringProperty eventFileName;
	
	public FxRecruitmentEventFileName () {
		this("");
	}
	
	public FxRecruitmentEventFileName (String fileName) {
		this.eventFileName = new SimpleStringProperty(fileName);
	}
	
	public StringProperty getEventFileNameProperty () {
		return this.eventFileName;
	}
	
	public String getEventFileName () {
		return this.eventFileName.get();
	}
	
	public void setEventFileName (String fileName) {
		this.eventFileName.set(fileName);
	}

}
