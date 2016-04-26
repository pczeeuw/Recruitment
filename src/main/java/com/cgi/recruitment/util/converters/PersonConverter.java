package com.cgi.recruitment.util.converters;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.models.FxPerson;

public class PersonConverter {

	public static Person convertToPerson(FxPerson fxP) {
		Person p = new Person ();
		p.setFirstName(fxP.getFirstName());
		p.setLastName(fxP.getLastName());
		p.setEmailAddress(fxP.getEmailAddress());
		p.setPhoneNumber(fxP.getPhoneNumber());
		p.setStudy(fxP.getStudy());
		p.setGraduationDate(fxP.getGraduationDate());
		p.setLookingFor(fxP.getLookingFor());
		p.setWorkingLocation(fxP.getWorkLocation());
		p.setFreePer(fxP.getWorkStartDate());
		p.setComments(fxP.getComments());
		return p;
	}
}
