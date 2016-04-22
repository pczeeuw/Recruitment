package com.cgi.recruitment.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.models.PersonOverviewModel;
import com.cgi.recruitment.util.PersonConverter;

@Component
public class PersonPersistService {
	
	@Autowired
	private PersonOverviewModel personModel;
	
	public void persistModel () {
		Person p = PersonConverter.convertToPerson(personModel.getPersonData().get(3));
		
		File file = new File("person.xml");
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = jaxb.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(p, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
