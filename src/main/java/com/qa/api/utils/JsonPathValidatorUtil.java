package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	private static String getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}
	
	
	public static <T> List <T> readList(Response response, String queryPath) {
		ReadContext ctx = JsonPath.parse(getJsonResponseAsString(response));
		 return ctx.read(queryPath);				
	}
	
	public static <T> List<Map<String, T>> readListOfMaps(Response response, String queryPath) {
		ReadContext ctx = JsonPath.parse(getJsonResponseAsString(response));
		List<Map<String,T>> DataList = ctx.read(queryPath);
		 return DataList;				
	}
	
//	ReadContext ctx =  JsonPath.parse(productResponse.getBody().asString());
//	Number sumOfPrice =   ctx.read("sum($[?(@.price > 70)]['price'])");
//	System.out.println("sum of price which is greater than 70 is " + sumOfPrice);
	
	public static <T>T read(Response response, String querypath) {
		 ReadContext ctx= JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(querypath);
		
	}

}

