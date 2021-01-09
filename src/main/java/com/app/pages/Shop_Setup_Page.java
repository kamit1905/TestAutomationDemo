package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Shop_Setup_Page extends TestBase{
	
	@FindBy(xpath="//input[@name='shopName']")
	WebElement shopName;
	
	@FindBy(xpath="//input[@name='shopAgentName']")
	WebElement shopAgentName;
	
	@FindBy(xpath="//input[@name='addressLineOne']")
	WebElement address;
	
	@FindBy(xpath="//input[@name='pinCodeId']")
	WebElement pincode;
	
	@FindBy(xpath="//input[@name='shopMobileNo']")
	WebElement mobNo;
	
	@FindBy(xpath="//input[@name='shopDescription']")
	WebElement shopDescription;
	
	@FindBy(xpath="//input[@name='registrationCode']")
	WebElement registrationCode;
	
	@FindBy(xpath="//input[@type='file']")
	WebElement shopProfileImg;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	public Shop_Setup_Page() {
		System.out.println("In shop setup ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void CreateNewShop(String name,String shoptype,String username,String add,String city,
			String pin,String mbno,String imgpath,String shopdes,String regcode) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, shopName);
		shopName.sendKeys(name);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='applicationTypeName']//option", shoptype);
		
		Common_Methods.waitForWebElement(driver, shopAgentName);
		shopAgentName.sendKeys(username);
		
		Common_Methods.waitForWebElement(driver, address);
		address.sendKeys(add);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
		
		Common_Methods.waitForWebElement(driver, pincode);
		pincode.sendKeys(pin);
		
		Common_Methods.toElement(mobNo);
		Common_Methods.waitForWebElement(driver, mobNo);
		mobNo.sendKeys(mbno);
		
		//Common_Methods.toElement(shopProfileImg);
		//Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, shopProfileImg);
		
		Common_Methods.waitForWebElement(driver, registrationCode);
		registrationCode.sendKeys(regcode);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
	}
	
	public void VerifyErrorMessageIfNotEnteredMandatoryField(String name) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, shopName);
		shopName.sendKeys(name);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		//System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Please fill all mandatory fields");
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
	}

}
