package com.cgi.recruitment.util.converters;

import java.util.HashSet;
import java.util.Set;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.domain.FxPerson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonListConverter {
	
	public static Set<Person> convertToPersonSet(ObservableList<FxPerson> fxList) {
		Set<Person> personList = new HashSet<>();
		for (FxPerson fxPer : fxList) {
			
			personList.add(PersonConverter.convertToPerson(fxPer));			
		}
		return personList;
	}
	
	public static ObservableList<FxPerson> convertToPersonObservableList (Set<Person> personSet) {
		ObservableList<FxPerson> result = FXCollections.observableArrayList();
		
		for (Person person : personSet) {
			result.add(PersonConverter.convertToFxPerson(person));
		}
		
		return result;
	}

}
