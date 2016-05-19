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
	
	public static boolean validateNotRequired (String input) {
		if (input.isEmpty())
			return input.isEmpty();
		else
			return true;
	}
	
	/**
	 * Non-required field. If input is null, return true. If format fails, return false.
	 * @param input
	 * @return
	 */
	public static boolean validateLocalDate (LocalDate input) {
		if (input == null)
			return true;
		
		if (DateConverter.format(input)== null) 
			return false;
		else
			return true;
	}
	
	/**
	 * Phone number must contain either no digits (Not a required field) or more than 7 digits
	 * @param input
	 * @return
	 */
	public static boolean validatePhoneNumber (String input) {
		String phone = PhoneNumberConverter.formatPhoneNumber(input);
		if (phone.length() != 0 && phone.length() < 7 )
			return false;
		else 
			return true;
	}

}
