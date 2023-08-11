package com.nagarro.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperty {
	
	public static Properties readProperty() throws IOException {
		String PropertiesPath="src\\main\\resources\\Config\\Config.Properties";
		File file = new File(PropertiesPath);
		InputStream Locator = new FileInputStream(PropertiesPath);
		Properties prop=new Properties();
		prop.load(Locator);
		return prop;
				
	}

}
