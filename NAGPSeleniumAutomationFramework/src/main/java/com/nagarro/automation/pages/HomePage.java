package com.nagarro.automation.pages;

/**
 * HomePage page is having all the elements of the HomePage
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
import com.nagarro.automation.util.KeyWords;

public class HomePage {

	static Logger logger = LoggerLoad.config("HomePage");
	@FindBy(xpath = "//div[@class='panel header']/ul[@class='header links']/li[@class='greet welcome']/span")
	private WebElement loginUserMsg;

	@FindBy(xpath = "//input[@id='search']")
	private WebElement searchTextBox;

	@FindBy(xpath = "//button[@title='Search']")
	private WebElement searchIcon;

	@FindBy(xpath = "//a[@class='logo']")
	private WebElement logoIcon;

	@FindBy(xpath = "//nav[@class='navigation']/ul/li/a/span[text()='Gear']//parent::a")
	private WebElement gearnav;

	WebDriver driver;
	ElementWait wait;
	KeyWords keys;

	// Get the login message
	public String getLoginUserMsg() {
		String msg = loginUserMsg.getText();
		logger.info("The login message on the page " + msg);
		return msg;
	}

	// Construtor for this class
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Ente the search text in search box
	public void InputSearch(String item) {
		searchTextBox.sendKeys(item);
		logger.info("Search text is entered in the search field on home page");
	}

	// Click on search
	public void ClickSearch() throws IOException {
		keys = new KeyWords(driver);
		keys.pressKey("Enter");
		logger.info("Search icon is clicked");

	}

	// get the page title
	public String getPageTitle() {
		logger.info("The Page title of the home page " + driver.getTitle());
		return driver.getTitle();
	}
	// Clear Search

	public void clearSearch() {
		searchTextBox.clear();
		logger.info("Search box is clear");

	}

	// Open HomePage
	public void OpenhomePage() {
		logoIcon.click();
		logger.info("Home page is open");
	}

	// Click gear
	public void ClickGear() {
		gearnav.click();
		logger.info("Gear icon is clicked");
	}

}
