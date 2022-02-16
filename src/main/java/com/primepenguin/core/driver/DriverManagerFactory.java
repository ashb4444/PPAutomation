package com.primepenguin.core.driver;

public class DriverManagerFactory {

	public static AbstractDriverManager getDriverManager(DriverType driverType) {
		
		AbstractDriverManager driverManager=null;
		switch (driverType) {
		case CHROME:
			driverManager= new ChromeDriverManager(); //created object of class implementing the abstract class
			break;

		default:
			break;
		}
		return driverManager;
	}
}
