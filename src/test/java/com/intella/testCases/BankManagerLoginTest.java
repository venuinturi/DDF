package com.intella.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.intella.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void BankManagerLogin() throws InterruptedException {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("AddCustomer"))),"login not successful");
		
		log.debug("bankmanager login Complete");
		
	}

}
