package com.cgi.recruitment;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.cgi.recruitment.fx.controllers.AddPersonController;
import com.cgi.recruitment.fx.controllers.PersonOverviewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SpringBootApplication
public class BootFxRecApplication extends Application{
	
	private static ApplicationContext context;
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootFxRecApplication.class, args);
		BootFxRecApplication.context = context;
		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		
		initRootLayout();
		
		showPersonOverview();
	}
	
	private void initRootLayout () {
		FXMLLoader loader = new FXMLLoader ();
		Resource resource = context.getBean("RootLayoutResource", Resource.class);
		
		System.out.println(resource.toString() + " exists= " + resource.exists());
		try {
			loader.setLocation(resource.getURL());
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Scene scene = new Scene (rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showPersonOverview () {
		FXMLLoader loader = new FXMLLoader ();
		Resource resource = context.getBean("PersonOverviewResource", Resource.class);
		System.out.println(resource.toString() + " exists= " + resource.exists());
		
		
		try {
			//Load Person Overview
			
			loader.setLocation(resource.getURL());
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(personOverview);
			
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAddPersonDialog () {
		FXMLLoader loader = new FXMLLoader ();
		Resource resource = context.getBean("AddPersonResource",Resource.class);
		
		try {
			loader.setLocation(resource.getURL());
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage ();
			dialogStage.setTitle("Aanmelden");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
			dialogStage.setFullScreen(true);
			Scene scence  = new Scene(page);
			dialogStage.setScene(scence);
			
			AddPersonController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
		} catch (Exception e) {
			System.err.println("An exception occured while loading the AddPersonPage " + e.getMessage());
		}
	}
}

