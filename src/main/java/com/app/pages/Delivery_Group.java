package com.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Delivery_Group extends TestBase{

	@FindBy(xpath="//i[contains(@class,'fa fa-chevron-up fa-lg')]")
	WebElement upArrowBtn;
	
	@FindBy(xpath="//i[contains(@class,'fa fa-chevron-down fa-lg')]")
	WebElement downArrowBtn;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	
		public Delivery_Group() {
			System.out.println("In Delivery Group Ctor");
			PageFactory.initElements(driver, this);
		}
		
		
		public boolean doSequence(String dgroup,String cname,int length,String updown) {
			try {
				driver.findElement(By.xpath("//input[@value='"+dgroup+"']/ancestor::div[3]//table//tbody//td[text()='"+cname+"']/ancestor::tr[1]//input[@name='radiobtn']")).click();
				if(updown.equals("uparrow")) {
					for(int i=1;i<=length;i++) {
						upArrowBtn.click();
					}
				}
				else {
					for(int i=1;i<=length;i++) {
						downArrowBtn.click();
					}
				}
				return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			
		}
		
		public void VerifyErrorMessageWithoutSelectingCustomer(String dgroup){
			try {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//normal[text()='"+dgroup+"']/ancestor::td[1]")).click();
				
				Thread.sleep(2000);
				upArrowBtn.click();
				Thread.sleep(2000);
				String getmsg=Common_Methods.getToastMessage();
				//System.out.println("getmsg "+getmsg);
				Assert.assertEquals(getmsg, "Please Select Any one Customer !");
				
				Thread.sleep(2000);
				downArrowBtn.click();
				Thread.sleep(2000);
				String getmsg1=Common_Methods.getToastMessage();
				//System.out.println("getmsg1"+getmsg1);
				Assert.assertEquals(getmsg1, "Please Select Any one Customer !");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		public void maintainDeliveryGroupSequence(String dgroup,String [] custArr) {
			//mahi,mario,mohan,cust test2,palash
			//Customer3,Customer1,Customer6,Customer7,Customer8(External Server)
			try {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//normal[text()='"+dgroup+"']/ancestor::td[1]")).click();
				List<String> custname= new ArrayList<String>();
				List<String> custname1= new ArrayList<String>();
				Common_Methods.shortWait();
				List<WebElement> li =driver.findElements(By.xpath("//input[@value='"+dgroup+"']/ancestor::div[3]//tbody//tr"));
				
			   for (int i = 1; i <=li.size(); i++) {
				String getCustName = driver.findElement(By.xpath("//input[@value='"+dgroup+"']/ancestor::div[3]//tbody//tr["+i+"]//td[2]")).getText();
				custname.add(getCustName);
				}
			   
			   System.out.println("custname "+custname);
			  
			  doSequence(dgroup,custname.get(3),3,"uparrow");
			  Common_Methods.shortWait();
			  doSequence(dgroup,custname.get(1),1,"uparrow");
			  Common_Methods.shortWait();
			  doSequence(dgroup,custname.get(2),1,"downarrow");
			  
			  /*Common_Methods.shortWait();
			  doSequence(dgroup, custname.get(5), 1, "uparrow");
			  
			  Common_Methods.shortWait();
			  doSequence(dgroup, custname.get(7), 1, "downarrow");*/
			  Common_Methods.waitForWebElement(driver, saveBtn);
			  saveBtn.click();
			  Thread.sleep(2000);
			  String getMsg=Common_Methods.getToastMessage();
			  Assert.assertEquals(getMsg, "Successfully Updated");
			
			Common_Methods.shortWait();
			List<WebElement> li1 =driver.findElements(By.xpath("//input[@value='"+dgroup+"']/ancestor::div[3]//tbody//tr"));
			
			   for (int i = 1; i <=li1.size(); i++) {
				String getCustName = driver.findElement(By.xpath("//input[@value='"+dgroup+"']/ancestor::div[3]//tbody//tr["+i+"]//td[2]")).getText();
				custname1.add(getCustName);
				}
			   System.out.println("custname1 "+custname1);
			   
			   for(int i=0;i<li1.size();i++) {
				   Assert.assertEquals(custname1.get(i), custArr[i],"Getting wrong delivery group name");
			   }
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		}
		
		public void verifyErrorMessageOnDeleteOfDeliveryGroup(String dgroup) {
			try {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//normal[text()='"+dgroup+"']/ancestor::tr[1]")).click();
				
				driver.findElement(By.xpath("//normal[text()='"+dgroup+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
				Thread.sleep(2000);
				String getMsg=Common_Methods.getToastMessage();
				//System.out.println("getMsg "+getMsg);
				Assert.assertEquals(getMsg, "First delink customers from delivery group");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
}
