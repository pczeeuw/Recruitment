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
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author zeeuwp
 *
 */

@Slf4j
@Component
public class AppConfiguration {
	private static final String SYSTEM_OS = System.getProperty("os.name").toLowerCase();
	private static final String PROPS_FILE = "recruitment.ini";

	private boolean isWindows;
	private boolean isUnix;

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
		Path appdataPath = Paths.get(System.getenv("APPDATA"));
		checkExists(appdataPath);

		appdataPath = Paths.get(appdataPath.toString(), "recruitment");
		if (!checkExists(appdataPath))
			createDirs(appdataPath);

		if (!checkExists(Paths.get(appdataPath.toString(), PROPS_FILE)))
			createIniFile(appdataPath);

		processIniFile(appdataPath);
	}

	private void processIniFile(Path p) {
		log.info("Attempting to read from existing recruitment.ini file");
		try (InputStream input = new FileInputStream(Paths.get(p.toString(), PROPS_FILE).toFile())) {
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
		props.setProperty("data.inifile", Paths.get(p.toString(), PROPS_FILE).toFile().toString());
		props.setProperty("data.eventdir", Paths.get(p.toFile().toString(), "events").toString());
		
		//Create the XML dir if it doesnt exist.
		if (!checkExists(Paths.get(props.getProperty("data.eventdir"))))
			createDirs(Paths.get(props.getProperty("data.eventdir")));
		
		log.info("Default properties are set.");
		storeProperties(props);
		log.info("Default properties saved to " + props.getProperty("data.inifile"));

	}

	private void storeProperties(Properties props) {
		try (OutputStream out = new FileOutputStream(
				Paths.get(props.getProperty("data.rootdir"), PROPS_FILE).toFile())) {
			props.store(out, "");
		} catch (IOException e) {
			log.error("Could not write recruitment.ini!");
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
		storeProperties(this.appConfigProperties);
		log.info("Updated Properties");
	}

}
