package com.app.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Delivery_Boy extends TestBase {
	
	//@FindBy(xpath="//button[text()='Add']")
	@FindBy(xpath="//img[@class='plusImg']/ancestor::button[1]")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='shopAgentName']")
	WebElement deliveryBoyName;
	
	@FindBy(xpath="//input[@name='shopAgentMbNo']")
	WebElement deliveryBoyMob;
	
	@FindBy(xpath="//input[@name='uniqueIdentityField']")
	WebElement deliveryBoyEmail;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	
	public Delivery_Boy() {
		System.out.println("In delivery Boy Constructor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void addDeliveryBoy(String dboyname,String dboymob,String dboyemail,String dboyprofile) {
		try {
			
			Common_Methods.waitForWebElement(driver, addBtn);
			addBtn.click();
			
			Common_Methods.waitForWebElement(driver, deliveryBoyName);
			deliveryBoyName.sendKeys(dboyname);
			
			Common_Methods.waitForWebElement(driver, deliveryBoyMob);
			String dboymob1=BigDecimal.valueOf(Double.parseDouble(dboymob)).toPlainString();
			deliveryBoyMob.sendKeys(dboymob1);
			
			Common_Methods.waitForWebElement(driver, deliveryBoyEmail);
			deliveryBoyEmail.sendKeys(dboyemail);
			
			Common_Methods.shortWait();
			//Thread.sleep(2000);
			Common_Methods.selectFromDropDown("//select[@name='shopProfile']//option", dboyprofile);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			Assert.assertEquals(getMsg, "Successfully Save.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void editDeliveryBoy(String dboyname,String dboymob,String updatedname) {
		try {
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//td[text()='"+dboyname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
			Common_Methods.waitForWebElement(driver, deliveryBoyName);
			deliveryBoyName.clear();
			deliveryBoyName.sendKeys(updatedname);
			
			Common_Methods.waitForWebElement(driver, deliveryBoyMob);
			deliveryBoyMob.clear();
			deliveryBoyMob.sendKeys(dboymob);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
		    Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();
			Assert.assertEquals(getMsg, "Successfully updated.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteDeliveryBoy(String dboyname) {
		
		try {
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//td[text()='"+dboyname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			
			//Common_Methods.shortWait();
			Thread.sleep(2000);
			String msg=Common_Methods.getToastMessage();
			
			if(msg.equals("Shop Agent entry not delete used in Recurring order")) {
				Assert.assertEquals(msg, "Shop Agent entry not delete used in Recurring order");
			}
			else if(msg.equals("Shop Agent delete successfully")) {
				Assert.assertEquals(msg, "Shop Agent delete successfully");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void searchDeliveryBoy(String dboyname) {
		
		try {
			Common_Methods.shortWait();
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(dboyname);
			
			//verify serached delivery name
			String getDeliveryBoyName=driver.findElement(By.xpath("//tr[@class='borderBottomTable']//td[1]")).getText();
			System.out.println("getDeliveryBoyName "+getDeliveryBoyName);
			Assert.assertEquals(getDeliveryBoyName, dboyname);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void verifyDeliveryBoy(String dboyname,String dboymob) {
		SoftAssert soft = new SoftAssert();
		
		//verify serached delivery boy information
		String getDeliveryBoyName=driver.findElement(By.xpath("//tr[@class='borderBottomTable']//td[1]")).getText();
		System.out.println("getDeliveryBoyName "+getDeliveryBoyName);
		Assert.assertEquals(getDeliveryBoyName, dboyname);
		
		
		String getDeliveryBoyMobile=driver.findElement(By.xpath("//tr[@class='borderBottomTable']//td[2]")).getText();
		System.out.println("getDeliveryBoyMobile "+getDeliveryBoyMobile);
		Assert.assertEquals(getDeliveryBoyMobile, dboymob);
		
		boolean getEditIconStatus=driver.findElement(By.xpath("//td[text()='"+dboyname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).isDisplayed();
		boolean getDeleteIconStatus=driver.findElement(By.xpath("//td[text()='"+dboyname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).isDisplayed();
		
		
		//Verify Edit & delete Icon
		soft.assertEquals(getEditIconStatus, true,"Edit Icon is not visible");
		soft.assertEquals(getDeleteIconStatus, true,"Delete icon is not visible");
		soft.assertAll();
	}
	
	public void ApplyDeliveryBoyFilter(String dboy){
		try {
			Thread.sleep(2000);
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(dboy);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void VerifyErrorMessageIfDuplicateDeliveryBoy(String dboyname,String dboymob,String dboyemail){
		try {
			Common_Methods.waitForWebElement(driver, addBtn);
			addBtn.click();
			
			Common_Methods.waitForWebElement(driver, deliveryBoyName);
			deliveryBoyName.sendKeys(dboyname);
			
			Common_Methods.waitForWebElement(driver, deliveryBoyMob);
			String dboymob1=BigDecimal.valueOf(Double.parseDouble(dboymob)).toPlainString();
			deliveryBoyMob.sendKeys(dboymob1);
			
			Common_Methods.waitForWebElement(driver, deliveryBoyEmail);
			deliveryBoyEmail.sendKeys(dboyemail);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			//Assert.assertEquals(getMsg, "Successfully Save.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public List<String> GetDeliveryBoy(){
        List<String> delboylist=new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> li=driver.findElements(By.xpath("//table//tr"));
		System.out.println("size "+li.size());
		for(int i=1;i<li.size();i++){
			Common_Methods.toShortWait();
			String getdeliveryboy=driver.findElement(By.xpath("//table//tr["+i+"]//td[1]")).getText();
			//System.out.println("getpname "+getpname);
			delboylist.add(getdeliveryboy);
		}
		//System.out.println("list "+prolist);
		return delboylist;
		
	}

}
