package com.primepenguin.pagerepo;

public class PPShopifySaleChannelPageRepo extends SalesChannelsCommonRepo{

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String SHOPIFY_CONNECT_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SHOPIFY_CONNECT_BUTTON_LOCATOR_VALUE = "//a[@href='/app/main/integration/sales-channel/shopify/install']";
	public static final String SHOPIFY_SHOP_NAME_FIELD_LOCATOR_TYPE = "xpath";
	public static final String SHOPIFY_SHOP_NAME_FIELD_LOCATOR_VALUE = "//input[@placeholder='Shop Name']";
	public static final String INSTALL_APP_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String INSTALL_APP_BUTTON_LOCATOR_VALUE = "//button[text()='Install unlisted app']";
	public static final String DASHBOARD_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String DASHBOARD_BUTTON_LOCATOR_VALUE = "//h3[text()='Shopify']//following::a[text()='Dashboard']";
}
