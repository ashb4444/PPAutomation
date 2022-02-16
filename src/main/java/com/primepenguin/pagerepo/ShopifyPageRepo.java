package com.primepenguin.pagerepo;

public class ShopifyPageRepo {

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String LABEL_STORE_ADDRESS_LOCATOR_TYPE = "id";
	public static final String LABEL_STORE_ADDRESS_LOCATOR_VALUE = "//label[text()='Store address']";
	public static final String STORE_ADDRESS_FIELD_LOCATOR_TYPE = "id";
	public static final String STORE_ADDRESS_FIELD_LOCATOR_VALUE = "shop_domain";
	public static final String NEXT_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String NEXT_BUTTON_LOCATOR_VALUE = "//button[text()='Next']";
	public static final String EMAIL_FIELD_LOCATOR_TYPE = "id";
	public static final String EMAIL_FIELD_LOCATOR_VALUE = "account_email";
	public static final String PASSWORD_FIELD_LOCATOR_TYPE = "id";
	public static final String PASSWORD_FIELD_LOCATOR_VALUE = "account_password";
	public static final String LOGIN_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String LOGIN_BUTTON_LOCATOR_VALUE = "//button[text()='Log in']";
	public static final String HOME_MENU_LOCATOR_TYPE = "xpath";
	public static final String HOME_MENU_LOCATOR_VALUE = "//span[text()='Home']";
	public static final String ORDERS_MENU_LOCATOR_TYPE = "xpath";
	public static final String ORDERS_MENU_LOCATOR_VALUE = "//span[text()='Orders']";
	
	// ------------ CREATE ORDER-----------------------
	public static final String CREATE_ORDER_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String CREATE_ORDER_BUTTON_LOCATOR_VALUE = "//span[text()='Create order']";
	public static final String BROWSE_PRODUCT_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String BROWSE_PRODUCT_BUTTON_LOCATOR_VALUE = "//button[text()='Browse products']";
	public static final String SEARCH_PRODUCT_FIELD_LOCATOR_TYPE = "id";
	public static final String SEARCH_PRODUCT_FIELD_LOCATOR_VALUE = "product_search_input";
	public static final String SELECT_SEARCHED_PRODUCT_LOCATOR_TYPE = "xpath";
	public static final String SELECT_SEARCHED_PRODUCT_LOCATOR_VALUE = "(//div[@class='ui-stack-item ui-stack-item--fill'])[2]";
	public static final String SIMPLE_PRODUCT_INVENTORY_LOCATOR_TYPE = "xpath";
	public static final String SIMPLE_PRODUCT_INVENTORY_LOCATOR_VALUE = "//input[contains(@id,'product_checkbox')]//following::div[5]";
	public static final String VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_TYPE = "xpath";
	public static final String VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_VALUE = "//input[contains(@id,'variant_checkbox')]";
	public static final String VARIABLE_PRODUCT_INVENTORY_LOCATOR_TYPE = "xpath";
	public static final String VARIABLE_PRODUCT_INVENTORY_LOCATOR_VALUE = "//input[contains(@id,'variant_checkbox')]//following::div[5]";
	public static final String NO_RESULT_FOUND_LOCATOR_TYPE = "xpath";
	public static final String NO_RESULT_FOUND_LOCATOR_VALUE = "//span[contains(text(),'No results')]";
	public static final String ADD_TO_ORDER_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String ADD_TO_ORDER_BUTTON_LOCATOR_VALUE = "//button[text()='Add to order']";
	public static final String SEARCH_CUSTOMERS_FIELD_LOCATOR_TYPE = "xpath";
	public static final String SEARCH_CUSTOMERS_FIELD_LOCATOR_VALUE = "//input[@placeholder='Search customers']";
	public static final String SEARCHED_CUSTOMER_LOCATOR_TYPE = "xpath";
	public static final String getSearchedCustomerLocatorValue(String customerEmail) {
		return "//p[contains(text(),'"+customerEmail+"')]";
	}
	public static final String MARK_AS_PAID_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String MARK_AS_PAID_BUTTON_LOCATOR_VALUE = "//button[text()='Mark as paid']";
	public static final String FINAL_CREATE_ORDER_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String FINAL_CREATE_ORDER_BUTTON_LOCATOR_VALUE = "//button[text()='Create order']";
	
	// ------------GENERATED ORDER INFORMATION-----------------------
	public static final String GENERATED_ORDER_NUMBER_LOCATOR_TYPE = "xpath";
	public static final String GENERATED_ORDER_NUMBER_LOCATOR_VALUE = "(//span[text()='Orders']//following::h1)[1]";
	
	
	
}
