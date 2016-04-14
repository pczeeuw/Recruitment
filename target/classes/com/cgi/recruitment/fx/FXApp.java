package com.cgi.recruitment.fx;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.net.SyslogOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class FXApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private Resource rootLayoutResource;
	private Resource personOverviewResource;


//	public FXApp () {
//		super ();
//		//rootLayoutResource = resourceLoader.getResource("classpath:/resources/views/RootLayout.fxml");
//		//personOverviewResource = resourceLoader.getResource("classpath:/resources/views/PersonOverview.fxml");
//	}
	

	
	@Override
	public void start(Stage primaryStage) {
		if (rootLayoutResource == null) {
			System.err.println("Root Layout Resource is NULL!");
		}
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		
		initRootLayout();
		
		showPersonOverview();
		
	}

	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader ();
			loader.setLocation(rootLayoutResource.getURL());
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene (rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run (String[] args) {
		Application.launch(args);
	}
	
	public void showPersonOverview () {
		try {
			//Load Person Overview
			FXMLLoader loader = new FXMLLoader ();
			loader.setLocation(personOverviewResource.getURL());
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(personOverview);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
