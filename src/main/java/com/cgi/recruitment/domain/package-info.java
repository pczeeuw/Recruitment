/**
* Defines custom Adapters for JaxB
*/
@XmlJavaTypeAdapters({
	@XmlJavaTypeAdapter(type=LocalDate.class,value=com.cgi.recruitment.services.adapters.LocalDateAdapter.class),
	@XmlJavaTypeAdapter(type=LocalDateTime.class,value=com.cgi.recruitment.services.adapters.LocalDateTimeAdapter.class)
	})
package com.cgi.recruitment.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
