package com.cgi.recruitment.fx.controllers;

import java.io.File;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.FXApp;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEventFileName;
import com.cgi.recruitment.fx.models.EventOverviewModel;
import com.cgi.recruitment.services.ExcelExportService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

@Component
@Lazy
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
	
	@Autowired
	private ExcelExportService excelService;
	
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
		if (eventModel.getSelectedEvent()==null)
			return;
		File file;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setInitialFileName(eventModel.getSelectedEvent().getEventDate().toString()+ "-" + eventModel.getSelectedEvent().getEventName()+"-"+eventModel.getSelectedEvent().getEventLocation());
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Alle bestanden", "*.*"),
                new FileChooser.ExtensionFilter("XLS", "*.xls")
                //new FileChooser.ExtensionFilter("XLSX", "*.xlsx")
            );
		file = fileChooser.showSaveDialog(fxApp.getStage());
		
		excelService.setRecruitmentEvent(eventModel.getSelectedEvent());
		excelService.setTargetFile(file);
		
		excelService.exportToXLSX();
		
	}

	public void setMainApp(FXApp fxApp) {
		this.fxApp = fxApp;
	}
	
}
