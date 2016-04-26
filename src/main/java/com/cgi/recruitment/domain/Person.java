package com.cgi.recruitment.domain;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;


@XmlRootElement(name="person")
@XmlType(propOrder = {"firstName","lastName","emailAddress","phoneNumber","study","graduationDate","lookingFor","workingLocation","freePer","comments"})
@Data 
public class Person {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String study;
	private LocalDate graduationDate;
	private String lookingFor;
	private String workingLocation;
	private LocalDate freePer;
	private String comments;
}
