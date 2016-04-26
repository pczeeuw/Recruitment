package com.cgi.recruitment.services.adapters;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.cgi.recruitment.util.converters.DateConverter;

public class LocalDateAdapter extends XmlAdapter <String, LocalDate> {

	@Override
	public String marshal(LocalDate localDate) throws Exception {
		
		return DateConverter.format(localDate);
	}

	@Override
	public LocalDate unmarshal(String date) throws Exception {
		return DateConverter.parse(date);
	}

}
