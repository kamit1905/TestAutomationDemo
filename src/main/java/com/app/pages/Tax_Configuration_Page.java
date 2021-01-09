package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Tax_Configuration_Page extends TestBase{
	
	@FindBy(xpath="//td[text()='Tax '][contains(.,'2')]/ancestor::tr[1]//input[@name='taxLabel']")
	WebElement taxLabel_1;

	@FindBy(xpath="//td[text()='Tax '][contains(.,'3')]/ancestor::tr[1]//input[@name='taxLabel']")
	WebElement taxLabel_2;
	
	@FindBy(xpath="//td[text()='Tax '][contains(.,'2')]/ancestor::tr[1]//input[@name='tax']")
	WebElement taxLabel_1_Value;
	
	@FindBy(xpath="//td[text()='Tax '][contains(.,'3')]/ancestor::tr[1]//input[@name='tax']")
	WebElement taxLabel_2_Value;
	
	@FindBy(xpath="//td[text()='Tax 3']/ancestor::tr[1]//input[@name='interTax']")
	WebElement interStateTax; 
	
	
	@FindBy(xpath="//td[text()='Tax 3']/ancestor::tr[1]//input[@name='outerTax']")
	WebElement outerStateTax;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	//Applicable
	public Tax_Configuration_Page() {
		System.out.println("In tax configuration page");
		PageFactory.initElements(driver, this);
	}
	
	public void UnselectTaxConfiguration(String taxlabel) {
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//input[@placeholder='"+taxlabel+"']/ancestor::tr[1]//input[@name='Applicable']")).click();
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
	}
	
	public void AddTaxConfiguration(String taxlabel,String taxlabel1,String taxlabelvalues,String taxlabelvalues1,String yesno) {
		
		
			Common_Methods.waitForWebElement(driver, taxLabel_1);
			taxLabel_1.clear();
			taxLabel_1.sendKeys(taxlabel);
			
			Common_Methods.waitForWebElement(driver, taxLabel_2);
			taxLabel_2.clear();
			taxLabel_2.sendKeys(taxlabel1);
			
			Common_Methods.waitForWebElement(driver, taxLabel_1_Value);
			taxLabel_1_Value.clear();
			taxLabel_1_Value.sendKeys(taxlabelvalues);
			
			Common_Methods.waitForWebElement(driver, taxLabel_2_Value);
			taxLabel_2_Value.clear();
			taxLabel_2_Value.sendKeys(taxlabelvalues1);
			
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[text()='Tax '][contains(.,'2')]/ancestor::tr[1]//input[@name='Applicable']")).click();
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[text()='Tax '][contains(.,'3')]/ancestor::tr[1]//input[@name='Applicable']")).click();
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@id='TaxReturn']//option", yesno);
			
		//Common_Methods.waitForWebElement(driver, saveBtn);
		//saveBtn.click();
		
	}

}
