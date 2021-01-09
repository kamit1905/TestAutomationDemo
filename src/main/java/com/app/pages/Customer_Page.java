package com.app.pages;

import java.math.BigDecimal;

import net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Customer_Page extends TestBase{
	
	@FindBy(xpath="//input[@name='customerName']")
	WebElement custName;
	
	@FindBy(xpath="//input[@name='customerMbNo']")
	WebElement mobileNo;
	
	@FindBy(xpath="//input[@name='customerEmailId']")
	WebElement custEmail;
	
	@FindBy(xpath="//textarea[@name='addressLineOne']")
	WebElement addressLine1;
	
	@FindBy(xpath="//input[@name='deliveryGroupToken']")
	WebElement deliverygroup;
	
	@FindBy(xpath="//input[@name='advanceAmount']")
	WebElement advanceAmt;
	
	@FindBy(xpath="//input[@name='customerDepositAmount']")
	WebElement deposit;
	
	@FindBy(xpath="//input[@name='outstandingAmount']")
	WebElement oustandingAmt;
	
	@FindBy(xpath="//button[text()=' Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtnRecurring;
	
	@FindBy(xpath="//button[text()='Save & Next']")
	WebElement saveAndNextBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	
	//@FindBy(xpath="//b[contains(text(),'Customer List')]/ancestor::div[1]//button[text()='Add']")
	//@FindBy(xpath="//button[text()='Add']")
	@FindBy(xpath="//img[@class='plusImg']/ancestor::button[1]")
	WebElement addBtnOnCustomer;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement custSearchInput;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//input[@name='deliveryTimeInHour']")
	WebElement timeInput;
	
	@FindBy(xpath="//input[@name='productQty']")
	//@FindBy(xpath="//input[@placeholder='Quantity']")
	WebElement productQty;
	
	@FindBy(xpath="//input[@name='dueDateCount']")
	WebElement duedate;
	
	@FindBy(xpath="//input[@name='balanceQuantityOfCans']")
	WebElement balanceQtyInput;
	
	@FindBy(xpath="//button[text()='>Save and Next']")
	WebElement saveAndNextBtn1;
	
	@FindBy(xpath="//button[text()='Update & Next']")
	WebElement updateAndNextBtn;
	
	@FindBy(xpath="//input[@name='deliveryTimeInHour']")
	WebElement deliveryTime;
	
	//@FindBy(xpath="//button[text()='Add']")
	@FindBy(xpath="//img[@class='plusImg']/ancestor::button[1]")
	WebElement addBtnOnRecurring;
	
	public Customer_Page() {
		System.out.println("In Customer Page Customer");
		PageFactory.initElements(driver, this);
	}
	
	//Add and Edit is based on flag
	public void addEditCustomer(boolean editflag,String cname,String mno,String cemail,String addressLine,String dgroup,String city,int advamt,int deposit1,int outstanding,String paymentcycle,String status) {
	    try {
	    	if(editflag) {
		    	
		    	driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[2]//i[@class='fa fa-edit']")).click();
		    	
		    	//Common_Methods.waitForWebElement(driver, custName);
			    //custName.sendKeys(cname);
			    
			    Common_Methods.waitForWebElement(driver, mobileNo);
			    mobileNo.clear();
			    mobileNo.sendKeys(mno);
			    
			    Common_Methods.waitForWebElement(driver,custEmail);
			    custEmail.clear();
			    custEmail.sendKeys(cemail);
			    
			    //Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
			    
			    //Common_Methods.selectFromDropDown("//select[@name='dailyBillingChoice']//option", paymentcycle);
			    
			    //Common_Methods.selectFromDropDown("//select[@name='isCustActive']//option", status);
			    
			    Common_Methods.waitForWebElement(driver, advanceAmt);
			    advanceAmt.clear();
			    advanceAmt.sendKeys(String.valueOf(advamt));
			    
			    Common_Methods.waitForWebElement(driver, deposit);
			    deposit.clear();
			    deposit.sendKeys(String.valueOf(deposit1));
			    
			    /*Common_Methods.waitForWebElement(driver, oustandingAmt);
			    oustandingAmt.clear();
			    oustandingAmt.sendKeys(String.valueOf(outstanding));
			    */
			    
			    //Common_Methods.waitForWebElement(driver, updateBtn);
			    //updateBtn.click();
			    
			    updateAndNextBtn.click();
			    
			    Common_Methods.shortWait();
			    
			    String getmsg=Common_Methods.getToastMessage();
				System.out.println("getmsg "+getmsg);
				Assert.assertEquals(getmsg, "Successfully Updated.");			    
			    
		    }
		    else {
		    	Common_Methods.waitForWebElement(driver, addBtnOnCustomer);
				addBtnOnCustomer.click();
			    
				Common_Methods.waitForWebElement(driver, custName);
			    custName.sendKeys(cname);
			    
			    Common_Methods.waitForWebElement(driver, mobileNo);
			    String mno1=BigDecimal.valueOf(Double.parseDouble(mno)).toPlainString();
			    mobileNo.sendKeys(mno1);
			    
			    Common_Methods.waitForWebElement(driver,custEmail);
			    custEmail.sendKeys(cemail);
			    
			    Common_Methods.waitForWebElement(driver, addressLine1);
			    addressLine1.sendKeys(addressLine);
			    
			    Common_Methods.waitForWebElement(driver, deliverygroup);
			    deliverygroup.sendKeys(dgroup);
			    driver.findElement(By.xpath("//input[@name='deliveryGroupToken']/ancestor::div[1]//li[text()='"+dgroup+"']")).click();
			    
			    Thread.sleep(2000);
			    Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
			    
			    Thread.sleep(2000);
			    Common_Methods.selectFromDropDown("//select[@name='dailyBillingChoice']//option", paymentcycle);
			    
			    Thread.sleep(2000);
			    Common_Methods.selectFromDropDown("//select[@name='isCustActive']//option", status);
			    
			    Common_Methods.waitForWebElement(driver, saveAndNextBtn);
			    saveAndNextBtn.click();
			    
			    Thread.sleep(2000);
			    String getmsg=Common_Methods.getToastMessage();
				System.out.println("getmsg "+getmsg);
				Assert.assertEquals(getmsg, "Successfully saved.");		   
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*public void addEditCustomer1(String cname,String mno,String cemail,String addressLine,String city,String paymentcycle,String status) {
		try {
			Thread.sleep(2000);
			//Common_Methods.waitForWebElement(driver, addBtn);
			//addBtn.click();
			driver.findElement(By.xpath("//button[text()='Add']")).click();
		    
			Common_Methods.waitForWebElement(driver, custName);
		    custName.sendKeys(cname);
		    
		    Common_Methods.waitForWebElement(driver, mobileNo);
		    String mno1=BigDecimal.valueOf(Double.parseDouble(mno)).toPlainString();
		    mobileNo.sendKeys(mno1);
		    
		    Common_Methods.waitForWebElement(driver,custEmail);
		    custEmail.sendKeys(cemail);
		    
		    Common_Methods.waitForWebElement(driver, addressLine1);
		    addressLine1.sendKeys(addressLine);
		    
		    Thread.sleep(2000);
		    Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
		    
		    Thread.sleep(2000);
		    Common_Methods.selectFromDropDown("//select[@name='dailyBillingChoice']//option", paymentcycle);
		    
		    Thread.sleep(2000);
		    Common_Methods.selectFromDropDown("//select[@name='isCustActive']//option", status);
		    
		    Common_Methods.waitForWebElement(driver, saveAndNextBtn);
		    saveAndNextBtn.click();

		    Common_Methods.shortWait();
		    String getmsg=driver.switchTo().alert().getText();
			System.out.println("getmsg "+getmsg);
			driver.switchTo().alert().accept();
			Assert.assertEquals(getmsg, "Successfully saved.");
			System.out.println("Press on ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
	public void addCustomerVerifyErrorMessageForAdvanceDeposit(String cname,String mno,String cemail,String addressLine,String city,int advamt,String paymentcycle,String status) {
		Common_Methods.waitForWebElement(driver, addBtnOnCustomer);
		addBtnOnCustomer.click();
		
		Common_Methods.waitForWebElement(driver, custName);
	    custName.sendKeys(cname);
	    
	    Common_Methods.waitForWebElement(driver, mobileNo);
	    mobileNo.sendKeys(mno);
	    
	    Common_Methods.waitForWebElement(driver,custEmail);
	    custEmail.sendKeys(cemail);
	    
	    Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
	    
	    Common_Methods.waitForWebElement(driver, advanceAmt);
	    advanceAmt.clear();
	    advanceAmt.sendKeys(String.valueOf(advamt));
	    
	    Common_Methods.selectFromDropDown("//select[@name='dailyBillingChoice']//option", paymentcycle);
	    
	    Common_Methods.selectFromDropDown("//select[@name='isCustActive']//option", status);
	    
	    Common_Methods.waitForWebElement(driver, saveAndNextBtn);
	    saveAndNextBtn.click();
	    
	    String errorMsg=driver.findElement(By.xpath("//div[@class='toasts-container ']")).getText();
	    System.out.println("errorMsg "+errorMsg);
	    Assert.assertEquals(errorMsg, "Please enter Valid Value","Not Getting error message");
	}
	
	public void verifyCancelBtnOnCustomer() {
		Common_Methods.waitForWebElement(driver, addBtnOnCustomer);
		addBtnOnCustomer.click();
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
		
		Common_Methods.shortWait();
		boolean status=Common_Methods.isElementVisible(custSearchInput);
		if(status) {
			System.out.println("Cancel button is working");
		}
		else {
			System.out.println("Cancel buttton is not working");
		}
		
	}
	
	
	/*public void placeRecurringOrder(String pname,String dboy,String pqty,String fdate,String duecount) {
		try {
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@name='productId']//option", pname);
			
			Thread.sleep(2000);
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",dboy);
			
			Common_Methods.waitForWebElement(driver, productQty);
			System.out.println("pqty "+pqty);
			String pqty1 []=BigDecimal.valueOf(Double.parseDouble(pqty)).toPlainString().split(".");
			System.out.println("pqty1 "+pqty1[1]);
			productQty.sendKeys(pqty1[1]);
			
			System.out.println("fdate "+fdate);
		    String fdate1[]=BigDecimal.valueOf(Double.parseDouble(fdate)).toPlainString().split(".");
			System.out.println("fdate1 "+fdate1[1]);
			//productQty.sendKeys(String.valueOf(pqty));
			
			driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+fdate1[1]+"']")).click();
		
			Common_Methods.waitForWebElement(driver, duedate);
			//duedate.sendKeys(String.valueOf(duecount));
			String duecount1[]=BigDecimal.valueOf(Double.parseDouble(duecount)).toPlainString().split(".");
			System.out.println("duecount1 "+duecount1[1]);
			duedate.sendKeys(duecount1[1]);
			
			//Common_Methods.waitForWebElement(driver, balanceQtyInput);
			//balanceQtyInput.clear();
			//balanceQtyInput.sendKeys(String.valueOf(bqty));
			
			//Common_Methods.waitForWebElement(driver, saveAndNextBtn1);
			//saveAndNextBtn1.click();
			Common_Methods.waitForWebElement(driver, saveBtnRecurring);
			saveBtnRecurring.click();
			
            Common_Methods.shortWait();
		    
		    String getmsg=driver.switchTo().alert().getText();
			System.out.println("getmsg "+getmsg);
			driver.switchTo().alert().accept();
			Assert.assertEquals(getmsg, "Successfully saved.");
			System.out.println("Press on ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
	
	public void verifyCancelButtonWhilePlacingRecurringOrder() {
		Common_Methods.waitForWebElement(driver, addBtnOnCustomer);
		addBtnOnCustomer.click();
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
		
		Common_Methods.shortWait();
		boolean status=Common_Methods.isElementVisible(custSearchInput);
		if(status) {
			System.out.println("Cancel button is working");
		}
		else {
			System.out.println("Cancel buttton is not working in recurring order");
		}
		
	}
	
	public void verifyRecurringOrder(String pname,int qty) {
		Common_Methods.waitForWebElement(driver, custSearchInput);
		custSearchInput.sendKeys(pname);
		
		String getProductName=driver.findElement(By.xpath("//tr[@class='borderBottomTable']//td[2]")).getText();
		String getQty=driver.findElement(By.xpath("//tr[@class='borderBottomTable']//td[4]")).getText();
		
		Assert.assertEquals(getProductName, pname);
		Assert.assertEquals(getQty, String.valueOf(qty));
	}
	
	public void verifyCustomerSearch(String cname) {
		Common_Methods.shortWait();
		Common_Methods.waitForWebElement(driver, custSearchInput);
		custSearchInput.sendKeys(cname);
		
		String getCustName=driver.findElement(By.xpath("")).getText();
		
	}
	
	public void addCustomerAndPlaceRecurringThroughExcel(String cname,String mno,String cemail,String addressLine,String city,String paymentcycle,String status,String pname,String dboy,String pqty,String deltime,String fdate,String monthyear,String duecount) {
		try {
			Thread.sleep(2000);
			Common_Methods.waitForWebElement(driver, addBtnOnCustomer);
			addBtnOnCustomer.click();
		    
			Common_Methods.waitForWebElement(driver, custName);
		    custName.sendKeys(cname);
		    
		    Common_Methods.waitForWebElement(driver, mobileNo);
		    String mno1=BigDecimal.valueOf(Double.parseDouble(mno)).toPlainString();
		    mobileNo.sendKeys(mno1);
		    
		    Common_Methods.waitForWebElement(driver,custEmail);
		    custEmail.sendKeys(cemail);
		    
		    Common_Methods.waitForWebElement(driver, addressLine1);
		    addressLine1.sendKeys(addressLine);
		    
		   // Thread.sleep(2000);
		    //Common_Methods.selectFromDropDown("//select[@name='cityId']//option", city);
		    
		    Thread.sleep(2000);
		    Common_Methods.selectFromDropDown("//select[@name='dailyBillingChoice']//option", paymentcycle);
		    
		    Thread.sleep(2000);
		    Common_Methods.selectFromDropDown("//select[@name='isCustActive']//option", status);
		    
		    //Common_Methods.waitForWebElement(driver, saveAndNextBtn);
		    saveAndNextBtn.click();
		    
		    Common_Methods.shortWait();
		    AddRecurringOrder(pname, dboy, pqty,deltime,fdate, monthyear, duecount);
		    
		    /*Common_Methods.shortWait();
		    String getmsg=Common_Methods.getToastMessage();
			//System.out.println("getmsg "+getmsg);
			//driver.switchTo().alert().accept();
			Assert.assertEquals(getmsg, "Successfully saved.");
			//System.out.println("Press on ok");
			
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@name='productId']//option", pname);
			
			Thread.sleep(2000);
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",dboy);
			
			Common_Methods.waitForWebElement(driver, productQty);
			//System.out.println("pqty "+pqty);
			String pqty1 []=BigDecimal.valueOf(Double.parseDouble(pqty)).toPlainString().split("\\.");
			//System.out.println("pqty1 "+pqty1[0]);
			productQty.sendKeys(pqty1[0]);
			
			//System.out.println("fdate "+fdate);
		    String fdate1[]=BigDecimal.valueOf(Double.parseDouble(fdate)).toPlainString().split("\\.");
			//System.out.println("fdate1 "+fdate1[0]);
			//productQty.sendKeys(String.valueOf(pqty));
			
			//driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
			driver.findElement(By.xpath("//input[@name='fromDate']")).click();
			Common_Methods.selectMonthFromCalender(monthyear);
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+fdate1[0]+"']")).click();
		
			Common_Methods.waitForWebElement(driver, duedate);
			//duedate.sendKeys(String.valueOf(duecount));
			String duecount1[]=BigDecimal.valueOf(Double.parseDouble(duecount)).toPlainString().split("\\.");
			//System.out.println("duecount1 "+duecount1[0]);
			duedate.sendKeys(duecount1[0]);
			
			Common_Methods.waitForWebElement(driver, saveBtnRecurring);
			saveBtnRecurring.click();
			
            Common_Methods.shortWait();
		    
		    String getmsg1=driver.switchTo().alert().getText();
			System.out.println("getmsg1 "+getmsg1);
			driver.switchTo().alert().accept();
			Assert.assertEquals(getmsg, "Successfully saved.");
			System.out.println("Press on ok");*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void verifyErrorMessageIfNotEnteredMandatoryField(String cname) {
		try {
			Thread.sleep(2000);
			Common_Methods.waitForWebElement(driver,addBtnOnCustomer);
			addBtnOnCustomer.click();
		    
			Common_Methods.waitForWebElement(driver, custName);
		    custName.sendKeys(cname);
		    
		    Common_Methods.waitForWebElement(driver, saveBtn);
		    saveBtn.click();
		    
		    Thread.sleep(2000);
		    String msg=Common_Methods.getToastMessage();
		    System.out.println("msg "+msg);
		    Assert.assertEquals(msg, "Please fill all mandatory fields");
		    
		    
		    Common_Methods.waitForWebElement(driver, saveAndNextBtn);
		    saveAndNextBtn.click();
		    Thread.sleep(2000);
		    String msg1=Common_Methods.getToastMessage();
		    System.out.println("msg "+msg1);
		    Assert.assertEquals(msg1, "Please fill all mandatory fields");
		    
		    cancelBtn.click();
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void deleteCustomerAndVerifyErrorMessage(String cname) {
		try {
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();;
		
			Thread.sleep(2000);
			String getMsg=Common_Methods.getToastMessage();

			if(getMsg.equals("Customer successfully deactivated records in tracking of cans delivered")) {
			Assert.assertEquals(getMsg, "Customer successfully deactivated records in tracking of cans delivered");
			}
			else if (getMsg.equals("Customer delete successfully")) {
				Assert.assertEquals(getMsg, "Customer delete successfully");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void DeleteRecurringOrder(String pname){
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			
			String msg=Common_Methods.getToastMessage();
			System.out.println("msgindelivery "+msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void AddRecurringOrder(String pname,String dboy,String pqty,String deltime,String fdate,String monthyear,String duecount){
		try {
			boolean status=Common_Methods.isElementVisible(addBtnOnRecurring);
			//System.out.println("status "+status);
			if(status){
				//driver.findElement(By.xpath("//button[text()='Add']")).click();
				addBtnOnRecurring.click();
			}
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@name='productId']//option", pname);
			
			Thread.sleep(2000);
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",dboy);
			
			Common_Methods.waitForWebElement(driver, deliveryTime);
			Common_Methods.toShortWait();
			//deliveryTime.sendKeys(deltime);
			deliveryTime.click();
			Common_Methods.SendKeysUsingJavascriptExecutor(deliveryTime, deltime);
			
			Common_Methods.waitForWebElement(driver, productQty);
			//System.out.println("pqty "+pqty);
			String pqty1 []=BigDecimal.valueOf(Double.parseDouble(pqty)).toPlainString().split("\\.");
			//System.out.println("pqty1 "+pqty1[0]);
			productQty.sendKeys(pqty1[0]);
			
			//System.out.println("fdate "+fdate);
		    String fdate1[]=BigDecimal.valueOf(Double.parseDouble(fdate)).toPlainString().split("\\.");
			//System.out.println("fdate1 "+fdate1[0]);
			//productQty.sendKeys(String.valueOf(pqty));
			
			//driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
			driver.findElement(By.xpath("//input[@name='fromDate']")).click();
			String getsta=Common_Methods.selectMonthFromCalender(monthyear);
			if(getsta.equals("Current month")) {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'datepicker__day--today')][text()='"+fdate1[0]+"']")).click();
			}else {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+fdate1[0]+"']")).click();
			}
			Common_Methods.waitForWebElement(driver, duedate);
			//duedate.sendKeys(String.valueOf(duecount));
			String duecount1[]=BigDecimal.valueOf(Double.parseDouble(duecount)).toPlainString().split("\\.");
			//System.out.println("duecount1 "+duecount1[0]);
			duedate.sendKeys(duecount1[0]);
			
			/*Common_Methods.waitForWebElement(driver, saveBtnRecurring);
			saveBtnRecurring.click();
			
	        Common_Methods.shortWait();
		    
		    String getmsg1=Common_Methods.getToastMessage();
			System.out.println("getmsg1 "+getmsg1);*/
			//Assert.assertEquals(getmsg, "Successfully saved.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String GetCustomerAdvanceAmount(String cname){
		String advamt="";
		try {
			Common_Methods.waitForWebElement(driver, custSearchInput);
			custSearchInput.clear();
			custSearchInput.sendKeys(cname);
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
			Common_Methods.waitForWebElement(driver, advanceAmt);
			Common_Methods.toShortWait();
			advamt=advanceAmt.getAttribute("value");
			System.out.println("advamt "+advamt);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return advamt;
		
		
	}
}
