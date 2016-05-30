package com.cgi.recruitment.util.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pczee Helper class for String > Date and Date > String conversion
 *
 *
 */
@Slf4j
public class DateConverter {
	private static final String DATE_PATTERN = "dd-MM-yyyy";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate parse(String dateString) {
		if (dateString == null)
			return null;
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			log.error("Could not parse date in String: " + dateString);
			return null;
		}
	}
}
