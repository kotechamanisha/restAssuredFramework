package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProductsWithJsonPathTest extends BaseTest {
	
	@Test
	public void getAllProductsTest() {
		 Response productResponse =  restclient.get(BASE_URL_FAKEPRODUCTS, FAKEPRODUCTS_URLS_ENDPOINT, null, null, AuthType.No_AUTH, ContentType.JSON);
	Assert.assertEquals(productResponse.statusCode(), 200);
	
//	ReadContext ctx=   JsonPath.parse(productResponse.getBody().asString());
//	 List<Integer> ids=   ctx.read("$..[?(@.price > 70)]['id']");
//	 System.out.println("list of ids : " +ids);
	
	List<Number> ids=  JsonPathValidatorUtil.readList(productResponse, "$..[?(@.price > 70)]['id']");
	System.out.println("list of ids : " +ids);
	
		
	List<Map<String,Object>> DataList = JsonPathValidatorUtil.readListOfMaps(productResponse, "$[?(@.price > 70)]['id','title','price']");
	System.out.println(DataList);
	
		
//	ReadContext ctx =  JsonPath.parse(productResponse.getBody().asString());
//	Number sumOfPrice =   ctx.read("sum($[?(@.price > 70)]['price'])");
//	System.out.println("sum of price which is greater than 70 is " + sumOfPrice);
	
	Number sumOfPrice = JsonPathValidatorUtil.read(productResponse, "sum($[?(@.price > 70)]['price'])");
	System.out.println("sum of price which is greater than 70 is " + sumOfPrice);
	
	}

	
}
