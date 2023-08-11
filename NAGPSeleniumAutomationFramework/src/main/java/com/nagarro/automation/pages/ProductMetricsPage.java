package com.nagarro.automation.pages;

/**
 * ProductMetricsPage  is having all the elements of the ProductMetricsPage
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

public class ProductMetricsPage {
	static Logger logger = LoggerLoad.config("ProductMetricsPage");

	WebDriver driver;

	@FindBy(xpath = "//div[@class='product-item-info']/a[1]")
	private WebElement firstItem;

	@FindBy(xpath = "//h1/span")
	private WebElement searchtext;

	public ProductMetricsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Select first item on the listing page
	public void selectFirstItem() {
		firstItem.click();
		logger.info("First item of the Product Metrics page is clicked");
	}

	// Get search result message
	public String getSearchResult() {
		String SearchText = searchtext.getText();
		logger.info("The search text on the page after search result");
		return SearchText;
	}

	// Get the page title
	public String getPageTitle() {
		logger.info("The Page title of the Product Metrics Page " + driver.getTitle());
		return driver.getTitle();
	}
}
