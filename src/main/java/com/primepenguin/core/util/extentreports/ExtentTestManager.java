package com.primepenguin.core.util.extentreports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports            extent        = ExtentManager.createExtentReports();
    
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
	
	
	
//	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
//	static ExtentReports extent = ExtentManager.getInstance();
//
//	public static synchronized ExtentTest getTest() {
//		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
//	}
//
//	public static synchronized void endTest() {
//		extent.flush();
//	}
//
//	public static synchronized ExtentTest startTest(String testName) {
//		ExtentTest test = extent.createTest(testName);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//		return test;
//	}
//	
//	public static synchronized ExtentTest startTest(String testName, String testDescription) {
//		ExtentTest test = extent.createTest(testName,testDescription);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//		return test;
//	}
}