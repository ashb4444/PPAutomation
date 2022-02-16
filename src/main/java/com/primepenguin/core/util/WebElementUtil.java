package com.primepenguin.core.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.primepenguin.core.exception.WebElementException;

public class WebElementUtil {

	public static WebElement findWebElementUntilVisible(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		WebElement webElement = null;
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		try {
			switch(locatorType){
			case "id":
				webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				break;
			case "name":
				webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				break;
			case "xpath":
				webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				break;
			case "className":
				webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				break;
			case "cssSelector":
				webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				break;
			}
		} catch(Exception e) {
			throw new WebElementException("Web element not found: " + locatorValue + " in the method findWebElementUntilVisible in the class WebElementUtil.java");
		}
		return webElement;
	}
	
	public static WebElement findWebElementUntilClickable(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		WebElement webElement = null;
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(5))
		        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		try {
			switch(locatorType){
			case "id":
				webElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				break;
			case "name":
				webElement = wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
				break;
			case "xpath":
				webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
				break;
			case "className":
				webElement = wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
				break;
			case "cssSelector":
				webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
				break;
			}
		} catch(Exception e) {
			throw new WebElementException("Web element not found: " + locatorValue + " in the method findWebElementUntilClickable in the class WebElementUtil.java",e);
		}
		return webElement;
	}

	public static List<WebElement> findWebElementsUntilClickable(WebDriver webDriver, String locatorType, String locatorValue){
	
		List<WebElement> webElements = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		switch(locatorType){
		case "id":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			break;
		case "name":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.name(locatorValue)))));
			break;
		case "xpath":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.xpath(locatorValue)))));
			break;
		case "className":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.className(locatorValue)))));
			break;
		case "cssSelector":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.cssSelector(locatorValue)))));
			break;
		}
		return webElements;
	}
	
	public static List<WebElement> findWebElementsUntilVisible(WebDriver webDriver, String locatorType, String locatorValue){
		
		List<WebElement> webElements = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		switch(locatorType){
		case "id":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			break;
		case "name":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.name(locatorValue)))));
			break;
		case "xpath":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.xpath(locatorValue)))));
			break;
		case "className":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.className(locatorValue)))));
			break;
		case "cssSelector":
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements((webDriver.findElements(By.cssSelector(locatorValue)))));
			break;
		}
		return webElements;
	}
}
