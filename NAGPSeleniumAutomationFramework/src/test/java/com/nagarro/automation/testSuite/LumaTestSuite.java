package com.nagarro.automation.testSuite;

/**
 * LumaTestSuite contains all the test cases and it is the Main Suite class
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nagarro.automation.driver.SetDriver;
import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.screenshot.Screenshotter;
import com.nagarro.automation.test.config.TestBase;
import com.nagarro.automation.testCase.AddToCartTestCase;
import com.nagarro.automation.testCase.ApplyFilterTestCase;
import com.nagarro.automation.testCase.LoginTestCase;
import com.nagarro.automation.testCase.RegisterUserTestCase;
import com.nagarro.automation.testCase.SearchTestCase;

public class LumaTestSuite extends TestBase {

	static Logger logger = LoggerLoad.config("LumaTestSuite");
	Screenshotter takescreenshot;
	RegisterUserTestCase Registerpage;
	LoginTestCase loginpage;
	SearchTestCase searchpage;
	AddToCartTestCase atcpage;
	ApplyFilterTestCase filterpage;

	@Test(description = "Verify the login with incorrect credentials", groups = { "Login" }, priority = 1)
	public void VerifyLoginWithIncorrectCred() {

		logger.info("Going to verify the login with Incorrect credentials");
		loginpage = new LoginTestCase(driver);
		loginpage.LoginWithIncorrectCredentialsTC();

	}

	@Test(description = "Verify the login with invalid credentials", groups = { "Login" }, priority = 2)
	public void VerifyLoginWithInvalidCred() throws IOException {
		logger.info("Going to verify the login with invalid credentials");
		loginpage = new LoginTestCase(driver);
		loginpage.LoginWithInvalidCredentialsTC();

	}

	@Test(description = "Verify the Mandatory fields of the login page", groups = { "Login" }, priority = 3)
	public void VerifyLoginWithoutCredentials() {
		logger.info("Going to verify the alert message after clicking on sign in button without giving credentials");
		loginpage = new LoginTestCase(driver);
		loginpage.LoginWithoutCredentialsTC();
	}

	@Test(description = "Verify the Mandatory fields of the Registration page", groups = { "Register" }, priority = 4)
	public void VerifyRegistrationPageMandatoryfield() {
		logger.info("Going to verify the Mandatory fields of the Registration page");
		Registerpage = new RegisterUserTestCase(driver);
		Registerpage.RegistrationFieldsTC();
	}

	@Test(description = "Verify the search functionality by search the item", groups = { "Search" }, priority = 5)
	public void VerifySearch() throws IOException {
		logger.info("Going to Verify the search functionality");
		searchpage = new SearchTestCase(driver);
		searchpage.SearchTC();

	}

	@Test(description = "Verify the filter after applying filter for the bags under gear section", groups = {
			"Search" }, priority = 6)
	public void VerifyApplyFilter() throws IOException {
		logger.info("Going to verify the filter after applying it for the bags under gear section");
		filterpage = new ApplyFilterTestCase(driver);
		filterpage.ApplyFilterTC();

	}

	@Test(description = "Verify the registration functionality by creating the new user for the site", groups = {
			"Register" }, priority = 7)
	public void VerifyCreateAnAccountPage() throws IOException {
		logger.info("Going to execute the Create An Account page Test Cases");
		Registerpage = new RegisterUserTestCase(driver);
		Registerpage.RegistrationUserTC();

	}

	@Test(description = "Verify the login functionality using correct username and password", groups = {
			"Login" }, priority = 8)
	public void VerifyloginPage() throws IOException {
		logger.info("Going to execute the login page Test cases");
		loginpage = new LoginTestCase(driver);
		loginpage.LoginTC();

	}

	@Test(description = "Verify the add to cart functionality by search the item and add it to cart", groups = {
			"AddtoCart" }, priority = 9)
	public void VerifyAddtoCart() throws IOException {
		logger.info("Going to Verify the add to cart functionality");
		atcpage = new AddToCartTestCase(driver);
		atcpage.AddToCartTC();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}
