package com.app.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Delivery_Route extends TestBase{
	
	@FindBy(xpath="//input[@name='customerName']")
	WebElement routeName;
	
	@FindBy(xpath="//input[@name='customerMbNo']")
	WebElement routeCode;
	
	@FindBy(xpath="//input[@name='deliveryGroupToken']")
	WebElement deliveryRouteSearch;
	
	@FindBy(xpath="//button[text()=' Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[text()=' Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	public Delivery_Route() {
		System.out.println("In delivery route ctor");
		PageFactory.initElements(driver, this);
	}
	
	//Airoli,Vashi,Achalpur
	public void AddDeliveryRoute(String routename,String routecode,List<String> delgroup,String [] serno) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, routeName);
		routeName.sendKeys(routename);
		
		Common_Methods.waitForWebElement(driver, routeCode);
		routeCode.sendKeys(routecode);
		
		Common_Methods.waitForWebElement(driver, deliveryRouteSearch);
		deliveryRouteSearch.clear();
		
		for(int i=0;i<delgroup.size();i++) {
			deliveryRouteSearch.sendKeys(delgroup.get(i));
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//div[@class='AutoCompleteText']//li[1]")).click();
		}
		
		DosequencingofDeliveryRoute(delgroup, serno);
		
	}
	
	public void EditDeliveryRoute(String routename,String routecode,List<String> delgroup,String [] serno) {
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[text()='"+routename+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		
		DosequencingofDeliveryRoute(delgroup, serno);
		
	}
	
	public void DosequencingofDeliveryRoute(List<String> delgroup,String [] serno) {
		
		Common_Methods.toShortWait();
		for(int i=0;i<delgroup.size();i++) {
			WebElement ele=driver.findElement(By.xpath("//div[text()='"+delgroup.get(i)+"']//input[@name='SerialNo']"));
			
			Common_Methods.toShortWait();
			Common_Methods.ClearTextUsingJavascriptExecutor(ele);
			Common_Methods.toShortWait();
			//driver.findElement(By.xpath("//div[text()='"+delgroup.get(i)+"']//input[@name='SerialNo']")).sendKeys(serno[i]);
			ele.sendKeys(serno[i]);
			//Common_Methods.SendKeysUsingJavascriptExecutor(ele, serno[i]);
			ele.click();
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='Delivery Group Name']")).click();
			//ele.sendKeys(Keys.TAB);
			//Common_Methods.toShortWait();
			//driver.findElement(By.xpath("//div[text()='"+delgroup.get(i)+"']//input[@name='SerialNo']")).sendKeys(Keys.TAB);
		}
		
		boolean sta = Common_Methods.isElementPresent("//button[text()=' Update']");
		System.out.println("sta "+sta);
		if(sta) {
		Common_Methods.waitForWebElement(driver, updateBtn);
		Common_Methods.toElement(updateBtn);
		updateBtn.click();
		}else {
			Common_Methods.waitForWebElement(driver, saveBtn);
			Common_Methods.toElement(saveBtn);
			//saveBtn.click();
		}
	}
	
	public void VerifyErrorMessageIfMissMandatoryField(String routename) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, routeName);
		routeName.sendKeys(routename);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();	
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
	}

}
