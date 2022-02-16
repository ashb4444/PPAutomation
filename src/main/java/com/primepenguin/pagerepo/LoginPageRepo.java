package com.primepenguin.pagerepo;

public class LoginPageRepo {

	// ------------ OBJECTS LOCATORS -----------------------
	public static final String COMPANY_NAME_LOCATOR_TYPE = "xpath";
	public static final String COMPANY_NAME_LOCATOR_VALUE = "//span[text()=' Not selected ']";
	public static final String CHANGE_COMPANY_NAME_LOCATOR_TYPE = "xpath";
	public static final String CHANGE_COMPANY_NAME_LOCATOR_VALUE = "//a[text()='Change']";
	public static final String SWITCH_TO_COMPANY_LOCATOR_TYPE = "xpath";
	public static final String SWITCH_TO_COMPANY_LOCATOR_VALUE = "(//label[text()='Switch to company']//following::label)[1]";
	public static final String TENANCYNAME_FIELD_LOCATOR_TYPE = "id";
	public static final String TENANCYNAME_FIELD_LOCATOR_VALUE = "tenancyNameInput";
	public static final String SWITCH_TO_TENANT_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SWITCH_TO_TENANT_BUTTON_LOCATOR_VALUE = "//span[text()='Switch to the tenant']";
	public static final String SWITCH_TO_HOST_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String SWITCH_TO_HOST_BUTTON_LOCATOR_VALUE = "//span[text()='Switch to the host']";
	public static final String USERNAME_FIELD_LOCATOR_TYPE = "xpath";
	public static final String USERNAME_FIELD_LOCATOR_VALUE = "//input[@name='userNameOrEmailAddress']";
	public static final String PASSWORD_FIELD_LOCATOR_TYPE = "xpath";
	public static final String PASSWORD_FIELD_LOCATOR_VALUE = "//input[@name='password']";
	public static final String LOGIN_BUTTON_LOCATOR_TYPE = "xpath";
	public static final String LOGIN_BUTTON_LOCATOR_VALUE = "//button[text()='Log in']";
	public static final String LOGGED_IN_ESHOP_USER_NAME_TYPE = "xpath";
	public static final String LOGGED_IN_ESHOP_USER_NAME_VALUE = "//span[@class='tenancy-name ng-star-inserted']";
}
