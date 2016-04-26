package com.cgi.recruitment.services;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.models.FxRecruitmentEvent;
import com.cgi.recruitment.util.converters.EventConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventPersistService {
	
	@Autowired
	@Qualifier("AppProperties")
	private Properties appProperties;
		
	public void persistModel (FxRecruitmentEvent fxRecruitmentEvent) {
		RecruitmentEvent recruitmentEvent = EventConverter.convertToRecruitmentEvent(fxRecruitmentEvent);
		
		log.info("Persisting event " + recruitmentEvent.getEventName());
		log.info("Filename is " + recruitmentEvent.getFileName());
		
		Path path = Paths.get(appProperties.getProperty("data.eventdir"),recruitmentEvent.getFileName());
		try {
			JAXBContext jaxb = JAXBContext.newInstance(RecruitmentEvent.class);
			Marshaller marshaller = jaxb.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(recruitmentEvent, path.toFile());
		} catch (JAXBException e) {
			log.error("Failed to persist event " + fxRecruitmentEvent.getEventName() + " to " + recruitmentEvent.getFileName());
			e.printStackTrace();
		}		
	}
	
	public List<String> getEventFileNames () {
		log.info("Retrieving excisting Recruitment Event XML files");
		ArrayList<String> fileNames = new ArrayList<>();
		
		
		Path dir = Paths.get(appProperties.getProperty("data.eventdir"));
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path entry : stream) {
				log.info("Iterating trough path: " + entry.getFileName());
				if (entry.toString().endsWith(".xml")) {
					fileNames.add(entry.getFileName().toString());
					log.info("Found file " + entry.getFileName().toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileNames;
		
	}

}
