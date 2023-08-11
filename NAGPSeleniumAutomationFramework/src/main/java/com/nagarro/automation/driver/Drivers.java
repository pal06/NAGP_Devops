package com.nagarro.automation.driver;

/**
 * Drivers is having all browsers related configuration
 *
 * @author Palvika
 */

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.nagarro.automation.reporting.LoggerLoad;

public class Drivers {

	public WebDriver driver;

	static Logger logger = LoggerLoad.config("Drivers");

	/* Method for ChromeDriver */
	public WebDriver chromeDriver() {
		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--ignore-certificate-errors");
			caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			caps.setCapability("acceptInsecureCert", true);
			caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			ChromeOptions options = new ChromeOptions();
			options.merge(caps);

			driver = new ChromeDriver(options);
			logger.info("going to invoke browser");

			if (driver == null)
				logger.error("exception null driver");
			driver.manage().window().maximize();
			logger.info("Chrome Driver initiatized ");
			driver.manage().deleteAllCookies();

		} catch (Exception e) {
			logger.error("execption is there");
		}
		return driver;
	}

	/* Method for Firefox Driver */
	public WebDriver firefoxDriver() {
		try {

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Firefox driver initialized ");
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return driver;
	}

	/* Method for Headless Driver */
	public WebDriver headless() {
		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless"); // For head less feature we need chrome 65 and above version
			chromeOptions.addArguments("--no-sandbox"); // chromedriver should be 2.35 or latest
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--window-size=1325x744");

			caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			caps.setCapability("acceptInsecureCert", true);
			caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			ChromeOptions options = new ChromeOptions();
			options.merge(caps);
			driver = new ChromeDriver(options);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();

			logger.info("Headless driver initiated");
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return driver;
	}

	/* Method for IE Driver */
	public WebDriver IEDriver() {
		try {

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies(); // clears cookies

			logger.info("Internet Explorer driver initialized ");
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return driver;
	}

	/* Method for Safari Driver */
	public WebDriver SafariDriver() {
		try {

			driver = new SafariDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Safari driver initialized ");
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return driver;
	}

}
