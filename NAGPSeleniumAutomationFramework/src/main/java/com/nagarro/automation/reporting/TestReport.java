package com.nagarro.automation.reporting;
/**
 * TestReport  is for creating the extent report
 *
 * @author Palvika
 */

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nagarro.automation.formats.CleanUp;
import com.nagarro.automation.screenshot.Screenshotter;

public class TestReport {

	static Logger log = LoggerLoad.config("TestReport");

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	Method method;
	ITestResult result;
	static Screenshotter takeScreenshot;
	WebDriver driver;
	CleanUp clean;

	public TestReport(Method method) {
		this.method = method;

	}

	public TestReport(ITestResult result, WebDriver driver) {
		this.result = result;
		this.driver = driver;
	}

	public TestReport() {
		log.info("Creating a HTML report");

	}

	public void createReport() throws IOException {
		clean = new CleanUp();
		clean.cleanDirectory();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("Current test results/TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Luma Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		addsysteminfo();

	}

	public void createTest() {
		extentTest = extent.createTest(method.getName());

	}

	public void addResult() throws IOException {
		takeScreenshot = new Screenshotter(driver);
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel("Test Case Failed: " + result.getName(), ExtentColor.RED));
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel("Test Case Failed: " + result.getThrowable(), ExtentColor.RED));
			String screenshotPath = Screenshotter.capture(result.getName());
			try {
				extentTest.fail("Screenshot of Failure: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP,
					MarkupHelper.createLabel("Test Case Skipped: " + result.getName(), ExtentColor.ORANGE));
		} else {

			extentTest.log(Status.PASS,
					MarkupHelper.createLabel("Test Case Passed: " + result.getName(), ExtentColor.GREEN));
			String screenshotPath = Screenshotter.capture(result.getName());
			try {
				extentTest.pass("Screenshot of Passed: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void flushReportInstance() {
		extent.flush();
	}

	public void addsysteminfo() {
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS Name", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("User Directory", System.getProperty("user.dir"));

	}
	
	public void ReportLogger(Status status, String MSG)
	{
		extentTest.log(status, MSG);
	}

}
