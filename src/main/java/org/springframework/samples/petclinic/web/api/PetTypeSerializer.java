package org.springframework.samples.petclinic.web.api;

import java.io.IOException;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class PetTypeSerializer extends JsonSerializer<PetType> {

	@Override
	public void serialize(PetType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.getName());		
	}

}
