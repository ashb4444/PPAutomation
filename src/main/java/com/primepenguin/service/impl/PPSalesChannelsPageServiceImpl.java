package com.primepenguin.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.exception.WebElementException;
import com.primepenguin.core.util.CommonUtil;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.SeleniumUtil;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.PrimePenguinOrder;
import com.primepenguin.pagerepo.DashboardPageRepo;
import com.primepenguin.pagerepo.PPShopifySaleChannelPageRepo;
import com.primepenguin.pagerepo.ProductMappingPageRepo;
import com.primepenguin.pagerepo.SalesChannelsCommonRepo;
import com.primepenguin.service.IPPSalesChannelsPageService;

public class PPSalesChannelsPageServiceImpl implements IPPSalesChannelsPageService {

	private WebDriver webDriver;
	private PrimePenguinOrder primePenguinOrder;
	private static Logger logger = Logger.getLogger(PPSalesChannelsPageServiceImpl.class);
	
	public PPSalesChannelsPageServiceImpl(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void clickOnSaveSalesChannelButton() throws WebElementException {
		
		logger.info("Inside the method clickOnSaveSalesChannelButton()");
		try {
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.SAVE_SALES_CHANNEL_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.SAVE_SALES_CHANNEL_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + SalesChannelsCommonRepo.SAVE_SALES_CHANNEL_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnSaveSalesChannelButton()", webElementException);
		}
	} 
	
	public void clickOnValidSKUYesButton() throws WebElementException {
		
		logger.info("Inside the method clickOnValidSKUYesButton()");
		try {
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.VALID_SKU_YES_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.VALID_SKU_YES_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + SalesChannelsCommonRepo.VALID_SKU_YES_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnValidSKUYesButton()", webElementException);
		}
	}
	
	public boolean verifyIsDashboardButtonAppear(String locatorType, String locatorValue) throws WebElementException {
		
		logger.info("Inside the method verifyIsDashboardButtonAppear()");
		try {
			if(CommonUtil.isWebElementDisplayed(webDriver, locatorType, locatorValue)) {
				return true;
			} else {
				return false;			
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Web element is not displayed: '"+locatorValue+"' in the method verifyIsDashboardButtonAppear()", webElementException);
		}
	}
	
	@Override
	public void saveDefaultProductMapping() {
		
		logger.info("Inside the method saveDefaultProductMapping()");
		ExtentTestManager.startTest("saveDefaultProductMapping", "Save default product mapping");
		try {
			logger.info("Navigating directly to product mapping URL");
			//navigating directly to product mapping URL because product mapping menu doesn't visible sometimes
			webDriver.get("https://app.primepenguin.com/app/main/product-mapping");
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method clickOnDefaultMappingButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on Default Product Mapping button");
			clickOnDefaultMappingButton();
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method selectLPForDefaultMapping()");
			ExtentTestManager.getTest().log(Status.INFO, "Selecting LP from Default Product mapping");
			selectLPForDefaultMapping();
			if(verifyIsSuccessNotificationDisplayed(SalesChannelsCommonRepo.SUCCESS_NOTIFICATION_LOCATOR_TYPE, SalesChannelsCommonRepo.SUCCESS_NOTIFICATION_LOCATOR_VALUE)) {
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.SALES_CHANNEL_MENU_ITEM_LOCATOR_TYPE, SalesChannelsCommonRepo.SALES_CHANNEL_MENU_ITEM_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.PASS, "Product mapping successfully saved");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to save default product mapping");
				Assert.fail();
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to save default product mapping: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to save default product mapping: " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Override
	public void checkIsOrderFetchedInPrimePenguin(String dashboardLocatorType, String dashboardLocatorValue, String orderNumber) {
		
		logger.info("Inside the method checkIsOrderFetchedInPrimePenguin()");
		ExtentTestManager.startTest("checkIsOrderFetchedInPrimePenguin", "Check order fetched in Prime Penguin");
		try {
			logger.info("Calling method clickOnDashboardButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on dashboard button");
			clickOnDashboardButton(dashboardLocatorType, dashboardLocatorValue);
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method clickOnSyncIntegration()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Sync integration");
			clickOnSyncIntegration();
			logger.info("Calling method clickOnOrdersDetailsLink()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on orders details link");
			clickOnOrdersDetailsLink();
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method searchOrderNumber()");
			ExtentTestManager.getTest().log(Status.INFO, "Searching the order number - " + orderNumber);
			searchOrderNumber(orderNumber);
			logger.info("Checking if verifyIsOrderFound() returns null or not");
			CommonUtil.pauseExecution(20000);
			if(verifyIsOrderFound()) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully fetched the order in Prime Penguin");
				ExtentTestManager.getTest().log(Status.INFO, "Order status is: " + getSalesChannelOrderStatus());
				setPrimePenguinOrderID();
			}else {
				ExtentTestManager.getTest().log(Status.FAIL, "Order not fetched in prime penguin");
				Assert.fail();
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Order not fetched in prime penguin: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Order not fetched in prime penguin: " + e.getMessage());
			Assert.fail();
		}
	}
	
	private void clickOnDashboardButton(String locatorType, String locatorValue) throws WebElementException {
		
		logger.info("Inside the method clickOnDashboardButton()");
		try {
			CommonUtil.clickOnElement(webDriver, locatorType, locatorValue);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + locatorValue + 
					" in the method clickOnDashboardButton()", webElementException);
		}
	}
	
	private void clickOnSyncIntegration() throws WebElementException {
		
		logger.info("Inside the method clickOnSyncIntegration()");
		try {
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Manage button");
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_VALUE);
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Sync Integration option");
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.SYNC_INTEGRATION_OPTION_LOCATOR_TYPE, SalesChannelsCommonRepo.SYNC_INTEGRATION_OPTION_LOCATOR_VALUE);
			ExtentTestManager.getTest().log(Status.INFO, "Clicking Yes on the Sync integration confirmation pop-up");
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.CONFIRM_SYNC_INTEGRATION_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.CONFIRM_SYNC_INTEGRATION_BUTTON_LOCATOR_VALUE);
			CommonUtil.waitForLoaderDissappear(webDriver);
			ExtentTestManager.getTest().log(Status.INFO, "Clicking OK on the sync confirmation request");
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.OK_SYNC_INTEGRATION_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.OK_SYNC_INTEGRATION_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to perform the sync integration", webElementException);
		}
	}
	
