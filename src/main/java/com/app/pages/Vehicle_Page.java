package com.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Vehicle_Page extends TestBase{
	
	@FindBy(xpath="//input[@id='VehicleName']")
	WebElement vehicleName;
	
	@FindBy(xpath="//input[@name='vehicleNumber']")
	WebElement vehicleNumber;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	public Vehicle_Page() {
		System.out.println("In vehicle page ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddVehicle(String vname,String pucyear,String pucdate,String regyear,String regdate,String vnumber) {
		
		List<String > prolist= new ArrayList<String>();
		
		List<WebElement> productsize=driver.findElements(By.xpath("//label[text()='Capacity of Vehicle:']/ancestor::div[1]/following-sibling::div[1]//tr"));
		
		int length=productsize.size();
		
		Common_Methods.waitForWebElement(driver, vehicleName);
		vehicleName.sendKeys(vname);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//label[text()='Puc Expiry Date:']/ancestor::div[2]//div[2]//input")).click();
		Common_Methods.selectMonthFromCalender(pucyear);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+pucdate+"']")).click();
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//label[text()='Registration Expiry Date:']/ancestor::div[2]//div[2]//input")).click();
		Common_Methods.selectMonthFromCalender(regyear);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+regdate+"']")).click();	
		
		
		for(int i=1;i<=length;i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='Capacity of Vehicle:']/ancestor::div[1]/following-sibling::div[1]//tr["+i+"]//input")).sendKeys("5");
		}
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		/*for(int j=1;j<=length;j++) {
			Common_Methods.toShortWait();
			String getpname=driver.findElement(By.xpath("//label[text()='Capacity of Vehicle:']/ancestor::div[1]/following-sibling::div[1]//tr["+j+"]//td[1]")).getText();
			System.out.println("getpname "+getpname);
			prolist.add(getpname);
		}*/
		
	}
	
	

}
