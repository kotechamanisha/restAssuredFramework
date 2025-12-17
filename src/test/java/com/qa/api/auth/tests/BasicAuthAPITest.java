package com.qa.api.auth.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthAPITest extends BaseTest{
	
	@Test
	private void pub() {
	Response response = restclient.get(BASE_URL_HEROKUAPP, HEROKUAPP_URLS_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
	Assert.assertEquals(response.statusCode(), 200);
	Assert.assertEquals(response.getBody().asString().contains("Congratulations! You must have the proper credentials."), true);
	}

}
