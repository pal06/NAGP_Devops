package com.nagarro.automation.pages;

/**
 * GearPage page is having all the elements of the Gear Page
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

public class GearPage {

	static Logger logger = LoggerLoad.config("GearPage");
	WebDriver driver;

	@FindBy(xpath = "//dl[@id='narrow-by-list2']/dd/ol[@class='items']/li/a[text()='Bags']")
	private WebElement Bags;

	public GearPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Clicking on bags
	public void clickBags() {
		Bags.click();
		logger.info("Bags is selected");

	}

}
