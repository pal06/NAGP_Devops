package com.nagarro.automation.pages;

/**
 * LandingPage  is having all the elements of the LandingPage
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

public class LandingPage {

	static Logger logger = LoggerLoad.config("LandingPage");

	WebDriver driver;

	@FindBy(xpath = "//div[@class='panel header']/ul[@class='header links']/li/a[contains(@href,'/customer/account/create/')]")
	private WebElement CreateAnAccountCTA;

	@FindBy(xpath = "//div[@class='panel header']/ul[@class='header links']/li[2]/a")
	private WebElement SignupCTA;

	// Constructor for this class
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	// Signup CTA click
	public void CreateAnAccountCTAClick() {
		CreateAnAccountCTA.click();
		logger.info("Create An Account CTA is clicked on Landing page");
	}

	// SignIn CTA click
	public void SignupCTAClick() {
		SignupCTA.click();
		logger.info("SignIn CTA is clicked on Landing page");
	}

	// get Page title
	public String getPageTitle() {
		logger.info("The Page title of the landing page " + driver.getTitle());
		return driver.getTitle();
	}

}
