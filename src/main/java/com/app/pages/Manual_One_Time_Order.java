package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Manual_One_Time_Order extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//label[text()='Customer']/ancestor::div[1]/following-sibling::div[2]//button[text()='Add']")
	WebElement customerAddBtn;
	
	@FindBy(xpath="//input[@name='myInput']")
	WebElement customerSearchInput;
	
	@FindBy(xpath="//input[@id='ProductSearch']")
	WebElement productSearchInput;
	
	@FindBy(xpath="//input[@name='Quantity']")
	WebElement quantityInput;
	
	@FindBy(xpath="//input[@name='Quantity']/ancestor::div[1]/following-sibling::div[2]//button[text()='Add']")
	WebElement productAddBtn;
	
	@FindBy(xpath="//button[text()='Back']")
	WebElement backBtn;
	
	@FindBy(xpath="//button[text()='Next']")
	WebElement nextBtn;
	
	@FindBy(xpath="//td[@id='oneTime'][5]//b")
	WebElement getItemTotalAmt;
	
	//b[text()='Total:']/ancestor::tr[1]//td[5]//b
	
	@FindBy(xpath="//label[text()='Item Total:']/ancestor::td[1]/following-sibling::td[2]//label")
	WebElement itemTotalInSummary;
	
	@FindBy(xpath="//label[text()='Sub Total:']/ancestor::td[1]/following-sibling::td[2]//label")
	WebElement subTotal;
	
	@FindBy(xpath="//label[text()='Tax:']/ancestor::td[1]/following-sibling::td[2]//label")
	WebElement taxEle;
	
	@FindBy(xpath="//label[text()='Delivery Charges:']/ancestor::td[1]/following-sibling::td[2]//input")
	WebElement getDeliveryCharges;
	
	@FindBy(xpath="//label[text()='Grand Total:']/ancestor::td[1]/following-sibling::td[2]//input")
	WebElement grandTotal;
	
	@FindBy(xpath="//label[text()='Item Total:']/ancestor::td[1]/following-sibling::td[2]//label")
	WebElement getActualItemTotal;
	
	@FindBy(xpath="//input[@name='fromDate']")
	WebElement deliveryDate;
	
	@FindBy(xpath="//input[@id='fromDate']")
	WebElement dateCalBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeOrderBtn;
	
	@FindBy(xpath="//b[text()='Generate']/ancestor::button[1]")
	WebElement generateBtn;
	
	@FindBy(xpath="//b[text()='Date:']/ancestor::div[1]/following-sibling::div[@class='col-md-2']//input")
	WebElement fromTime;
	
	public Manual_One_Time_Order() {
		PageFactory.initElements(driver, this);
	}
	
	public void SelectCustomerAndDeliveryBoy(String customer,String delboy,String yermonthfrom,String dayfrom) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.waitForWebElement(driver, customerSearchInput);
		customerSearchInput.sendKeys(customer);
		Common_Methods.toShortWait();
		
		driver.findElement(By.xpath("//li[text()='"+customer+"']")).click();
		
		
		Common_Methods.waitForWebElement(driver, deliveryDate);
		deliveryDate.click();
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
		
		Common_Methods.selectFromDropDown("//select[@name='deliveryBoyId']//option", delboy);
	}
	
	public void AddManualOneTimeOrder(List<String> pname,List<String> feature,int qty) {
		int granttotal=0;
		
		for(int i=0;i<pname.size();i++) {
			Common_Methods.waitForWebElement(driver, productSearchInput);
			productSearchInput.sendKeys(pname.get(i));
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//li[text()='"+pname.get(i)+"']")).click();
			Common_Methods.toShortWait();
			
			driver.findElement(By.xpath("//td[text()='"+feature.get(0)+"']/ancestor::tr[1]//input")).click();
			
			Common_Methods.waitForWebElement(driver, nextBtn);
			nextBtn.click();
			
			if(qty>1) {
				Common_Methods.waitForWebElement(driver, quantityInput);
				quantityInput.clear();
				quantityInput.sendKeys(String.valueOf(qty));
			}
			
			Common_Methods.waitForWebElement(driver, productAddBtn);
			productAddBtn.click();
			Common_Methods.toShortWait();
		}
		
		Common_Methods.toShortWait();
		String getItemTotal=getItemTotalAmt.getText();
		//System.out.println("getItemTotal "+getItemTotal);
		
		Common_Methods.toShortWait();
		String getActualItemT=getActualItemTotal.getText();
		//System.out.println("getActualItemT "+getActualItemT);
		
		Common_Methods.toShortWait();
		String getActualSubTotal=subTotal.getText();
		//System.out.println("getActualSubTotal "+getActualSubTotal);
		
		Common_Methods.toShortWait();
		String getActualTax=taxEle.getText();
		//System.out.println("getActualTax "+getActualTax);
		
		Common_Methods.toShortWait();
		String getActualDeliveryCharges=getDeliveryCharges.getAttribute("value");
		//String getActualDeliveryCharges=getDeliveryCharges.getText();
		//System.out.println("getActualDeliveryCharges "+getActualDeliveryCharges);
		
		Common_Methods.toShortWait();
		granttotal=Integer.parseInt(getActualSubTotal)+Integer.parseInt(getActualTax)+Integer.parseInt(getActualDeliveryCharges);
		System.out.println("granttotal "+granttotal);
		
		String getActualGrandTotal=grandTotal.getAttribute("value");
		//String getActualGrandTotal=grandTotal.getText();
		System.out.println("getActualGrandTotal "+getActualGrandTotal);
		
		//Assert.assertEquals(getItemTotal, getSubTotal,"Getting wrong total amount and subtotal amount");
		
		Assert.assertEquals(getItemTotal, getActualItemT/*"Getting wrong item total amount"*/);
		
		Assert.assertEquals(getActualGrandTotal, String.valueOf(granttotal)/*"Getting wrong grand total"*/);
		
		//Common_Methods.waitForWebElement(driver, cancelBtn);
		//cancelBtn.click();
		Common_Methods.waitForWebElement(driver, placeOrderBtn);
		placeOrderBtn.click();
		
	}
	
     public void ChangeStatusAgainstOrder(String custName,String staTab,String yermonthfrom,String dayfrom,String changeStatus,
    		 List<String> pname,int qty) {
		
		driver.findElement(By.xpath("//button[contains(text(),'"+staTab+"')]")).click();
		
		Common_Methods.waitForWebElement(driver, dateCalBtn);
		Common_Methods.clickByJs(dateCalBtn);
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
		
		WebElement ele1=driver.findElement(By.xpath("//b[text()='"+custName+"']"));
		Common_Methods.toElement(ele1);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//b[text()='"+custName+"']/ancestor::div[2]/following-sibling::div[2]//i[@class='fa fa-info-circle']")).click();
	
		WebElement ele=driver.findElement(By.xpath("//button[text()='Update']"));
		Common_Methods.toElement(ele);
		
		if(changeStatus.equals("Update")) {
			UpdateProductQty(pname, qty);
		}
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//div[@class='card-footer']//button[text()='"+changeStatus+"']")).click();
		
		
     }
     
     public void UpdateProductQty(List<String> pname,int qty) {
    	 
    	 for(int i=0;i<pname.size();i++) {
    		 Common_Methods.toShortWait();
    		 driver.findElement(By.xpath("//td[contains(text(),'"+pname.get(i)+"')]/ancestor::tr[1]//input[@name='orderQty']")).clear();
    		 
    		 Common_Methods.toShortWait();
    		 driver.findElement(By.xpath("//td[contains(text(),'"+pname.get(i)+"')]/ancestor::tr[1]//input[@name='orderQty']")).sendKeys(String.valueOf(qty));
    		 
    		 driver.findElement(By.xpath("//td[contains(text(),'"+pname.get(i)+"')]/ancestor::tr[1]//input[@name='orderQty']")).sendKeys(Keys.TAB);
    	 }
     }
     
     public void SelectAndGenerateDistributionReport(String yermonthfrom,String dayfrom) {
    	 Common_Methods.waitForWebElement(driver, fromTime);
 		fromTime.click();
 		Common_Methods.toShortWait();
 		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
 		
 		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
 		
 		Common_Methods.waitForWebElement(driver, generateBtn);
 		generateBtn.click();
    	 
     }
     
     public void VerifyEntryInDistributionReport(String custname,List<String> pname) {
    	 
    	 List<WebElement> ele = driver.findElements(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//td[2]//tr"));
    	 
    	 for(int i=1;i<=ele.size();i++) {
    		 String getPname=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//td[2]//tr["+i+"]//td[1]")).getText();
    		 System.out.println("getPname "+getPname);
    		 
    		 Assert.assertEquals(getPname, pname.get(i-1),"Getting wrong product name");
    	 }
     }

}
