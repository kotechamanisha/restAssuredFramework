package com.qa.api.products.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.pojo.Product.Rating;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllProducts extends BaseTest{
	
	@Test
	public void GetAllProducts() {
		Response response=   restclient.get(BASE_URL_FAKEPRODUCTS, FAKEPRODUCTS_URLS_ENDPOINT, null, null, AuthType.No_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		 Product[] products=  ObjectMapperUtil.deserialise(response, Product[].class);
		 for(Product product : products) {
			 System.out.println("Id : "  +product.getId());
			 System.out.println("Title : "  +product.getTitle());
			 System.out.println("Price : "  +product.getPrice());
			 System.out.println("Description : "  +product.getDescription());
			 System.out.println("Image : "  +product.getImage());
			 System.out.println("Rating : rate : "  +product.getRating().getRate() );
			 System.out.println("Rating : count : "  +product.getRating().getCount() );
			 System.out.println("---------------------");
		 }
	}
	

}
