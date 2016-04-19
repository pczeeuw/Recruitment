package com.cgi.recruitment.fx;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.fx.controllers.AddPersonController;
import com.cgi.recruitment.fx.controllers.PersonOverviewController;
import com.cgi.recruitment.fx.models.FxPerson;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

@Component
public class FXApp implements ApplicationContextAware {

	private ApplicationContext context;

	private Stage primaryStage;
	private BorderPane rootLayout;

	private Resource[] screenResources;
//	private Resource[] cssResources;

	public void initApp(Stage primaryStage) {

		loadScreenResources();
//		loadCssResources();

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Recruitment App");

		try {
			loadRootLayout();
			loadPersonOverview();
		} catch (IOException e) {
			System.err.println("Failed to load Scene");
			e.printStackTrace();
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public void showAddPersonDialog(FxPerson person) {
		try {
			this.loadAddPersonDialog(person);
		} catch (IOException e) {
			System.err.println("Failed to load the AddPersonDialog");
			e.printStackTrace();
		}
	}

//	private Resource getCssResourceByFileName(String fileName) {
//		for (Resource res : cssResources) {
//			System.out.println("Css file: " + res.toString());
//			if (res.getFilename().equalsIgnoreCase(fileName))
//				return res;
//		}
//
//		return null;
//	}

	private Resource getScreenResourceByFileName(String fileName) {
		for (Resource res : screenResources) {
			if (res.getFilename().equalsIgnoreCase(fileName))
				return res;
		}
		return null;
	}

	private void loadAddPersonDialog(FxPerson person) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource screenResource = getScreenResourceByFileName("AddPerson.fxml");
		//Resource cssResource = getCssResourceByFileName("add-person.css");

		loader.setLocation(screenResource.getURL());
		VBox page = (VBox) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Aanmelden");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		dialogStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		dialogStage.setFullScreen(true);
		
		Rectangle2D screenSize = Screen.getPrimary().getBounds();
		
		System.out.println("Screen is: " + screenSize.getHeight() + ":" + screenSize.getWidth());
		
		//page.widthProperty().add(screenSize.getWidth());
		//page.heightProperty().add(screenSize.getHeight());

		Scene scene = new Scene(page);
		//scene.getStylesheets().add(cssResource.toString());
		dialogStage.setScene(scene);
		

		
		AddPersonController controller = loader.getController();
		controller.setFxPerson (person);
		//controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
	}

	private void loadPersonOverview() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);
		Resource resource = getScreenResourceByFileName("PersonOverview.fxml");
		loader.setLocation(resource.getURL());
		AnchorPane personOverview = (AnchorPane) loader.load();

		rootLayout.setCenter(personOverview);

		PersonOverviewController controller = loader.getController();
		controller.setMainApp(this);

		rootLayout.setCenter(personOverview);

	}

//	private void loadCssResources() {
//		try {
//			this.cssResources = context.getResources("classpath:views/css/*.css");
//		} catch (IOException e) {
//			System.err.println("Failed to load Css files from classpath");
//			e.printStackTrace();
//		}
//
//	}

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
