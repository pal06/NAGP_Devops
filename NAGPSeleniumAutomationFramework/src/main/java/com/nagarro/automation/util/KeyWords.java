package com.nagarro.automation.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.nagarro.automation.reporting.LoggerLoad;

public class KeyWords {

	static Logger logger = LoggerLoad.config("KeyWords");
	WebDriver driver;
	Actions act;
	Alert alrt;

	public KeyWords(WebDriver driver) {
		this.driver = driver;

	}

	public void go(String URL) {
		driver.get(URL);
		logger.info("navigated to the URL");
	}

	public void pressKey(String key) {

		act = new Actions(driver);

		switch (key) {
		case "Enter":
			// Press the Enter key
			act.sendKeys(Keys.ENTER).perform();
			logger.info("Enter key is pressed");
			break;
		case "Tab":
			// Press the Tab key
			act.sendKeys(Keys.TAB).perform();
			logger.info("Tab key is pressed");
			break;
		case "Space":
			// Press the Space key
			act.sendKeys(Keys.SPACE).perform();
			logger.info("Space key is pressed");
			break;
		default:
			logger.error("Invalid input");
		}
	}

	public String getAlertText() {
		alrt = driver.switchTo().alert();
		return alrt.getText();

	}

}
