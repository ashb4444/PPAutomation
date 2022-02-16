package com.primepenguin.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAPIUtil {
	
	static {
		RestAssured.baseURI=ConfigurationManager.getInstance().getConfig().getServiceAPIURL();
		RestAssuredConfig.config().redirect(RestAssuredConfig.config().getRedirectConfig().followRedirects(false));
	}
	
	public static Response postRequest(String URL, String requestBody) {
						
		Response response = RestAssured.given()
			          		.headers("Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
			          		.body(requestBody)
			          		.post(URL);
		
		return response;
	}
	
	public static Response postRequest(String accessToken, String URL, String requestBody) {
		
		Response response = RestAssured.given()
							.headers("Authorization","Bearer " + accessToken,"Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
			          		.body(requestBody)
			          		.post(URL);
		
		return response;
	}
	
	public static Response putRequest(String accessToken, String URL, String requestBody) {
		
		Response response = RestAssured.given()
							.headers("Authorization","Bearer " + accessToken,"Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
			          		.body(requestBody)
			          		.put(URL);
		
		return response;
	}
	
	public static Response sendRequest(String accessToken, String URL, Map<String,Object> queryParametersMap) {
		
		Response response = RestAssured.given()
			          		.headers("Authorization","Bearer " + accessToken,"Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
			          		.queryParams(queryParametersMap).when()
			          		.get(URL);
		
		return response;
	}

	
	public static String postRequest(String URL, Map<String,Object> queryParametersMap) {
		
		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjIwNiIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJhZG1pbiIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiNk5GTjMzSEpINUpaWTZVQzVWSFA3V083VEJFRkNVUFEiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQWRtaW4iLCJMb2dpc3RpY1Byb3ZpZGVyQWRtaW4iXSwiaHR0cDovL3d3dy5hc3BuZXRib2lsZXJwbGF0ZS5jb20vaWRlbnRpdHkvY2xhaW1zL3RlbmFudElkIjoiMTQwIiwic3ViIjoiMjA2IiwianRpIjoiZTUzNGJkZTUtMTZkZi00MTkwLWEwNzEtNzc2YTUxYWZjNDllIiwiaWF0IjoxNTg2NTM1MDk2LCJ0b2tlbl92YWxpZGl0eV9rZXkiOiI3ZjUwY2M3Ny1lM2I0LTQ1YzMtODk0Ny1mMjcwYzRlZmE2MDIiLCJ1c2VyX2lkZW50aWZpZXIiOiIyMDZAMTQwIiwidG9rZW5fdHlwZSI6IjAiLCJuYmYiOjE1ODY1MzUwOTYsImV4cCI6MTU4NjYyMTQ5NiwiaXNzIjoiVGVjaG5vbG9neSIsImF1ZCI6IlRlY2hub2xvZ3kifQ.wNaj4eGXsG0Rx3ev_U9RiNjAP6HGqvea1BAaBT3owDA";
		
		Response response =
			      RestAssured.given()
			          .headers(
			              "Authorization",
			              "Bearer " + token,
			              "Content-Type",
			              ContentType.JSON,
			              "Accept",
			              ContentType.JSON)
			          .queryParams(queryParametersMap).when()
			          .get(URL);
		
		System.out.println(response.asString());
		
		return response.asString();
	}
	
	public static Response getToken(String URL) {
				
		//RestAssured.baseURI=ConfigurationManager.getInstance().getConfig().getServiceAPIURL();
		String body="{\r\n" + 
				"  \"userNameOrEmailAddress\": \"admin\",\r\n" + 
				"  \"password\": \"lpp\",\r\n" + 
				"  \"tenantId\": 140,\r\n" + 
				"  \"twoFactorVerificationCode\": \"string\",\r\n" + 
				"  \"rememberClient\": true,\r\n" + 
				"  \"twoFactorRememberClientToken\": \"string\",\r\n" + 
				"  \"singleSignIn\": true,\r\n" + 
				"  \"returnUrl\": \"string\",\r\n" + 
				"  \"captchaResponse\": \"string\"\r\n" + 
				"}";
				Response response =
					      RestAssured.given()
					          .headers(
					              "Content-Type",
					              ContentType.JSON,
					              "Accept",
					              ContentType.JSON)
					          .body(body)
					          .post(URL);
				
				return response;
	}
	
	public static List<String> getPropertyValuesList(String propertyName, Response response){
		
		return response.jsonPath().getList(propertyName);
	}
	
	public static void main(String[] args) {
		
		Map<String,Object> queryParametersMap = new HashMap<String, Object>();
		queryParametersMap.put("MergeBundleLineItems", false);
		queryParametersMap.put("Id", 239457);
		queryParametersMap.put("IncludeChild", true);
		
		postRequest("https://service.primepenguin.com/api/services/app/LogisticProviderOrders/GetById", queryParametersMap);
	}
}
