package com.primepenguin.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.primepenguin.core.driver.AbstractDriverManager;
import com.primepenguin.core.driver.DriverManagerFactory;
import com.primepenguin.core.driver.DriverType;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.TestListener;


public class BaseTest {
	
	private AbstractDriverManager driverManager;
	protected WebDriver webDriver;
	private String primePenguinTabHandler;
	
	/**
	 * Method initializeBaseSetUp - In this method: 
	 * first creating the driverManager object, then getting the webDriver object and hitting the application URL
	 * Annotation BeforClass - The annotated method will be run before the first test method in the current class is invoked
	 */
	@BeforeClass
	public void initializeBaseSetUp() throws InterruptedException {
		
		driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
		webDriver = driverManager.getWebDriver();
		webDriver.manage().window().maximize();
		//webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		webDriver.get(ConfigurationManager.getInstance().getConfig().getApplicationURL());
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		setPrimePenguinTabHandler(webDriver.getWindowHandle());
		TestListener.setWebDriver(webDriver);
	}
	
	/**
	 * Method tearDown - closing the web driver 
	 * Annotation BeforClass - The annotated method will be run after the test method in a current class has its run
	 */
	@AfterClass
	public void tearDown() {
		driverManager.quitWebDriver();
	}
	
	public String getPrimePenguinTabHandler() {
		return primePenguinTabHandler;
	}

	public void setPrimePenguinTabHandler(String primePenguinTabHandler) {
		this.primePenguinTabHandler = primePenguinTabHandler;
	}
}
