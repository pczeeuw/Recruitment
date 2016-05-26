package com.cgi.recruitment.fx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.services.EventPersistService;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
@Lazy
public class NewEventController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField locationField;
	@FXML
	private DatePicker dateField;
	
	@Autowired
	private EventPersistService persistService;
	
	private Stage dialogStage;
	
	@FXML
	public void createNewEvent () {
		FxRecruitmentEvent event = new FxRecruitmentEvent ();
		event.setEventName(nameField.getText());
		event.setEventLocation(locationField.getText());
		event.setEventDate(dateField.getValue());
		
		persistService.persistEvent(event);
		dialogStage.close();
	}
	
	public void setDialogStage (Stage dialog) {
		this.dialogStage = dialog;
	}
}
