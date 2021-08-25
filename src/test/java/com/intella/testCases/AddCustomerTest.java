package com.intella.testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.*;

import com.intella.base.TestBase;

public class AddCustomerTest extends TestBase{
	
	
	@Test (dataProvider="getData")
	public void AddCustomer(String FirstName, String LastName, String PostCode, String AlertText) throws InterruptedException {
		
		driver.findElement(By.cssSelector(OR.getProperty("AddCustomer"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("FirstName"))).sendKeys(FirstName);
		driver.findElement(By.cssSelector(OR.getProperty("LastName"))).sendKeys(LastName);
		driver.findElement(By.cssSelector(OR.getProperty("PostCode"))).sendKeys(PostCode);
		driver.findElement(By.cssSelector(OR.getProperty("submitButton"))).click();
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(AlertText));
		Thread.sleep(2000);
		alert.accept();
		log.debug("customer added "+FirstName);
		Reporter.log(FirstName+" user added");
		
//		Assert.fail("Login Test Failed");
	}
	
	@DataProvider
	public Object[][] getData(){
		
		
		String SheetName="AddCustomerTest";
		int rows=er.getRowCount(SheetName);
		int cols=er.getColumnCount(SheetName);
		
		Object[][] data= new Object[rows-1][cols];
		
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			for(int colNum=0;colNum<cols;colNum++) {
				
				System.out.println("rownum count is"+rowNum);
				System.out.println("ColNum count is"+colNum);
				data[rowNum-2][colNum]=er.getCellData(SheetName, colNum, rowNum);
				
				
			}
		}
		
		return data;
		
		
	}

}
