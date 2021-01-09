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

public class Subscription_Page extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='customerName']")
	WebElement cusName;
	
	@FindBy(xpath="//label[text()='Start Time:']/ancestor::div[1]/following-sibling::div[@class='col-md-8']//input")
	WebElement fromTime;
	
	@FindBy(xpath="//label[text()='End Time:']/ancestor::div[1]/following-sibling::div[@class='col-md-8']//input")
	WebElement toTime;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//b[text()='Subscription']/ancestor::div[2]//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[text()='Add']")
	WebElement addBtn1;
	
	@FindBy(xpath="//button[text()='Linked Subscription']")
	WebElement linkedSubscriptionBtn;
	
	@FindBy(xpath="//button[text()='Stop Subscription']")
	WebElement stopSubscriptionBtn;
	
	@FindBy(xpath="//input[@name='remark']")
	WebElement inputRemark;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement yesBtn;
	
	public Subscription_Page() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddIndividualSubscription(String customer,String delgroup,String pname,String yermonthfrom,String dayfrom,
			String endtime,String yermonthto,String dayto,List<String> slots,String freq,List<String> holiday,String customizeholiday,
			List<String> week1h,List<String> week2h,List<String> week3h,List<String> week4h) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, cusName);
		cusName.sendKeys(customer);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//li[text()='"+customer+"']")).click();
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='deliveryGroupToken']//option", delgroup);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//label[text()='Product Name']/ancestor::div[1]/following-sibling::div[@class='col-md-8']//select[@class='form-control']//option", pname);
		
		
		Common_Methods.waitForWebElement(driver, fromTime);
		fromTime.click();
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
		
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
		System.out.println("endtime "+endtime);
		if(endtime.equals("Till Cancelation")) {
				Common_Methods.selectFromDropDown("//select[@name='EndDate']//option", endtime);
		}else {  //Select Date
			Common_Methods.toShortWait();
			Common_Methods.selectFromDropDown("//select[@name='EndDate']//option", endtime);
			Common_Methods.toShortWait();
			Common_Methods.waitForWebElement(driver, toTime);
			toTime.click();
			Common_Methods.toShortWait();
			Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthto);
			
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayto+"']")).click();

		}
			
		for(int i=0;i<slots.size();i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='Time Slot']/ancestor::div[1]/following-sibling::div[@class='col-md-8']//label[text()='"+slots.get(i)+"']/ancestor::div[1]//input")).click();
			
			WebElement ele=driver.findElement(By.xpath("//label[text()='"+freq+"']"));
			Common_Methods.toElement(ele);
			
			WebElement ele1 = driver.findElement(By.xpath("//button[text()='Customize']"));
			Common_Methods.toElement(ele1);
			
			if(freq.equals("Daily")) {
				
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//input[@type='number']")).sendKeys("1.5");
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//input[@type='number']")).sendKeys(Keys.TAB);;
				
				if(customizeholiday.equals("Customize")) {
					Common_Methods.toShortWait();
					driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//button[text()='Customize']")).click();
					
					WebElement eleweek5 = driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='Week2']"));
					Common_Methods.toElement(eleweek5);
					
					if(week1h.size()>0) {
						for(int b=0;b<week1h.size();b++) {
							Common_Methods.toShortWait();
							driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='Week1']/ancestor::div[1]/following-sibling::div[1]//label[text()='"+week1h.get(b)+"']/ancestor::div[1]//input")).click();
						}
						
					}
					
					if(week2h.size()>0) {
						for(int c=0;c<week2h.size();c++) {
							Common_Methods.toShortWait();
							driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='Week2']/ancestor::div[1]/following-sibling::div[1]//label[text()='"+week2h.get(c)+"']/ancestor::div[1]//input")).click();
						}
					}
					
					if(week3h.size()>0) {
						for(int d=0;d<week3h.size();d++) {
							Common_Methods.toShortWait();
							driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='Week3']/ancestor::div[1]/following-sibling::div[1]//label[text()='"+week3h.get(d)+"']/ancestor::div[1]//input")).click();
						}
					}
					
					if(week4h.size()>0) {
						for(int e=0;e<week4h.size();e++) {
							Common_Methods.toShortWait();
							driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='Week4']/ancestor::div[1]/following-sibling::div[1]//label[text()='"+week4h.get(e)+"']/ancestor::div[1]//input")).click();
						}
					}
					
					Common_Methods.toShortWait();
					driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//button[text()='Add']")).click();
					
				}else {
					for(int j=0;j<holiday.size();j++) {
						Common_Methods.toShortWait();
						WebElement holi = driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//label[text()='"+holiday.get(j)+"']/ancestor::div[1]//input"));
						holi.click();
					}
				}
				
			}else {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[@class='card-body']//label[text()='"+freq+"']/ancestor::div[1]//input")).click();
				
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='Quantity Day1']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys("1.5");
				driver.findElement(By.xpath("//label[text()='Quantity Day1']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys(Keys.TAB);
				
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys("1");
				driver.findElement(By.xpath("//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys(Keys.TAB);
				
				/*Common_Methods.toShortWait();
				WebElement holidayinput =driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[@class='card-body']//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input"));
				holidayinput.sendKeys(Keys.ARROW_UP);
				
				Common_Methods.toShortWait();
				holidayinput.sendKeys(Keys.ARROW_UP);*/
				
			}
		}
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
		
		/*Common_Methods.toShortWait();
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);*/
	}
	
	
	public void GoToLinkedSubscription(String cname) {
		//boolean flag=Common_Methods.isElementPresent("//img[@class='plusImg']");
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, cusName);
		cusName.sendKeys(cname);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//li[text()='"+cname+"']")).click();
		
		Common_Methods.waitForWebElement(driver, linkedSubscriptionBtn);
		linkedSubscriptionBtn.click();	
			
	}
	
	public void UpdateSubscription(String pname,String dayfrom,String yermonthfrom,String dayto,String yermonthto,String endtime,List<String> slots,String freq,String qty) {
		
		driver.findElement(By.xpath("//b[text()='"+pname+"']/ancestor::div[4]//i[@class='fa fa-edit']")).click();
		
		Common_Methods.waitForWebElement(driver, fromTime);
		fromTime.click();
		Common_Methods.toShortWait();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthfrom);
		
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayfrom+"']")).click();
		
		if(endtime.equals("Till Cancelation")) {
			//Nothing to do if to time is till cancelation keep as it is
		}else {
			
			Common_Methods.toShortWait();
			Common_Methods.selectFromDropDown("//select[@name='EndDate']//option", endtime);
			Common_Methods.toShortWait();
			Common_Methods.waitForWebElement(driver, toTime);
			toTime.click();
			Common_Methods.toShortWait();
			Common_Methods.selectMonthFromCalenderForDeliveryBoy(yermonthto);
			
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+dayto+"']")).click();
			
		}
		
		for(int i=0;i<slots.size();i++) {
			Common_Methods.toShortWait();
			
			WebElement ele1 = driver.findElement(By.xpath("//button[text()='Customize']"));
			Common_Methods.toElement(ele1);
			
			if(freq.equals("Daily")) {
				
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//input[@type='number']")).clear();
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//input[@type='number']")).sendKeys(qty);
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//span[text()='"+slots.get(i)+"']/ancestor::div[1]/following-sibling::div[1]//input[@type='number']")).sendKeys(Keys.TAB);;
			}else {
				
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='Quantity Day1']/ancestor::div[1]/following-sibling::div[1]//input")).clear();
				driver.findElement(By.xpath("//label[text()='Quantity Day1']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys("3");
				driver.findElement(By.xpath("//label[text()='Quantity Day1']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys(Keys.TAB);
				
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input")).clear();
				driver.findElement(By.xpath("//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys("4");
				driver.findElement(By.xpath("//label[text()='Quantity Day2']/ancestor::div[1]/following-sibling::div[1]//input")).sendKeys(Keys.TAB);
			}
		}

		//Common_Methods.waitForWebElement(driver, saveBtn);
		//saveBtn.click();
	}
	
	public void VerifySubscriptionAgainstCustomer(String pname,String fdate,String tdate,List<String> holidaylist,String holiday) {
		/*Common_Methods.toShortWait();
		List<WebElement> list = driver.findElements(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']"));
		int len = list.size();
		
		for(int i=1;i<=len;i++) {
			Common_Methods.toShortWait();
			String getActualPname=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='Product Name:']/ancestor::div[1]//b")).getText();
			Assert.assertEquals(getActualPname, pname,"Getting wrong product name");
			
			Common_Methods.toShortWait();
			String getActualFromDate=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='From Date:']/ancestor::div[1]//b")).getText();
			Assert.assertEquals(getActualFromDate, fdate);
			
			Common_Methods.toShortWait();
			String getActualToDate=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='To Date:']/ancestor::div[1]//b")).getText();
			Assert.assertEquals(getActualToDate, tdate);
			
			Common_Methods.toShortWait();
			List<WebElement> holidaysize = driver.findElements(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='Holidays']/ancestor::div[1]//span[2]//span"));
			if(holidaysize.size()>0) {
				for(int j=0;j<holidaysize.size();j++) {
					Common_Methods.toShortWait();
					String getholiday=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='Holidays']/ancestor::div[1]//span[2]//span["+j+"]")).getText();
					Assert.assertEquals(getholiday, holidaylist.get(j),"Getting wrong holiday "+j);		
				}
			}else{
				String getHoliday=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']["+i+"]//span[text()='Holidays']/ancestor::div[1]//b")).getText();
				Assert.assertEquals(getHoliday, holiday,"Getting wrong holiday");
			}
			
		}*/
		
		WebElement ele=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']//b[text()='"+pname+"']"));
		Common_Methods.toElement(ele);
		
		Common_Methods.toShortWait();
		String getActualFromDate=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']//b[text()='"+pname+"']/ancestor::div[3]//span[text()='From Date:']/ancestor::div[1]//b")).getText();
		Assert.assertEquals(getActualFromDate, fdate, "Getting wrong from date");
		
		String getActualToDate=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']//b[text()='"+pname+"']/ancestor::div[3]//span[text()='To Date:']/ancestor::div[1]//b")).getText();
		Assert.assertEquals(getActualToDate, tdate, "Getting wrong to date");
		
		Common_Methods.toShortWait();
		List<WebElement> holidaysize = driver.findElements(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//b[text()='"+pname+"']/ancestor::div[3]//span[text()='Holidays']/ancestor::div[1]//span[2]//span"));
		
		if(holidaysize.size()>0) {
			for(int j=1;j<=holidaysize.size()-1;j++) {
				Common_Methods.toShortWait();
				String getholiday=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//b[text()='"+pname+"']/ancestor::div[3]//span[text()='Holidays']/ancestor::div[1]//span[2]//span["+j+"]")).getText();
				Assert.assertEquals(getholiday, holidaylist.get(j-1),"Getting wrong holiday ");		
			}
			
		}else {
			String getActualHoliday=driver.findElement(By.xpath("//div[@style='height: 500px; overflow-y: auto;']//div[@class='card']//b[text()='"+pname+"']/ancestor::div[3]//span[text()='Holidays']/ancestor::div[1]//b")).getText();
			
			Assert.assertEquals(getActualHoliday,holiday,"Getting wrong holiday");
		}
		
	}
	
	
	public void StopSubscription(String pname,String remark,String tdate,String monthyear) {
		
		driver.findElement(By.xpath("//b[text()='"+pname+"']/ancestor::div[4]//i[@class='fa fa-edit']")).click();
		
		Common_Methods.waitForWebElement(driver, stopSubscriptionBtn);
		stopSubscriptionBtn.click();
		
		Common_Methods.waitForWebElement(driver, inputRemark);
		inputRemark.sendKeys(remark);
		
		WebElement ele=driver.findElement(By.xpath("//label[text()='date:']/ancestor::div[1]/following-sibling::div[@class='col-md-8']//input"));
		
		ele.click();
		Common_Methods.selectMonthFromCalenderForDeliveryBoy(monthyear);
		
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+tdate+"']")).click();
		
		Common_Methods.waitForWebElement(driver, yesBtn);
		yesBtn.click();
		
	}

}
