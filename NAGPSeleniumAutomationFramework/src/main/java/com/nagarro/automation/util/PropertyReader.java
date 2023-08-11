package com.nagarro.automation.util;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class PropertyReader {
	
	public Properties prop;
	public String getDriverValue() throws IOException
	{
		prop = GetProperty.readProperty();
		String driverValue = prop.getProperty("Browser");
		return driverValue;
	}
	
	public String getURL() throws IOException
	{
		
		prop = GetProperty.readProperty();
		String URL = prop.getProperty("URL");
		return URL;
	}
	
	public int getWaitTime() throws IOException
	{
		prop=GetProperty.readProperty();
		 int waitTime = Integer.parseInt(prop.getProperty("Wait.time"));
		return waitTime;
	}

}
