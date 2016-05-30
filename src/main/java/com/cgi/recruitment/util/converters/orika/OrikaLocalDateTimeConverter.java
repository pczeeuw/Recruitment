package com.cgi.recruitment.util.converters.orika;

import java.time.LocalDateTime;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OrikaLocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {

	@Override
	public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType) {
		return (source);
	}

	@Override
	public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType) {
		return (source);
	}

}
