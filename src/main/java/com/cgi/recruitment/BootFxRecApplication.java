package com.cgi.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cgi.recruitment.fx.FXApp;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class BootFxRecApplication extends Application {
	
	private static ApplicationContext context;


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootFxRecApplication.class, args);
		BootFxRecApplication.context = context;
		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		FXApp app = context.getBean(FXApp.class);
		app.initApp(primaryStage);
	}

}

