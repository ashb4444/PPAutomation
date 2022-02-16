package com.primepenguin.test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.UserType;
import com.primepenguin.service.ShopifyPageService;
import com.primepenguin.service.impl.PPLoginServiceImpl;

public class LoginPageTest extends BaseTest {

	private PPLoginServiceImpl loginService;
	private ShopifyPageService shopifyPageService;
	private static Logger logger = Logger.getLogger(LoginPageTest.class);
	
	@Test
	public void testPrimePenguintLogin() {
		
		logger.info("Inside the method testPrimePenguintLogin()");
		if(webDriver.getTitle().contains("Prime Penguin")) {
			//ExtentTestManager.getTest().log(Status.PASS, "Successfully navigated to the Prime Penguin App URL");
			loginService = new PPLoginServiceImpl(webDriver,UserType.ESHOP);
			loginService.loginToPrimePenguin();
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Not able to navigate to the Prime Penguin App URL");
		}
	}
	
	//@Test
	public void testShopifyLogin() {
		
		shopifyPageService= new ShopifyPageService(webDriver);
		webDriver.get(ConfigurationManager.getInstance().getConfig().getShopifyURL());
		shopifyPageService.loginToShopify();
	}
}
