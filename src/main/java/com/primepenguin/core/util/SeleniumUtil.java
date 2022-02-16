package com.primepenguin.core.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {

	public static WebElement findWebElementUntilVisible(WebDriver webDriver, String locatorType, String locatorValue){
		
		WebElement webElement = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
			        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
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
		} catch(Exception exception) {
			exception.printStackTrace();
			return webElement;
		}
		return webElement;
	}
	
	public static WebElement findWebElementUntilClickable(WebDriver webDriver, String locatorType, String locatorValue){
		
		WebElement webElement = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(30))
			        .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
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
		} catch(Exception exception) {
			exception.printStackTrace();
			return webElement;
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

	public static void waitForPageLoad(WebDriver webDriver) {
		
		ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(pageLoadCondition);
	}
	
	public static void pauseExecution(long milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkAndClickValuePresentInList(List<WebElement> departmentElementList, String value) {
		
		//if the list contains the value which is provided in the test data excel sheet then click on that element and return true else return false
		for(WebElement element: departmentElementList) {
			if(element.getText().equals(value)){
				element.click();
				return true;
			}
		}
		return false;
	}
	
	public static void openNewTab(WebDriver webDriver) {
		((JavascriptExecutor)webDriver).executeScript("window.open()");
	}
	
	public static void selectValueFromSelectDropDown(WebElement webElement, String value) {
		
		Select select = new Select(webElement);
		List<WebElement> webElements = select.getOptions();
		for(WebElement element : webElements) {
			if(element.getText().equals(value)) {
				element.click();
			}
		}
	}
}
