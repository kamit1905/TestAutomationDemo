package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Stock_Report extends TestBase{
	
	public Stock_Report() {
		System.out.println("In Stock Report Constructor");
		PageFactory.initElements(driver, this);
	}
	
	public void verifyStockReport(String pname,int totalproduct,int recuproduct,int productonplant) {
		//Common_Methods.shortWait();
		String getTotalProduct=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getTotalProduct "+getTotalProduct);
		
		//Common_Methods.shortWait();
		String getRecurringProduct=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[3]")).getText();
		System.out.println("getRecurringProduct "+getRecurringProduct);
		
		//Common_Methods.shortWait();
		String getProductOnPlant=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[5]")).getText();
		System.out.println("getProductOnPlant "+getProductOnPlant);
		
		
		Assert.assertEquals(getTotalProduct, String.valueOf(totalproduct));
		
		Assert.assertEquals(getRecurringProduct, String.valueOf(recuproduct));
		
		Assert.assertEquals(getProductOnPlant, String.valueOf(productonplant));
	}
	
	public int GetTotalProductInStockReport(String pname){
		String getTotalProduct=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getTotalProduct "+getTotalProduct);
		return Integer.parseInt(getTotalProduct);
	}

}
