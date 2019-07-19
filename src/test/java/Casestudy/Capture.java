package Casestudy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.testng.annotations.Test;

public class Capture{

	public static String takeScreenshot(WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		TakesScreenshot ts=(TakesScreenshot) driver;
		  File src=ts.getScreenshotAs(OutputType.FILE);
		  String des=("C:\\Selenium\\screenshot.html");
		  FileUtils.copyFile(src, new File(des));
		  return des;
	}
}
