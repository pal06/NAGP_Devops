package com.nagarro.automation.driver;

/**
 * SetDrivers set the browser
 *
 * @author Palvika
 */

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.GetProperty;
import com.nagarro.automation.util.PropertyReader;

import org.apache.log4j.Logger;

public class SetDriver {

	public WebDriver SeleniumDriver;

	public Drivers Driver;
	PropertyReader pr;

	static Logger logger = LoggerLoad.config("SetDriver");

	String getDriverValue() throws IOException {

		pr = new PropertyReader();
		return pr.getDriverValue();

	}

	/* Invoke the browser */
	public WebDriver InvokeDriver() throws IOException

	{
		Driver = new Drivers();
		String browser = getDriverValue();

		if (browser.equalsIgnoreCase("chrome")) {
			logger.info("Going to invoke the browser Chrome==>" + browser);
			try {

				SeleniumDriver = Driver.chromeDriver();
				logger.info(SeleniumDriver);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (browser.equalsIgnoreCase("fireforx")) {
			logger.info("Going to invoke the browser Firefox");
			try {

				SeleniumDriver = Driver.firefoxDriver();
				logger.info(SeleniumDriver);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase("headless")) {
			logger.info("Going to invoke the browser Headless==>" + browser);
			try {

				SeleniumDriver = Driver.headless();
				logger.info(SeleniumDriver);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase("InternetExplorer")) {
			logger.info("Going to invoke the browser Internet Explorer==>" + browser);
			try {

				SeleniumDriver = Driver.IEDriver();
				logger.info(SeleniumDriver);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (browser.equalsIgnoreCase("Safari")) {
			logger.info("Going to invoke the browser Safari==>" + browser);
			String osName = System.getProperty("os.name");
			if (osName.equalsIgnoreCase("iOS")) {
				try {

					SeleniumDriver = Driver.SafariDriver();
					logger.info(SeleniumDriver);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else
				logger.error("OS is incompatible with the browser Safari");

		}

		else {
			logger.info("Going to invoke the browser Chrome");
			SeleniumDriver = Driver.chromeDriver();
		}

		return SeleniumDriver;

	}

}
