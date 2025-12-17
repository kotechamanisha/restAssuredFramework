package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.qameta.allure.restassured.AllureRestAssured;


public class BaseTest{
	protected static String BASE_URL_GOREST;
	
	
	//******************* API BAse URLS***************************//
	// protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	protected final static String BASE_URL_FAKEPRODUCTS = "https://fakestoreapi.com";
	protected final static String BASE_URL_FOR_AMADEOUS_TOKEN = "https://test.api.amadeus.com";
	protected final static String BASE_URL_AMADEOUS_DATA = "https://test.api.amadeus.com";
	protected final static String BASE_URL_HEROKUAPP = "https://the-internet.herokuapp.com/";
	protected final static String BASE_URL_GoogleGemini = "https://generativelanguage.googleapis.com";
	
	
	
	//******************* API endpoints***************************//
		protected final static String GOREST_URLS_ENDPOINT = "/public/v2/users";
		protected final static String FAKEPRODUCTS_URLS_ENDPOINT = "/products";
		protected final static String AMADEOUS_TOKEN_URLS_ENDPOINT = "/v1/security/oauth2/token";
		protected final static String AMADEOUS_DATA_URLS_ENDPOINT = "/v2/reference-data/urls/checkin-links";
		protected final static String HEROKUAPP_URLS_ENDPOINT = "/basic_auth";
		protected final static String GoogleGemini_URLS_ENDPOINT = "/v1beta/models/gemini-2.0-flash:generateContent";
	
	protected RestClient restclient;
	
	@BeforeTest
	public void Setup() {
		restclient = new RestClient();		
		
	}
	@BeforeSuite
	public void initSetup() {
		RestAssured.filters(new AllureRestAssured());
		BASE_URL_GOREST = ConfigManager.get("gorest.baseuri");

	}
	
	
}


