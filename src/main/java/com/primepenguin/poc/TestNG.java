package com.primepenguin.poc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.primepenguin.core.configuration.EnvironmentConstants;
import com.primepenguin.core.driver.AbstractDriverManager;
import com.primepenguin.core.driver.DriverManagerFactory;
import com.primepenguin.core.driver.DriverType;

public class TestNG {

	AbstractDriverManager driverManager;
	WebDriver webDriver;
	 
	  
	  @BeforeClass
	  public void beforeClass() {
	   
		driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
		webDriver = driverManager.getWebDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.get("http://google.com");
	  }

	  @Test
	  public void openMyBlog() {
		WebElement e = webDriver.findElement(By.xpath("//a[text()='Gmailssds']"));
		System.out.println(e.getText());
	  }
	  
	  @Test
	  public void openMySBlog() {
		WebElement e = webDriver.findElement(By.xpath("//a[text()='Gmail']"));
		System.out.println(e.getText());
	  }
	 
	  @AfterClass
	  public void afterClass() {
		  driverManager.quitWebDriver();
	  }
}
