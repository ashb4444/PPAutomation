package com.primepenguin.core.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.primepenguin.core.exception.WebElementException;
import com.primepenguin.pagerepo.SalesChannelsCommonRepo;

public class CommonUtil {

	public static void enterValueInTextField(WebDriver webDriver, String locatorType, String locatorValue, String value) throws WebElementException  {
		
		WebElement element = WebElementUtil.findWebElementUntilVisible(webDriver, locatorType, locatorValue);
		
		if(element.isEnabled()) {
			element.sendKeys(value);
		} else {
			throw new WebElementException("Web element: " + locatorValue + " is not enabled");
		}
	}
	
	public static void clickOnElement(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		WebElement element = WebElementUtil.findWebElementUntilClickable(webDriver, locatorType, locatorValue);
		
		if(element.isEnabled()) {
			try {
				element.click();
			} catch(Exception exception) {
				throw new WebElementException("Web element not clickable: " + locatorValue + " in the method clickOnElement", exception);
			}
		} else {
			throw new WebElementException("Web element: " + locatorValue + " is not enabled");
		}
	}
	
	public static void moveToElementAndClick(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		Actions action =  new Actions(webDriver);
		try {
			switch(locatorType){
			case "xpath":
				action.moveToElement(webDriver.findElement(By.xpath(locatorValue))).click().perform();
				break;
			case "id":
				action.moveToElement(webDriver.findElement(By.id(locatorValue))).click().perform();
				break;
			}
		} catch(Exception exception) {
			throw new WebElementException("Web element not clickable: " + locatorValue + " in the method clickOnElement", exception);
		}
	}
	
	public static void moveToElementAndEnterText(WebDriver webDriver, String locatorType, String locatorValue, String text) throws WebElementException {
		
		Actions action =  new Actions(webDriver);
		try {
			switch(locatorType){
			case "xpath":
				action.moveToElement(webDriver.findElement(By.xpath(locatorValue))).sendKeys(text).perform();
				break;
			case "id":
				action.moveToElement(webDriver.findElement(By.xpath(locatorValue))).sendKeys(text).perform();
				break;
			}
		} catch(Exception exception) {
			throw new WebElementException("Web element not clickable: " + locatorValue + " in the method clickOnElement", exception);
		}
	}
	
	public static String getElementText(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		WebElement element = WebElementUtil.findWebElementUntilClickable(webDriver, locatorType, locatorValue);
		
		if(element.isDisplayed()) {
			return element.getText();
		} else {
			throw new WebElementException("Web element: " + locatorValue + " is not displayed");
		}
	}
	
	public static boolean isWebElementDisplayed(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException {
		
		WebElement element = WebElementUtil.findWebElementUntilVisible(webDriver, locatorType, locatorValue);
		if(element.isDisplayed()) {
			return true;
		} else {
			throw new WebElementException("Web element: " + locatorValue + " is not displayed");
		}
	}
	
	public static void pauseExecution(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}
	
	public static void openNewTab(WebDriver webDriver) {
		((JavascriptExecutor)webDriver).executeScript("window.open()");
	}
	
	public static void waitForLoaderDissappear(WebDriver webDriver) throws WebElementException {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(60))
		        .pollingEvery(Duration.ofSeconds(6)).ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(SalesChannelsCommonRepo.LOADER_LOCATOR_VALUE)));
		} catch(Exception e) {
			throw new WebElementException("Web element not found: " + SalesChannelsCommonRepo.LOADER_LOCATOR_VALUE + " in the method waitForLoaderDissaper");
		}
	}
	
	public static void waitForPageLoad(WebDriver webDriver) {
		
		ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(60))
		        .pollingEvery(Duration.ofSeconds(6)).ignoring(NoSuchElementException.class);
        wait.until(pageLoadCondition);
	}
	
	public static void pressEnterKey(WebDriver webDriver, String locatorType, String locatorValue) throws WebElementException  {
		
		WebElement element = WebElementUtil.findWebElementUntilVisible(webDriver, locatorType, locatorValue);
		
		if(element.isEnabled()) {
			try {
				element.sendKeys(Keys.ENTER);
			} catch(Exception exception) {
				throw new WebElementException("Not able to press enter key: " + locatorValue + " in the method pressEnterKey", exception);
			}
		} else {
			throw new WebElementException("Web element: " + locatorValue + " is not enabled");
		}
	}
	
	public static void selectValueFromDropDown(WebDriver webDriver, String locatorType, String locatorValue, String value) throws WebElementException {
		
		WebElement webElement = WebElementUtil.findWebElementUntilVisible(webDriver, locatorType, locatorValue);

		Select select = new Select(webElement);
		List<WebElement> webElements = select.getOptions();
		try {
			for(WebElement element : webElements) {
				if(element.getText().equals(value)) {
					try {
						element.click();
					} catch(Exception exception) {
						throw new WebElementException("Web element not clickable: " + locatorValue + " in the method selectValueFromSelectDropDown", exception);
					}
				}
			}
		} catch(Exception e) {
			throw new WebElementException("Not able to select value from the drop down in the method selectValueFromSelectDropDown");
		}
	}
}
