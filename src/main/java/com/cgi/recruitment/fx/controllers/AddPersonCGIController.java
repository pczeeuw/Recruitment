package com.cgi.recruitment.fx.controllers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.domain.FxPerson;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
@Lazy
public class AddPersonCGIController {
	@FXML
	private TextField spokenWithFld;
	
	@FXML
	private TextArea CGICommentsArea;
	
	private FxPerson fxPerson;
	
	private Stage stage;
	
	public void setFxPerson(FxPerson fxP) {
		this.fxPerson = fxP;
	}
	
	public void setDialogStage (Stage stage) {
		this.stage= stage;
	}
 	
	@FXML
	public void addPersonCGI () {
		fxPerson.setSpokenWith(spokenWithFld.getText());
		fxPerson.setCommentsCGI(CGICommentsArea.getText());
		
		stage.close();
	}

}
