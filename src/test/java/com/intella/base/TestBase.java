package com.intella.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.intella.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static WebDriver driver;
	public static Properties config=new Properties();
	public static Properties OR= new Properties();
	public static Logger log= Logger.getLogger("devpenoyLogger");
	public static ExcelReader er = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public static FileInputStream fis;

	@BeforeSuite
	public void setup() throws IOException {
		
		if(driver==null) {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("config file loaded");
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR file loaded");
			System.out.println(config.getProperty("testsiteurl"));
			System.out.println(OR.getProperty("bmlBtn"));
		}
		if(config.getProperty("browser").equals("chrome")) {
			log.debug("Initialise chrome driver");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(config.getProperty("browser").equals("edge")) {
			log.debug("Initialise Edge driver");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Opened the requested URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,4);
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}
		
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	
}
