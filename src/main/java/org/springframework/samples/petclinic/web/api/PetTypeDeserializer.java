package org.springframework.samples.petclinic.web.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.web.PetTypeFormatter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;

@Component
public class PetTypeDeserializer extends JsonDeserializer<PetType> {

	@Autowired
	PetTypeFormatter formatter;
	
	@Override
	public PetType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		PetType result=null;
		try {
			result=formatter.parse(p.getText(), Locale.getDefault());
		} catch (Exception e) {			
			throw new IOException(e);
		} 
		return result;
	}

}
