package com.cgi.recruitment.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.models.FxRecruitmentEvent;
import com.cgi.recruitment.util.converters.EventConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonPersistService {
	
	@Autowired
	private Properties AppProperties;
		
	public void persistModel (FxRecruitmentEvent fxRecruitmentEvent) {
		RecruitmentEvent recruitmentEvent = EventConverter.convertToRecruitmentEvent(fxRecruitmentEvent);
		
		log.info("Persisting event " + recruitmentEvent.getEventName());
		log.info("Filename is " + recruitmentEvent.getFileName());
		
		Path path = Paths.get(AppProperties.getProperty("data.eventdir"),recruitmentEvent.getFileName());
		try {
			JAXBContext jaxb = JAXBContext.newInstance(RecruitmentEvent.class);
			Marshaller marshaller = jaxb.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(recruitmentEvent, path.toFile());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
