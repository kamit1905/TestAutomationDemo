package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Dashboard_Page extends TestBase{
	
	@FindBy(xpath="//button[text()='Show']")
	WebElement showBtn;
	
	@FindBy(xpath="//a[@class='close']")
	WebElement closeBtn;
	
	public Dashboard_Page() {
		System.out.println("In dashboard page ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void ApplyFilterOnDashboard(String name,String fmonthyear,String tomonthyear,String fdate,String tdate) {
		
		if(name.equals("Custom")) {
			Common_Methods.selectFromDropDown("//select[@name='filter']//option", name);
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//input[@name='fromDate']")).click();
			String getsta=Common_Methods.selectMonthFromCalender(fmonthyear);
			if(getsta.equals("Current month")) {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+"day-"+fdate+"']")).click();
			}else {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+"day-"+fdate+"']")).click();
			}
			
			driver.findElement(By.xpath("//label[text()='To Date']/ancestor::div[1]/following-sibling::div[1]//input")).click();
			String getsta1=Common_Methods.selectMonthFromCalender(tomonthyear);
			if(getsta1.equals("Current month")) {
				//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+"day-"+tdate+"']")).click();
			}else {
				//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'react-datepicker__day')][@aria-label='"+"day-"+tdate+"']")).click();
			}
			
			Common_Methods.waitForWebElement(driver, showBtn);
			//showBtn.click();
			Common_Methods.clickByJs(showBtn);
			
		}else {
		Common_Methods.selectFromDropDown("//select[@name='filter']//option", name);
		}
	}
	
	public void VerifyOrdersInStatus(String stscolumn,String [] emails,String colid) {
		
		List<WebElement> getOrderList=driver.findElements(By.xpath("//label[contains(text(),'"+stscolumn+"')]/ancestor::div[2]/following-sibling::div[1]//div["+colid+"]//div[@class='orderCards card']"));
		int len=getOrderList.size();
		
		for(int i=1;i<len;i++) {
			Common_Methods.toShortWait();
			//Common_Methods.toElement(driver.findElement(By.xpath("//div[contains(text(),'"+emails[i-1]+"')]")));
			//Common_Methods.toShortWait();
			String getEmail=driver.findElement(By.xpath("//label[contains(text(),'"+stscolumn+"')]/ancestor::div[2]/following-sibling::div[1]//div["+colid+"]//div[@class='orderCards card']["+i+"]//div[@class='profileDetails card-body']")).getText();
			System.out.println("getEmail "+getEmail);
			
			//Assert.assertEquals(getEmail, emails[i-1],"Getting wrong customer emailid in order list");	
			Assert.assertTrue(getEmail.contains(emails[i-1]), "Getting wrong customer emailid in order list");
		}
	}
	
	public void OpenOrdeDetails(String email) {
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//div[contains(text(),'"+email+"')]/ancestor::div[1]//img")).click();
	}
	
	public int CalculateTotalAmountAgainstProduct(int qty,int rate) {
		int total;
		total=qty*rate;
		System.out.println("total "+total);
		return total;
	}
	
	public void VerifyOrderDetails(String name,String ordersts) {
		int calculatedamt=0;
		List<WebElement> getProdList=driver.findElements(By.xpath("//tbody//tr"));
		int len=getProdList.size();
		
		String getname=driver.findElement(By.xpath("//label[text()='Name:']/ancestor::div[1]/following-sibling::div[1]")).getText();
		System.out.println("getname "+getname);
		//Assert.assertEquals(getname, "");
		
		String getdeliverydate=driver.findElement(By.xpath("//label[text()='Delivery Date:']/ancestor::div[1]/following-sibling::div[1]")).getText();
		System.out.println("getdeliverydate "+getdeliverydate);
		//Assert.assertEquals(getdeliverydate, "");
		
		String getordersts=driver.findElement(By.xpath("//label[text()='Order Status:']/ancestor::div[1]/following-sibling::div[1]")).getText();
		System.out.println("getordersts "+getordersts);
		Assert.assertEquals(getordersts, ordersts);
		
		for(int i=1;i<=len;i++) {
			//Common_Methods.toShortWait();
			//String getpname=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			Common_Methods.toShortWait();
			String getqty=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[4]")).getText();
			Common_Methods.toShortWait();
			String getrate=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[5]")).getText();
			String [] getrate1=getrate.split("\\.");
			System.out.println(getrate1[0]);
			Common_Methods.toShortWait();
			String actualtotal=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[6]")).getText();
			
			
			int calAmt=CalculateTotalAmountAgainstProduct(Integer.parseInt(getqty), Integer.parseInt(getrate1[0]));			//calculate total amt
			Assert.assertEquals(actualtotal, String.valueOf(calAmt));			//verify total against product
			
			calculatedamt=calculatedamt+calAmt;
		}
		
		Common_Methods.toShortWait();
		String totalprice=driver.findElement(By.xpath("//td[text()='Total Price']/ancestor::tfoot[1]//td[5]")).getText();
		String [] totalprice1=totalprice.split("/.");
		System.out.println("totalprice1 "+totalprice1);
		//Assert.assertEquals(totalprice1[1], String.valueOf(calculatedamt),"Getting wrong total calculted price");
		
		Common_Methods.waitForWebElement(driver, closeBtn);
		closeBtn.click();
	}


}
