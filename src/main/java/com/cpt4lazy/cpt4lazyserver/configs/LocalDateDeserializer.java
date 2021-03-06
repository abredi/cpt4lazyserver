package com.cpt4lazy.cpt4lazyserver.configs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/***
 * Custom Deserialization for the LocalDate type
 * @author cmmap
 *
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
	private static final long serialVersionUID = 1L;

	protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return LocalDate.parse(parser.readValueAs(String.class), DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
