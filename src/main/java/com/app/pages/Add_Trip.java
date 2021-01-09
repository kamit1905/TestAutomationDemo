package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Add_Trip extends TestBase{
	
	@FindBy(xpath="//button[text()='Add']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='tripName']")
	WebElement tripName;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	public Add_Trip() {
		System.out.println("In add trip constructor");
		PageFactory.initElements(driver, this);
	}
	
	public void addTrip(String tname,String [] dboy,String dgroup,String vehicle) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.shortWait();
		Common_Methods.waitForWebElement(driver, tripName);
		tripName.sendKeys(tname);
		
		for(int i=0;i<dboy.length;i++) {
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//div[text()='dboy["+i+"]']/ancestor::div[1]//input[@type='checkbox']")).click();
		}
	
		Common_Methods.selectFromDropDown("//option[contains(text(),'Select Delivery Group')]/ancestor::select[1]//option", dgroup);
		
		Common_Methods.selectFromDropDown("//option[contains(text(),'Select Vehicle')]/ancestor::select[1]//option", vehicle);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
	}
	//20871604

}
