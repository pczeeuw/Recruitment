package com.cgi.recruitment.util.converters;

import java.time.LocalDateTime;
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
public class DateTimeConverter {
	private static final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDateTime dateTime) {
		if (dateTime == null)
			return null;
		return DATE_FORMATTER.format(dateTime);
	}

	public static LocalDateTime parse(String dateString) {
		if (dateString == null)
			return null;
		try {
			return DATE_FORMATTER.parse(dateString, LocalDateTime::from);
		} catch (DateTimeParseException e) {
			log.error("Could not parse date in String: " + dateString);
			return null;
		}
	}
}
