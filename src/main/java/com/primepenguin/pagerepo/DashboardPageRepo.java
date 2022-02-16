package com.primepenguin.pagerepo;

public class DashboardPageRepo {

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String WELCOME_ADMIN_CLOSE_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String WELCOME_ADMIN_CLOSE_BUTTON_LOCATOR_VALUE = "(//span[text()='1']//following-sibling::button)[1]";
	public static final String INTEGRATION_MENU_OPTION_LOCATOR_TYPE = "xpath";
	public static final String INTEGRATION_MENU_OPTION_LOCATOR_VALUE = "//span[contains(text(),'Integration')]";
	public static final String SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_TYPE = "xpath";
	public static final String SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_VALUE = "//span[contains(text(),'Sales Channels')]";
	public static final String PRODUCT_MAPPING_SUB_MENU_OPTION_LOCATOR_TYPE = "xpath";
	public static final String PRODUCT_MAPPING_SUB_MENU_OPTION_LOCATOR_VALUE = "//span[contains(text(),'Product Mapping')]";
	public static final String DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE = "//button[contains(text(),'Default Product Mapping')]";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_DROP_DOWN_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_DROP_DOWN_LOCATOR_VALUE = "//span[text()='Default Product Mapping']//following::select[1]";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_VALUE_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_VALUE_LOCATOR_VALUE = "//option[text()='ashish-eshop']";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_VALUE = "//span[text()='Default Product Mapping']//following::select[1]";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_VALUE_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_LOCATOR_VALUE = "(//option[text()='ashish_logistics'])[1]";
	public static final String SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE = "(//option[text()='ashish_logistics'])[1]";
}
