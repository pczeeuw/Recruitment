package com.cgi.recruitment.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author zeeuwp
 *
 */

@Configuration
@Slf4j
public class AppConfiguration {
	private static final String SYSTEM_OS = System.getProperty("os.name").toLowerCase();
	private static final String INI_FILE = "recruitment.ini";
	private static final String PROPS_FILE = "recruitment.properties";

	private boolean isWindows;
	private boolean isUnix;

	private Path appdataPath;

	@Value("${info.build.version}")
	private String appVersion;

	private Properties appConfigProperties;

	@PostConstruct
	public void initAppProps() {
		log.info("Setting application Properties for Recruitment App " + appVersion);
		detectOS();
		log.info("Detected OS: " + SYSTEM_OS);
		log.info("OS is Windows? " + isWindows);
		log.info("OS is Unix? " + isUnix);
		if (isWindows)
			setLocalAppPropsWin();
	}

	private void detectOS() {
		isWindows = SYSTEM_OS.indexOf("win") >= 0;
		isUnix = (SYSTEM_OS.indexOf("nix") >= 0 || SYSTEM_OS.indexOf("nux") >= 0);
	}

	private void setLocalAppPropsWin() {
		appdataPath = Paths.get(System.getenv("APPDATA"));
		checkExists(appdataPath);

		appdataPath = Paths.get(appdataPath.toString(), "recruitment");
		if (!checkExists(appdataPath))
			createDirs(appdataPath);
		
		if (!checkExists(Paths.get(appdataPath.toString(), INI_FILE)))
			createIniFile(appdataPath);
		
		if (!checkExists(Paths.get(appdataPath.toString(), PROPS_FILE))) {
			log.info("No recruitment.properties file found!");
			createPropertiesFile(appdataPath);
		}
		processIniFile(appdataPath);
	}

	private void processIniFile(Path p) {
		log.info("Attempting to read from existing recruitment.ini file");
		try (InputStream input = new FileInputStream(Paths.get(p.toString(), INI_FILE).toFile())) {
			appConfigProperties = new Properties();
			appConfigProperties.load(input);
			log.info("Succesfully retrieved properties from existing recruitment.ini");
		} catch (IOException e) {
			log.error("Could not load recruitment.ini!");
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean checkExists(Path p) {
		if (p.toFile().exists()) {
			log.info("Path " + p.toString() + " found!");
			return true;
		} else {
			log.error("Path " + p.toString() + " not found! Using default.");
			return false;
		}
	}

	private void createDirs(Path p) {
		log.info("No previous User Data found, creating new Directories.");
		p.toFile().mkdirs();
	}

	private void createIniFile(Path p) {
		Properties props = new Properties();
		props.setProperty("app.version", appVersion);
		props.setProperty("data.rootdir", p.toFile().toString());
		props.setProperty("data.inifile", Paths.get(p.toString(), INI_FILE).toFile().toString());
		props.setProperty("data.eventdir", Paths.get(p.toFile().toString(), "events").toString());
		props.setProperty("recruitment.config", Paths.get(p.toString(), PROPS_FILE).toFile().toString());
		
		//Create the XML dir if it doesnt exist.
		if (!checkExists(Paths.get(props.getProperty("data.eventdir"))))
			createDirs(Paths.get(props.getProperty("data.eventdir")));
		
		log.info("Default properties are set.");
		storeProperties(props, Paths.get(props.getProperty("data.rootdir"), INI_FILE));
		log.info("Default properties saved to " + props.getProperty("data.inifile"));
	}
	
	private void createPropertiesFile(Path target) {
		Properties recProps = new Properties ();
//		recProps.setProperty("recruitment.fields.en", "FirstName,LastName,EmailAddress,PhoneNumber,Study,GraduationDate,InterestedIn,Region,PrefStartDate,CareerLevel,Specialism,Branch,Role,Comments");
//		recProps.setProperty("recruitment.fields.nl", "Voornaam,Achternaam,E-mailadres,Telefoonnummer,Studie,Afstudeerdatum,Interesse In,Regio,Startdatum,Carriere Niveau,Specialisme,Branche,Role,Commentaar");
		recProps.setProperty("recruitment.values.opleidingsniveau", "HBO,WO,PhD,Anders,Test");
		recProps.setProperty("recruitment.values.regio", "Noord,Oost,Randstad,Zuid,Landelijk");
		recProps.setProperty("recruitment.values.interesse", "Baan,Afstudeerstage,Samenwerking,Oplossing,Anders,Nvt");
		recProps.setProperty("recruitment.values.vaardigheden", "Java,PHP,.Net");
		recProps.setProperty("recruitment.values.werkveld", "Utilities,Goverment,Banking,Transport");
		recProps.setProperty("recruitment.values.ervaring", "Student,Young Professional,Medior Professional,Senior Professional");
		recProps.setProperty("recruitment.values.rol", "Afstudeeropdracht,Technical Software Engineer,Software Developer,Business");
		
		storeProperties(recProps, Paths.get(target.toFile().toString(), PROPS_FILE));
	}
	
	private void storeProperties(Properties props, Path target) {
		try (OutputStream out = new FileOutputStream(target.toFile())) {
			props.store(out, "");
		} catch (IOException e) {
			log.error("Could not write the properties file!");
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Bean(name="AppVersion")
	public String getAppVersion() {
		return this.appVersion;
	}

	@Bean(name="AppProperties")
	public Properties getApplicationProperties () {
		return this.appConfigProperties;
	}

	public void updateAppConfigProperties() {
		storeProperties(this.appConfigProperties,Paths.get(appConfigProperties.getProperty("data.rootdir"), INI_FILE));
		log.info("Updated Properties");
	}
}
