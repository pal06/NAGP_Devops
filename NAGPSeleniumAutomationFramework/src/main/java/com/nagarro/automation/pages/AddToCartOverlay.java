package com.nagarro.automation.pages;


/**
 * AddToCartOverlay page is having all the elements of the overlay 
 *
 * @author Palvika
 */

import org.apache.log4j.Logger;
import org.apache.xpath.compiler.Keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.KeyWords;

public class AddToCartOverlay {
	static Logger logger = LoggerLoad.config("AddToCartOverlay");

	WebDriver driver;
	KeyWords key;

	@FindBy(xpath = "//div[@class='header content']/div/a[contains(@class,'showcart')]")
	private WebElement viewCart;

	public AddToCartOverlay(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	public void ClickAddtoCart() {
		String cartURL = viewCart.getAttribute("href");
		key = new KeyWords(driver);
		key.go(cartURL);

	}

}
