package com.primepenguin.test;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.primepenguin.api.repo.PrimePenguinOrderStatus;
import com.primepenguin.api.service.impl.LogisticProviderOrderAPIServiceImpl;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.SeleniumUtil;
import com.primepenguin.model.ProductType;
import com.primepenguin.model.UserType;
import com.primepenguin.service.PPShopifySaleChannelPageService;
import com.primepenguin.service.ShopifyPageService;
import com.primepenguin.service.impl.PPLoginServiceImpl;

public class PPShopifySaleChannelTest extends BaseTest{

	private PPLoginServiceImpl loginService;
	private ShopifyPageService shopifyPageService;
	private PPShopifySaleChannelPageService ppShopifySaleChannelPageService;
	private LogisticProviderOrderAPIServiceImpl logisticProviderOrderAPIService;
	private String shopifyTabHandler;
	
	/**
	 * Method initializeServices - In this method, all the required services object is created
	 * Annotation BeforClass - The annotated method will be run before the first test method in the current class is invoked
	 */
	@BeforeClass
	public void initializeServices() {
		
		loginService=new PPLoginServiceImpl(webDriver,UserType.ESHOP);
		shopifyPageService=new ShopifyPageService(webDriver);
		ppShopifySaleChannelPageService = new PPShopifySaleChannelPageService(webDriver);
		logisticProviderOrderAPIService = new LogisticProviderOrderAPIServiceImpl();
	}
	
	/**
	 * Test case - login to the prime penguin application
	 */
	@Test(priority = 1)
	public void testPrimePenguinLogin() {
		loginService.loginToPrimePenguin();
	}
	
	/**
	 * Test case - login to the shopify application
	 * Step1: Opening new tab of the browser
	 * Step2: Storing all browser's window handles in a list
	 * Step3: Getting the shopify tab handler
	 * Step4: Switching focus to the shopify tab
	 * Step5: Hitting the shopify URL
	 * Step6: Executing the loginToShopify method of the shopifyPageService
	 */
	@Test(priority = 2, dependsOnMethods = { "testPrimePenguinLogin"})
	public void testShopifyLogin() {
		
		SeleniumUtil.openNewTab(webDriver);
		ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
		shopifyTabHandler=tabs.get(1);
		webDriver.switchTo().window(shopifyTabHandler);
		webDriver.get(ConfigurationManager.getInstance().getConfig().getShopifyURL());
		shopifyPageService.loginToShopify();
	}
	
	/**
	 * Test case - Integrate with the shopify sale channel
	 * Step1: Switching focus to the prime pemguin tab
	 * Step2: Executing the connectToShopifySaleChannel method of the ppShopifySaleChannelPageService
	 */
	@Test(priority = 3, dependsOnMethods = { "testPrimePenguinLogin", "testShopifyLogin" })
	public void testShopifySaleChannelIntegration() {
		webDriver.switchTo().window(getPrimePenguinTabHandler());
		ppShopifySaleChannelPageService.connectToShopifySaleChannel();
	}
	
	/**
	 * Test case - Create order in shopify
	 * Step1: Switching focus to the shopify tab
	 * Step2: Executing the createOrder method of the shopifyPageService - in the method parameter passing the product type as simple
	 */
	@Test(priority = 4, dependsOnMethods = { "testShopifySaleChannelIntegration" })
	public void testCreateOrderInShopify() {
		webDriver.switchTo().window(shopifyTabHandler);
		shopifyPageService.createOrder(ProductType.SIMPLE);
	}
	
	/**
	 * Test case - Check if the created order in shopify is fetched in prime penguin or not
	 * Step1: Switching focus to the prime pemguin tab
	 * Step2: Executing the checkIsOrderFetchedInPrimePenguin method of the ppShopifySaleChannelPageService - in the method parameter we are passing the created shopify object
	 */
	@Test(priority = 5, dependsOnMethods = { "testCreateOrderInShopify" })
	public void testIsOrderFetchedInPrimePenguin() {
		webDriver.switchTo().window(getPrimePenguinTabHandler());
		//ppShopifySaleChannelPageService.checkIsOrderFetchedInPrimePenguin(shopifyPageService.getShopiyOrder());
	}
	
