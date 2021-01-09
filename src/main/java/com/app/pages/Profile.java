package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Profile extends TestBase{
	
	@FindBy(xpath="//input[@name='shopMobileNo']")
	WebElement mobNo;
	
	@FindBy(xpath="//input[@name='pincode']")
	WebElement pin;
	
	@FindBy(xpath="//textarea[@name='addressLineOne']")
	WebElement address;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//span[text()='LOGIN']/ancestor::button[1]")
	WebElement signInBtn;
	
	@FindBy(xpath="//li[text()='Account']")
	WebElement accountBtn;
	
	public Profile(){
		System.out.println("In profile ctor");
		PageFactory.initElements(driver, this);
		
	}
	
	public void EditProfile(String shopname,String mobno,String pinc,String shopaddress){
		//driver.findElement(By.xpath("//a[text()='"+shopname+"']")).click();
		Common_Methods.waitForWebElement(driver, accountBtn);
		accountBtn.click();
		
		Common_Methods.ClickNavigation("Profile");
		Common_Methods.waitForWebElement(driver, mobNo);
		mobNo.clear();
		mobNo.sendKeys(mobno);
		
		Common_Methods.waitForWebElement(driver, pin);
		pin.clear();
		pin.sendKeys(pinc);
		
		Common_Methods.waitForWebElement(driver, address);
		address.clear();
		address.sendKeys(shopaddress);
		
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
		
		//Common_Methods.shortWait();
		//signInBtn.click();
	}
	

}
