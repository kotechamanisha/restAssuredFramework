package com.qa.api.gorest.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	
//	@Test
//	private void createUserWithJson() {
//		
//		File userJson = new File("src/test/resources/jsons/User.json");
//	
//	Response postResponse =	restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, 
//			userJson, AuthType.BEARER_TOKEN, ContentType.JSON);
//	 
//	 assertEquals(postResponse.statusCode(), 201);
//	 System.out.println("name is " +postResponse.path("name") );
//}
	
	@Test
	public void deleteUser(){
		
		// create a user
		System.out.println("########################create a user############################");
		
		File UserFile = new File("src/test/resources/jsons/User.json");		
		
		Response response =  restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, UserFile, AuthType.BEARER_TOKEN, ContentType.JSON);
		String userId = response.jsonPath().getString("id");
		System.out.println("user id is " + userId);
		
		
		System.out.println("########################delete a user############################");
		Response deleteresponse = restclient.delete(BASE_URL_GOREST, GOREST_URLS_ENDPOINT + "/"+userId, AuthType.BEARER_TOKEN, ContentType.JSON);
		// deleteresponse.prettyPrint();
		System.out.println("status code is " + deleteresponse.statusCode());
		
		
		//restclient.delete(BASE_URL_GOREST, FAKEPRODUCTS_URLS_ENDPOINT, null, null)
		
	}

}
