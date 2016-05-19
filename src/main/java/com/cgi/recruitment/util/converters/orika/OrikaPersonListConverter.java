package com.cgi.recruitment.util.converters.orika;

import java.util.Set;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.fx.domain.FxPerson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

public class OrikaPersonListConverter extends BidirectionalConverter<ObservableList <FxPerson>,Set<Person>> {

	@Override
	public Set<Person> convertTo(ObservableList<FxPerson> source, Type<Set<Person>> destinationType) {
		
		return mapperFacade.mapAsSet(source, TypeFactory.valueOf(FxPerson.class),TypeFactory.valueOf(Person.class));
	}

	@Override
	public ObservableList<FxPerson> convertFrom(Set<Person> source, Type<ObservableList<FxPerson>> destinationType) {
		// TODO Auto-generated method stub
		ObservableList<FxPerson> list = FXCollections.observableArrayList();
		list.addAll(mapperFacade.mapAsList(source, TypeFactory.valueOf(Person.class),TypeFactory.valueOf(FxPerson.class)));
		return list;
	}

}
