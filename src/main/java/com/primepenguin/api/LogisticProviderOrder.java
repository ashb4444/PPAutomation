package com.primepenguin.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.primepenguin.api.repo.LogisticProviderOrderAPIRepo;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.RestAPIUtil;

import io.restassured.response.Response;

public class LogisticProviderOrder {

	private static Logger logger = Logger.getLogger(LogisticProviderOrder.class);
	
	/**
	 * Method - getSingleOrderById
	 * In this method, first creating a map and then putting the API parameters in this
	 * and then sending the request using the RestAPIUtil sendRequest method
	 * @param accessToken
	 * @param mergeBundleLineItems
	 * @param orderId
	 * @param includeChild
	 * @return
	 * 
	 */
	public Response getSingleOrderById(String accessToken, boolean mergeBundleLineItems, long orderId, boolean includeChild) {
		
		logger.info("Inside the method getSingleOrderById()");
		Map<String,Object> queryParametersMap = new HashMap<String, Object>();
		queryParametersMap.put("MergeBundleLineItems", mergeBundleLineItems);
		queryParametersMap.put("Id", orderId);
		queryParametersMap.put("IncludeChild", includeChild);

		return RestAPIUtil.sendRequest(accessToken,LogisticProviderOrderAPIRepo.GETBYID_API_URL, queryParametersMap);
	}
	
	/**
	 * Method - createOrderFulfillment
	 * In this method, first getting the logistic provider Id from the configuration file
	 * and then sending the request using the RestAPIUtil postRequest method
	 * @param accessToken
	 * @param orderId
	 * @return
	 */
	public Response createOrderFulfillment(String accessToken, long orderId) {
		
		logger.info("Inside the method createOrderFulfillment()");
		int logisticProviderId = ConfigurationManager.getInstance().getConfig().getLpId();
		
		String requestBody="{\r\n" + 
				"  \"orderId\": "+orderId+",\r\n" + 
				"  \"logisticProviderId\": "+logisticProviderId+",\r\n" + 
				"  \"trackingCompany\": \"dhl\",\r\n" + 
				"  \"trackingNumber\": \"121\",\r\n" + 
				"  \"trackingUrl\": \"www.dhl.com/121\",\r\n" + 
				"  \"openNowAndFulfillLater\": true,\r\n" + 
				"  \"lineItem\": [\r\n" + 

				"  ],\r\n" + 
				"  \"notifyCustomer\": true\r\n" + 
				"}";
		
		logger.info("******Create Order fulfillment API request body is: " + requestBody);
		return RestAPIUtil.postRequest(accessToken,LogisticProviderOrderAPIRepo.FULFILLMENT_API_URL,requestBody);
	}
	
	/**
	 * Method - updateOrderFulfillmentStatus
	 * In this method, sending the request using the RestAPIUtil putRequest method
	 * @param accessToken
	 * @param orderId
	 * @param fulfillmentId
	 * @param status
	 * @return
	 */
	public Response updateOrderFulfillmentStatus(String accessToken, long orderId, String status) {
		
		logger.info("Inside the method updateOrderFulfillmentStatus()");
		String requestBody="{\r\n" + 
				"  \"orderId\": "+orderId+",\r\n" +  
				"  \"status\": \""+status+"\",\r\n" + 
				"  \"notifyCustomer\": true,\r\n" + 
				"  \"tracking\": {\r\n" + 
				"    \"trackingCompany\": \"dhl\",\r\n" + 
				"    \"trackingNumber\": \"121\",\r\n" + 
				"    \"trackingUrl\": \"www.dhl.com/121\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		logger.info("******Update order fulfillment status API request body is: " + requestBody);
		return RestAPIUtil.putRequest(accessToken,LogisticProviderOrderAPIRepo.FULFILLMENTSTATUS_API_URL,requestBody);
	}
	
	/**
	 * Method - createOrderRefund
	 * In this method, sending the request using the RestAPIUtil postRequest method
	 * @param accessToken
	 * @param orderId
	 * @return
	 */
	public Response createOrderRefund(String accessToken, long orderId) {
		
		logger.info("Inside the method createOrderRefund()");
		String requestBody="{\r\n" + 
				"  \"orderId\": "+orderId+",\r\n" + 
				"  \"restock\": true,\r\n" + 
				"  \"notify\": true,\r\n" + 
				"  \"note\": \"automation test\",\r\n" + 
				"  \"warehouseStatus\": \"refunded\",\r\n" + 
				"  \"shipping\": {\r\n" + 
				"    \"fullRefund\": true,\r\n" + 
				"    \"amount\": 0\r\n" + 
				"  },\r\n" + 
				"  \"refundLineItems\": [\r\n" + 
				"   \r\n" + 
				"  ]\r\n" + 
				"}";
		
		logger.info("******Create Refund Order API request body is: " + requestBody);
		return RestAPIUtil.postRequest(accessToken,LogisticProviderOrderAPIRepo.CREATE_REFUND_API_URL,requestBody);
	}
}
