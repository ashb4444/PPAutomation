package com.primepenguin.api;

import com.primepenguin.api.repo.TokenAuthAPIRepo;
import com.primepenguin.core.util.RestAPIUtil;

import io.restassured.response.Response;

public class TokenAuth {

	public Response authenticate(String userName, String password, int tenantId) {
		
		String requestBody ="{\r\n" + 
				"  \"userNameOrEmailAddress\": \""+userName+"\",\r\n" + 
				"  \"password\": \""+password+"\",\r\n" + 
				"  \"tenantId\": "+tenantId+",\r\n" + 
				"  \"twoFactorVerificationCode\": \"string\",\r\n" + 
				"  \"rememberClient\": true,\r\n" + 
				"  \"twoFactorRememberClientToken\": \"string\",\r\n" + 
				"  \"singleSignIn\": true,\r\n" + 
				"  \"returnUrl\": \"string\",\r\n" + 
				"  \"captchaResponse\": \"string\"\r\n" + 
				"}";
		
		return RestAPIUtil.postRequest(TokenAuthAPIRepo.AUTHENTICATE_API_URL, requestBody);
	}
}
