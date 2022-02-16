package com.primepenguin.pagerepo;

public class ProductMappingPageRepo {

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE = "//button[contains(text(),'Default Mapping')]";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_DROP_DOWN_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_DROP_DOWN_LOCATOR_VALUE = "//span[text()='Default Mapping']//following::select[1]";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_VALUE_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_SALES_CHANNELS_VALUE_LOCATOR_VALUE = "//option[text()='ashish-eshop']";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_VALUE = "//span[text()='Default Mapping']//following::select[2]";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_VALUE_LOCATOR_TYPE = "xpath";
	public static final String DEFAULT_PRODUCT_MAPPING_LP_VALUE_LOCATOR_VALUE = "(//option[text()='ashish_logistics'])";
	public static final String SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE = "(//span[text()='Save'])[1]";
}
