package com.primepenguin.poc;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primepenguin.model.Configuration;

public class TestJSON {
	
	private static TestJSON instance;
    private static final Object lock = new Object();
	private static String propertyFilePath = System.getProperty("user.dir")+"\\config.json";
	private static Configuration config;

	public static Configuration getConfig() {
		return config;
	}

	//Create a Singleton instance. We need only one instance of Property Manager.
    public static TestJSON getInstance () throws JsonParseException, JsonMappingException, IOException {
        if (instance == null) {
            synchronized (lock) {
                instance = new TestJSON();
                instance.loadData();
            }
        }
        return instance;
    }
 
    //Get all configuration data and assign to related fields.
    private void loadData() throws JsonParseException, JsonMappingException, IOException {
       
    	ObjectMapper mapper = new ObjectMapper();
    	config = mapper.readValue(new File(propertyFilePath), Configuration.class);
    }
    
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
//		ObjectMapper mapper = new ObjectMapper();
//        Config config = mapper.readValue(new File(propertyFilePath), Config.class);
//        System.out.println(config.getEshopPassword());
		System.out.println(TestJSON.getInstance().getConfig().getApplicationURL());
	}
}
