package com.primepenguin.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.exception.WebElementException;
import com.primepenguin.core.util.CommonUtil;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.pagerepo.PPWooCommerceSaleChannelPageRepo;
import com.primepenguin.service.IPPWooCommerceService;

public class PPWooCommerceServiceImpl extends PPSalesChannelsPageServiceImpl implements IPPWooCommerceService {

	private WebDriver webDriver;
	private static Logger logger = Logger.getLogger(PPWooCommerceServiceImpl.class);
	
	public PPWooCommerceServiceImpl(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	@Override
	public void connectToWooCommerceSalesChannel() {
		
		logger.info("Inside the method connectToWooCommerceSalesChannel()");
		ExtentTestManager.startTest("connectToWooCommerceSalesChannel", "Connect to WooCommerce Sales Channel Test");
		try {
			CommonUtil.waitForLoaderDissappear(webDriver);
			String wooCommerceButtonText = CommonUtil.getElementText(webDriver, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_BUTTON_LOCATOR_VALUE);
			
			if(wooCommerceButtonText.equalsIgnoreCase("Dashboard")) {
				deactivateSalesChannelIntegration("dashboard", PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE);
				CommonUtil.waitForLoaderDissappear(webDriver);
				CommonUtil.pauseExecution(10000);
			}
			logger.info("Calling method clickOnConnectWooCoomerceButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Connect button");
			clickOnConnectWooCoomerceButton();
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method enterValueInShopURLTextField()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the WooCoomerce Shop URL");
			enterValueInShopURLTextField();
			logger.info("Calling method enterValueInConsumerKeyTextField()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the Consumer Key");
			enterValueInConsumerKeyTextField();
			logger.info("Calling method enterValueInConsumerSecretTextField()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the Consumer Secret");
			enterValueInConsumerSecretTextField();
			logger.info("Calling method clickOnSaveSalesChannelButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Save button");
			clickOnSaveSalesChannelButton();
			logger.info("Calling method clickOnValidSKUYesButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Valid SKU Yes button");
			clickOnValidSKUYesButton();
			CommonUtil.waitForLoaderDissappear(webDriver);
			CommonUtil.pauseExecution(20000);
			webDriver.navigate().refresh();
			CommonUtil.waitForLoaderDissappear(webDriver);
			if(verifyIsDashboardButtonAppear(PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE)) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully integrated with WooCommerce Sales channel");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to integrate with WooCommerce Sales channel");
				Assert.fail();
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to integrate with WooCommerce Sales channel: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to integrate with WooCommerce Sales channel: " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Override
	public void checkIsOrderFetchedInPrimePenguin(String orderNumber) {
		logger.info("Inside the method checkIsOrderFetchedInPrimePenguin()");
		super.checkIsOrderFetchedInPrimePenguin(PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE, orderNumber);
	}
	
	@Override
	public void deactivateSalesChannelIntegration(){
		
		logger.info("Inside the method deactivateSalesChannelIntegration()");
		ExtentTestManager.startTest("deactivateSalesChannelIntegration", "Deactivate WooCommerce Sales Channel Test");
		try {
			super.deactivateSalesChannelIntegration("orders", PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE);
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to deactivate WooCommerce Sales channel: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to deactivate WooCommerce Sales channel: " + e.getMessage());
			Assert.fail();
		}
	}
	
	private void clickOnConnectWooCoomerceButton() throws WebElementException {
		
		logger.info("Inside the method clickOnConnectWooCoomerceButton()");
		try {
			CommonUtil.clickOnElement(webDriver, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONNECT_BUTTON_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONNECT_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONNECT_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnConnectWooCoomerceButton()", webElementException);
		}
	}
	
	private void enterValueInShopURLTextField() throws WebElementException {
		
		logger.info("Inside the method enterValueInShopUrlTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver,PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_SHOP_NAME_FIELD_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_SHOP_NAME_FIELD_LOCATOR_VALUE, ConfigurationManager.getInstance().getConfig().getWooCommerceShopURL());
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_SHOP_NAME_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInShopUrlTextField()", webElementException);
		}
	}
	
	private void enterValueInConsumerKeyTextField() throws WebElementException {
		
		logger.info("Inside the method enterValueInConsumerKeyTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver,PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_KEY_FIELD_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_KEY_FIELD_LOCATOR_VALUE, ConfigurationManager.getInstance().getConfig().getWooCommerceConsumerKey());
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_KEY_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInConsumerKeyTextField()", webElementException);
		}
	}
	
	private void enterValueInConsumerSecretTextField() throws WebElementException {
		
		logger.info("Inside the method enterValueInConsumerSecretTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver,PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_SECRET_FIELD_LOCATOR_TYPE, PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_SECRET_FIELD_LOCATOR_VALUE, ConfigurationManager.getInstance().getConfig().getWooCommerceConsumerSecret());
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + PPWooCommerceSaleChannelPageRepo.WOOCOMMERCE_CONSUMER_SECRET_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInConsumerSecretTextField()", webElementException);
		}
	}
}
