package com.qa.api.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.*;
import com.qa.api.exceptions.APIException;

import io.restassured.response.Response;

public class ObjectMapperUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T>T deserialise(Response response, Class<T>targetclass) {
		
		try {
			 return mapper.readValue(response.getBody().asString(), targetclass);
		} catch (Exception e) {
			throw new APIException("Deserialization is failed " + targetclass.getName());
		}
		
		
		
	}

	
}
