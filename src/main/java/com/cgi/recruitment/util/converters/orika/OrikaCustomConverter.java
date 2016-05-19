package com.cgi.recruitment.util.converters.orika;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxPerson;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Class used for converting RecruitmentEvents and Persons.
 * Two Custom converters are used (LocalDate Converter and PersonListConverter).
 * 
 * Make sure that the (Fx)RecruitmentEvent and (Fx)Person classes have the same field names!
 * 
 * @author zeeuwp
 *
 */


public class OrikaCustomConverter {
	
	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private static MapperFacade mapper;
	static {
		mapperFactory.getConverterFactory().registerConverter(new OrikaLocalDateConverter());
		
		mapperFactory.getConverterFactory().registerConverter(new OrikaPersonListConverter ());
		
		
		mapperFactory.classMap(RecruitmentEvent.class, FxRecruitmentEvent.class)
		.byDefault()
		.register();
		
		mapperFactory.classMap(Person.class, FxPerson.class)
		.byDefault()
		.register();
		
		mapper = mapperFactory.getMapperFacade();
	}
	
	public static FxRecruitmentEvent convertToFx (RecruitmentEvent source) {
		return mapper.map(source, FxRecruitmentEvent.class);
	}
	
	public static RecruitmentEvent convertFromFx (FxRecruitmentEvent source) {
		return mapper.map(source, RecruitmentEvent.class);
	}
	
	

}
