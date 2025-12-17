package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import static org.hamcrest.Matchers.*;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient {
	
	//define Response specs :
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204Or404 = expect().statusCode(anyOf(equalTo(204), equalTo(404)));
	private ResponseSpecification responseSpec201Or200 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
			
	
	private RequestSpecification setupRequest(String baseUrl, AuthType authtype,ContentType contentType ) {
		 RequestSpecification request =   RestAssured.given().log().all()
		.baseUri(baseUrl)
		.contentType(contentType)
		.accept(contentType);
		 
		 switch (authtype) {
		case BEARER_TOKEN:						
			request.header("Authorization" , "Bearer "+ConfigManager.get("bearerToken"));			
			break;
		case BASIC_AUTH:			
			request.header("Authorization" , "Basic "+ getBasicToken());			
			break;			
		case API_KEY:
			request.header("x-goog-api-key" , ConfigManager.get("apikey"));			
			break;
		case No_AUTH:
			System.out.println("Auth is not required....");
			break;			
		default:
			System.out.println("This auth is not supported...Please pass right authtype");
			throw new APIException("===========Invalid Authtype=========");
			
		}
		 
		 return request;
		 
	}
	
	private String getBasicToken() {
		String credentials = ConfigManager.get("basicauthusername")+":"+ConfigManager.get("basicauthpassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}
	
	
	
	private void applyParams(RequestSpecification request, Map<String, String> queryParams,
			Map<String, String> pathParams) {
		if(queryParams!=null)
		{
			request.queryParams(queryParams);
		}
		if(pathParams!=null)
		{
			request.pathParams(pathParams);
		}
		
	}

	/**
	 * this method is used to call GET APIs
	 * @param baseUrl
	 * @param endpoint
	 * @param queryParams
	 * @param pathParams
	 * @param authtype
	 * @param contentType
	 * @return it returns the GET API call response..
	 */
	public Response get(String baseUrl, String endpoint , 
			Map<String, String> queryParams,
			Map<String, String> pathParams, 
			AuthType authtype,
			ContentType contentType) {
		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);
		applyParams(request,queryParams,pathParams);
		
		Response response =    request.get(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	public <T> Response post(String baseUrl, String endpoint , 
			Map<String, String> queryParams,
			Map<String, String> pathParams, 
			T body,	AuthType authtype,
			ContentType contentType) {
		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);
		applyParams(request,queryParams,pathParams);
		request = request.body(body);		
		Response response =    request.post(endpoint).then().spec(responseSpec201).extract().response();
		response.prettyPrint();
		return response;		
	}
	
	public  Response post(String baseUrl, String endpoint , 
			Map<String, String> queryParams,
			Map<String, String> pathParams, 
			File file,	AuthType authtype,
			ContentType contentType) {
		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);		
		applyParams(request,queryParams,pathParams);		
		Response response =    request.body(file).post(endpoint).then().spec(responseSpec201Or200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public Response post(String baseUrl, String endpoint , 
			String grant_type,
			String client_id,
			String client_secret,
			ContentType contentType) {
		
		System.out.println("grant_type" + grant_type);
		System.out.println("client_id" + client_id);
		System.out.println("client_secret" + client_secret);
		
		Response response=   RestAssured.given().log().all()
		.contentType(contentType)
		.formParam("grant_type", grant_type)
		.formParam("client_id", client_id)
		.formParam("client_secret", client_secret)
		.urlEncodingEnabled(false)
		.when().log().all()
		.urlEncodingEnabled(false)
		.post(baseUrl+endpoint);
		
		response.prettyPrint();
		return response;		
	}
	
	public   <T>Response put(String baseUrl, String endpoint , 
			Map<String, String> queryParams,
			Map<String, String> pathParams,
			T body,	AuthType authtype,
			ContentType contentType) {
		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);
		applyParams(request,queryParams,pathParams);
		request = request.body(body);
		
		Response response =    request.put(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	public  <T>Response patch(String baseUrl, String endpoint , 
			Map<String, String> queryParams,
			Map<String, String> pathParams, 
			T body,	AuthType authtype,
			ContentType contentType) {		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);
		applyParams(request,queryParams,pathParams);
		request = request.body(body);		
		Response response =    request.patch(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	public  Response delete(String baseUrl, String endpoint , 
			AuthType authtype,
			ContentType contentType) {		
		RequestSpecification request=  setupRequest(baseUrl,authtype,contentType);
		Response response =    request.delete(endpoint).then().spec(responseSpec204Or404).extract().response();	
		return response;
		
	}

	
	
}
