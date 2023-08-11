package com.nagarro.automation.testCase;

/**
 * LoginTestCase contains all the test cases for Login functionality
 *
 * @author Palvika
 */

import java.awt.Checkbox;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.automation.pages.HomePage;
import com.nagarro.automation.pages.LandingPage;
import com.nagarro.automation.pages.LoggedInPage;
import com.nagarro.automation.pages.LoginPage;
import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.ExcelReader;

public class LoginTestCase {

	static Logger logger = LoggerLoad.config("LoginTestCase");
	private String PageTitle = "Customer Login";
	private String EmailErrorMsg = "Please enter a valid email address";
	private String IncorrectCredError = "The account sign-in was incorrect";
	WebDriver driver;
	LoginPage page;
	LandingPage landingPage;
	HomePage homepage;
	ExcelReader reader;
	String UserName;
	String password;
	LoggedInPage loggedpage;

	public LoginTestCase(WebDriver driver) {
		this.driver = driver;
	}

	// Check page load
	private void checkpageLoad() {
		page = new LoginPage(driver);
		String Title = page.getPageTitle();
		Assert.assertTrue(Title.contains(PageTitle));
	}

	// Login into the site with username and password from the sheet
	private void logininto() {
		getDataFromSheet();
		page = new LoginPage(driver);
		page.enterUserName(UserName);
		page.enterPassword(password);
		page.signupbutton();

	}

	// Check login with out giving credentials
	private void loginWOCredentials() {
		page = new LoginPage(driver);
		page.signupbutton();
	}

	// Get the Alert message for without credentials
	private void checkAlertMsg() {
		page = new LoginPage(driver);
		String msg = page.getAlertMsg();
		Assert.assertTrue(msg.contains("A login and a password are required."));
	}

	// Check the login message
	private String loginMsg() {
		homepage = new HomePage(driver);
		String msg = homepage.getLoginUserMsg();
		return msg;

	}

	// Main TC for login functionality
	public void LoginTC() throws IOException {
		loggedoutUser();
		OpenLoginpage();
		checkpageLoad();
		logininto();
		String msg = loginMsg();
		Assert.assertTrue(msg.contains("Welcome"));
		loggedoutUser();
	}

	// Open Login page
	private void OpenLoginpage() {
		landingPage = new LandingPage(driver);
		landingPage.SignupCTAClick();
	}

	// get the data from data sheet
	private void getDataFromSheet() {
		reader = new ExcelReader("RegisterPage");
		int row = reader.getRowCount();
		int col = reader.getColCount();

		if (row == -1 || col == -1) {
			logger.error("The defined sheet is empty");

		} else {
			UserName = reader.getCellData(2, 1);
			password = reader.getCellData(4, 1);
			logger.info("UserName: " + UserName);
			logger.info("Password: " + password);

		}

	}

	// login with Invalid credentials
	private void loginWithInvalidCred() throws IOException {
		page = new LoginPage(driver);
		page.enterUserName("Hello");
		page.enterPassword("hello");
		page.signupbutton();

	}

	// Login with incorrect credentials
	private void loginWithIncorrectCred() {
		page = new LoginPage(driver);
		page.enterUserName("Hello@gmail.com");
		page.enterPassword("hello");
		page.signupbutton();
	}

	// get the alert message for incorrect credentials
	private void getAlertMsg() {
		page = new LoginPage(driver);
		String alertError = page.getAlertMsg();
		Assert.assertTrue(alertError.contains(IncorrectCredError));

	}

	// get the email error as validation fails for email
	private void getEmailError() {
		page = new LoginPage(driver);
		String errormsg = page.getEmailError();
		Assert.assertTrue(errormsg.contains(EmailErrorMsg));
	}

	// Main TC for login without credentials
	public void LoginWithoutCredentialsTC() {
		OpenLoginpage();
		checkpageLoad();
		loginWOCredentials();

	}

	// Main TC for login with invalid credentials
	public void LoginWithInvalidCredentialsTC() throws IOException {
		OpenLoginpage();
		checkpageLoad();
		loginWithInvalidCred();
		getEmailError();
	}

	// Main TC for login with Incorrect credentials
	public void LoginWithIncorrectCredentialsTC() {
		OpenLoginpage();
		checkpageLoad();
		loginWithIncorrectCred();

	}

	// Logged out the user
	private void loggedoutUser() throws IOException {
		loggedpage = new LoggedInPage(driver);
		loggedpage.logoutUser();

	}

}