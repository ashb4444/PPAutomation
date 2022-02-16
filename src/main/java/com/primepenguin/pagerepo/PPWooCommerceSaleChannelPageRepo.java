package com.primepenguin.pagerepo;

public class PPWooCommerceSaleChannelPageRepo extends SalesChannelsCommonRepo{

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String WOOCOMMERCE_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String WOOCOMMERCE_BUTTON_LOCATOR_VALUE = "(//h3[text()='Woocommerce']//following::a)[1]";
	public static final String WOOCOMMERCE_CONNECT_BUTTON_LOCATOR_TYPE = "id";
	public static final String WOOCOMMERCE_CONNECT_BUTTON_LOCATOR_VALUE = "woocommerce-connect-button";
	public static final String WOOCOMMERCE_SHOP_NAME_FIELD_LOCATOR_TYPE = "xpath";
	public static final String WOOCOMMERCE_SHOP_NAME_FIELD_LOCATOR_VALUE = "//input[@placeholder='Shop Name']";
	public static final String WOOCOMMERCE_CONSUMER_KEY_FIELD_LOCATOR_TYPE = "xpath";
	public static final String WOOCOMMERCE_CONSUMER_KEY_FIELD_LOCATOR_VALUE = "//input[@placeholder='Consumer Key']";
	public static final String WOOCOMMERCE_CONSUMER_SECRET_FIELD_LOCATOR_TYPE = "xpath";
	public static final String WOOCOMMERCE_CONSUMER_SECRET_FIELD_LOCATOR_VALUE = "//input[@placeholder='Consumer Secret']";
	public static final String DASHBOARD_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String DASHBOARD_BUTTON_LOCATOR_VALUE = "//h3[text()='Woocommerce']//following::a[text()=' Dashboard']";
}
