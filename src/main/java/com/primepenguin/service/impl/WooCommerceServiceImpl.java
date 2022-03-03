package com.primepenguin.service.impl;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.exception.WebElementException;
import com.primepenguin.core.util.CommonUtil;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.ProductType;
import com.primepenguin.model.WooCommerceOrder;
import com.primepenguin.pagerepo.saleschannel.WooCommercePageRepo;
import com.primepenguin.service.IWooCommerceService;

public class WooCommerceServiceImpl implements IWooCommerceService {

	private WebDriver webDriver;
	private WooCommerceOrder WooCommerceOrder;
	private static Logger logger = Logger.getLogger(WooCommerceServiceImpl.class);
	
	public WooCommerceServiceImpl(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	@Override
	public void loginToWooCommerce() {
		
		logger.info("Inside the method loginToWooCommerce()");
		ExtentTestManager.startTest("loginToWooCommerce", "WooCommerce Login Test");
		try {
			logger.info("Calling method setUserName()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the woocommerce user name");
			setUserName();
			logger.info("Calling method setPassword()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the woocommerce password");
			setPassword();
			logger.info("Calling method clickOnLoginButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the login button");
			clickOnLoginButton();
			CommonUtil.pauseExecution(1000);
			if(verifyIsUserNameDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in to the Woocommerce");
			}else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to the Woocommerce");
				Assert.fail();
			}
			
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to woocommerce: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to woocommerce: " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Override
	public void createWooCoomerceOrder(ProductType productType) {
		
		logger.info("Inside the method createWooCoomerceOrder()");
		ExtentTestManager.startTest("createWooCoomerceOrder", "WooCommerce Create Order");
		try {
			logger.info("Calling method clickOnWooCommerceMenuItem()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the woocommerce menu item");
			clickOnWooCommerceMenuItem();
			logger.info("Calling method clickOnOrdersMenuItem()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the orders menu item");
			clickOnOrdersMenuItem();
			logger.info("Calling method clickOnAddOrderButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Add order button");
			clickOnAddOrderButton();
			logger.info("Calling method clickOnCustomerDropDrown()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the customer drop down");
			clickOnCustomerDropDrown();
			logger.info("Calling method searchCustomer()");
			ExtentTestManager.getTest().log(Status.INFO, "Searching the customer");
			searchCustomer();
			logger.info("Calling method clickONAddItemButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Add Item button");
			clickONAddItemButton();
			logger.info("Calling method clickONAddProductButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Add Product button");
			clickONAddProductButton();
			logger.info("Calling method clickOnProductDropDrown()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the product drop down");
			clickOnProductDropDrown();
			if(productType.equals(ProductType.SIMPLE)){
				logger.info("Calling method searchProduct()");
				ExtentTestManager.getTest().log(Status.INFO, "Searching the product");
				searchProduct(ProductType.SIMPLE);
			}
			logger.info("Calling method clickOnAddSearchedProductButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Add button");
			clickOnAddSearchedProductButton();
			CommonUtil.pauseExecution(10000);
			CommonUtil.scrollToTop(webDriver);
			logger.info("Calling method clickOnCreateOrderButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Create Order button");
			clickOnCreateOrderButton();
			CommonUtil.waitForPageLoad(webDriver);
			if(verifyIsOrderNotificationDisplayed()) {
				setOrderDetails();
				ExtentTestManager.getTest().log(Status.PASS, "WooCommerce order created successfully: " + getWooCommerceOrder().getOrderNumber());
			}else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to create Woocommerce order");
				Assert.fail();
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to create Woocommerce order: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to create Woocommerce order: " + e.getMessage());
			Assert.fail();
		}
	}
	
	private void setUserName() throws WebElementException {
		
		logger.info("Inside the method setUserName()");
		try {
			String userName = ConfigurationManager.getInstance().getConfig().getWooCommerceEmail();
			CommonUtil.enterValueInTextField(webDriver, WooCommercePageRepo.EMAIL_FIELD_LOCATOR_TYPE, WooCommercePageRepo.EMAIL_FIELD_LOCATOR_VALUE, userName);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + WooCommercePageRepo.EMAIL_FIELD_LOCATOR_VALUE + 
					" in the method setUserName()", webElementException);
		}
	}
	
	private void setPassword() throws WebElementException {
		
		logger.info("Inside the method setPassword()");
		try {
			String password = ConfigurationManager.getInstance().getConfig().getWooCommercePassword();
			CommonUtil.enterValueInTextField(webDriver, WooCommercePageRepo.PASSWORD_FIELD_LOCATOR_TYPE, WooCommercePageRepo.PASSWORD_FIELD_LOCATOR_VALUE, password);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + WooCommercePageRepo.PASSWORD_FIELD_LOCATOR_VALUE + 
					" in the method setPassword()", webElementException);
		}
	}
	
