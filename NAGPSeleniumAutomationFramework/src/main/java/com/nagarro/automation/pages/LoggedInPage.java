package com.nagarro.automation.pages;

/**
 * LoggedInPage  is having all the elements of the LoggedInPage
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.ElementWait;
import com.nagarro.automation.util.KeyWords;

public class LoggedInPage {

	static Logger logger = LoggerLoad.config("LoggedInPage");

	WebDriver driver;

	@FindBy(xpath = "//button[contains(@class,'switch')]")
	private WebElement switchToggle;

	@FindBy(xpath = "//li[@class='greet welcome']/span")
	private WebElement welcomeMsg;

	@FindBy(xpath = "//div[@class='customer-menu']/ul[contains(@class,'header')]/li[@class='authorization-link']/a[1]")
	private WebElement logoutCTA;

	@FindBy(xpath = "//div[@role='alert']/div/div")
	private WebElement registerSuccessMsg;
	
	@FindBy(xpath="//h1/span")
	private WebElement heading;

	ElementWait wait;

//Constructor for this class
	public LoggedInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// check logged in User
	private boolean checkLoggedInUser() throws IOException {
		wait=new ElementWait(driver);
		wait.waitForElementClickable(welcomeMsg);
		String msg = welcomeMsg.getText();
		boolean status = msg.contains("Welcome,");
		return status;
	}

	// get Welcome message
	public String WelcomeMsg() throws IOException {
		wait=new ElementWait(driver);
		wait.waitForElementClickable(welcomeMsg);
		logger.info("The Welcome Msg is " + welcomeMsg.getText());
		return welcomeMsg.getText();
	}
	// getRegister message

	public String getRegisterSuccessMsg() {
		String text = registerSuccessMsg.getText();
		return text;
	}
	//Get the heading element
	public String getH1()
	{
		String text=heading.getText();
		return text;
	}

	// Logout the User
	public void logoutUser() throws IOException {
		wait = new ElementWait(driver);
		boolean status = checkLoggedInUser();
		logger.info("Checking if user is logged in or not");
		if (status) {
			wait.waitForElementClickable(switchToggle);
			switchToggle.click();
			wait.waitForElementClickable(logoutCTA);
			logoutCTA.click();
			logger.info("Going to logout the user");

		} else
			logger.info("User is not logged in on the site");

	}
}
