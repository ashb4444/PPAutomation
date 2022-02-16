package com.primepenguin.core.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primepenguin.model.Configuration;
 
//**********************************************************************************************************
//Description: PropertyManager class reads global configurations and use them during test execution.
//**********************************************************************************************************
public class ConfigurationManager {
 
    private static ConfigurationManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+"\\config.json";
    private Configuration config;
 
    //Create a Singleton instance. We need only one instance of Property Manager.
    public static ConfigurationManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new ConfigurationManager();
                instance.loadData();
            }
        }
        return instance;
    }
 
    //Get all configuration data and assign to related fields.
    private void loadData() {
       
        //Read configuration.json file and map into the config pojo
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	config = mapper.readValue(new File(propertyFilePath), Configuration.class);
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
            e.printStackTrace();
        }
    }
	
    public Configuration getConfig() {
		return config;
	}
}
