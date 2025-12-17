package com.qa.api.utils;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;

public class SchemaValidator {

	public static boolean validateSchema(Response response, String SchemafileName) {
		try {
			response.then().assertThat().body(matchesJsonSchemaInClasspath(SchemafileName));
			System.out.println("Schema got validated for file " + SchemafileName);
			return true;
		} catch (Exception e) {
			System.out.println("Schema validation got fail for file " + SchemafileName);
			return false;
		}
	
	}

}
