package com.nagarro.automation.testCase;

/**
 * RegisterUserTestCase contains all the test cases for RegisterUserTestCase
 *
 * @author Palvika
 */
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.graphbuilder.math.func.RandFunction;
import com.nagarro.automation.pages.CreateAnAccountPage;
import com.nagarro.automation.pages.LandingPage;
import com.nagarro.automation.pages.LoggedInPage;
import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.reporting.TestReport;
import com.nagarro.automation.util.ExcelReader;

public class RegisterUserTestCase {

	static Logger logger = LoggerLoad.config("RegisterUserTestCase");
	static TestReport report = new TestReport();
	WebDriver driver;
	CreateAnAccountPage page;
	LandingPage homepage;
	LoggedInPage loggedpage;
	String FirstName;
	String LastName;
	String Email;
	String Password;
	String ConfirmPassword;
	ExcelReader reader;
	String errorMsg;
	private String PageTitle = "Create New Customer Account";
	private String ExpectedH1 = "My Account";

	public RegisterUserTestCase(WebDriver driver) {
		this.driver = driver;
	}

	// check page load
	private void checkpageLoad() {
		page = new CreateAnAccountPage(driver);
		String Title = page.getPageTitle();
		report.ReportLogger(Status.PASS, "the title of the page" + Title);
		Assert.assertTrue(Title.contains(PageTitle));

	}

	// check success message for the registration
	private void checkSuccessMsg() throws IOException {
		loggedpage = new LoggedInPage(driver);
		String SuccessMsg = loggedpage.getH1();
		logger.info("The expected Heading is " + ExpectedH1 + " and the actual Success Message is "
				+ SuccessMsg);
		Assert.assertTrue(SuccessMsg.contains(ExpectedH1));
	}

	// Register user with the data getting from the data sheet
	private void registerUser() {
		getDataFromSheet();
		Random random = new Random();
		int randomNumber = random.nextInt(100) + 1; // generates a random number between 0 and 99 and adds 1 to shift it
													// to the range 1 to 100
		page = new CreateAnAccountPage(driver);
		page.enterFirstname(randomNumber + FirstName);
		page.enterLastname(randomNumber + LastName);
		page.newsLettercheckbox();
		page.enterEmail(randomNumber + Email);
		page.enterPassword(randomNumber + Password);
		page.enterConfirmpassword(randomNumber + ConfirmPassword);
		page.createAnAccountButton();
	}

	// Open Signup page
	private void OpenCreateAnAccountpage() {
		homepage = new LandingPage(driver);
		homepage.CreateAnAccountCTAClick();
		report.ReportLogger(Status.PASS, "Register page is open");
	}

	// Main TC for registering the user
	public void RegistrationUserTC() throws IOException {
		OpenCreateAnAccountpage();
		checkpageLoad();
		registerUser();
		checkSuccessMsg();
		loggedoutUser();

	}

	// Getting the data from the data sheet
	private void getDataFromSheet() {
		reader = new ExcelReader("RegisterPage");
		int row = reader.getRowCount();
		int col = reader.getColCount();

		if (row == -1 || col == -1) {
			logger.error("The defined sheet is empty");

		} else {
			FirstName = reader.getCellData(0, 1);
			LastName = reader.getCellData(1, 1);
			Email = reader.getCellData(2, 1);
			Password = reader.getCellData(3, 1);
			ConfirmPassword = reader.getCellData(4, 1);

			logger.info("FirstName: " + FirstName);
			logger.info("LastName: " + LastName);
			logger.info("Email: " + Email);
			logger.info("Password: " + Password);
			logger.info("Confirm Password: " + ConfirmPassword);
		}
	}

	// Logged out the user
	private void loggedoutUser() throws IOException {
		loggedpage = new LoggedInPage(driver);
		loggedpage.logoutUser();

	}

	// mandatory fields of the registration page
	private void checkMandatoryFields() {
		page = new CreateAnAccountPage(driver);
		page.createAnAccountButton();
		errorMsg = page.getFirstnameError();
		Assert.assertTrue(errorMsg.contains("required field"));
		report.ReportLogger(Status.PASS, "FirstName is mandatory field");
		errorMsg = page.getLastNameError();
		Assert.assertTrue(errorMsg.contains("required field"));
		report.ReportLogger(Status.PASS, "LastName is mandatory field");
		errorMsg = page.getEmailError();
		Assert.assertTrue(errorMsg.contains("required field"));
		report.ReportLogger(Status.PASS, "Email is mandatory field");
		errorMsg = page.getPasswordError();
		Assert.assertTrue(errorMsg.contains("required field"));
		report.ReportLogger(Status.PASS, "Password is mandatory field");
		errorMsg = page.getConfirmPasswordError();
		Assert.assertTrue(errorMsg.contains("required field"));
		report.ReportLogger(Status.PASS, "ConfirmPassword is mandatory field");

	}

	// Main TC for checking the mandatory fields
	public void RegistrationFieldsTC() {
		OpenCreateAnAccountpage();
		checkpageLoad();
		checkMandatoryFields();

	}

}
