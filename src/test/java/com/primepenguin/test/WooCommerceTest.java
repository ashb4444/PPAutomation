package com.primepenguin.test;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.primepenguin.api.repo.PrimePenguinOrderStatus;
import com.primepenguin.api.service.ILogisticProviderOrderAPIService;
import com.primepenguin.api.service.impl.LogisticProviderOrderAPIServiceImpl;
import com.primepenguin.core.util.CommonUtil;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.ProductType;
import com.primepenguin.model.UserType;
import com.primepenguin.service.IPPWooCommerceService;
import com.primepenguin.service.IWooCommerceService;
import com.primepenguin.service.impl.PPLoginServiceImpl;
import com.primepenguin.service.impl.PPWooCommerceServiceImpl;
import com.primepenguin.service.impl.WooCommerceServiceImpl;

public class WooCommerceTest extends BaseTest {

	private PPLoginServiceImpl ppLoginService;
	private IWooCommerceService wooCommerceService;
	private IPPWooCommerceService ppWooCommerceService;
	private ILogisticProviderOrderAPIService logisticProviderOrderAPIService;
	private String wooCommerceTabHandler;
	
	@BeforeClass
	public void initializeServices() {
		
		ppLoginService=new PPLoginServiceImpl(webDriver,UserType.ESHOP);
		wooCommerceService=new WooCommerceServiceImpl(webDriver);
		ppWooCommerceService=new PPWooCommerceServiceImpl(webDriver);
		logisticProviderOrderAPIService = new LogisticProviderOrderAPIServiceImpl();
	}
	
	@Test(priority = 1)
	public void testWooCommerceLogin() {
		webDriver.get(ConfigurationManager.getInstance().getConfig().getWooCommerceLoginURL());
		wooCommerceService.loginToWooCommerce();
	}
	
	@Test(priority = 2, dependsOnMethods = { "testWooCommerceLogin" })
	public void testCreateWooCommerceSimpleProductOrder() {
		wooCommerceService.createWooCoomerceOrder(ProductType.SIMPLE);
	}
}
