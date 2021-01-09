package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Payment_Page extends TestBase{
	
	@FindBy(xpath="//button[text()='Pay']")
	WebElement payBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//input[@name='showall']")
	WebElement showAllCheckBox;
	
	
	public Payment_Page() {
		System.out.println("In Payment Page Constructor");
		PageFactory.initElements(driver, this);
	}

	
	
	public void doPayment(String custname,String deliveryboy,int amt,String remark) {
		
	try {
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@type='checkbox']")).click();
		//Thread.sleep(2000);
		//Common_Methods.selectFromDropDown("//td[text()='"+custname+"']/ancestor::tr[1]//select[@name='deliveryboy']//option", deliveryboy);
		
		driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Amount']")).clear();
		driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Amount']")).sendKeys(String.valueOf(amt));
		
		driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Remark']")).sendKeys(remark);
		
		payBtn.click();
		Thread.sleep(2000);
		Assert.assertEquals(Common_Methods.getToastMessage(), "Payment succesfull.","Not Getting Toast Message After Payment");
		
	  } catch (Exception e) {
		System.out.println(e.getMessage());
	   }
		
	}
	
	public void doPaymentAndVerifyErrorMessage(String custname,String deliveryboy,int amt,String remark) {
		
		try {
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@type='checkbox']")).click();
			//Thread.sleep(2000);
			//Common_Methods.selectFromDropDown("//td[text()='"+custname+"']/ancestor::tr[1]//select[@name='deliveryboy']//option", deliveryboy);
			
			driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Amount']")).clear();
			driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Amount']")).sendKeys(String.valueOf(amt));
			
			driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[1]//input[@id='Remark']")).sendKeys(remark);
			
			Assert.assertEquals(Common_Methods.getToastMessage(), "please less than or equal to total balance amount !","Not Getting Toast Message After Payment");
			
		  } catch (Exception e) {
			System.out.println(e.getMessage());
		   }
			
		}
	
	public void verifyPaymentDetailsForCustomer(String custname,int total,int bal,int paid) {
		try {
			int balance=0;
			Common_Methods.shortWait();
			WebElement ele=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//i[@class='fa fa-chevron-down']"));
			Common_Methods.clickByJs(ele);
			
			Common_Methods.shortWait();
			List<WebElement> li=driver.findElements(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//table//tbody"));

			for(int i=1;i<=li.size();i++) {
				String getBalAmt=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//table//tbody["+i+"]//td[3]")).getText();
				int getBalAmt1=Integer.parseInt(getBalAmt);
				balance=balance+getBalAmt1;
			}
			
			System.out.println("balance "+balance);
			
			//Get Total Amt,Paid Amt,Bal Amt
			//String getTotal=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//div[@class='collapse show']//td[2]")).getText();
			//System.out.println("getTotal "+getTotal);
			String getBal=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//div[@class='collapse show']//td[3]")).getText();
			System.out.println("getBal "+getBal);
			//String getPaid=driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//div[@class='collapse show']//td[4]")).getText();;
			//System.out.println("getPaid "+getPaid);
			
			
			//Verify Total Amt
			//Assert.assertEquals(getTotal, String.valueOf(total),"Getting Wrong Total Amt");
			
			//Verify Balance Amt
			Assert.assertEquals(getBal, String.valueOf(balance),"Getting Wrong Bal Amt");
			
			//Verify Paid Amt
			//Assert.assertEquals(getPaid, String.valueOf(paid),"Getting Wrong Paid Amt");
			
			Common_Methods.shortWait();
			//Collapse arrow once again for Customer
			driver.findElement(By.xpath("//td[text()='"+custname+"']/ancestor::tr[2]//i[@class='fa fa-chevron-down']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void searchCustomerAndPayAmoount(String custname,String dboy,int amt,String remark) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.clear();
		searchInput.sendKeys(custname);
		Common_Methods.shortWait();
		doPayment(custname, dboy, amt, remark);
		
	}
	
	public void verifyAdvanceAmtAgaingParticularCus(String cname,int advamt) {
		
		Common_Methods.waitForWebElement(driver, showAllCheckBox);
		showAllCheckBox.click();
		
		Common_Methods.shortWait();
		String getAdvAmt=driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//span[@class='textColorGreen']")).getText();
		System.out.println("getAdvAmt "+getAdvAmt);
		Assert.assertTrue(getAdvAmt.contains(String.valueOf(advamt)),"It contains wrong adv amt");
	}
	
	public void verifyBalAmtInPayment(String cname,String bal) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.clear();
		searchInput.sendKeys(cname);
		Common_Methods.shortWait();
		
		String getBal1=driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//td[3]")).getText();
		Assert.assertTrue(getBal1.contains(bal),"Getting Wrong Balance Amt on Payment Screen");
	
	}
	
	public String GetBalanceAmtForParticularCustomer(String cname){
		String getBal1=driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//td[3]")).getText();
		System.out.println("getBal1 "+getBal1);
		return getBal1;
	}
	
	public int getBalForAllCustomerOnPaymentScreen(){
		int balamt=0;int adv=0;
		try {
			Thread.sleep(2000);
			List<WebElement> li =driver.findElements(By.xpath("//table//tbody//tr"));
			int len=li.size()/2;
			
			
			for(int i=1;i<=len;i++){
				boolean flag=Common_Methods.isElementPresent("//table//tbody//tr["+i+"]//span");
				if(flag){
					String getadvamt=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//span[@class='textColorGreen']")).getText();
					int getadv2=Integer.parseInt(getadvamt);
					System.out.println("getadv2 "+getadv2);
					adv=adv + getadv2;
				}
				else{
				String getbalamt=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[@name='BalanceAmt']")).getText();
				//String [] getbalamt1=getbalamt.split(":");
				
				//int getbalamt2=Integer.parseInt(getbalamt1[1]);
				//System.out.println("getbalamt2 "+getbalamt2);
				int getbalamt2=Integer.parseInt(getbalamt);
				balamt= getbalamt2 + balamt;
				System.out.println("balamt "+balamt);
				}
			}
			
			System.out.println("balamtonpaymentscreen "+balamt);
			System.out.println("Advanceamountonpaymentscreen "+adv);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return balamt;
	}

}
