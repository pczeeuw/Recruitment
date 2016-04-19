package com.cgi.recruitment.util;

public class PhoneNumberConverter {
	
	/**
	 * 
	 * @param input
	 * @return formatted phone number as String (for example: 31612345678)
	 */
	public static String formatPhoneNumber (String input) {
		input = input.replaceAll("[\\D]", "");
		return input;
	}

}
