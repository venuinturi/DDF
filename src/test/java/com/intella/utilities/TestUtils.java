package com.intella.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.intella.base.TestBase;
//contains all the methods which are commonly used
public class TestUtils extends TestBase{

	public static void captureScreenshot() throws IOException {
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\error.jpg"));
	}
	
}
