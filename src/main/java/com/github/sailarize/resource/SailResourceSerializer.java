package com.github.sailarize.resource;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SailResourceSerializer extends JsonSerializer<SailResource>{

	@Override
	public void serialize(SailResource resource, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		
		
		gen.writeStartObject();

		gen.writeFieldName("#links");
		gen.writeObject(resource.getLinks());
		
		if(!resource.getForms().isEmpty()){
			gen.writeFieldName("#forms");
			gen.writeObject(resource.getForms());
		}

		
		gen.writeEndObject();
	}

}
