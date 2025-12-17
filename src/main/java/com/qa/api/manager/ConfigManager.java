package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigManager {
	
	
	
	private static Properties prop = new Properties();
	
	static {
		//String filename = "config.properties";
		String env =System.getProperty("env", "prod");
		String filename = "config_"+env+".properties";
		 InputStream  inputstream =
				 ConfigManager.class.getClassLoader().getResourceAsStream(filename);
		 
		 
		 if(inputstream!=null)
		 {
			 try {
					prop.load(inputstream);
					System.out.println("config properties  ===> " + prop);
				} catch (IOException e) {
					e.printStackTrace();
				}
		 }		
	}
		
	public static String get(String key) {
		return prop.getProperty(key);
		
	}
	
	public static void set(String key, String value) {
		prop.setProperty(key, value);
	}
	
}