	private void clickOnLoginButton() throws WebElementException {
		
		logger.info("Inside the method clickOnLoginButton()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.LOGIN_BUTTON_LOCATOR_TYPE, WooCommercePageRepo.LOGIN_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.LOGIN_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnLoginButton()", webElementException);
		}
	}
	
	private boolean verifyIsUserNameDisplayed() throws WebElementException {
		
		logger.info("Inside the method verifyIsUserNameDisplayed()");
		String loggedInUserName = ConfigurationManager.getInstance().getConfig().getWooCommerceLoggedInUserName();
		try {
			if(CommonUtil.isWebElementDisplayed(webDriver, "xpath", "//span[text()='"+loggedInUserName+"']")) {
				return true;
			} else {
				return false;			
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Web element is not displayed: //span[text()='"+loggedInUserName+"'] in the method verifyIsUserNameDisplayed()", webElementException);
		}
	}
	
	private void clickOnWooCommerceMenuItem() throws WebElementException {
		
		logger.info("Inside the method clickOnWooCommerceMenuItem()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.WOOCOMMERCE_MENU_LOCATOR_TYPE, WooCommercePageRepo.WOOCOMMERCE_MENU_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.WOOCOMMERCE_MENU_LOCATOR_VALUE + 
					" in the method clickOnWooCommerceMenuItem()", webElementException);
		}
	}
	
	private void clickOnOrdersMenuItem() throws WebElementException {
		
		logger.info("Inside the method clickOnOrdersMenuItem()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.ORDERS_MENU_LOCATOR_TYPE, WooCommercePageRepo.ORDERS_MENU_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.ORDERS_MENU_LOCATOR_VALUE + 
					" in the method clickOnOrdersMenuItem()", webElementException);
		}
	}
	
	private void clickOnAddOrderButton() throws WebElementException {
		
		logger.info("Inside the method clickOnAddOrderButton()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.ADD_ORDER_BUTTON_LOCATOR_TYPE, WooCommercePageRepo.ADD_ORDER_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.ADD_ORDER_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnAddOrderButton()", webElementException);
		}
	}
	
	private void clickOnCustomerDropDrown() throws WebElementException {
		
		logger.info("Inside the method clickOnCustomerDropDrown()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.CUSTOMER_DROPDOWN_LOCATOR_TYPE, WooCommercePageRepo.CUSTOMER_DROPDOWN_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.CUSTOMER_DROPDOWN_LOCATOR_VALUE + 
					" in the method clickOnCustomerDropDrown()", webElementException);
		}
	}
	
	private void searchCustomer() throws WebElementException {
		
		logger.info("Inside the method searchCustomer()");
		try {
			String customerEmail = ConfigurationManager.getInstance().getConfig().getWooCommerceCustomerEmail();
			CommonUtil.enterValueInTextField(webDriver,WooCommercePageRepo.SEARCH_CUSTOMER_FIELD_LOCATOR_TYPE, WooCommercePageRepo.SEARCH_CUSTOMER_FIELD_LOCATOR_VALUE, customerEmail);
			waitTillRecordSearched();
			CommonUtil.clickOnElement(webDriver, "xpath", "//li[contains(text(),'"+customerEmail+"')]");
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to search customer in the method searchCustomer()", webElementException);
		}
	}
	
	private void clickONAddItemButton() throws WebElementException {
		
		logger.info("Inside the method clickONAddItemButton()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.ADD_ITEMS_BUTTON_LOCATOR_TYPE, WooCommercePageRepo.ADD_ITEMS_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.ADD_ITEMS_BUTTON_LOCATOR_VALUE + 
					" in the method clickONAddItemButton()", webElementException);
		}
	}
	
	private void clickONAddProductButton() throws WebElementException {
		
		logger.info("Inside the method clickONAddItemButton()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.ADD_PRODUCTS_BUTTON_LOCATOR_TYPE, WooCommercePageRepo.ADD_PRODUCTS_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.ADD_PRODUCTS_BUTTON_LOCATOR_VALUE + 
					" in the method clickONAddProductButton()", webElementException);
		}
	}
	
	private void clickOnProductDropDrown() throws WebElementException {
		
		logger.info("Inside the method clickOnProductDropDrown()");
		try {
			CommonUtil.moveToElementAndClick(webDriver, WooCommercePageRepo.SEARCH_PRODUCT_DROPDOWN_LOCATOR_TYPE, WooCommercePageRepo.SEARCH_PRODUCT_DROPDOWN_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.SEARCH_PRODUCT_DROPDOWN_LOCATOR_VALUE + 
					" in the method clickOnProductDropDrown()", webElementException);
		}
	}
	
	private void searchProduct(ProductType productType) throws WebElementException {
		
		logger.info("Inside the method searchProduct()");
		try {
			String productSKU = null;
			if(productType.equals(ProductType.SIMPLE)) {
				productSKU = ConfigurationManager.getInstance().getConfig().getWooCommerceSimpleProductSKU();
			}
			CommonUtil.moveToElementAndEnterText(webDriver,WooCommercePageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_TYPE, WooCommercePageRepo.SEARCH_PRODUCT_FIELD_LOCATOR_VALUE, productSKU);
			waitTillRecordSearched();
			CommonUtil.clickOnElement(webDriver, "xpath", "//li[contains(text(),'"+productSKU+"')]");
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to search customer in the method searchProduct()", webElementException);
		}
	}
	
	private void waitTillRecordSearched() throws WebElementException {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(WooCommercePageRepo.SEARCH_RECORD_LOCATOR_VALUE)));
		} catch(Exception e) {
			throw new WebElementException("Web element not found: " + WooCommercePageRepo.SEARCH_RECORD_LOCATOR_VALUE + " in the method waitTillRecordSearched()");
		}
	}
	
	private void clickOnAddSearchedProductButton() throws WebElementException {
		
		logger.info("Inside the method clickOnAddSearchedProductButton()");
		try {
			CommonUtil.clickOnElement(webDriver, WooCommercePageRepo.ADD_SEARCHED_PRODUCT_LOCATOR_TYPE, WooCommercePageRepo.ADD_SEARCHED_PRODUCT_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.ADD_SEARCHED_PRODUCT_LOCATOR_VALUE + 
					" in the method clickOnAddSearchedProductButton()", webElementException);
		}
	}
	
	private void clickOnCreateOrderButton() throws WebElementException {
		
		logger.info("Inside the method clickOnCreateOrderButton()");
		try {
			CommonUtil.moveToElementAndClick(webDriver, WooCommercePageRepo.CREATE_ORDER_BUTTON_LOCATOR_TYPE, WooCommercePageRepo.CREATE_ORDER_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + WooCommercePageRepo.CREATE_ORDER_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnCreateOrderButton()", webElementException);
		}
	}
	
	private boolean verifyIsOrderNotificationDisplayed() throws WebElementException {
		
		logger.info("Inside the method verifyIsOrderNotificationDisplayed()");
		try {
			if(CommonUtil.isWebElementDisplayed(webDriver, WooCommercePageRepo.ORDER_UPDATED_NOTIFICATION_LOCATOR_TYPE, WooCommercePageRepo.ORDER_UPDATED_NOTIFICATION_LOCATOR_VALUE)){
				return true;
			} else {
				return false;			
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Web element not displayed: " + WooCommercePageRepo.ORDER_UPDATED_NOTIFICATION_LOCATOR_VALUE + 
					" in the method verifyIsOrderNotificationDisplayed()", webElementException);		
		}
	}
	
	private void setOrderDetails() throws WebElementException {
		
		String orderDetails = CommonUtil.getElementText(webDriver, WooCommercePageRepo.GENERATED_ORDER_NUMBER_LOCATOR_TYPE, WooCommercePageRepo.GENERATED_ORDER_NUMBER_LOCATOR_VALUE);
		String orderNumber = orderDetails.substring(orderDetails.indexOf("#")+1, orderDetails.indexOf(" de"));
		WooCommerceOrder = new WooCommerceOrder();
		WooCommerceOrder.setOrderNumber(orderNumber);
	}

	public WooCommerceOrder getWooCommerceOrder() {
		return WooCommerceOrder;
	}
}
