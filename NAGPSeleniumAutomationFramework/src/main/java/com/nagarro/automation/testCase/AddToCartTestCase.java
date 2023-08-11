package com.nagarro.automation.testCase;

/**
 * AddToCartTestCase contains all the test cases for Add to CART
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.automation.pages.AddToCartOverlay;
import com.nagarro.automation.pages.AddToCartPage;
import com.nagarro.automation.pages.HomePage;
import com.nagarro.automation.pages.ProductDisplayPage;
import com.nagarro.automation.pages.ProductMetricsPage;
import com.nagarro.automation.reporting.LoggerLoad;

public class AddToCartTestCase {

	static Logger logger = LoggerLoad.config("AddToCartTestCase");

	WebDriver driver;
	HomePage page;
	ProductMetricsPage pmp;
	ProductDisplayPage pdp;
	AddToCartPage atc;
	AddToCartOverlay atcOverlay;
	LoginTestCase LoginTC;

	public AddToCartTestCase(WebDriver driver) {
		this.driver = driver;
	}

	// First login into the site
	private void loginUser() throws IOException {
		LoginTC = new LoginTestCase(driver);
		LoginTC.LoginTC();

	}

	// check pageload
	private void checkpageLoad() {
		page = new HomePage(driver);
		String Title = page.getPageTitle();
		logger.info("the page title is " + Title);
		Assert.assertTrue(Title.contains("Home"));

	}

	// Search item tees
	private void SearchItem() throws IOException {
		page = new HomePage(driver);
		page.clearSearch();
		page.InputSearch("tees");
		page.ClickSearch();
	}

	// Get the search result message
	private void getSearchResult() {
		pmp = new ProductMetricsPage(driver);
		String resulttext = pmp.getSearchResult();
		Assert.assertTrue(resulttext.contains("Search results for:"));

	}

	// Select first item
	private void AddFirstItem() {
		pmp = new ProductMetricsPage(driver);
		pmp.selectFirstItem();
	}

	// Check items selected
	private void checkItemSelected() {
		pdp = new ProductDisplayPage(driver);
		String title = pdp.getPageTitle();
		Assert.assertTrue(title.contains("Tee"));
	}

	// Select the size of the item
	private void selectSize() {
		pdp = new ProductDisplayPage(driver);
		pdp.selectFirstSize();

	}

	// Select the color of the item
	private void selectColor() {
		pdp = new ProductDisplayPage(driver);
		pdp.selectFirstColor();

	}

	// Select the count of the item
	private void selectNumItem() {
		pdp = new ProductDisplayPage(driver);
		pdp.itemCount("1");

	}

	// add item into cart
	private void ClickAddToCart() {
		pdp = new ProductDisplayPage(driver);
		pdp.ClickAddToCart();
	}

	// Click on add to cart icon
	private void ClickAddtoCartIcon() {
		pdp = new ProductDisplayPage(driver);
		pdp.ClickaddtoCartIcon();
		redirectAddtoCart();

	}

	// redirect add to cart
	private void redirectAddtoCart() {
		atcOverlay = new AddToCartOverlay(driver);
		atcOverlay.ClickAddtoCart();
	}

	// check items added in the cart
	private void checkItemAdded() {
		atc = new AddToCartPage(driver);
		String PageTitle = atc.getPageTitle();
		Assert.assertTrue(PageTitle.contains("Shopping Cart"));
		String itemTitle = atc.getItemTitle();
		Assert.assertTrue(PageTitle.contains("Tee"));

	}

	// Main TC for Add to cart functionality
	public void AddToCartTC() throws IOException {
		loginUser();
		checkpageLoad();
		SearchItem();
		getSearchResult();
		AddFirstItem();
		checkItemSelected();
		selectSize();
		selectColor();
		selectNumItem();
		ClickAddToCart();
		ClickAddtoCartIcon();
		checkItemAdded();
	}

}
