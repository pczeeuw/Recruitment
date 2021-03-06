

package com.cgi.recruitment.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "event")
@XmlType(propOrder = {"eventName","eventLocation","eventDate","personList"})
public class RecruitmentEvent {
	
	private String eventName;
	private String eventLocation;
	private LocalDate eventDate;
	
	@XmlElementWrapper(name = "persons")
	@XmlElement (name = "person", type = Person.class)	
	private Set<Person> personList;
	
	public RecruitmentEvent () {
		this ("","",null);
	}
	
	public RecruitmentEvent (String eventName, String eventLocation, LocalDate eventDate) {
		this(eventName,eventLocation,eventDate,new HashSet<Person>() );
	}
	
	public RecruitmentEvent (String eventName, String eventLocation, LocalDate eventDate, Set<Person> personList) {
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.eventDate = eventDate;
		this.personList = personList;
	}
	
	public String getFileName () {
		return String.format("%3$s-%2$s-%1$s.xml", eventName,eventLocation,eventDate.toString());
	}
	
}
