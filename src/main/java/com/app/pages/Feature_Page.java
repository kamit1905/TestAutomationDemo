package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Feature_Page extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//button[text()='Add Value']")
	WebElement addValueBtn;
	
	@FindBy(xpath="//button[text()='Save']")	
	WebElement saveBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//input[@name='featureName']")
	WebElement featureName;
	
	@FindBy(xpath="//label[contains(text(),'Feature Value')]/ancestor::div[1]//label[contains(.,'1')]/ancestor::div[2]//input")
	WebElement featureValue1;
	
	@FindBy(xpath="//label[contains(text(),'Feature Value')]/ancestor::div[1]//label[contains(.,'2')]/ancestor::div[2]//input")
	WebElement featureValue2;
	
	@FindBy(xpath="//label[contains(text(),'Feature Value')]/ancestor::div[1]//label[contains(.,'3')]/ancestor::div[2]//input")
	WebElement featureValue3;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	public Feature_Page() {
		System.out.println("In Feature page ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddFeature(String featurename,String fvalue1,String fvalue2,String fvalue3) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, addValueBtn);
		addValueBtn.click();
		addValueBtn.click();
		
		Common_Methods.waitForWebElement(driver, featureName);
		featureName.sendKeys(featurename);
		
		Common_Methods.waitForWebElement(driver, featureValue1);
		featureValue1.sendKeys(fvalue1);
		
		Common_Methods.waitForWebElement(driver, featureValue2);
		featureValue2.sendKeys(fvalue2);
		
		Common_Methods.waitForWebElement(driver, featureValue3);
		featureValue3.sendKeys(fvalue3);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		//Assert.assertEquals(getMsg, "");
	}
	
	public void AddFeatureAndVerifyErrorMessage(String featurename) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, featureName);
		featureName.sendKeys(featurename);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		/*Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsf "+getMsg);*/
			
	}
	
	public void SearchFeature(String fname) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.sendKeys(fname);
	}
	
	public void SearchFeature(String fname,String aname) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.sendKeys(fname);
		
		if(aname.equals("Edit")) {
			driver.findElement(By.xpath("//td[text()='"+fname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		}else {
			driver.findElement(By.xpath("//td[text()='"+fname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			searchInput.clear();
		}
		
	}
	

}
