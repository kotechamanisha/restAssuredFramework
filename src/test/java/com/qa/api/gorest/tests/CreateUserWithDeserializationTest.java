package com.qa.api.gorest.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.ObjectMapperUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithDeserializationTest extends BaseTest{
	
	@BeforeClass
	private void setToken() {
		ConfigManager.set("bearerToken","db8dc08fc593f37f5dfc8eb98e7710c113ba6b5796c46b0ab4fbcbca59e06416");
	}

	@Test
	public void createUser() {
		
		UserLombok user = new UserLombok(null, "Deserialization test", StringUtils.getRandomemailId() , "female", "active");
		
		Response createUserResponse =  restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT,  null, null,user, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		int UserId = createUserResponse.jsonPath().getInt("id");
			UserLombok createdUser = ObjectMapperUtil.deserialise(createUserResponse, UserLombok.class);
			Assert.assertEquals(createdUser.getId(), UserId);
			Assert.assertEquals(createdUser.getName(), user.getName());
			Assert.assertEquals(createdUser.getEmail(), user.getEmail());
			Assert.assertEquals(createdUser.getGender(), user.getGender());
			Assert.assertEquals(createdUser.getStatus(), user.getStatus());
		// System.out.println("name of user is " +createdUser.getName());
		 // System.out.println("email of user is " + createdUser.getEmail());
		
	
		
	}
}
