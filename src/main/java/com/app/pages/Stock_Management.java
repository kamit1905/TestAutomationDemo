package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Stock_Management extends TestBase{
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	//@FindBy(xpath="//td[text()='%replacable%']/ancestor::tr[1]//input[@placeholder='New Stock']")
	//WebElement productstockinput;
	String productstockinput="//td[text()='%replacable%']/ancestor::tr[1]//input[@placeholder='New Stock']";
	
	public Stock_Management() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void addNewStock(String pname,String newstock,String damagestock) {
		Common_Methods.shortWait();
		driver.findElement(Common_Methods.get(productstockinput,pname)).sendKeys(newstock);
		//driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//input[@placeholder='New Stock']")).sendKeys(newstock);
		
		//driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[3]")).sendKeys("25");
		//driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[3]")).sendKeys("5");
		
		driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//input[@placeholder='Damage Stock']")).sendKeys(damagestock);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
	}
	
	public void verifyStockAfterUpdate(String pname,int lateststock) {
		
		String getLatestStock=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getLatestStock "+getLatestStock);
		
		Assert.assertEquals(getLatestStock, String.valueOf(lateststock),"Getting Wrong Latest Stock");
			
	}
	
	public int GetLatestStockQty(String pname){
		String getLatestStock=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getLatestStock "+getLatestStock);
		
		return Integer.parseInt(getLatestStock);
	}

}
