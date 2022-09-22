package org.springframework.samples.petclinic.web.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.GenericIdToEntityConverter;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class BaseEntitySerializer<T extends BaseEntity> extends JsonSerializer<T> {

	@Autowired
	GenericIdToEntityConverter converter;
	
	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeNumber(value.getId());		
	}

}
