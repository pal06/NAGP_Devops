package com.nagarro.automation.pages;

/**
 * AddToCartPage page is having all the elements of the Add To CartPage 
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

public class AddToCartPage {
	static Logger logger = LoggerLoad.config("AddToCartOverlay");
	WebDriver driver;

	@FindBy(xpath = "//td/div[@class='product-item-details']/strong/a")
	private WebElement itemTitle;

	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	public String getPageTitle() {
		logger.info("The Page title of the Add to Cart page " + driver.getTitle());
		return driver.getTitle();
	}

	public String getItemTitle() {
		String Title = itemTitle.getText();
		logger.info("The selected item title " + Title);
		return Title;
	}

}
