package com.nagarro.automation.test.config;
/**
 * TestBase do all the configuration before executing test
 *
 * @author Palvika
 */

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.nagarro.automation.driver.SetDriver;
import com.nagarro.automation.reporting.TestReport;
import com.nagarro.automation.util.PropertyReader;

public class TestBase {
	
		public TestReport report;
		public  WebDriver driver;
		SetDriver register;
		PropertyReader Prop;
	   
	    @BeforeSuite
	    public void CreateHTMLReport() throws IOException {
	        report=new TestReport();
	        report.createReport();
	    }
	    @BeforeTest
		
		public void invokeBrowser() throws IOException
		{
			register=new SetDriver();
			driver=register.InvokeDriver();
			Prop=new PropertyReader();
			driver.get(Prop.getURL());
			
		}
		

	    @BeforeMethod
	    public void CreateTest(Method method) {
	       
	    	report=new TestReport(method);
	    	report.createTest();
	    }

	    @AfterMethod
	    public void AddResult(ITestResult result) throws IOException {
	        
	    	report=new TestReport(result, driver);
	    	report.addResult();
	    }

	    @AfterSuite
	    public void afterSuite() {
	        report=new TestReport();
	        report.flushReportInstance();
	    }

}
