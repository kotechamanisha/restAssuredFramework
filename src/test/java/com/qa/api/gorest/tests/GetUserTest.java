package com.qa.api.gorest.tests;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
	
	@Test
	public void getAllUsersTest() {
		Response response = restclient.get(BASE_URL_GOREST , GOREST_URLS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		// return response;
				
	}
	
	@Test
	public void getAllUsersWithQueryParamTest() {
		
		Map<String,String> queryparam = new HashMap<String, String>();
		queryparam.put("gender","female");
		queryparam.put("status", "active");
		
		Response rs= restclient.get(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, queryparam, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		rs.prettyPrint();
		assertEquals(rs.statusCode(),200);
		Assert.assertTrue(rs.statusLine().contains("OK"));
	}
	
	@Test
	public void getSingleUserTest() {
		
	String userId = "8263962";
		
		Response rs= restclient.get(BASE_URL_GOREST, GOREST_URLS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		rs.prettyPrint();
		assertEquals(rs.statusCode(),200);
		Assert.assertTrue(rs.statusLine().contains("OK"));
	}
	

}
