package com.cgi.recruitment.domain;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.StringProperty;
import lombok.Data;


@XmlRootElement(name="person")
@XmlType(propOrder = {"firstName","lastName","emailAddress","phoneNumber","study","graduationDate","educationLevel","interestedIn","region","prefStartDate","comments","careerLevel","specialism","branch","role","spokenWith","commentsCGI","newsLetter","applyDate","eventName","eventLocation","eventDate"})

@Data 
public class Person {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String study;
	private LocalDate graduationDate;
	private String educationLevel;
	private String interestedIn;
	private String region;
	private LocalDate prefStartDate;	
	private String careerLevel;
	private String specialism;
	private String branch;
	private String role;
	private String comments;
	private String spokenWith;
	private String commentsCGI;
	private String newsLetter;
	private LocalDate applyDate;
	private String eventName;
	private String eventLocation;
	private LocalDate eventDate;
}
