package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Pending_Cans extends TestBase{
	
	@FindBy(xpath="//input[@name='currentDate']")
	WebElement fromDate;
	
	@FindBy(xpath="//b[text()='Generate']/ancestor::button[1]")
	WebElement generateBtn;
	
	public Pending_Cans() {
		System.out.println("In Pending Cans Report Ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void applyFilterOnPendingCans(String fdate,String frommonthyear,String dboy,String pname) {
		Common_Methods.waitForWebElement(driver, fromDate);
		
		//Common_Methods.selectMonthFromCalender(frommonthyear);
		driver.findElement(By.xpath("//input[@name='currentDate']")).click();
		String getsta=Common_Methods.selectMonthFromCalender(frommonthyear);
		if(getsta.equals("Current month")) {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+"day-"+fdate+"']")).click();
		}else {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+"day-"+fdate+"']")).click();
		}
		
		Common_Methods.selectDeliveryBoyFilter(dboy);
		
		Common_Methods.selectProduct(pname);
		
		Common_Methods.waitForWebElement(driver, generateBtn);
		generateBtn.click();
		
	}
	
	public void verifyPendingCansCustomerwise(String cname,String pname,int pendingcansforrecurring) {
		String getRecurringDueQty=driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//td[text()='"+cname+"']/ancestor::tr[1]//td[5]")).getText();
		System.out.println("getRecurringDueQty "+getRecurringDueQty);
		
		//Verify Pending Cans Qty 
		Assert.assertEquals(getRecurringDueQty, String.valueOf(pendingcansforrecurring),"Getting wrong pending cans qty for recurring order");
		
	}
	
	public void verifyTotalPendingCansForParticularProduct(int totalcan) {
		String getActualPendingCansForRecurring=driver.findElement(By.xpath("//tr[@class='reportTotalRow']//td[3]")).getText();
		System.out.println("getActualPendingCansForRecurring "+getActualPendingCansForRecurring);
		
		List<WebElement> lie=driver.findElements(By.xpath("//div[contains(text(),'Pending Cans Report')]/ancestor::div[1]//tbody//tr"));
		
		int totalcans=0;
		for(int i=1;i<=lie.size()-1;i++) {
			String getCans=driver.findElement(By.xpath("//div[contains(text(),'Pending Cans Report')]/ancestor::div[1]//tbody//tr["+i+"]//td[5]")).getText();
			int getCans1=Integer.parseInt(getCans);
			totalcans=totalcans+getCans1;
		}
		
		System.out.println("totalcans "+totalcans);
		Assert.assertEquals(String.valueOf(totalcans), String.valueOf(totalcan),"Getting wrong Total Cans Qty");
		
		//Getactual pending cans & verify it with calculated qty
		Assert.assertEquals(String.valueOf(totalcans), getActualPendingCansForRecurring);
		
	}
	
	public String getCountOfPendingCans(String cname,String pname) {
		Common_Methods.toShortWait();
		String getCount=driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//td[text()='"+pname+"']/ancestor::tr[1]//td[5]")).getText();
		System.out.println("getCount "+getCount);
		return getCount;
	}
	
	
	public void getTotalPendingCans(){
		try {
			int getonetimecount1=0;
			int getrecurringcount1=0;
			Thread.sleep(2000);
			String getActualOneTimeCount=driver.findElement(By.xpath("//td[text()='Total']/ancestor::tr[1]//td[2]")).getText();
			System.out.println("getActualOneTimeCount "+getActualOneTimeCount);
			String getActualRecurringCount=driver.findElement(By.xpath("//td[text()='Total']/ancestor::tr[1]//td[3]")).getText();
			System.out.println("getActualRecurringCount "+getActualRecurringCount);
			Thread.sleep(2000);
			List<WebElement> li = driver.findElements(By.xpath("//table//tbody//tr"));
			
			for(int i=1;i<li.size();i++){
				String getonetime=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[4]")).getText();
				String getrecurring=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[5]")).getText();
				
				getonetimecount1=Integer.parseInt(getonetime) + getonetimecount1;
				getrecurringcount1=Integer.parseInt(getrecurring) + getrecurringcount1;
			}
			
			System.out.println("getonetimecount1 "+getonetimecount1);
			System.out.println("getrecurringcount1 "+getrecurringcount1);
			
			Assert.assertEquals(getActualOneTimeCount, String.valueOf(getonetimecount1),"Getting wrong one time product count");
			Assert.assertEquals(getActualRecurringCount, String.valueOf(getrecurringcount1),"Getting wrong recurring count");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
