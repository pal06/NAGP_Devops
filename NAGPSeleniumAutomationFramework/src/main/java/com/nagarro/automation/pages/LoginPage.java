package com.nagarro.automation.pages;

/**
 * LoginPage  is having all the elements of the LoginPage
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.xpath.compiler.Keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.KeyWords;

public class LoginPage {

	static Logger logger = LoggerLoad.config("LoginPage");

	WebDriver driver;
	private String PageTitle = "Customer Login";
	KeyWords key;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement Username;

	@FindBy(xpath = "//*[@id='pass']")
	private WebElement Password;

	@FindBy(xpath = "//*[@id='send2']")
	private WebElement submit;

	@FindBy(partialLinkText = "Forgot Your Password?")
	private WebElement forgetPasswordCTA;

	@FindBy(xpath = "//div[@id='email-error']")
	private WebElement emailError;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Get Page Title
	public String getPageTitle() {
		logger.info("The Page title of the page " + driver.getTitle());
		return driver.getTitle();
	}

	// Enter username
	public void enterUserName(String name) {
		Username.sendKeys(name);
		logger.info("Username is entered in the Username field on Login page");
	}

	// Enter password
	public void enterPassword(String password) {
		Password.sendKeys(password);
		logger.info("Password is entered in the Password field on Login page");
	}

	// Click Signin button
	public void signupbutton() {
		submit.click();
		logger.info("Submit button is clicked");
	}

	// Click forget password CTA
	public void clickForgetPasswordCTA() {
		forgetPasswordCTA.click();
		logger.info("forgetPassword CTA is clicked");
	}

	// get Alert msg
	public String getAlertMsg() {
		key = new KeyWords(driver);
		String msg = key.getAlertText();
		logger.info("The alert message on Login page " + msg);
		return msg;

	}

	// Get Email error
	public String getEmailError() {
		logger.info("The Email error on login page " + emailError.getText());
		return emailError.getText();
	}

}
