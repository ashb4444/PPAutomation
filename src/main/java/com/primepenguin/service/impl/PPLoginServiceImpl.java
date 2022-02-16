package com.primepenguin.service.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.primepenguin.core.exception.WebElementException;
import com.primepenguin.core.util.CommonUtil;
import com.primepenguin.core.util.ConfigurationManager;
import com.primepenguin.core.util.extentreports.ExtentTestManager;
import com.primepenguin.model.UserType;
import com.primepenguin.pagerepo.LoginPageRepo;
import com.primepenguin.service.IPPLoginService;

public class PPLoginServiceImpl implements IPPLoginService {

	private WebDriver webDriver;
	private UserType userType;
	private static Logger logger = Logger.getLogger(PPLoginServiceImpl.class);
	
	public PPLoginServiceImpl(WebDriver webDriver, UserType userType) {
		this.webDriver = webDriver;
		this.userType = userType;
	}
	
	@Override
	public void loginToPrimePenguin() {
		
		logger.info("Inside the method loginToPrimePenguin()");
		ExtentTestManager.startTest("loginToPrimePenguin", "Prime Penguin Login Test");
		try {
			logger.info("Calling method setTenancyName()");
			ExtentTestManager.getTest().log(Status.INFO, "Setting the tenancy name");
			setTenancyName();
			logger.info("Calling method setUserName()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the user name");
			setUserName();
			logger.info("Calling method setPassword()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering the password");
			setPassword();
			logger.info("Calling method clickOnLoginButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the login button");
			clickOnLoginButton();
			CommonUtil.waitForLoaderDissappear(webDriver);
			if(userType.equals(UserType.ESHOP)) {
				if(verifyIsUserNameDisplayed(ConfigurationManager.getInstance().getConfig().getEshopTenancyName())) {
					ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in to the Prime Penguin application");
				}else {
					ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to prime penguin application");
					Assert.fail();
				}
			}
		} catch (WebElementException e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to prime penguin application: " + e.getMessage());
			Assert.fail();
		} catch(Exception e) {
			logger.info("Exception",e);
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to login to prime penguin application: " + e.getMessage());
			Assert.fail();
		}
	}
	
	private void setTenancyName() throws WebElementException, InterruptedException {
		
		logger.info("Inside the method setTenancyName()");
		//webDriver.navigate().refresh();
		
		if(getCompanyName().equalsIgnoreCase("Not selected")) {
			String eShoptenancyName = ConfigurationManager.getInstance().getConfig().getEshopTenancyName();
			logger.info("Calling method clickOnChangeCompanyLink()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Change Company link");
			clickOnChangeCompanyLink();
			logger.info("Calling method clickOnSwitchToCompanyButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Switch To Company Button");
			clickOnSwitchToCompanyButton();
			logger.info("Calling method enterValueInTenancyNameTextField()");
			ExtentTestManager.getTest().log(Status.INFO, "Entering value in the tenancy name field");
			enterValueInTenancyNameTextField(eShoptenancyName);
			logger.info("Calling method clickOnSwitchToTenantButton()");
			ExtentTestManager.getTest().log(Status.INFO, "Clicking on the Switch To TenantButton");
			clickOnSwitchToTenantButton();
			CommonUtil.pauseExecution(10000);
			verifyIfCompanyNameIsChanged(eShoptenancyName);
		}
	}
	
	private void setUserName() throws WebElementException {
		
		//webDriver.navigate().refresh();
		if(userType.equals(UserType.ESHOP)) {
			enterValueInUserNameTextField(ConfigurationManager.getInstance().getConfig().getEshopUserName());
		}else if(userType.equals(UserType.LOGISTIC_PROVIDER)) {
			enterValueInUserNameTextField(ConfigurationManager.getInstance().getConfig().getLpUserName());
		}
	}
	
	private void setPassword() throws WebElementException {
		
		if(userType.equals(UserType.ESHOP)) {
			enterValueInPasswordTextField(ConfigurationManager.getInstance().getConfig().getEshopPassword());
		}else if(userType.equals(UserType.LOGISTIC_PROVIDER)) {
			enterValueInPasswordTextField(ConfigurationManager.getInstance().getConfig().getLpPassword());
		}
	}
	
	private String getCompanyName() throws WebElementException {
		
		logger.info("Inside the method getLoggedInUserName()");
		try {
			return CommonUtil.getElementText(webDriver,LoginPageRepo.COMPANY_NAME_LOCATOR_TYPE,LoginPageRepo.COMPANY_NAME_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to get the text of the Web element: " + LoginPageRepo.COMPANY_NAME_LOCATOR_VALUE + 
					" in the method getCompanyName()", webElementException);
		}
	}
	
	private void clickOnChangeCompanyLink() throws WebElementException {
		
		logger.info("Inside the method clickOnChangeCompanyLink()");
		try {
			CommonUtil.clickOnElement(webDriver,LoginPageRepo.CHANGE_COMPANY_NAME_LOCATOR_TYPE, LoginPageRepo.CHANGE_COMPANY_NAME_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + LoginPageRepo.CHANGE_COMPANY_NAME_LOCATOR_VALUE + 
					" in the method clickOnChangeCompanyLink()", webElementException);
		}
	}
	
