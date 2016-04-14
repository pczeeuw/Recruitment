package com.cgi.recruitment;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.cgi.recruitment.fx.controllers.PersonOverviewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
}

