package com.cgi.recruitment.util.vallidators;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cgi.recruitment.util.converters.DateConverter;
import com.cgi.recruitment.util.converters.PhoneNumberConverter;

public class PersonValidator {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,})$";
	
	public static boolean validateEmailAddress (String emailAddress) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}
	
	public static boolean validateNotEmpty (String input) {
		return input.length() > 0;
	}
	
	public static boolean validateLocalDate (LocalDate input) {
		if (DateConverter.format(input)== null) 
			return false;
		else
			return true;
	}
	
	public static boolean validatePhoneNumber (String input) {
		String phone = PhoneNumberConverter.formatPhoneNumber(input);
		if (phone.length() < 9 || phone.length() > 12)
			return false;
		else 
			return true;
	}

}
