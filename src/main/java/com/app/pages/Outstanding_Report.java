package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Outstanding_Report extends TestBase{
	
	
	public Outstanding_Report() {
		System.out.println("In Outstanding Report Constructor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyOutstandingReportCustomerWise(String cname,int bal) {
		
		String getActualBal=driver.findElement(By.xpath("//td[contains(text(),'"+cname+"')]/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getActualBal "+getActualBal );
		Assert.assertEquals(getActualBal, String.valueOf(bal),"Getting wrong balance amount");
		
	}
	
	public void verifyAndCalculateTotalBalanceIfCustomerHaveManyBills(String cname,int bal) {
		
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//td[contains(text(),'"+cname+"')]")).click();
		String getTotalBalAmt=driver.findElement(By.xpath("//b[contains(text(),'Total Balance Amount')]/ancestor::div[1]/following-sibling::div//b")).getText();
		System.out.println("getTotalBalAmt "+getTotalBalAmt);
		
		Common_Methods.shortWait();
		List<WebElement> li=driver.findElements(By.xpath("//tbody//tr"));
		int totalbal=0;
		
		for(int i=1;i<=li.size();i++) {
			String getBal=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[3]")).getText();
			//System.out.println("//tbody//tr["+i+"]//td[3]");
			int getBal1=Integer.parseInt(getBal);
			totalbal=totalbal+getBal1;
		}
		
		System.out.println("totalbal "+totalbal);
		
		//Verify calculated bal & actual bal
		Assert.assertEquals(String.valueOf(totalbal), String.valueOf(bal),"Getting wrong calculated bal amt");
		
		//Verify 
		Assert.assertEquals(getTotalBalAmt, String.valueOf(bal),"Getting wrong total bal amt");
	}
	
	public String getAndVerifyBalAmountOnAllScreen(String cname) {
		
		String getActualBal=driver.findElement(By.xpath("//td[contains(text(),'"+cname+"')]/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getActualBal "+getActualBal );
		return getActualBal;
	
	}
	
	public int calculateTotalBalAmt(){
		int balamt=0;
		try {
			Thread.sleep(2000);
			List<WebElement> li =driver.findElements(By.xpath("//table//tbody//tr"));
			
			for(int i=1;i<li.size();i++){
				String getbalamt=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[2]")).getText();
				balamt=Integer.parseInt(getbalamt)+balamt;
			}
			System.out.println("balamtinoutstandindg "+balamt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return balamt;
	}

}
