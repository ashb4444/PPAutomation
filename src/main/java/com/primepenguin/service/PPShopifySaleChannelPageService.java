package com.primepenguin.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.SeleniumUtil;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.pagerepo.PPShopifySaleChannelPageRepo;
import com.primepenguin.service.impl.PPSalesChannelsPageServiceImpl;

public class PPShopifySaleChannelPageService extends PPSalesChannelsPageServiceImpl {
 
	private WebDriver webDriver;

	public PPShopifySaleChannelPageService(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}
	
	public void connectToShopifySaleChannel() {
		
//		SeleniumUtil.findWebElementUntilClickable(webDriver, DashboardPageRepo.INTEGRATION_MENU_OPTION_LOCATOR_TYPE, DashboardPageRepo.INTEGRATION_MENU_OPTION_LOCATOR_VALUE).click();
//		SeleniumUtil.pauseExecution(2000);
//		SeleniumUtil.findWebElementUntilClickable(webDriver, DashboardPageRepo.SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_TYPE, DashboardPageRepo.SALES_CHANNELS_SUB_MENU_OPTION_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Connect button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.SHOPIFY_CONNECT_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.SHOPIFY_CONNECT_BUTTON_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Entering the Shopify shop name");
		SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.SHOPIFY_SHOP_NAME_FIELD_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.SHOPIFY_SHOP_NAME_FIELD_LOCATOR_VALUE).sendKeys(ConfigurationManager.getInstance().getConfig().getShopifyStroreAddress());
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Save button");
		//SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.SAVE_INTEGRATION_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.SAVE_INTEGRATION_BUTTON_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking Yes on the Valid SKU confirmation pop-up");
		SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.VALID_SKU_YES_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.VALID_SKU_YES_BUTTON_LOCATOR_VALUE).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Install unlisted App button");
		SeleniumUtil.findWebElementUntilClickable(webDriver, PPShopifySaleChannelPageRepo.INSTALL_APP_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.INSTALL_APP_BUTTON_LOCATOR_VALUE).click();
		SeleniumUtil.pauseExecution(4000);
		ExtentTestManager.getTest().log(Status.INFO, "Refreshing the page");
		webDriver.navigate().refresh();
		WebElement dashboardButtonElement = SeleniumUtil.findWebElementUntilVisible(webDriver, PPShopifySaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_TYPE, PPShopifySaleChannelPageRepo.DASHBOARD_BUTTON_LOCATOR_VALUE);
		if(dashboardButtonElement.isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Successfully integrated with Shopify Sale channel");
		} else {
			//ExtentTestManager.getTest().log(Status.FAIL, "Failed to integrate with Shopify Sale channel");
		}
	}
}
