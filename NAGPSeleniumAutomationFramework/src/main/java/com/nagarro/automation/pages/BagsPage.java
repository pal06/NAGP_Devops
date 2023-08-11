package com.nagarro.automation.pages;

/**
 * BagsPage page is having all the elements of the BagsPage
 *
 * @author Palvika
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.nagarro.automation.reporting.LoggerLoad;
import com.nagarro.automation.util.ElementWait;

public class BagsPage {

	static Logger logger = LoggerLoad.config("BagsPage");
	WebDriver driver;
	ElementWait wait;

	@FindBy(xpath = "//div[contains(@class,'filter-options-title')][text()='Style']")
	private WebElement styleFilter;

	@FindBy(xpath = "//div[contains(@class,'filter-options-title')][text()='Style']//following-sibling::div/ol/li/a[contains(@href,'style_bags=24')]")
	private WebElement bagPackStyleFilter;

	@FindBy(xpath = "//span[@class='filter-label']")
	private WebElement filterLabel;

	@FindBy(xpath = "//span[@class='filter-value']")
	private WebElement filterValue;

	public BagsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	public void selectStyleFilter() throws IOException {
		wait = new ElementWait(driver);
		wait.waitForElementClickable(styleFilter);
		styleFilter.click();
		wait.waitForElementClickable(bagPackStyleFilter);
		bagPackStyleFilter.click();
		logger.info("Style filter is applied");

	}

	public String getFilterLabel() {
		logger.info("Applied filter label is " + filterLabel.getText());
		return filterLabel.getText();
	}

	public String getFilterValue() {
		logger.info("Applied filter value is " + filterValue.getText());
		return filterValue.getText();
	}

}
