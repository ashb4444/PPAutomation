package com.primepenguin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {

	@JsonProperty
    private  String applicationURL;
	@JsonProperty
    private  String serviceAPIURL;
	@JsonProperty
    private  String eshopTenancyName;
	@JsonProperty
	private  String eshopUserName;
	@JsonProperty
	private  String eshopPassword;
	@JsonProperty
	private  String lpTenancyName;
	@JsonProperty
	private  String lpUserName;
	@JsonProperty
	private  String lpPassword;
	@JsonProperty
	private  int lpTenantId;
	@JsonProperty
	private  int lpId;
	
	@JsonProperty
	private  String shopifyURL;
	@JsonProperty
	private  String shopifyStroreAddress;
	@JsonProperty
	private  String shopifyEmail;
	@JsonProperty
	private  String shopifyPassword;
	@JsonProperty
	private  String shopifyVariableProductSKU;
	@JsonProperty
	private  String shopifySimpleProductSKU;
	@JsonProperty
	private  String shopifyCustomerEmail;
	
	@JsonProperty
	private  String wooCommerceShopURL;
	@JsonProperty
	private  String wooCommerceConsumerKey;
	@JsonProperty
	private  String wooCommerceConsumerSecret;
	@JsonProperty
	private  String wooCommerceLoginURL;
	@JsonProperty
	private  String wooCommerceEmail;
	@JsonProperty
	private  String wooCommercePassword;
	@JsonProperty
	private  String wooCommerceLoggedInUserName;
	@JsonProperty
	private  String wooCommerceSimpleProductSKU;
	@JsonProperty
	private  String wooCommerceCustomerEmail;
	
	public String getApplicationURL() {
		return applicationURL;
	}
	public String getServiceAPIURL() {
		return serviceAPIURL;
	}
	public String getEshopTenancyName() {
		return eshopTenancyName;
	}
	public String getEshopUserName() {
		return eshopUserName;
	}
	public String getEshopPassword() {
		return eshopPassword;
	}
	public String getLpTenancyName() {
		return lpTenancyName;
	}
	public String getLpUserName() {
		return lpUserName;
	}
	public String getLpPassword() {
		return lpPassword;
	}
	public int getLpTenantId() {
		return lpTenantId;
	}
	public int getLpId() {
		return lpId;
	}
	public String getShopifyURL() {
		return shopifyURL;
	}
	public String getShopifyStroreAddress() {
		return shopifyStroreAddress;
	}
	public String getShopifyEmail() {
		return shopifyEmail;
	}
	public String getShopifyPassword() {
		return shopifyPassword;
	}
	public String getShopifyVariableProductSKU() {
		return shopifyVariableProductSKU;
	}
	public String getShopifySimpleProductSKU() {
		return shopifySimpleProductSKU;
	}
	public String getShopifyCustomerEmail() {
		return shopifyCustomerEmail;
	}

	public String getWooCommerceShopURL() {
		return wooCommerceShopURL;
	}
	public String getWooCommerceConsumerKey() {
		return wooCommerceConsumerKey;
	}
	public String getWooCommerceConsumerSecret() {
		return wooCommerceConsumerSecret;
	}
	public String getWooCommerceLoginURL() {
		return wooCommerceLoginURL;
	}
	public String getWooCommerceEmail() {
		return wooCommerceEmail;
	}
	public String getWooCommercePassword() {
		return wooCommercePassword;
	}
	public String getWooCommerceLoggedInUserName() {
		return wooCommerceLoggedInUserName;
	}
	public String getWooCommerceSimpleProductSKU() {
		return wooCommerceSimpleProductSKU;
	}
	public String getWooCommerceCustomerEmail() {
		return wooCommerceCustomerEmail;
	}
}
