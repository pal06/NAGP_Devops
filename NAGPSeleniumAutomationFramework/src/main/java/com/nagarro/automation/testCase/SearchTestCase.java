package com.nagarro.automation.testCase;

/**
 * SearchTestCase contains all the test cases for Search 
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.automation.pages.CreateAnAccountPage;
import com.nagarro.automation.pages.HomePage;
import com.nagarro.automation.pages.ProductMetricsPage;
import com.nagarro.automation.reporting.LoggerLoad;

public class SearchTestCase {

	static Logger logger = LoggerLoad.config("SearchTC");
	WebDriver driver;
	HomePage page;
	ProductMetricsPage pmp;

	public SearchTestCase(WebDriver driver) {
		this.driver = driver;
	}

	// check page load
	private void checkpageLoad() {
		page = new HomePage(driver);
		String Title = page.getPageTitle();
		Assert.assertTrue(Title.contains("Home"));

	}

	// search item
	private void SearchItem() throws IOException {
		page = new HomePage(driver);
		page.InputSearch("tees ");
		page.ClickSearch();
	}

	// Get the search result
	private void getSearchResult() {
		pmp = new ProductMetricsPage(driver);
		String resulttext = pmp.getSearchResult();
		Assert.assertTrue(resulttext.contains("Search results for:"));

	}

	// Open home page
	private void OpenHomePage() {
		page = new HomePage(driver);
		page.OpenhomePage();

	}

	// main TC for search item
	public void SearchTC() throws IOException {

		OpenHomePage();
		checkpageLoad();
		SearchItem();
		getSearchResult();
	}

}
