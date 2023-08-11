package com.nagarro.automation.util;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.automation.reporting.LoggerLoad;

import dev.failsafe.Timeout;

public class ElementWait {
	
	static Logger logger = LoggerLoad.config("ElementWait");
	WebDriver driver;
	PropertyReader prop;
	WebDriverWait wait;
	int waittime;
	
	public ElementWait(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	public void waitForElementClickable(WebElement element) throws IOException
	{
		try {
		waittime=getTime();
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("***************" +e);
			e.printStackTrace();
		}
		logger.info("the value of wait is "+waittime);
		wait=new WebDriverWait(driver, Duration.ofSeconds(waittime));
        // Wait until the element with ID "myElement" is visible
       element = wait.until(ExpectedConditions.elementToBeClickable(element));
      

	}
	
	public int getTime() throws IOException
	{
		prop=new PropertyReader();
		waittime=prop.getWaitTime();
		return waittime;
	}
	
	
		
		
	
	

}
