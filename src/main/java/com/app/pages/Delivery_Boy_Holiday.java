package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Delivery_Boy_Holiday extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//label[text()='From date']/ancestor::div[1]/following-sibling::div[@class='col-md-7']//input")
	WebElement fromDate;
	
	@FindBy(xpath="//label[text()='To date']/ancestor::div[1]/following-sibling::div[@class='col-md-7']//input")
	WebElement toDate;
	
	@FindBy(xpath="//input[@name='lineOne']")
	WebElement reasonInput;
	
	public Delivery_Boy_Holiday() {
		System.out.println("In delivery boy holiday ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void AddDeliveryBoyHoliday(String delboy,String yermonthfrom,String dayfrom,String yermonthto,String dayto,String reason
			,List<String> deliveryroute,List<String> delgroup,List<String> delgroup1,List<String> delboylist,List<String> delboylist1) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option", delboy);
		
		Common_Methods.waitForWebElement(driver, fromDate);
		fromDate.click();
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
		
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
		
		Common_Methods.waitForWebElement(driver, toDate);
		toDate.click();
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthto);
	
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayto+"']")).click();
	
		Common_Methods.waitForWebElement(driver, reasonInput);
		reasonInput.sendKeys(reason);
		
		List<WebElement> routelist = driver.findElements(By.xpath("//div[@class='col-md-9']//div[@class='row form-group']"));
		int routelen = routelist.size();
		System.out.println("routelen "+routelen);
		for(int i=1;i<=routelen;i++) {
			List<WebElement> delgrouplist = driver.findElements(By.xpath("//div[@class='col-md-9']//div[@class='row form-group']["+i+"]//div[text()='"+deliveryroute.get(i-1)+"']/ancestor::div[2]/following-sibling::div[@class='col-md-8']//div[@class='row']"));
			int delgrouplen = delgrouplist.size();
			System.out.println("delgrouplen "+delgrouplen);
			if(i==1) {
				for(int j=0;j<delgrouplen;j++) {
					WebElement ele = driver.findElement(By.xpath("//div[text()='"+delgroup.get(j)+"']"));
					Common_Methods.toElement(ele);
					Common_Methods.selectFromDropDown("//div[text()='"+delgroup.get(j)+"']/ancestor::div[1]//div[@class='col-md-6']//select[@name='shopAgentId2']//option", delboylist.get(j));
					//driver.findElement(By.xpath("//div[text()='"+delgroup.get(j)+"']/ancestor::div[1]//div[@class='col-md-6']//select[@name='shopAgentId2']//option[text()='"+delboylist.get(j)+"']")).click();
				}
			}else {
				for(int j=0;j<delgrouplen;j++) {
					WebElement ele = driver.findElement(By.xpath("//div[text()='"+delgroup1.get(j)+"']"));
					Common_Methods.toElement(ele);
					Common_Methods.selectFromDropDown("//div[text()='"+delgroup1.get(j)+"']/ancestor::div[1]//div[@class='col-md-6']//select[@name='shopAgentId2']//option", delboylist1.get(j));
					//driver.findElement(By.xpath("//div[text()='"+delgroup1.get(j)+"']/ancestor::div[1]//div[@class='col-md-6']//select[@name='shopAgentId2']//option[text()='"+delboylist1.get(j)+"']")).click();
				}
			}
			
			
			
		}
		
		
		
	}

}
