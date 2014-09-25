package net.avaleo.hackathon;

import java.util.TimeZone;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Created by mst on 25.09.14.
 */
public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
	private static final long serialVersionUID = 1L;

	public ObjectMapper() {
		setTimeZone(TimeZone.getDefault());
		registerModule(new JodaModule());

		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}