package com.nagarro.automation.testCase;

/**
 * ApplyFilterTestCase contains all the test cases for ApplyFilterTestCase
 *
 * @author Palvika
 */
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.automation.pages.AddToCartOverlay;
import com.nagarro.automation.pages.AddToCartPage;
import com.nagarro.automation.pages.BagsPage;
import com.nagarro.automation.pages.GearPage;
import com.nagarro.automation.pages.HomePage;
import com.nagarro.automation.pages.LoginPage;
import com.nagarro.automation.pages.ProductDisplayPage;
import com.nagarro.automation.pages.ProductMetricsPage;
import com.nagarro.automation.reporting.LoggerLoad;

public class ApplyFilterTestCase {

	static Logger logger = LoggerLoad.config("AddToCartTestCase");

	WebDriver driver;
	ProductMetricsPage pmp;
	HomePage homepage;
	GearPage gearpage;
	BagsPage bagspage;
	String ExpectedFilterLabel = "Style";
	String ExpectedFilterValue = "Backpack";

	public ApplyFilterTestCase(WebDriver driver) {
		this.driver = driver;
	}

	// Open home page
	private void OpenHomePage() {
		homepage = new HomePage(driver);
		homepage.OpenhomePage();

	}

	// check the page open
	private void checkpageLoad() {
		homepage = new HomePage(driver);
		String Title = homepage.getPageTitle();
		Assert.assertTrue(Title.contains("Home"));
	}

	// click on gear in the menu
	private void ClickGear() {
		homepage = new HomePage(driver);
		homepage.ClickGear();

	}

	// Click on bags under the gear
	private void ClickBags() {
		gearpage = new GearPage(driver);
		gearpage.clickBags();
	}

	// Apply filter on the bags
	private void ApplyFilter() throws IOException {
		bagspage = new BagsPage(driver);
		bagspage.selectStyleFilter();
	}

	// verify the filter
	private void VerifyFilter() {
		bagspage = new BagsPage(driver);
		String filterlabel = bagspage.getFilterLabel();
		Assert.assertTrue(filterlabel.contains(ExpectedFilterLabel));
		String filtervalue = bagspage.getFilterValue();
		Assert.assertTrue(filtervalue.contains(ExpectedFilterValue));
	}

	// Main TC for apply filter
	public void ApplyFilterTC() throws IOException {
		OpenHomePage();
		checkpageLoad();
		ClickGear();
		ClickBags();
		ApplyFilter();
		VerifyFilter();
	}
}
