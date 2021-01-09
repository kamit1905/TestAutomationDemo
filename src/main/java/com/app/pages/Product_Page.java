package com.app.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.util.TestUtil;

public class Product_Page extends TestBase{
	
	@FindBy(xpath="//a[text()='Product']")
	WebElement productTab;
	
	//@FindBy(xpath="//button[text()='Add']")
	@FindBy(xpath="//img[@class='plusImg']/ancestor::button[1]")
	WebElement productAddBtn;
	
	@FindBy(xpath="//input[@placeholder='Product Name']")
	WebElement productName;
	
	@FindBy(xpath="//input[@placeholder='Product Description']")
	WebElement productDescription;
	
	@FindBy(xpath="//input[@placeholder='Product Price']")
	WebElement productPrice;	
	
	@FindBy(xpath="//select[@name='unitId']//option")
	WebElement unit;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement productSearch;
	
	@FindBy(xpath="//input[@name='Total_Product']")
	WebElement totalProductQty;
	
	@FindBy(xpath="//input[@name='productImageUrl']")
	WebElement productImg;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	
	
	
	public Product_Page() {
		System.out.println("In Product Page Constructor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void addProduct(String pname,String desc,int price,String unit1,String emptycan) {
		
		try {
			Common_Methods.waitForWebElement(driver, productAddBtn);
			productAddBtn.click();
			Common_Methods.waitForWebElement(driver, productName);
			productName.sendKeys(pname);
			Common_Methods.waitForWebElement(driver, productDescription);
			productDescription.sendKeys(desc);
			Common_Methods.waitForWebElement(driver, productPrice);
			productPrice.sendKeys(String.valueOf(price));
			Common_Methods.waitForWebElement(driver, unit);
			Common_Methods.selectFromDropDown("//select[@name='unitId']//option", unit1);
			
			//Common_Methods.waitForWebElement(driver, totalProductQty);
			//totalProductQty.sendKeys(String.valueOf(stock1));
			
			//Common_Methods.waitForWebElement(driver, productImg);
			//Common_Methods.uploadFile("C:\\Users\\Amit\\Documents\\Images\\Water_Jar.jpg", productImg);
			
			Common_Methods.selectFromDropDown("//select[@placeholder='Select Can Return']//option", emptycan);
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Thread.sleep(2000);
			/*boolean altstatus=Common_Methods.isAlertPresent();
			System.out.println("altstatus "+altstatus);
			if(altstatus){
				String getmsg=driver.switchTo().alert().getText();
				System.out.println("getmsg "+getmsg);
				if(getmsg.equals("Product added Successfully")) {
					driver.switchTo().alert().accept();
				}
				else if(getmsg.equals("product is already added."));{
					System.out.println("Verified Error Messsage");
					driver.switchTo().alert().accept();
				}
		
			}*/
			
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			Assert.assertEquals(getMsg, "successfully saved.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void verifyAddedProduct(String pname,String pdesc,int pprice) {
		
		try {
			//Common_Methods.waitForWebElement(driver, productTab);
			//productTab.click();
			
			//Thread.sleep(1000);
			//String getproductName=driver.findElement(By.xpath("//tr[@class='borderBottomTable']['"+pno+"']//td[@class='pl-2'][1]")).getText();
			String getproductName=driver.findElement(By.xpath("//tbody//td[1]")).getText();
			System.out.println("getproductName "+getproductName);
			Assert.assertEquals(getproductName,pname);
			//String getproductDescription=driver.findElement(By.xpath("//tr[@class='borderBottomTable']['"+pno+"']//td[@class='pl-2'][2]")).getText();
			String getproductDescription=driver.findElement(By.xpath("//tbody//td[2]")).getText();
			System.out.println("getProductDescription "+getproductDescription);
			Assert.assertEquals(getproductDescription, pdesc);
			//String getproductPrice=driver.findElement(By.xpath("//tr[@class='borderBottomTable']['"+pno+"']//td[@class='pl-2'][3]")).getText();
			String getproductPrice=driver.findElement(By.xpath("//tbody//td[3]")).getText();
			System.out.println("getproductPrice "+getproductPrice);
			Assert.assertEquals(getproductPrice, String.valueOf(pprice));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void editProduct(String pname,String emptycan,String updatedproductname) {
		
		try {
			driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
			Common_Methods.waitForWebElement(driver, productName);
			Thread.sleep(2000);
			productName.clear();
			productName.sendKeys(updatedproductname);
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@placeholder='Select Can Return']//option", emptycan);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			Assert.assertEquals(getMsg, "successfully updated");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void verifyProductSearch(String pname) {
		try {
			Common_Methods.waitForWebElement(driver, productSearch);
			productSearch.sendKeys(pname);
			
			String getProductName=driver.findElement(By.xpath("//tbody//td[1]")).getText();
			System.out.println("getProductName "+getProductName);
			Assert.assertEquals(getProductName, pname);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void verifyErrorMessageIfNotEnteredMandatoryField(String pname) {
		try {
			Common_Methods.waitForWebElement(driver, productAddBtn);
			productAddBtn.click();
			Common_Methods.waitForWebElement(driver, productName);
			productName.sendKeys(pname);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			Thread.sleep(2000);
			String getmsg=Common_Methods.getToastMessage();
			System.out.println("getmsg "+getmsg);
			Assert.assertEquals(getmsg, "Please fill all mandatory fields.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ApplyProductFilter(String pname,String action){
		try {
			Thread.sleep(2000);
			Common_Methods.waitForWebElement(driver, productSearch);
			productSearch.clear();
			productSearch.sendKeys(pname);
			
			Common_Methods.toShortWait();
			if(action.equals("Edit")) {
				driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			}else if(action.equals("Delete")){
				driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteProduct(String pname) {
		try {
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			
			Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			
			if(getMsg.equals("Product Delete Successfully")) {
				Assert.assertEquals(getMsg, "Product Delete Successfully");
			}
			else{
				Assert.assertEquals(getMsg, "Product entry not delete used in bill details");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<String> ReturnProductName(){
		List<String> prolist=new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> li=driver.findElements(By.xpath("//table//tr"));
		System.out.println("size "+li.size());
		for(int i=1;i<li.size();i++){
			Common_Methods.toShortWait();
			String getpname=driver.findElement(By.xpath("//table//tr["+i+"]//td[1]")).getText();
			//System.out.println("getpname "+getpname);
			prolist.add(getpname);
		}
		//System.out.println("list "+prolist);
		return prolist;
		//15.75
	}
	
	
	public boolean verifyAddedTaxInProduct(String taxname,boolean flag1) {
		List<WebElement> li=driver.findElements(By.xpath("//label[text()='Tax:']/ancestor::div[1]/following-sibling::div[1]//div[@class='form-check']"));
		int len=li.size();
		boolean flag=true;
		
		List<String> list=new ArrayList<String>();
		
		for(int i=1;i<=len;i++) {
			Common_Methods.toShortWait();
			String getTax=driver.findElement(By.xpath("//label[text()='Tax:']/ancestor::div[1]/following-sibling::div[1]//div[@class='form-check']["+i+"]//label")).getText();
			//System.out.println("getTax "+getTax);
			list.add(getTax);
		}
		
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String gettaxvalue=(String) it.next();
			System.out.println("gettaxvalue "+gettaxvalue);
			if(gettaxvalue.equals(taxname)) {
				//System.out.println("Product tax is still present eventhough unchecked it from tax configuration");
				flag=true;
			}else {
				System.out.println("Tax is not present in product");
				flag=false;
			}
		}
		
		if(flag==flag1) {
			System.out.println("Tax value is present");
			return flag;
		}else {
			System.out.println("Tax value is gone after removing it from tax configuration");
			return flag;
		}
		
	}
	
	
	
}
