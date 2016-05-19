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
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.util.converters.orika.OrikaCustomConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventPersistService {
	
	@Autowired
	@Qualifier("AppProperties")
	private Properties appProperties;
		
	public void persistEvent (FxRecruitmentEvent fxRecruitmentEvent) {
		RecruitmentEvent recruitmentEvent = OrikaCustomConverter.convertFromFx(fxRecruitmentEvent);
		
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
	
//	public List<RecruitmentEvent> getEventList () {
//		List<RecruitmentEvent> result = new ArrayList<>();
//		
//		for (String fileName : getEventFileNames()) {
//			
//		}
//		
//		return result;
//	}
	
	public List<String> getEventFileNames () {
		log.info("Retrieving excisting Recruitment Event XML files");
		ArrayList<String> fileNames = new ArrayList<>();
				
		Path dir = Paths.get(appProperties.getProperty("data.eventdir"));
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path entry : stream) {
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
	
	public FxRecruitmentEvent getPersistedEvent (String fileName) {
		Path path = Paths.get(appProperties.getProperty("data.eventdir"),fileName);
		
		try {
			
			JAXBContext jaxb = JAXBContext.newInstance(RecruitmentEvent.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			return OrikaCustomConverter.convertToFx(((RecruitmentEvent) unmarshaller.unmarshal(path.toFile())) );
		} catch (JAXBException e) {
			log.error("Failed to load from File!");
			e.printStackTrace();
			return null;
		}
	}

}