	private void clickOnOrdersDetailsLink() throws WebElementException {
		
		logger.info("Inside the method clickOnOrdersDetailsLink()");
		try {
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.ORDER_DETAILS_LINK_LOCATOR_TYPE, SalesChannelsCommonRepo.ORDER_DETAILS_LINK_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + SalesChannelsCommonRepo.ORDER_DETAILS_LINK_LOCATOR_VALUE + 
					" in the method clickOnOrdersDetailsLink()", webElementException);
		}
	}
	
	private void searchOrderNumber(String orderNumber) throws WebElementException {
		
		logger.info("Inside the method searchOrderNumber()");
		try {
			CommonUtil.enterValueInTextField(webDriver, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_TYPE, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE, orderNumber);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE + 
					" in the method searchOrderNumber()", webElementException);
		}
	}
	
	private boolean verifyIsOrderFound() throws WebElementException {
		
		logger.info("Inside the method verifyIsOrderFound()");
		boolean result=false;
		int totalCount=0;
		
		while(totalCount!=1) {
			CommonUtil.pressEnterKey(webDriver, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_TYPE, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE);
			CommonUtil.waitForLoaderDissappear(webDriver);
			String gridTotalText = CommonUtil.getElementText(webDriver, SalesChannelsCommonRepo.GRID_TOTAL_RECORD_FIELD_LOCATOR_TYPE, SalesChannelsCommonRepo.GRID_TOTAL_RECORD_FIELD_LOCATOR_VALUE);
			gridTotalText=gridTotalText.trim();
			
			totalCount = Integer.parseInt(gridTotalText.substring(gridTotalText.indexOf(":")+2));
			if(totalCount==1) {
				result = true;
				break;
			}
			SeleniumUtil.pauseExecution(10000);
		}
		return result;
	}
	
	private String getSalesChannelOrderStatus() throws WebElementException {
		
		logger.info("Inside the method getSalesChannelOrderStatus()");
		try {
			return CommonUtil.getElementText(webDriver, SalesChannelsCommonRepo.ORDER_STATUS_COLUMN_VALUE_LOCATOR_TYPE, SalesChannelsCommonRepo.ORDER_STATUS_COLUMN_VALUE_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + SalesChannelsCommonRepo.ORDER_STATUS_COLUMN_VALUE_LOCATOR_VALUE + 
					" in the method getSalesChannelOrderStatus()", webElementException);
		}
	}
	
	public void setPrimePenguinOrderID() throws WebElementException {
		
		logger.info("Inside the method fetchPrimePenguinOrderID()");
		String orderId = CommonUtil.getElementText(webDriver, PPShopifySaleChannelPageRepo.ORDER_ID_COLUMN_VALUE_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.ORDER_ID_COLUMN_VALUE_LOCATOR_VALUE);
		ExtentTestManager.getTest().log(Status.PASS, "Successfully fetched the prime penguin order Id");
		ExtentTestManager.getTest().log(Status.INFO, "Prime Penguin Order Id is: " + orderId);
		primePenguinOrder = new PrimePenguinOrder();
		primePenguinOrder.setPPOrderId(Long.parseLong(orderId));
	}
	
	private void clickOnDefaultMappingButton() throws WebElementException {
		
		logger.info("Inside the method clickOnDefaultMappingButton()");
		try {
			CommonUtil.clickOnElement(webDriver, ProductMappingPageRepo.DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE, ProductMappingPageRepo.DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + ProductMappingPageRepo.DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnDefaultMappingButton()", webElementException);
		}
	}
	
	private void selectLPForDefaultMapping() throws WebElementException {
		
		logger.info("Inside the method selectLPForDefaultMapping()");
		try {
			CommonUtil.selectValueFromDropDown(webDriver, ProductMappingPageRepo.DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_TYPE, ProductMappingPageRepo.DEFAULT_PRODUCT_MAPPING_LP_DROP_DOWN_LOCATOR_VALUE, ConfigurationManager.getInstance().getConfig().getLpTenancyName());
			CommonUtil.clickOnElement(webDriver, ProductMappingPageRepo.SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_TYPE, ProductMappingPageRepo.SAVE_DEFAULT_PRODUCT_MAPPING_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to select LP from default product mapping dropdown: in the method selectLPForDefaultMapping()", webElementException);
		}
	}
	
	private boolean verifyIsSuccessNotificationDisplayed (String locatorType, String locatorValue) throws WebElementException {
		
		logger.info("Inside the method verifyIsSuccessNotificationDisplayed()");
		try {
			if(CommonUtil.isWebElementDisplayed(webDriver, locatorType, locatorValue)) {
				return true;
			} else {
				return false;			
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Success Notification is not displayed: '"+locatorValue+"' in the method verifyIsSuccessNotificationDisplayed()", webElementException);
		}
	}
	
	public void checkIsFulfillmentsCountUpdated(Long orderId) {

		ExtentTestManager.getTest().log(Status.INFO, "Clicking on Sales Channels menu");
		SeleniumUtil.findWebElementUntilClickable(webDriver, DashboardPageRepo.SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_TYPE, DashboardPageRepo.SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on shopify dashboard button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on orders details link");
		SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.ORDER_DETAILS_LINK_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.ORDER_DETAILS_LINK_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Searching the order Id - " + orderId);
		WebElement searchOrderFieldElement = SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.SEARCH_ORDER_FIELD_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE);
		searchOrderFieldElement.sendKeys(Long.toString(orderId));
		
		boolean isCountUpdated=false;
		for(int count=0; count<10; count++) {
			searchOrderFieldElement.sendKeys(Keys.ENTER);
			int fulfillmentsCount = Integer.parseInt(SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.FULFILLMENTS_COLUMN_VALUE_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.FULFILLMENTS_COLUMN_VALUE_LOCATOR_VALUE).getText().trim());		
			if(fulfillmentsCount>0) {
				isCountUpdated=true;
				ExtentTestManager.getTest().log(Status.PASS, "The Fulfillments count successfully updated");
				break;
			}
			SeleniumUtil.pauseExecution(6000);
		}
		if(!isCountUpdated) {
			ExtentTestManager.getTest().log(Status.FAIL, "The Fulfillments count not updated");
			Assert.fail();
		}
	}
	
	public void checkIsOrderStatusUpdated(Long ppOrderId, String status) {
		
		ExtentTestManager.startTest("checkIsOrderStatusUpdated", "Check order status updated in Prime Penguin");
		logger.info("Inside the method checkIsOrderStatusUpdated()");
		try {
			logger.info("Clicking on Back Button");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Back button from orders page");
			CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.BACK_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.BACK_BUTTON_LOCATOR_VALUE);
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method clickOnSyncIntegration()");
			clickOnSyncIntegration();
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method clickOnOrdersDetailsLink()");
			clickOnOrdersDetailsLink();
			CommonUtil.waitForLoaderDissappear(webDriver);
			logger.info("Calling method searchOrderNumber()");
			searchOrderNumber(ppOrderId.toString());
			
			boolean isOrderStatusUpdated=false;
			for(int count=0; count<30; count++) {
				CommonUtil.pressEnterKey(webDriver, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_TYPE, SalesChannelsCommonRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE);
				String orderStatus = CommonUtil.getElementText(webDriver, SalesChannelsCommonRepo.ORDER_STATUS_COLUMN_VALUE_LOCATOR_TYPE, SalesChannelsCommonRepo.ORDER_STATUS_COLUMN_VALUE_LOCATOR_VALUE).trim();
				if(orderStatus.equalsIgnoreCase(status)) {
					isOrderStatusUpdated=true;
					ExtentTestManager.getTest().log(Status.PASS, "The order status successfully updated to: " + status);
					break;
				}
				SeleniumUtil.pauseExecution(10000);
			}
			if(!isOrderStatusUpdated) {
				ExtentTestManager.getTest().log(Status.FAIL, "The order status didn't updated to: " + status);
				Assert.fail();
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "The order status didn't updated to: " + status);
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "The order status didn't updated to: " + status);
			Assert.fail();
		}
	}
	
	public void checkIsRefundsCountUpdated(Long orderId) {

		WebElement searchOrderFieldElement = SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.SEARCH_ORDER_FIELD_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.SEARCH_ORDER_FIELD_LOCATOR_VALUE);
		boolean isCountUpdated=false;
		for(int count=0; count<10; count++) {
			searchOrderFieldElement.sendKeys(Keys.ENTER);
			int refundsCount = Integer.parseInt(SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.REFUNDS_COLUMN_VALUE_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.REFUNDS_COLUMN_VALUE_LOCATOR_VALUE).getText().trim());		
			if(refundsCount>0) {
				isCountUpdated=true;
				ExtentTestManager.getTest().log(Status.PASS, "The Refunds count successfully updated");
				break;
			}
			SeleniumUtil.pauseExecution(6000);
		}
		if(!isCountUpdated) {
			ExtentTestManager.getTest().log(Status.FAIL, "The Refunds count not updated");
			Assert.fail();
		}
	}
	
	public void deactivateSalesChannelIntegration(String currentPage, String locatorType, String locatorValue) throws WebElementException {
		
		logger.info("Inside the method deactivateSalesChannelIntegration()");
		try {
			if(currentPage.equalsIgnoreCase("dashboard")) {
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Dashboard button");
				CommonUtil.clickOnElement(webDriver, locatorType, locatorValue);
				CommonUtil.waitForLoaderDissappear(webDriver);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Manage button");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Deactivate Integration option");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.DEACTIVATE_INTEGRATION_OPTION_LOCATOR_TYPE, SalesChannelsCommonRepo.DEACTIVATE_INTEGRATION_OPTION_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking Yes on the deactivate integration confirmation pop-up");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_VALUE);
				CommonUtil.waitForLoaderDissappear(webDriver);
			} else if(currentPage.equalsIgnoreCase("orders")) {
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Back button");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.BACK_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.BACK_BUTTON_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Manage button");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.MANAGE_BUTTON_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Deactivate Integration option");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.DEACTIVATE_INTEGRATION_OPTION_LOCATOR_TYPE, SalesChannelsCommonRepo.DEACTIVATE_INTEGRATION_OPTION_LOCATOR_VALUE);
				ExtentTestManager.getTest().log(Status.INFO, "Clicking Yes on the deactivate integration confirmation pop-up");
				CommonUtil.clickOnElement(webDriver, SalesChannelsCommonRepo.CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_TYPE, SalesChannelsCommonRepo.CONFIRM_DEACTIVATE_INTEGRATION_BUTTON_LOCATOR_VALUE);
				CommonUtil.waitForLoaderDissappear(webDriver);
				webDriver.navigate().refresh();
				CommonUtil.waitForLoaderDissappear(webDriver);
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to deactivate the sales channel integration in the method deactivateSalesChannelIntegration()", webElementException);
		}
	}
	
	public PrimePenguinOrder getPrimePenguinOrder() {
		return primePenguinOrder;
	}
}
