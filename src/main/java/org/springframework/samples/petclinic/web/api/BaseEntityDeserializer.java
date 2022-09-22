package org.springframework.samples.petclinic.web.api;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.GenericIdToEntityConverter;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@Component
public class BaseEntityDeserializer<T extends BaseEntity> extends JsonDeserializer<T> implements ContextualDeserializer {

	private Class<?> targetClass;
	
	@Autowired
	GenericIdToEntityConverter converter;
	
	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {		
		int id=p.getIntValue();
		return (T)converter.convert(id,targetClass);
	}

	@Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property){
        //Find here the targetClass to be deserialized  
        String targetClassName=ctxt.getContextualType().toCanonical();
        try {
            targetClass = Class.forName(targetClassName);
        } catch (ClassNotFoundException e) {            
            e.printStackTrace();
        }
        return this;
    }

}
