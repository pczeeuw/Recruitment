package com.cgi.recruitment.util.converters;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.domain.FxPerson;

public class PersonConverter {

	public static Person convertToPerson(FxPerson fxP) {
		Person p = new Person ();
		p.setFirstName(fxP.getFirstName());
		p.setLastName(fxP.getLastName());
		p.setEmailAddress(fxP.getEmailAddress());
		p.setPhoneNumber(fxP.getPhoneNumber());
		p.setStudy(fxP.getStudy());
		p.setGraduationDate(fxP.getGraduationDate());
		p.setInterestedIn(fxP.getInterestedIn());
		p.setRegion(fxP.getRegion());
		p.setPrefStartDate(fxP.getWorkStartDate());
		p.setComments(fxP.getComments());
		return p;
	}
	
	public static FxPerson convertToFxPerson (Person p) {
		FxPerson fxP = new FxPerson ();
		fxP.setFirstName(p.getFirstName());
		fxP.setLastName(p.getLastName());
		fxP.setEmailAddress(p.getEmailAddress());
		fxP.setPhoneNumber(p.getPhoneNumber());
		fxP.setStudy(p.getStudy());
		fxP.setGraduationDate(p.getGraduationDate());
		fxP.setInterestedIn(p.getInterestedIn());
		fxP.setRegion(p.getRegion());
		fxP.setWorkStartDate(p.getPrefStartDate());
		fxP.setComments(p.getComments());
		return fxP;
	}
	
	public static FxPerson convertsToFxPerson (Person p) {
		return null;
	}
}
