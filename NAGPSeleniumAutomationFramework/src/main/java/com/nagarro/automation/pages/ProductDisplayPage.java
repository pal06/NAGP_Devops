package com.nagarro.automation.pages;

/**
 * ProductDisplayPage  is having all the elements of the ProductDisplayPage
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

public class ProductDisplayPage {

	static Logger logger = LoggerLoad.config("ProductDisplayPage");

	WebDriver driver;

	@FindBy(xpath = "//div[@class='swatch-attribute size']/div/div[1]")
	private WebElement firstSize;

	@FindBy(xpath = "//div[@class='swatch-attribute color']/div/div[1]")
	private WebElement firstColor;

	@FindBy(xpath = "//input[@id='qty']")
	private WebElement itemCount;

	@FindBy(xpath = "//button[@id='product-addtocart-button']")
	private WebElement addToCartCTA;

	@FindBy(xpath = "//div[@class='header content']/div/a[contains(@href,'/checkout/cart/')]")
	private WebElement addToCartIcon;

	public ProductDisplayPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Select first size of the item
	public void selectFirstSize() {
		firstSize.click();
		logger.info("First size of the item is selected");
	}

	// Select first color of the item
	public void selectFirstColor() {
		firstColor.click();
		logger.info("First Color of the item is selected");
	}

	// select the no. of item
	public void itemCount(String items) {
		itemCount.clear();
		itemCount.sendKeys(items);
		logger.info("Count of item is selected");
	}

	// Click on Add to cart button
	public void ClickAddToCart() {
		addToCartCTA.click();
		logger.info("Add to Cart CTA is clicked");
	}

	// Get page title
	public String getPageTitle() {
		logger.info("The Page title of the Add to Cart page " + driver.getTitle());
		return driver.getTitle();
	}

	// Click on add to cart icon
	public void ClickaddtoCartIcon() {
		addToCartIcon.click();
		logger.info("Add to Cart Icon is clicked");

	}

}
