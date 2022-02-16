package com.primepenguin.core.util.extentreports;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static WebDriver webDriver;
	private static Logger logger = Logger.getLogger(TestListener.class);
	
	public static WebDriver getWebDriver() {
		return webDriver;
	}
    
    public void onStart(ITestContext iTestContext) {
        logger.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", getWebDriver());
    }
    
    public void onFinish(ITestContext iTestContext) {
        
    	logger.info("I am in onFinish method " + iTestContext.getName());
        ExtentManager.extentReports.flush();
    }
   
    public void onTestStart(ITestResult iTestResult) {
        
    	//Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }
    
    public void onTestSuccess(ITestResult iTestResult) {
        
    	logger.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
    	String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(getWebDriver())).getScreenshotAs(OutputType.BASE64);
    	ExtentTestManager.getTest().log(Status.PASS, "Test passed",
    	        ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    
    public void onTestFailure(ITestResult iTestResult) {
        
    	logger.info(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        //Object testClass = iTestResult.getInstance();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(getWebDriver())).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed",
        ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    
    public void onTestSkipped(ITestResult iTestResult) {
        
    	logger.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }
    
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
	
	public static void setWebDriver(WebDriver webDriver) {
		TestListener.webDriver = webDriver;
	}

	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
	
//	public void onStart(ITestContext context) {
//		System.out.println("*** Test Suite " + context.getName() + " started ***");
//	}
//
//	public void onFinish(ITestContext context) {
//		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
//		ExtentTestManager.endTest();
//		ExtentManager.getInstance().flush();
//	}
//
//	public void onTestStart(ITestResult result) {
//		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
//		ExtentTestManager.startTest(result.getMethod().getMethodName());
//	}
//
//	public void onTestSuccess(ITestResult result) {
//		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
//		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
//	}
//
//	public void onTestFailure(ITestResult result) {
//		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
//	}
//
//	public void onTestSkipped(ITestResult result) {
//		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
//		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
//	}
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
//	}

}