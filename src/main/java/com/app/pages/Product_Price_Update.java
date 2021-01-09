package com.app.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Product_Price_Update extends TestBase{
	
	@FindBy(xpath="//button[text()='Back']")
	WebElement backBtn;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//button[text()='Categories']")
	WebElement categoriesBtn;
	
	public Product_Price_Update() {
		System.out.println("In price update ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void SelectCategory(String catename) {
		
		Common_Methods.waitForWebElement(driver, categoriesBtn);
		categoriesBtn.click();
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//button[text()='"+catename+"']")).click();
		
	}
	
	public void SelectSubCategory(String subcatename) {
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//button[text()='"+subcatename+"']")).click();
	}
	
	public void SelectSubCategory(String subcatename,boolean flag) {
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//button[text()='"+subcatename+"']")).click();
		
		if(flag) {
			Common_Methods.waitForWebElement(driver, backBtn);
			backBtn.click();
		}
	}
	
	
	public void UpdateProductPrice(String [] pname,String [] feature, Map<String, String> hashselling,Map<String, String> hashmrp) {
		System.out.println("selling "+hashselling.size());
		System.out.println("mrp "+hashmrp.size());
		for(int i=1;i<=pname.length;i++) {
			WebElement ele=driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')]/ancestor::tr[1]"));
			Common_Methods.toShortWait();
			
			int j=0;
			for(Entry<String, String> selling : hashselling.entrySet()) {
				Common_Methods.toElement(ele);
				driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[j]+"')]/ancestor::tr[1]//input[@placeholder='Selling Price']")).sendKeys(selling.getValue());
				j++;
			}
			
			
			int k=0;
			for(Entry<String, String> mrpvalue : hashmrp.entrySet()) {
				Common_Methods.toElement(ele);
				driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[k]+"')]/ancestor::tr[1]//input[@placeholder='Mrp']")).sendKeys(mrpvalue.getValue());
				k++;
			}
				
	    }
		
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
	}
	
	public void UpdateProductP(String [] pname,String [] feature,String [] sp,String [] mrp) {
		
		for(int i=1;i<=pname.length;i++) {
		
		WebElement ele=driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')]/ancestor::tr[1]"));
		Common_Methods.toShortWait();
		Common_Methods.toElement(ele);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[i-1]+"')]/ancestor::tr[1]//input[@placeholder='Selling Price']")).sendKeys(sp);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[i-1]+"')]/ancestor::tr[1]//input[@placeholder='Mrp']")).sendKeys(mrp);
		
		}
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
	}
	
	public void MarkOutOfStockProduct(String [] pname,String [] flag,String [] feature) {
		
		for(int i=1;i<=pname.length;i++) {
			for(int j=1;j<feature.length;j++) {
				if(flag[i-1].equals("true")) {
					driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[i-1]+"')]/ancestor::tr[1]//input[@name='stock']")).click();
				}else {
					driver.findElement(By.xpath("//td[contains(text(),'"+pname[i-1]+"')][contains(.,'"+feature[i-1]+"')]/ancestor::tr[1]//input[@name='stock']")).click();
				}
			}
	  }
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
			
	}

}
