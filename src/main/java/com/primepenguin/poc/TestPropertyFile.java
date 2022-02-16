package com.primepenguin.poc;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestPropertyFile {

	String result = "";
	InputStream inputStream;
	
	public String getPropValues() throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = System.getProperty("user.dir")+"\\config.properties";
			
			inputStream = new FileInputStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			String user = prop.getProperty("user");
			String company1 = prop.getProperty("company1");
			String company2 = prop.getProperty("company2");
			String company3 = prop.getProperty("company3");
 
			result = "Company List = " + company1 + ", " + company2 + ", " + company3;
			System.out.println(result + " by user=" + user);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
	
	public static void main(String[] args) {
		TestPropertyFile f = new TestPropertyFile();
		try {
			f.getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