	/**
	 * Test case - Fetching the prime penguin order id
	 */
	@Test(priority = 6, dependsOnMethods = { "testIsOrderFetchedInPrimePenguin"})
	public void testFetchPrimePenguinOrderID() {
		//ppShopifySaleChannelPageService.fetchPrimePenguinOrderID();
	}
	
	/**
	 * Test case - Save the default product mapping
	 */
	@Test(priority = 7, dependsOnMethods = { "testFetchPrimePenguinOrderID" })
	public void testDefaultProductMapping() {
		ppShopifySaleChannelPageService.saveDefaultProductMapping();
	}
	
	/**
	 * Test case - Getting the access token for the LP API
	 */
	@Test(priority = 8, dependsOnMethods = { "testDefaultProductMapping"})
	public void testAuthenitcateAPI() {
		logisticProviderOrderAPIService.getAccessToken();
	}
	
	/**
	 * Test case - Executing the create order fulfillment LP API
	 * In the createOrderFulfillment method passing two parameters:
	 * In the first parameter passing the prime penguin order id
	 * In the second parameter, passing inprogress status array
	 */
	@Test(priority = 9, dependsOnMethods = { "testAuthenitcateAPI"})
	public void testCreateOrderFulfillmentAPI() {
		//logisticProviderOrderAPIService.createOrderFulfillment(ppShopifySaleChannelPageService.getPrimePenguinOrder().getOrderId(), PrimePenguinOrderStatus.INPROGRESS);
	}
	
	/**
	 * Test case - Checking if the fulfillment count is updated or not
	 */
	@Test(priority = 10, dependsOnMethods = { "testCreateOrderFulfillmentAPI"})
	public void testIsFulfillmentsCountUpdated() {
		//ppShopifySaleChannelPageService.checkIsFulfillmentsCountUpdated(ppShopifySaleChannelPageService.getPrimePenguinOrder().getOrderId());
	}
	
	/**
	 * Test case - Checking if the prime penguin order status is updated to inprogress or not
	 */
	@Test(priority = 11, dependsOnMethods = { "testIsFulfillmentsCountUpdated"})
	public void testIsOrderStatusUpdated() {
		//ppShopifySaleChannelPageService.checkIsOrderStatusUpdated("inprogress");
	}
	
	/**
	 * Test case - Executing the update order fulfillment status LP API
	 * In the updateOrderFulfillmentStatus method passing two parameters:
	 * In the first parameter passing the prime penguin order id
	 * In the second parameter, passing fulfilled status array
	 */
	@Test(priority = 12, dependsOnMethods = { "testIsOrderStatusUpdated"})
	public void testUpdateOrderFulfillmentStatusAPI() {
		//logisticProviderOrderAPIService.updateOrderFulfillmentStatus(ppShopifySaleChannelPageService.getPrimePenguinOrder().getOrderId(), PrimePenguinOrderStatus.FULFILLED);
	}
	
	/**
	 * Test case - Checking if the prime penguin order status is updated to fulfilled or not
	 */
	@Test(priority = 13, dependsOnMethods = { "testUpdateOrderFulfillmentStatusAPI"})
	public void testIsOrderFulfilled() {
		//ppShopifySaleChannelPageService.checkIsOrderStatusUpdated("fulfilled");
	}
	
	/**
	 * Test case - Executing the create order refund LP API
	 * In the createOrderRefund method passing prime penguin order id
	 */
	@Test(priority = 14, dependsOnMethods = { "testIsOrderFulfilled"})
	public void testCreateOrderRefundAPI() {
		//logisticProviderOrderAPIService.createOrderRefund(ppShopifySaleChannelPageService.getPrimePenguinOrder().getOrderId());
	}
	
	/**
	 * Test case - Checking if the refund count is updated or not
	 */
	@Test(priority = 15, dependsOnMethods = { "testCreateOrderRefundAPI"})
	public void testIsRefundsCountUpdated() {
		//ppShopifySaleChannelPageService.checkIsRefundsCountUpdated(ppShopifySaleChannelPageService.getPrimePenguinOrder().getOrderId());
	}
	
	/**
	 * Test case - Deactivate the shopify sale channel integration
	 */
	@Test(priority = 16, dependsOnMethods = { "testShopifySaleChannelIntegration" })
	public void testDeactivateShopifySaleChannelIntegration() {
		//ppShopifySaleChannelPageService.deactivateSalesChannelIntegration();
	}
}
