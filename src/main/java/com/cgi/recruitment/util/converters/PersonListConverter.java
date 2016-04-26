package com.cgi.recruitment.util.converters;

import java.util.HashSet;
import java.util.Set;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.models.FxPerson;

import javafx.collections.ObservableList;

public class PersonListConverter {
	
	public static Set<Person> convertToPersonSet(ObservableList<FxPerson> fxList) {
		Set<Person> personList = new HashSet<>();
		for (FxPerson fxPer : fxList) {
			
			personList.add(PersonConverter.convertToPerson(fxPer));			
		}
		return personList;
	}

}
