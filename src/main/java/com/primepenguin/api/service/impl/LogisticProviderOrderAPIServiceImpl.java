package com.primepenguin.api.service.impl;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.api.LogisticProviderOrder;
import com.primepenguin.api.TokenAuth;
import com.primepenguin.api.repo.StatusCode;
import com.primepenguin.api.service.ILogisticProviderOrderAPIService;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;

import io.restassured.response.Response;

public class LogisticProviderOrderAPIServiceImpl implements ILogisticProviderOrderAPIService {
	
	private String accessToken;
	//private Long fulfillmentId;
	private LogisticProviderOrder logisticProviderOrder = new LogisticProviderOrder();
	private static Logger logger = Logger.getLogger(LogisticProviderOrderAPIServiceImpl.class);

	public void getAccessToken() {
		
		logger.info("Inside the method getAccessToken()");
		TokenAuth tokenAuth = new TokenAuth();
		String lpUserName = ConfigurationManager.getInstance().getConfig().getLpUserName();
		String lpPassword = ConfigurationManager.getInstance().getConfig().getLpPassword();
		int lpTenantId = ConfigurationManager.getInstance().getConfig().getLpTenantId();
		Response response = tokenAuth.authenticate(lpUserName, lpPassword, lpTenantId);
		logger.info("Authentication API returned: " + response.getStatusCode());
		if (response.getStatusCode()==StatusCode.SUCCESS.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, "Successfully executed Authenticate API");
			accessToken = response.jsonPath().getMap("result").get("accessToken").toString();
		}else {
			ExtentTestManager.getTest().log(Status.FAIL, "Error in executing Authenticate API");
			Assert.fail();
		}
	}
	
	public void createOrderFulfillment(long orderId, String[] inprogressStatuses) {
		
		Response response = logisticProviderOrder.createOrderFulfillment(accessToken, orderId);
		System.out.println("****Create Order Fulfillment API response body is: " + response.asString());
		ExtentTestManager.getTest().log(Status.INFO, "Create order Fulfillment API response is : " + response.getStatusCode());
		if (response.getStatusCode()==StatusCode.SUCCESS.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, "Successfully executed create order Fulfillment API");
			String status = response.jsonPath().getMap("result").get("status").toString();
			if(Arrays.asList(inprogressStatuses).stream().anyMatch(status::equalsIgnoreCase)) {
				ExtentTestManager.getTest().log(Status.PASS, "API returned inprogress status");
				//fulfillmentId = Long.parseLong(response.jsonPath().getMap("result").get("id").toString());
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "API returned invalid status");
				Assert.fail();
			}
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Error in executing create order fulfillment API");
			Assert.fail();
		}
	}
	
	public void updateOrderFulfillmentStatus(long orderId, String[] fulfilledStatuses) {
		
		logger.info("Inside the method updateOrderFulfillmentStatus()");
		Response response = logisticProviderOrder.updateOrderFulfillmentStatus(accessToken, orderId, "Fulfilled");
		logger.info("****Update Order Fulfillment Satus API response body is: " + response.asString());
		ExtentTestManager.getTest().log(Status.INFO, "Update order Fulfillment satus API response is : " + response.getStatusCode());
		if (response.getStatusCode()==StatusCode.SUCCESS.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, "Successfully executed update order Fulfillment status API");
			String status = response.jsonPath().getMap("result").get("status").toString();
			if(Arrays.asList(fulfilledStatuses).stream().anyMatch(status::equalsIgnoreCase)) {
				ExtentTestManager.getTest().log(Status.PASS, "API returned fulfilled status");
			}else {
				ExtentTestManager.getTest().log(Status.FAIL, "API returned invalid status");
				Assert.fail();
			}
		}else {
			ExtentTestManager.getTest().log(Status.FAIL, "Error in executing update order Fulfillment status API");
			Assert.fail();
		}
	}
	
	public void createOrderRefund(long orderId) {
		
		Response response = logisticProviderOrder.createOrderRefund(accessToken, orderId);
		System.out.println("****Create Order Refund API response body is: " + response.asString());
		ExtentTestManager.getTest().log(Status.INFO, "Create order Refund API response is : " + response.getStatusCode());
		if (response.getStatusCode()==StatusCode.SUCCESS.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, "Successfully executed create order Refund API");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Error in executing create order Rulfillment API");
			Assert.fail();
		}
	}
}
