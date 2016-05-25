package com.cgi.recruitment.util.converters;

public class CheckListConverter {
	
	/**
	 * Normalizes the result of AList.toString ()
	 * 
	 * @param input In array form [xxx,xxx,xxx,xxx]
	 * @return output normalized: xxx,xxx,xxx,xxx
	 */
	public static String normalizeArray (String input ) {
		return input.substring(1, input.length()-1);
	}

}
