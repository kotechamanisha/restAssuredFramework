package com.qa.api.amadeousAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getAccessTokenAmadeous extends BaseTest{
	
	@Test
	private void getAccessTOken() {
		
		Response response=    restclient.post(BASE_URL_FOR_AMADEOUS_TOKEN, AMADEOUS_TOKEN_URLS_ENDPOINT, 
				ConfigManager.get("grant_type_Amadeous"), 
				ConfigManager.get("clicentId_Amadeous"), 
				ConfigManager.get("client_secret_Amadeous"), ContentType.URLENC);
		response.prettyPrint();
		//response.jsonPath().toString("access_token");
		String access_token=  response.jsonPath().getString("access_token");
		System.out.println(access_token);
		
		ConfigManager.set("bearerToken", access_token);
		
		
		Map<String, String> airlinelink =  new HashMap<String, String>();
		airlinelink.put("airlineCode", "IB");
		
		
	Response dataResponse=	 restclient.get(BASE_URL_AMADEOUS_DATA, AMADEOUS_DATA_URLS_ENDPOINT, airlinelink, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	String dataid = dataResponse.jsonPath().getString("data.id");
	Assert.assertEquals(dataResponse.statusCode(), 200);
	Assert.assertEquals(dataid, "[IBEN-GBAll]");
	
		
	}
	
	
	
	
	
	
	
	

}
