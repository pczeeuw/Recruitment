package com.cgi.recruitment.fx.controllers;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.stage.Stage;

@Component
public class DisclaimerController {
	
	private Stage stage;
	
	public void setDialogStage (Stage stage) {
		this.stage= stage;
	}
	@FXML
	private void gelezen () {
		this.stage.close();
	}

}
