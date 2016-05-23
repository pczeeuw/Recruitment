package com.cgi.recruitment.util.converters;

public class LastNameConverter {
	
	/**
	 * If a Last Name contains a prefix (Van, De, Van der, etc.) the name will be
	 * formatted. (de Boer -> Boer, de).
	 * 
	 * How to handle multiple last names?
	 * 
	 * @param input unformatted last name (eg. de Boer)
	 * @return formatted last name (eg. Boer, de)
	 */
	public static String convertLastName (String input) {
		String result;
		
		if (input.contains(" ") && !(input.contains(","))) {
			String lnArray[] = input.split("\\s+");
			result = lnArray[lnArray.length-1];
			result += ",";
			for (int i = 0 ; i < lnArray.length -1; i++) {
				result += " " + lnArray[i];
			}
		} else {
			result = input;
		}	
		return result;
	}
}
