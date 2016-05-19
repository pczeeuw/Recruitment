package com.cgi.recruitment.util.converters.orika;

import java.time.LocalDate;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Class used for Orika (since it cannot map LocalDate without a converter).
 * 
 * @author zeeuwp
 *
 */
public class OrikaLocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {

	@Override
	public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType) {
		return (source);
	}

	@Override
	public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType) {
		return (source);
	}

}
