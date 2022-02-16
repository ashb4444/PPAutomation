package com.primepenguin.core.driver;

import org.openqa.selenium.chrome.ChromeDriver;

import com.primepenguin.core.configuration.EnvironmentConstants;

public class ChromeDriverManager extends AbstractDriverManager {

	@Override
	protected void createWebDriver() {
		System.setProperty(EnvironmentConstants.CHROME_DRIVER_KEY, EnvironmentConstants.CHROME_DRIVER_PATH);
		this.webDriver= new ChromeDriver();
	}
}
