package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Collection_Report extends TestBase{
		
	@FindBy(xpath="//b[text()='Generate']/ancestor::button[1]")
	WebElement generateBtn;
	
	public Collection_Report() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void applyFilterForCollectionReport(String frommonthyear,String tomonthyear,String fdate,String tdate,String dboyname) {
		
		driver.findElement(By.xpath("//input[@name='fromDate']")).click();
		String getsta=Common_Methods.selectMonthFromCalender(frommonthyear);
		if(getsta.equals("Current month")) {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+fdate+"']")).click();
		}else {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'react-datepicker__day')][@aria-label='"+fdate+"']")).click();
		}
		
	
		driver.findElement(By.xpath("//input[@name='toDate']")).click();
		String getsta1=Common_Methods.selectMonthFromCalender(tomonthyear);
		if(getsta.equals("Current month")) {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+tdate+"']")).click();
		}else {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'react-datepicker__day')][@aria-label='"+tdate+"']")).click();
		}
		
		Common_Methods.selectDeliveryBoyFilter(dboyname);
		
		Common_Methods.waitForWebElement(driver, generateBtn);
		generateBtn.click();
	}
	
	
	public void calculateTotalCollectedAmtAndPendingAmountForParticularDeliveryBoy(){
		try {
			//Verify total collected amt 
			int totalcoll=0;
			Thread.sleep(2000);
			List<WebElement> li=driver.findElements(By.xpath("//tbody//tr"));
		     
		     for(int i=1;i<=li.size()-1;i++) {
		    	 String getCollectedAmt=driver.findElement(By.xpath("//div[contains(text(),'Collection Report')]/ancestor::div[1]//tbody//tr["+i+"]//td[4]")).getText();
		    	 int coll1=Integer.parseInt(getCollectedAmt);
		    	 //System.out.println("coll1 "+coll1);
		    	 totalcoll=totalcoll+coll1;
		     }
		     
		 
		     String getFinalTotal=driver.findElement(By.xpath("//tr[@class='reportTotalRow']//td[2]")).getText();
		     System.out.println("getFinalTotal "+getFinalTotal);		
		     
		     Assert.assertEquals(getFinalTotal, String.valueOf(totalcoll),"Getting wrong total amt");
		     
		     //Verify pending amount
		     int totalpending=0;
		     Thread.sleep(2000);
				List<WebElement> li1=driver.findElements(By.xpath("//tbody//tr"));
			     
			     for(int i=1;i<=li1.size()-1;i++) {
			    	 String getCollectedAmt=driver.findElement(By.xpath("//div[contains(text(),'Collection Report')]/ancestor::div[1]//tbody//tr["+i+"]//td[5]")).getText();
			    	 int coll1=Integer.parseInt(getCollectedAmt);
			    	 totalpending=totalpending+coll1;
			     }
			     
			     String getTotalPendingAmt=driver.findElement(By.xpath("//tr[@class='reportTotalRow']//td[3]")).getText();
			     System.out.println("getTotalPendingAmt "+getTotalPendingAmt);		
			     
			     Assert.assertEquals(getTotalPendingAmt, String.valueOf(totalpending),"Getting wrong total Pending Amt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void calculateTotalCollectedAmt() {
		try {
			int total=0;
			List<WebElement> li=driver.findElements(By.xpath("//tbody//tr"));
		     
		     for(int i=1;i<=li.size()-1;i++) {
		    	 String getCollectedAmt=driver.findElement(By.xpath("//div[contains(text(),'Collection Report')]/ancestor::div[1]//tbody//tr["+i+"]//td[4]")).getText();
		    	 int coll1=Integer.parseInt(getCollectedAmt);
		    	 //System.out.println("coll1 "+coll1);
		    	 total=total+coll1;
		     }
		     
		     //Verify Collected Total And Final Total
		     String getFinalTotal=driver.findElement(By.xpath("//tr[@class='reportTotalRow']//td[2]")).getText();
		     System.out.println("getFinalTotal "+getFinalTotal);		
		     
		     Assert.assertEquals(getFinalTotal, String.valueOf(total),"Getting wrong total amt");
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void calculateTotalAmtPending() {
		try {
			int total=0;
			List<WebElement> li=driver.findElements(By.xpath("//tbody//tr"));
		     
		     for(int i=1;i<=li.size()-1;i++) {
		    	 String getCollectedAmt=driver.findElement(By.xpath("//div[contains(text(),'Collection Report')]/ancestor::div[1]//tbody//tr["+i+"]//td[5]")).getText();
		    	 int coll1=Integer.parseInt(getCollectedAmt);
		    	 total=total+coll1;
		     }
		     
		     //Verify  Total Pendgin Amt 
		     String getTotalPendingAmt=driver.findElement(By.xpath("//tr[@class='reportTotalRow']//td[3]")).getText();
		     System.out.println("getTotalPendingAmt "+getTotalPendingAmt);		
		     
		     Assert.assertEquals(getTotalPendingAmt, String.valueOf(total),"Getting wrong total Pending Amt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//100000002730
	
	public void verifyCollectedAndPendingAmtCustomerWise(String cname,String dboy,int collectedAmt,int pendingAmt) {
		try {
			String getActualCollectedAmt=driver.findElement(By.xpath("//td[text()='"+dboy+"']/ancestor::tr[1]//td[text()='"+cname+"']/ancestor::tr[1]//td[4]")).getText();
			System.out.println("getActualCollectedAmt "+getActualCollectedAmt);
			String getActualPendingAmt=driver.findElement(By.xpath("//td[text()='"+dboy+"']/ancestor::tr[1]//td[text()='"+cname+"']/ancestor::tr[1]//td[5]")).getText();
			System.out.println("getActualPendingAmt "+getActualPendingAmt);
			
			//Verify Collected Amt
			Assert.assertEquals(getActualCollectedAmt, String.valueOf(collectedAmt),"Getting wrong collected Amt");
			
			//Verify Pending Amt
			Assert.assertEquals(getActualPendingAmt, String.valueOf(pendingAmt),"Getting wrong pending amt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void verifyPendingAmtCustomerWise(String cname,String dboy,String pendingAmt) {
		try {
			
			String getActualPendingAmt=driver.findElement(By.xpath("//td[text()='"+dboy+"']/ancestor::tr[1]//td[text()='"+cname+"']/ancestor::tr[1]//td[5]")).getText();
			System.out.println("getActualPendingAmt "+getActualPendingAmt);
			
			//Verify Pending Amt
			Assert.assertEquals(getActualPendingAmt, pendingAmt,"Getting wrong pending amt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