	private void clickOnSwitchToCompanyButton() throws WebElementException {
		
		logger.info("Inside the method clickOnSwitchToCompanyButton()");
		try {
			CommonUtil.clickOnElement(webDriver, LoginPageRepo.SWITCH_TO_COMPANY_LOCATOR_TYPE, LoginPageRepo.SWITCH_TO_COMPANY_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + LoginPageRepo.SWITCH_TO_COMPANY_LOCATOR_VALUE + 
					" in the method clickOnSwitchToCompanyButton()", webElementException);
		}
	}
	
	private void enterValueInTenancyNameTextField(String tenancyName) throws WebElementException {
		
		logger.info("Inside the method enterValueInTenancyNameTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver,LoginPageRepo.TENANCYNAME_FIELD_LOCATOR_TYPE, LoginPageRepo.TENANCYNAME_FIELD_LOCATOR_VALUE, tenancyName);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + LoginPageRepo.TENANCYNAME_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInTenancyNameTextField()", webElementException);
		}
	}
	
	private void clickOnSwitchToTenantButton() throws WebElementException {
		
		logger.info("Inside the method clickOnSwitchToTenantButton()");
		try {
			CommonUtil.clickOnElement(webDriver, LoginPageRepo.SWITCH_TO_TENANT_BUTTON_LOCATOR_TYPE, LoginPageRepo.SWITCH_TO_TENANT_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + LoginPageRepo.SWITCH_TO_TENANT_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnSwitchToTenantButton()", webElementException);
		}
	}
	
	private void verifyIfCompanyNameIsChanged(String tenancyName) throws WebElementException {
		
		logger.info("Inside the method verifyIfCompanyNameIsChanged()");
		try {
			CommonUtil.isWebElementDisplayed(webDriver, "xpath", "//span[@title='"+tenancyName+"']");
			ExtentTestManager.getTest().log(Status.PASS, "Company name successfully changed");
		} catch(WebElementException webElementException) {
			throw new WebElementException("Web element is not displayed: //span[@title='"+tenancyName+"'] in the method verifyIfCompanyNameIsChanged()", webElementException);
		}
	}
	
	private void clickOnSwitchToHostButton() throws WebElementException {
		
		logger.info("Inside the method clickOnSwitchToHostButton()");
		try {
			CommonUtil.clickOnElement(webDriver, LoginPageRepo.SWITCH_TO_HOST_BUTTON_LOCATOR_TYPE, LoginPageRepo.SWITCH_TO_HOST_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + LoginPageRepo.SWITCH_TO_HOST_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnSwitchToHostButton()", webElementException);
		}
	}
	
	private void enterValueInUserNameTextField(String userName) throws WebElementException {
		
		logger.info("Inside the method enterValueInTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver, LoginPageRepo.USERNAME_FIELD_LOCATOR_TYPE, LoginPageRepo.USERNAME_FIELD_LOCATOR_VALUE, userName);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + LoginPageRepo.USERNAME_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInUserNameTextField()", webElementException);
		}
	}
	
	private void enterValueInPasswordTextField(String password) throws WebElementException {
		
		logger.info("Inside the method enterValueInPasswordTextField()");
		try {
			CommonUtil.enterValueInTextField(webDriver, LoginPageRepo.PASSWORD_FIELD_LOCATOR_TYPE, LoginPageRepo.PASSWORD_FIELD_LOCATOR_VALUE, password);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to enter value in the text field element: " + LoginPageRepo.PASSWORD_FIELD_LOCATOR_VALUE + 
					" in the method enterValueInPasswordTextField()", webElementException);
		}
	}
	
	private void clickOnLoginButton() throws WebElementException {
		
		logger.info("Inside the method clickOnLoginButton()");
		try {
			CommonUtil.clickOnElement(webDriver, LoginPageRepo.LOGIN_BUTTON_LOCATOR_TYPE, LoginPageRepo.LOGIN_BUTTON_LOCATOR_VALUE);
		} catch(WebElementException webElementException) {
			throw new WebElementException("Unable to click on the Web element: " + LoginPageRepo.LOGIN_BUTTON_LOCATOR_VALUE + 
					" in the method clickOnLoginButton()", webElementException);
		}
	}
	
	private boolean verifyIsUserNameDisplayed(String tenancyName) throws WebElementException {
		
		logger.info("Inside the method verifyIsUserNameDisplayed()");
		try {
			if(CommonUtil.isWebElementDisplayed(webDriver, "xpath", "//span[text()='"+tenancyName+"\\']")) {
				return true;
			} else {
				return false;			
			}
		} catch(WebElementException webElementException) {
			throw new WebElementException("Web element is not displayed: //span[text()='"+tenancyName+"\\'] in the method verifyIsUserNameDisplayed()", webElementException);
		}
	}
}
