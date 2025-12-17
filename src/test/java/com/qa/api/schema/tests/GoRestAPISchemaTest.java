package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestAPISchemaTest extends BaseTest {

	@BeforeClass
	private void setToken() {
		ConfigManager.set("bearerToken", "db8dc08fc593f37f5dfc8eb98e7710c113ba6b5796c46b0ab4fbcbca59e06416");
	}

	@Test
	public void getUserSchemaTest() {
		Response response = restclient.get(BASE_URL_GOREST, GOREST_URLS_ENDPOINT + "/8283154", null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);

		Assert.assertTrue(SchemaValidator.validateSchema(response, "user.json"));
	}

	@Test
	public void CreateUserSchemaTest() {


//		UserLombok user = new UserLombok("manisha", "test@gmail111.com", "female", "active");

		UserLombok user = UserLombok.builder()
				.name("manisha")
				.email("manisha108123@gmail.com")
				.status("active")
				.gender("female").build();

		Response createUserRespose = restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, user,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		createUserRespose.prettyPrint();
		Assert.assertEquals(createUserRespose.statusCode(), 201);
		Assert.assertTrue(SchemaValidator.validateSchema(createUserRespose, "user.json"));
	}

}
