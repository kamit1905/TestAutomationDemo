package com.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Booklet_Required_Page extends TestBase{
	
	
	
	public Booklet_Required_Page() {
		System.out.println("In booklet required ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void VerifyRequiredQuantityOfJarsInBookletRequired(String [] reqQty,String pname) {
		Common_Methods.toShortWait();
		List<WebElement> productqtylist=driver.findElements(By.xpath("//th[text()='Customer Name']/ancestor::thead[1]//th"));
		int len=productqtylist.size();
		System.out.println("len "+len);
		List<String> addqtylist=new ArrayList<String>();
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='productId']//option", pname);
		
		for(int i=2;i<=len;i++) {
			Common_Methods.toShortWait();
			String getQty=driver.findElement(By.xpath("//th[text()='Customer Name']/ancestor::thead[1]//th["+i+"]")).getText();
			System.out.println("getQty "+getQty);
			addqtylist.add(getQty);
		}
		
		System.out.println("addqtylist "+addqtylist);
		
		for(int j=0;j<addqtylist.size();j++) {
		//Assert.assertEquals(reqQty[j], addqtylist.get(j),"Gettting wrong qty against product");
		Assert.assertTrue(addqtylist.get(j).contains(reqQty[j]), "Gettting wrong qty against product");	
		}
	}

}
