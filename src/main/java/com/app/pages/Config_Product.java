package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Config_Product extends TestBase{
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	public Config_Product() {
		System.out.println("In Config product ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void SaveConfigureProduct(String pname,String qty,String deselectcheckbox) {
		try {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//b[text()='Select Product:']/ancestor::div[2]//td[text()='"+pname+"']/ancestor::tr[1]//input")).click();
			
			if(deselectcheckbox.equals("Deselect")) {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//b[text()='Select Quantity:']/ancestor::div[2]//td[text()='"+qty+"']/ancestor::tr[1]//input")).click();
			}else {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//b[text()='Select Quantity:']/ancestor::div[2]//td[text()='"+qty+"']/ancestor::tr[1]//input")).click();
				
			}
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Common_Methods.toShortWait();
			String getMsg=Common_Methods.getToastMessage();
			//System.out.println("getMsg "+getMsg);
			Assert.assertEquals(getMsg, "Success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void VerifyErrorMessgeIfNotSelected(String qty) {
		
		try {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//b[text()='Select Quantity:']/ancestor::div[2]//td[text()='"+qty+"']/ancestor::tr[1]//input")).click();
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Common_Methods.toShortWait();
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			Assert.assertEquals(getMsg, "Product or Quantity is not selected. Please select product and Quantity both.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
