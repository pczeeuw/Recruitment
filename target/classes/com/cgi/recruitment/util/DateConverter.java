package com.cgi.recruitment.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * @author pczee Helper class for String > Date and Date > String conversion
 *
 *
 */
public class DateConverter {
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			System.err.println("Could not format dateString: " + dateString);
			return null;
		}
	}
}
