package com.nagarro.automation.pages;

/**
 * CreateAnAccountPage page is having all the elements of the Create An AccountPage
 *
 * @author Palvika
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.KeyWords;

public class CreateAnAccountPage {

	static Logger logger = LoggerLoad.config("CreateAnAccountPage");

	WebDriver driver;
	KeyWords key;

	@FindBy(xpath = "//*[@id='firstname']")
	private WebElement FistName;

	@FindBy(xpath = "//*[@id='lastname']")
	private WebElement LastName;

	@FindBy(xpath = "//input[@name='is_subscribed']")
	private WebElement NewsLetter;

	@FindBy(xpath = "//*[@id='email_address']")
	private WebElement Email;

	@FindBy(xpath = "//*[@id='password']")
	private WebElement Password;

	@FindBy(xpath = "//*[@id='password-confirmation']")
	private WebElement ConfirmPassword;

	@FindBy(xpath = "//button[@title='Create an Account']")
	private WebElement CreateAnAccountbutton;

	@FindBy(xpath = "//*[@id='firstname-error']")
	private WebElement firstNameError;

	@FindBy(xpath = "//*[@id='lastname-error']")
	private WebElement lastNameError;

	@FindBy(xpath = "//*[@id='email_address-error']")
	private WebElement emailError;

	@FindBy(xpath = "//*[@id='password-error']")
	private WebElement passwordError;

	@FindBy(xpath = "//*[@id='password-confirmation-error']")
	private WebElement confirmpasswordError;

	// Constructor for this class
	public CreateAnAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Get the page Title
	public String getPageTitle() {
		logger.info("The Page title of the Create An Account page " + driver.getTitle());
		return driver.getTitle();
	}

	// Enter first name
	public void enterFirstname(String name) {
		FistName.sendKeys(name);
		logger.info("First name is entered in the FirstName field");
	}

	// Enter last name
	public void enterLastname(String lastname) {
		LastName.sendKeys(lastname);
		logger.info("Last name is entered in the LastName field");
	}

	// Check the checkbox
	public void newsLettercheckbox() {
		NewsLetter.click();
		logger.info("NewsLetter checkbox is checked");
	}

	// Enter the email
	public void enterEmail(String email) {
		Email.sendKeys(email);
		logger.info("Email is entered in the Email field");
	}

	// Enter password
	public void enterPassword(String password) {
		Password.sendKeys(password);
		logger.info("Password is entered in the Password field");
	}

	// Enter confirm password
	public void enterConfirmpassword(String confirmPassword) {
		ConfirmPassword.sendKeys(confirmPassword);
		logger.info("Confirm Password is entered in the ConfirmPassword field");
	}

	// Click on signup button
	public void createAnAccountButton() {
		CreateAnAccountbutton.click();
		logger.info("Create An Account CTA is clicked");
	}

	// get the error message
	public String getFirstnameError() {
		return firstNameError.getText();
	}

	// get the error message
	public String getLastNameError() {
		return lastNameError.getText();
	}

	// get the error message
	public String getEmailError() {
		return emailError.getText();
	}

	// get the error message
	public String getPasswordError() {
		return passwordError.getText();
	}

	// get the error message
	public String getConfirmPasswordError() {
		return confirmpasswordError.getText();
	}

	// get the Success message
	public String getSuccessMsg() {
		key = new KeyWords(driver);
		String msg = key.getAlertText();
		logger.info("The Success Message is " + key.getAlertText());
		return msg;

	}

}
