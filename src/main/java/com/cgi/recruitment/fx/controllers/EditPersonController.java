package com.cgi.recruitment.fx.controllers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;

import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Lazy
public class EditPersonController {
	
	private FXApp fxApp;
	private FxPerson person;
	private FxRecruitmentEvent event;
	
	public void setMainApp(FXApp fxApp) {
		this.fxApp = fxApp;
	}

	public void setFxPerson(FxPerson person) {
		this.person = person;
		
	}
	
	public void setFxRecruitmentEvent(FxRecruitmentEvent event) {
		this.event = event;
	}
	
	@FXML
	private void backToPersonOverview() {
		this.fxApp.showPersonOverview(this.event);
	}

}
