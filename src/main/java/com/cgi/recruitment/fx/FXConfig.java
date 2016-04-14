package com.cgi.recruitment.fx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class FXConfig {
	
	@Bean(name="RootLayoutResource")
	public Resource getRootLayoutResource (ResourceLoader resourceLoader) {
		Resource result = resourceLoader.getResource("classpath:/views/RootLayout.fxml");
		System.out.println(result.toString());
		return result;
	}
	
	@Bean(name="PersonOverviewResource")
	public Resource getPersonOverviewResource (ResourceLoader resourceLoader) {
		Resource result = resourceLoader.getResource("classpath:/views/PersonOverview.fxml");
		System.out.println(result.toString());
		return result;
	}
	
	@Bean(name="AddPersonResource")
	public Resource getAddPersonResource (ResourceLoader resourceLoader) {
		Resource result = resourceLoader.getResource("classpath:/views/ContactDetails.fxml");
		System.out.println(result.toString());
		return result;
	}

}
