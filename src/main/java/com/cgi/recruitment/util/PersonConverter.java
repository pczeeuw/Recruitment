package com.cgi.recruitment.util;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.models.FxPerson;

public class PersonConverter {

	public static Person convertToPerson(FxPerson fxPerson) {
		Person p = Person.builder()
				.firstName(fxPerson.getFirstName())
				.lastName(fxPerson.getLastName())
				.emailAddress(fxPerson.getEmailAddress())
				.phoneNumber(fxPerson.getPhoneNumber())
				.study(fxPerson.getStudy())
				.graduationDate(fxPerson.getGraduationDate())
				.lookingFor(fxPerson.getLookingFor())
				.workingLocation(fxPerson.getWorkLocation())
				.freePer(fxPerson.getWorkStartDate())
				.comments(fxPerson.getComments())
				.build();
		return p;
	}
}
