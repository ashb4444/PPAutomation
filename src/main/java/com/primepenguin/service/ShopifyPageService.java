package com.primepenguin.service;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.SeleniumUtil;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.ProductType;
import com.primepenguin.model.ShopifyOrder;
import com.primepenguin.pagerepo.ShopifyPageRepo;

public class ShopifyPageService {

	private WebDriver webDriver;
	private ShopifyOrder shopifyOrder;
	
	public ShopifyPageService(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void loginToShopify() {
		
		if(SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.STORE_ADDRESS_FIELD_LOCATOR_TYPE, ShopifyPageRepo.STORE_ADDRESS_FIELD_LOCATOR_VALUE).isDisplayed()) {
			
			ExtentTestManager.getTest().log(Status.PASS, "Successfully navigated to the Shopify Login page");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the Shopify store address");
			SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.STORE_ADDRESS_FIELD_LOCATOR_TYPE, ShopifyPageRepo.STORE_ADDRESS_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifyStroreAddress());
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the next button");
			SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.NEXT_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.NEXT_BUTTON_LOCATOR_VALUE).click();
			//SeleniumUtil.pauseExecution(4000);
			ExtentTestManager.getTest().log(Status.INFO, "Entering the email address");
			SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.EMAIL_FIELD_LOCATOR_TYPE, ShopifyPageRepo.EMAIL_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifyEmail());
			//SeleniumUtil.pauseExecution(4000);
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the next button");
			SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.NEXT_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.NEXT_BUTTON_LOCATOR_VALUE).click();
			//SeleniumUtil.pauseExecution(4000);
			ExtentTestManager.getTest().log(Status.INFO, "Entering the password");
			SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.PASSWORD_FIELD_LOCATOR_TYPE, ShopifyPageRepo.PASSWORD_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifyPassword());
			//SeleniumUtil.pauseExecution(4000);
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the login button");
			SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.LOGIN_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.LOGIN_BUTTON_LOCATOR_VALUE).click();
			if(SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.HOME_MENU_LOCATOR_TYPE, ShopifyPageRepo.HOME_MENU_LOCATOR_VALUE).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in to the shopify");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to log in to the Shopify");
				Assert.fail();
			}
		}else {
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to navigate to Shopify Login page");
			Assert.fail();
		}
	}
	
	public void createOrder(ProductType productType) {
		
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on orders menu");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.ORDERS_MENU_LOCATOR_TYPE, ShopifyPageRepo.ORDERS_MENU_LOCATOR_VALUE).click();
		SeleniumUtil.pauseExecution(4000);
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on created order button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.CREATE_ORDER_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.CREATE_ORDER_BUTTON_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on browse product button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.BROWSE_PRODUCT_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.BROWSE_PRODUCT_BUTTON_LOCATOR_VALUE).click();
		if(productType.equals(ProductType.SIMPLE)) {
			ExtentTestManager.getTest().log(Status.INFO, "Searching the product");
			SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_TYPE, ShopifyPageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifySimpleProductSKU());
			SeleniumUtil.pauseExecution(6000);
			//if(!SeleniumUtil.getWebElement(webDriver, ShopifyPageRepo.NO_RESULT_FOUND_LOCATOR_TYPE, ShopifyPageRepo.NO_RESULT_FOUND_LOCATOR_VALUE).isDisplayed()) {
				//if(SeleniumUtil.getWebElement(webDriver, ShopifyPageRepo.SIMPLE_PRODUCT_CHECK_BOX_LOCATOR_TYPE, ShopifyPageRepo.SIMPLE_PRODUCT_CHECK_BOX_LOCATOR_VALUE).isDisplayed()) {
					ExtentTestManager.getTest().log(Status.INFO, "Selecting the searched product");
					SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.SELECT_SEARCHED_PRODUCT_LOCATOR_TYPE, ShopifyPageRepo.SELECT_SEARCHED_PRODUCT_LOCATOR_VALUE).click();
				//}
			//}
		} else if(productType.equals(ProductType.VARIABLE)) {
			SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_TYPE, ShopifyPageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifySimpleProductSKU());
			if(!SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.NO_RESULT_FOUND_LOCATOR_TYPE, ShopifyPageRepo.NO_RESULT_FOUND_LOCATOR_VALUE).isDisplayed()) {
				if(SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_TYPE, ShopifyPageRepo.VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_VALUE).isDisplayed()) {
					SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_TYPE, ShopifyPageRepo.VARIABLE_PRODUCT_CHECK_BOX_LOCATOR_VALUE).click();
				}
			}
		}
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on add product to order button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.ADD_TO_ORDER_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.ADD_TO_ORDER_BUTTON_LOCATOR_VALUE).click();
		SeleniumUtil.pauseExecution(4000);
		ExtentTestManager.getTest().log(Status.INFO, "Searching customer");
		String customerEmail = ConfigurationManager.getInstance().getConfig().getShopifyCustomerEmail();
		SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.SEARCH_CUSTOMERS_FIELD_LOCATOR_TYPE, ShopifyPageRepo.SEARCH_CUSTOMERS_FIELD_LOCATOR_VALUE).sendKeys(customerEmail);
		SeleniumUtil.pauseExecution(4000);
		ExtentTestManager.getTest().log(Status.INFO, "Selecting the searched customer");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.SEARCHED_CUSTOMER_LOCATOR_TYPE, ShopifyPageRepo.getSearchedCustomerLocatorValue(customerEmail)).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on mark as paid button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.MARK_AS_PAID_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.MARK_AS_PAID_BUTTON_LOCATOR_VALUE).click();
		SeleniumUtil.pauseExecution(8000);
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on create order button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, ShopifyPageRepo.FINAL_CREATE_ORDER_BUTTON_LOCATOR_TYPE, ShopifyPageRepo.FINAL_CREATE_ORDER_BUTTON_LOCATOR_VALUE).click();
		SeleniumUtil.pauseExecution(8000);
		setOrderDetails();
	}
	
	public void setOrderDetails() {
		
		String orderNumber = SeleniumUtil.findWebElementUntilVisible(webDriver, ShopifyPageRepo.GENERATED_ORDER_NUMBER_LOCATOR_TYPE, ShopifyPageRepo.GENERATED_ORDER_NUMBER_LOCATOR_VALUE).getText();
		orderNumber = orderNumber.replace("#", "");
		shopifyOrder = new ShopifyOrder();
		shopifyOrder.setOrderNumber(orderNumber);
	}
	
	public ShopifyOrder getShopiyOrder() {
		return shopifyOrder;
	}
}
