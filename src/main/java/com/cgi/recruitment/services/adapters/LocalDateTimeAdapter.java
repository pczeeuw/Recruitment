package com.cgi.recruitment.services.adapters;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.cgi.recruitment.util.converters.DateTimeConverter;

public class LocalDateTimeAdapter extends XmlAdapter <String, LocalDateTime> {

	@Override
	public String marshal(LocalDateTime localDate) throws Exception {
		
		return DateTimeConverter.format(localDate);
	}

	@Override
	public LocalDateTime unmarshal(String date) throws Exception {
		return DateTimeConverter.parse(date);
	}
}