package com.cgi.recruitment.domain;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Builder
@XmlRootElement
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
