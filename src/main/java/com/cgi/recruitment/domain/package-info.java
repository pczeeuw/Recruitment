/**
* documentation comments...
*/
@XmlJavaTypeAdapters({@XmlJavaTypeAdapter(type=LocalDate.class,value=com.cgi.recruitment.services.adapters.LocalDateAdapter.class)})
package com.cgi.recruitment.domain;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
