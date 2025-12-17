package com.qa.api.gorest.tests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.CSVReaderUtil;
import com.qa.api.utils.ExcelUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	@BeforeClass
	private void pub() {
		ConfigManager.set("bearerToken", "db8dc08fc593f37f5dfc8eb98e7710c113ba6b5796c46b0ab4fbcbca59e06416");
	}

	public String getRandomemailId() {
		String emailId = "manisha" + System.currentTimeMillis() + "@mkt.com";
		return emailId;
	}

	@DataProvider
	public Object[][] UserTestingData() {
		return new Object[][] { 
			{ "manisha", "female", "active" }, 
			{ "joshna", "female", "Inactive" },
			{ "rahul", "male", "active" } };
	}
	@DataProvider
	public Object[][] getUserCSVData() {
		return CSVReaderUtil.CSVTestingData("GoRestAPIUserData.csv");		
	}
	
	@DataProvider
	public Object[][] getUseExcelVData() {
		return ExcelUtils.readExcelData("GoRestUserTestData.xlsx");		
	}

	
	@Test(dataProvider = "getUseExcelVData")
		private void createUserWithPojo(String name, String gender, String status) {
		// User user = new User("manisha_pojo",getRandomemailId(), "female", "active");
		
		
		UserLombok user = UserLombok.builder().id(null).name(name).email(getRandomemailId()).gender(gender)
				.status(status).build();

		Response postResponse = restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, user,
				AuthType.BEARER_TOKEN, ContentType.JSON);

		Assert.assertEquals(postResponse.statusCode(), 201);
		System.out.println("name is " + postResponse.path("name"));
		System.out.println("userId is " + postResponse.path("id"));
		System.out.println("--------------------------");

	}

	@Test
	private void createUserWithJson() {
		File userJson = new File("src/test/resources/jsons/User.json");
		Response postResponse = restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, userJson,
				AuthType.BEARER_TOKEN, ContentType.JSON);

		Assert.assertEquals(postResponse.statusCode(), 201);
		System.out.println("name is " + postResponse.path("name"));
		System.out.println("--------------------------");
	}

}
