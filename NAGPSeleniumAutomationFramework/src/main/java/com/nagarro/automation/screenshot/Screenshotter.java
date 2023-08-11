package com.nagarro.automation.screenshot;
/**
 * Screenshotter  is for cpaturing the screenshot
 *
 * @author Palvika
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.nagarro.automation.formats.FrameworksFileFormats;

public class Screenshotter {
	
	static WebDriver driver;
	public Screenshotter(WebDriver driver)
	{
		
		this.driver=driver;
	}
	
	
	public static String capture(String TCName) throws IOException {
		
		
		String currentPath=System.getProperty("user.dir");

		TakesScreenshot scrShot =((TakesScreenshot)driver);
		 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		 String DestPath=currentPath+"/Current test results/"+FrameworksFileFormats.SCREENSHOT_FOLDER_NAME+"/"+TCName+System.currentTimeMillis()+FrameworksFileFormats.SCREENSHOT_EXTENSION;
		 File DestFile=new File(DestPath);
		 FileUtils.copyFile(SrcFile, DestFile);
		return DestPath;
	}
	

}
