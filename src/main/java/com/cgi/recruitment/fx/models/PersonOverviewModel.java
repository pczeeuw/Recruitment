package com.cgi.recruitment.fx.models;

import org.springframework.stereotype.Component;

import com.cgi.recruitment.util.converters.DateConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class PersonOverviewModel {
	
	private ObservableList<FxPerson> personData;
	
	public PersonOverviewModel () {
		System.out.println("PersonOverviewModel created!");
		this.personData = FXCollections.observableArrayList();
	}
	
	public ObservableList<FxPerson> getPersonData () {
		return this.personData;
	}
	
	public void fillListWithTestData () {
        personData.add(new FxPerson("Hans", "Muster"));
        personData.add(new FxPerson("Ruth", "Mueller"));
        personData.add(new FxPerson("Heinz", "Kurz"));
        personData.add(new FxPerson("Cornelia", "Meier","cornelia.meier@gmail.com","0615847542","Biology",DateConverter.parse("01.01.2017"),"Tester","Amsterdam",DateConverter.parse("01.02.2017"),"none"));
        personData.add(new FxPerson("Werner", "Meyer"));
        personData.add(new FxPerson("Lydia", "Kunz"));
        personData.add(new FxPerson("Anna", "Best"));
        personData.add(new FxPerson("Stefan", "Meier"));
        personData.add(new FxPerson("Martin", "Mueller"));
	}
	

}
