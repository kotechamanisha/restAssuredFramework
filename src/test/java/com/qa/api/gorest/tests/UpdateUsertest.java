package com.qa.api.gorest.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUsertest extends BaseTest{
	int UserId;
	
	
	
	@Test
	private void UpdateUserWithPojo() {	
	
		UserLombok user =    UserLombok.builder()
		.name("manisha_builderpattern")
		.email(StringUtils.getRandomemailId())
		.status("active")
		.gender("female")
		.build();
					
				
	Response postResponse =	restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, 
				user, AuthType.BEARER_TOKEN, ContentType.JSON);	
	UserId = postResponse.path("id");
	System.out.println(UserId);		 
	
	
	user.setStatus("inactive");		
	user.setName("manisha_builderpattern_updated");	
	Response patchResponse =	restclient.patch(BASE_URL_GOREST, GOREST_URLS_ENDPOINT+"/"+UserId, null, null, 
				user, AuthType.BEARER_TOKEN, ContentType.JSON);	
	Assert.assertEquals(patchResponse.jsonPath().getString("status"), user.getStatus());
	Assert.assertEquals(patchResponse.jsonPath().getString("name"), user.getName());
	}
	
	@Test
	private void UpdateUserWithPut() {	
	
		UserLombok user =    UserLombok.builder()
		.name("manisha_builderpatternwith put")
		.email(StringUtils.getRandomemailId())
		.status("active")
		.gender("female")
		.build();
					
				
	Response postResponse =	restclient.post(BASE_URL_GOREST, GOREST_URLS_ENDPOINT, null, null, 
				user, AuthType.BEARER_TOKEN, ContentType.JSON);	
	UserId = postResponse.body().path("id");
	// UserId = postResponse.path("id");
	System.out.println(UserId);		 
	
	
	user.setStatus("inactive");		
	user.setName("manisha_builderpattern with put_updated");	
	Response patchResponse =	restclient.put(BASE_URL_GOREST, GOREST_URLS_ENDPOINT+"/"+UserId, null, null, 
				user, AuthType.BEARER_TOKEN, ContentType.JSON);	
	Assert.assertEquals(patchResponse.jsonPath().getString("status"), user.getStatus());
	Assert.assertEquals(patchResponse.jsonPath().getString("name"), user.getName());
	}
		
		

}
