package com.primepenguin.poc;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.primepenguin.core.driver.AbstractDriverManager;
import com.primepenguin.core.driver.DriverManagerFactory;
import com.primepenguin.core.driver.DriverType;

public class Test {

	public static void main(String[] args) {
		
//		AbstractDriverManager driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
//		WebDriver webDriver = driverManager.getWebDriver();
//		webDriver.manage().window().maximize();
//		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		webDriver.get("http://google.com");
//		String currentHandle= webDriver.getWindowHandle();
//		
//		WebElement e = webDriver.findElement(By.xpath("//a[text()='Gmail']"));
//		System.out.println(e.getText());
//		
//		((JavascriptExecutor)webDriver).executeScript("window.open()");
//		
//		ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
//		webDriver.switchTo().window(tabs.get(1));
//		webDriver.get("http://slack.com");
//		//webDriver.switchTo().window(tabs.get(0));
//		WebElement e1 = webDriver.findElement(By.xpath("//span[text()='Why Slack?']"));
//		System.out.println(e1.getText());
//		webDriver.switchTo().window(currentHandle);
//		e.click();
		//span[@title='ashish_shop']
//		String text="ashish_shop";
//		System.out.println("//span[text()='"+text+"\\']");
		
//		String text = "Order #34 details";
//		System.out.println(text.substring(text.indexOf("#")+1, text.indexOf(" de")));
		
		String text = " Total: 1 ";
		text=text.trim();
		System.out.println(text);
		System.out.println(text.substring(text.indexOf(":")+2));
	}
}
