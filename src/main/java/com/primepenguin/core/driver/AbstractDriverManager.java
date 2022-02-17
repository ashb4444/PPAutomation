package com.primepenguin.core.driver;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverManager {

	protected WebDriver webDriver;
	protected abstract void createWebDriver();
	
	public void quitWebDriver() {
		if(webDriver!=null) {
			webDriver.close();
			webDriver.quit();
			webDriver=null;
		}
	}
	
	public WebDriver getWebDriver() {
		if(webDriver==null) {
			createWebDriver();
		}
		return webDriver;
	}
}
