package com.qa.api.geminiapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoogleGeminiPromptTest extends BaseTest{	

	@Test
	public void GoogleGeminiApiKey() {
		String requestBody = "{\r\n"
				+ "    \"contents\": [\r\n"
				+ "      {\r\n"
				+ "        \"parts\": [\r\n"
				+ "          {\r\n"
				+ "            \"text\": \"Explain how AI works\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }";
		ConfigManager.set("apikey", "AIzaSyCTJihMNUssEYSeikgA5cydA-dxo-FHOck");
		
	Response response =	 restclient.post(BASE_URL_GoogleGemini, GoogleGemini_URLS_ENDPOINT, null, null, 
			requestBody , AuthType.API_KEY, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);
	}
}
