package com.primepenguin.pagerepo;

public class SalesChannelsCommonRepo {

	public static final String SALES_CHANNEL_MENU_ITEM_LOCATOR_TYPE = "xpath";
	public static final String SALES_CHANNEL_MENU_ITEM_LOCATOR_VALUE = "//span[text()='Sales Channels']";
	public static final String LOADER_LOCATOR_VALUE = "//div[@id='lottie-preloader-container']//div[@id='lottie-preloader-screen']//*[name()='svg']";
	public static final String SAVE_SALES_CHANNEL_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SAVE_SALES_CHANNEL_BUTTON_LOCATOR_VALUE = "(//span[text()='Save'])[1]";
	public static final String VALID_SKU_YES_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String VALID_SKU_YES_BUTTON_LOCATOR_VALUE = "//button[text()='Yes']";
	public static final String ORDER_DETAILS_LINK_LOCATOR_TYPE = "xpath";
	public static final String ORDER_DETAILS_LINK_LOCATOR_VALUE = "//a[contains(@href,'order-list')]";
	public static final String SEARCH_ORDER_FIELD_LOCATOR_TYPE = "name";
	public static final String SEARCH_ORDER_FIELD_LOCATOR_VALUE = "filterText";
	public static final String GRID_TOTAL_RECORD_FIELD_LOCATOR_TYPE = "xpath";
	public static final String GRID_TOTAL_RECORD_FIELD_LOCATOR_VALUE = "//span[contains(@class,'total-records-count')]";
	public static final String ORDER_STATUS_COLUMN_VALUE_LOCATOR_TYPE = "xpath";
	public static final String ORDER_STATUS_COLUMN_VALUE_LOCATOR_VALUE = "//span[text()='Sales Channel Status']//following::span[1]";
	public static final String ORDER_ID_COLUMN_VALUE_LOCATOR_TYPE = "xpath";
	public static final String ORDER_ID_COLUMN_VALUE_LOCATOR_VALUE = "(//th[contains(text(),'Id')]//following::td)[2]";
	public static final String FULFILLMENTS_COLUMN_VALUE_LOCATOR_TYPE = "xpath";
	public static final String FULFILLMENTS_COLUMN_VALUE_LOCATOR_VALUE = "//span[text()='Fulfillments']//following-sibling::div[1]";
	public static final String REFUNDS_COLUMN_VALUE_LOCATOR_TYPE = "xpath";
	public static final String REFUNDS_COLUMN_VALUE_LOCATOR_VALUE = "//span[text()='Fulfillments']//following-sibling::div[1]";

	public static final String BACK_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String BACK_BUTTON_LOCATOR_VALUE = "//a[contains(text(),'Back')]";
	public static final String MANAGE_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String MANAGE_BUTTON_LOCATOR_VALUE = "//button[contains(text(),'Manage')]";
	public static final String SYNC_INTEGRATION_OPTION_LOCATOR_TYPE = "xpath";
	public static final String SYNC_INTEGRATION_OPTION_LOCATOR_VALUE = "//a[contains(text(),'Sync Integration')]";
	public static final String CONFIRM_SYNC_INTEGRATION_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String CONFIRM_SYNC_INTEGRATION_BUTTON_LOCATOR_VALUE = "//button[text()='Yes']";
	public static final String OK_SYNC_INTEGRATION_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String OK_SYNC_INTEGRATION_BUTTON_LOCATOR_VALUE = "//button[text()='Ok']";
	public static final String DEACTIVATE_INTEGRATION_OPTION_LOCATOR_TYPE = "xpath";
	public static final String DEACTIVATE_INTEGRATION_OPTION_LOCATOR_VALUE = "//a[contains(text(),'Deactivate Integration')]";
	public static final String CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_VALUE = "//button[text()='Yes']";
	
	public static final String SUCCESS_NOTIFICATION_LOCATOR_TYPE = "xpath";
	public static final String SUCCESS_NOTIFICATION_LOCATOR_VALUE = "//span[text()='Saved successfully.']";
}
