package com.cgi.recruitment.fx.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEventFileName;
import com.cgi.recruitment.fx.models.EventOverviewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
public class EventOverviewController {
	@FXML
	private TableView<FxRecruitmentEventFileName> eventTable;
	@FXML
	private TableColumn<FxRecruitmentEventFileName, String> eventFileNameColumn;
	@FXML
	private TableColumn<FxRecruitmentEvent, LocalDate> eventDateColumn;

	@FXML
	private Label eventNameLabel;
	@FXML
	private Label eventLocationLabel;
	@FXML
	private Label eventDateLabel;
	@FXML
	private Label personCountLabel;

	@Autowired
	private EventOverviewModel eventModel;
	
	private FXApp fxApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the event table with the column.
		eventFileNameColumn.setCellValueFactory(cellData -> cellData.getValue().getEventFileNameProperty());
		// eventDateColumn.setCellValueFactory(cellData ->
		// cellData.getValue().getEventDateProperty());

		showEventDetails(null);

		eventModel.fillEventData();

		eventTable.setItems(eventModel.getEventData());

		eventTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showEventDetails(newValue));

	}

	private void showEventDetails(FxRecruitmentEventFileName eventFile) {
		if (eventFile == null) {
			this.eventNameLabel.setText("");
			this.eventLocationLabel.setText("");
			this.eventDateLabel.setText("");
			this.personCountLabel.setText("");
		} else {
			eventModel.setSelectedEvent(eventFile.getEventFileName());
			FxRecruitmentEvent fxEvent = eventModel.getSelectedEvent();
			
			this.eventNameLabel.setText(fxEvent.getEventName());
			this.eventLocationLabel.setText(fxEvent.getEventLocation());
			this.eventDateLabel.setText(fxEvent.getEventDate().toString());
			this.personCountLabel.setText("" + fxEvent.getPersonList().size());
		}

	}
	
	@FXML
	public void newEvent () {
		this.fxApp.showNewEventDialog();
		eventModel.fillEventData();
	}
	
	@FXML
	public void openEvent () {
		this.fxApp.showPersonOverview(eventModel.getSelectedEvent());
	}
	
	@FXML
	public void exportEvent () {
		
	}

	public void setMainApp(FXApp fxApp) {
		this.fxApp = fxApp;
	}
	
}
