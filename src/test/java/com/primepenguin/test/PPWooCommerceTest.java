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

public class PPWooCommerceTest extends BaseTest {

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
	
	/**
	 * Test case - Login to prime penguin
	 */
	@Test(priority = 1)
	public void testPrimePenguinLogin() {
		ppLoginService.loginToPrimePenguin();
	}
	
	/**
	 * Test case - Connect to woocommerce sales channel
	 */
	@Test(priority = 2, dependsOnMethods = { "testPrimePenguinLogin" })
	public void testWooCommerceSalesChannelIntegration() {
		ppWooCommerceService.connectToWooCommerceSalesChannel();
	}
	
	/**
	 * Test case - Save the default product mapping
	 */
	
	@Test(priority = 3, dependsOnMethods = { "testWooCommerceSalesChannelIntegration" })
	public void testSaveDefaultProductMapping() {
		ppWooCommerceService.saveDefaultProductMapping();
	}
	
	@Test(priority = 4, dependsOnMethods = { "testWooCommerceSalesChannelIntegration" })
	public void testWooCommerceLogin() {
		
		CommonUtil.openNewTab(webDriver);
		ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
		wooCommerceTabHandler=tabs.get(1);
		webDriver.switchTo().window(wooCommerceTabHandler);
		webDriver.get(ConfigurationManager.getInstance().getConfig().getWooCommerceLoginURL());
		wooCommerceService.loginToWooCommerce();
	}
	
	@Test(priority = 5, dependsOnMethods = { "testWooCommerceLogin" })
	public void testCreateWooCommerceSimpleProductOrder() {
		wooCommerceService.createWooCoomerceOrder(ProductType.SIMPLE);
	}
	
	/**
	 * Test case - Check if the created order in woocommerce is fetched in prime penguin or not
	 * Step1: Switching focus to the prime penguin tab
	 * Step2: Executing the checkIsOrderFetchedInPrimePenguin method of the ppShopifySaleChannelPageService
	 */
	@Test(priority = 6, dependsOnMethods = { "testCreateWooCommerceSimpleProductOrder" })
	public void testIsOrderFetchedInPrimePenguin() {
		webDriver.switchTo().window(getPrimePenguinTabHandler());
		ppWooCommerceService.checkIsOrderFetchedInPrimePenguin(wooCommerceService.getWooCommerceOrder().getOrderNumber());
	}
	
	/**
	 * Test case - Getting the access token for the LP API
	 */
	@Test(priority = 7, dependsOnMethods = { "testIsOrderFetchedInPrimePenguin"})
	public void testAuthenitcateAPI() {
		ExtentTestManager.startTest("testAuthenitcateAPI", "Test Authenticate API");
		logisticProviderOrderAPIService.getAccessToken();
	}
	
	/**
	 * Test case - Executing the update order fulfillment status LP API
	 * In the updateOrderFulfillmentStatus method passing two parameters:
	 * In the first parameter passing the prime penguin order id
	 * In the second parameter, passing fulfilled status array
	 */
	@Test(priority = 8, dependsOnMethods = { "testAuthenitcateAPI"})
	public void testUpdateOrderFulfillmentStatusAPI() {
		ExtentTestManager.startTest("testUpdateOrderFulfillmentStatusAPI", "Test UpdateOrderFulfillmentStatus API");
		logisticProviderOrderAPIService.updateOrderFulfillmentStatus(ppWooCommerceService.getPrimePenguinOrder().getPPOrderId(), PrimePenguinOrderStatus.FULFILLED);
	}
	
	/**
	 * Test case - Checking if the prime penguin order status is updated to fulfilled or not
	 */
	@Test(priority = 9, dependsOnMethods = { "testUpdateOrderFulfillmentStatusAPI"})
	public void testIsOrderFulfilled() {
		ppWooCommerceService.checkIsOrderStatusUpdated(ppWooCommerceService.getPrimePenguinOrder().getPPOrderId(),"fulfilled");
	}
	
	/**
	 * Test case - Deactivate the shopify sale channel integration
	 */
	@Test(priority = 10, dependsOnMethods = { "testIsOrderFulfilled" })
	public void testDeactivateShopifySaleChannelIntegration() {
		ppWooCommerceService.deactivateSalesChannelIntegration();
	}
}
