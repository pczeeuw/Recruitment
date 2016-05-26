package com.cgi.recruitment.fx;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.controllers.AddPersonCGIController;
import com.cgi.recruitment.fx.controllers.AddPersonController;
import com.cgi.recruitment.fx.controllers.EventOverviewController;
import com.cgi.recruitment.fx.controllers.NewEventController;
import com.cgi.recruitment.fx.controllers.PersonOverviewController;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.util.AppConfiguration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FXApp implements ApplicationContextAware {

	private ApplicationContext context;

	private Stage primaryStage;
	private BorderPane rootLayout;

	private Resource[] screenResources;

	public void initApp(Stage primaryStage) {
		loadScreenResources();
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Recruitment App " + ((AppConfiguration)context.getBean(AppConfiguration.class)).getAppVersion());

		try {
			loadRootLayout();
			loadEventOverview();
		} catch (IOException e) {
			log.error("Failed to load Scene");
			e.printStackTrace();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public void showAddPersonDialog(PersonOverviewModel model) {
		try {
			this.loadAddPersonDialog(model);
		} catch (IOException e) {
			log.error("Failed to load the AddPersonDialog");
			e.printStackTrace();
		}
	}
	
	public void showPersonOverview (FxRecruitmentEvent event) {
		try {
			this.loadPersonOverview(event);
		} catch (IOException e) {
			log.error("Failed to load PersonOverview");
			e.printStackTrace();
		}
	}
	
	public void showEventOverview () {
		try {
			this.loadEventOverview();
		} catch (IOException e) {
			log.error("Failed to load EventOverview");
			e.printStackTrace();
		}
	}
	
	public void showNewEventDialog () {
		try {
			this.loadNewEventDialog();
		} catch (IOException e) {
			log.error("Could not load New Event Dialog");
			e.printStackTrace();
		}
	}
	
	public void showAddPersonCGIDialog (FxPerson person) {
		try {
			this.loadNewAddPersonCGIDialog (person);
		} catch  (IOException e) {
			log.error("Could not load Add Person CGI Dialog");
			e.printStackTrace();
		}
	}
	
	public Stage getStage () {
		return this.primaryStage;
	}

	private Resource getScreenResourceByFileName(String fileName) {
		for (Resource res : screenResources) {
			if (res.getFilename().equalsIgnoreCase(fileName))
				return res;
		}
		return null;
	}

	private void loadAddPersonDialog(PersonOverviewModel model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource screenResource = getScreenResourceByFileName("AddPerson.fxml");

		loader.setLocation(screenResource.getURL());
		VBox page = (VBox) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Aanmelden");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		dialogStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		dialogStage.setFullScreen(true);
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
				
		AddPersonController controller = (AddPersonController) loader.getController();
		controller.setPersonOverviewModel(model);
		controller.setMainApp(this);

		dialogStage.showAndWait();
	}

	private void loadPersonOverview(FxRecruitmentEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource resource = getScreenResourceByFileName("PersonOverview.fxml");
		loader.setLocation(resource.getURL());
		AnchorPane personOverview = (AnchorPane) loader.load();

		rootLayout.setCenter(personOverview);

		PersonOverviewController controller = loader.getController();
		controller.setPersonOverviewModel(new PersonOverviewModel(event));
		controller.setMainApp(this);

		rootLayout.setCenter(personOverview);
	}
	
	private void loadEventOverview() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource resource = getScreenResourceByFileName("EventOverview.fxml");
		loader.setLocation(resource.getURL());
		AnchorPane personOverview = (AnchorPane) loader.load();

		rootLayout.setCenter(personOverview);

		EventOverviewController controller = loader.getController();
		controller.setMainApp(this);

		rootLayout.setCenter(personOverview);
	}
	
	private void loadNewEventDialog () throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource screenResource = getScreenResourceByFileName("NewEvent.fxml");

		loader.setLocation(screenResource.getURL());
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Nieuw Event");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
				
		NewEventController controller = (NewEventController) loader.getController();
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
	}
	
	private void loadNewAddPersonCGIDialog (FxPerson person) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource screenResource = getScreenResourceByFileName("AddPersonCGI.fxml");

		loader.setLocation(screenResource.getURL());
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Invullen door CGI medewerker");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
				
		AddPersonCGIController controller = (AddPersonCGIController) loader.getController();
		controller.setFxPerson(person);
		controller.setDialogStage(dialogStage);
		
		dialogStage.showAndWait();
	}

	private void loadScreenResources() {
		try {
			this.screenResources = context.getResources("classpath:views/*.fxml");
		} catch (IOException e) {
			System.err.println("Failed to load fxml files from classpath");
			e.printStackTrace();
		}
	}

	private void loadRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);

		Resource resource = getScreenResourceByFileName("RootLayout.fxml");

		loader.setLocation(resource.getURL());

		rootLayout = (BorderPane) loader.load();

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
