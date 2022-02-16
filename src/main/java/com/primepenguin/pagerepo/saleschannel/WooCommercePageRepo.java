package com.primepenguin.pagerepo.saleschannel;

public class WooCommercePageRepo {

	// ------------ LOGIN -----------------------
	public static final String EMAIL_FIELD_LOCATOR_TYPE = "id";
	public static final String EMAIL_FIELD_LOCATOR_VALUE = "user_login";
	public static final String PASSWORD_FIELD_LOCATOR_TYPE = "id";
	public static final String PASSWORD_FIELD_LOCATOR_VALUE = "user_pass";
	public static final String LOGIN_BUTTON_LOCATOR_TYPE = "id";
	public static final String LOGIN_BUTTON_LOCATOR_VALUE = "wp-submit";
	
	// ------------ CREATE ORDER-----------------------
	public static final String WOOCOMMERCE_MENU_LOCATOR_TYPE = "xpath";
	public static final String WOOCOMMERCE_MENU_LOCATOR_VALUE = "//div[text()='WooCommerce']";
	public static final String ORDERS_MENU_LOCATOR_TYPE = "xpath";
	public static final String ORDERS_MENU_LOCATOR_VALUE = "//a[text()='Orders']";
	public static final String ADD_ORDER_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String ADD_ORDER_BUTTON_LOCATOR_VALUE = "//a[text()='Add order']";
	public static final String CUSTOMER_DROPDOWN_LOCATOR_TYPE = "id";
	public static final String CUSTOMER_DROPDOWN_LOCATOR_VALUE = "select2-customer_user-container";
	public static final String SEARCH_CUSTOMER_FIELD_LOCATOR_TYPE = "xpath";
	public static final String SEARCH_CUSTOMER_FIELD_LOCATOR_VALUE = "(//input[@class='select2-search__field'])[2]";
	public static final String ADD_ITEMS_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String ADD_ITEMS_BUTTON_LOCATOR_VALUE = "//button[text()='Add item(s)']";
	public static final String ADD_PRODUCTS_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String ADD_PRODUCTS_BUTTON_LOCATOR_VALUE = "//button[text()='Add product(s)']";
	public static final String SEARCH_PRODUCT_DROPDOWN_LOCATOR_TYPE = "xpath";
	public static final String SEARCH_PRODUCT_DROPDOWN_LOCATOR_VALUE = "//span[text()='Search for a product…']";//"(//span[@class='select2-selection__arrow'])[1]";
	public static final String SEARCH_PRODUCT_FIELD_LOCATOR_TYPE = "xpath";
	public static final String SEARCH_PRODUCT_FIELD_LOCATOR_VALUE = "(//input[@class='select2-search__field'])[2]";
	public static final String SEARCH_RECORD_LOCATOR_VALUE = "//li[contains(text(),'Searching.')]";
	public static final String ADD_SEARCHED_PRODUCT_LOCATOR_TYPE = "id";
	public static final String ADD_SEARCHED_PRODUCT_LOCATOR_VALUE = "btn-ok";
	public static final String CREATE_ORDER_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String CREATE_ORDER_BUTTON_LOCATOR_VALUE = "//button[text()='Create']";
	public static final String ORDER_UPDATED_NOTIFICATION_LOCATOR_TYPE = "xpath";
	public static final String ORDER_UPDATED_NOTIFICATION_LOCATOR_VALUE = "//p[text()='Order updated.']";
	
	// ------------GENERATED ORDER INFORMATION-----------------------
	public static final String GENERATED_ORDER_NUMBER_LOCATOR_TYPE = "xpath";
	public static final String GENERATED_ORDER_NUMBER_LOCATOR_VALUE = "//h2[@class='woocommerce-order-data__heading']";
}
