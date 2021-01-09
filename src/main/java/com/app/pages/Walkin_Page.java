package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Walkin_Page extends TestBase{
	
	@FindBy(xpath="//input[@placeholder='Search By Customer name/Mobile number']")
	WebElement SearchInput;
	
	@FindBy(xpath="//input[@name='Quantity']")
	WebElement Quantity;
	
	@FindBy(xpath="//input[@name='ReturnIn']")
	WebElement ReturnIn;
	
	@FindBy(xpath="//input[@name='fromDate']")
	WebElement FromDate;
	
	@FindBy(xpath="//button[text()='Payment']")
	WebElement PaymentBtn;
	
	@FindBy(xpath="//label[text()='Advance Amount']/ancestor::div[2]")
	WebElement Advance;
	
	@FindBy(xpath="//button[text()='Pay']")
	WebElement PayBtn;
	
	@FindBy(xpath="//button[text()='Renew']")
	WebElement RenewBtn;
	
	@FindBy(xpath="//button[text()='Pay & Print']")
	WebElement PayAndPrintBtn;
	
	@FindBy(xpath="//input[@name='Deposit']")
	WebElement DepositInput;
	
	@FindBy(xpath="//input[@placeholder='Search By Customer name']")
	WebElement SearchInputOnReturn;
	
	@FindBy(xpath="//b[text()='Payment']/ancestor::div[2]//label[contains(text(),'Advance Amount')]")
	WebElement Adv;
	
	
	public Walkin_Page(){
		System.out.println("In walkin page ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void PlaceWalkinOrder(String cus,String pname,int qua,int returnindays,String mode,String adv,String modeofpayment){
		try {
			
			boolean flag=Common_Methods.isElementVisible(SearchInput);
			System.out.println("flag "+flag);
			if(flag){
			Common_Methods.waitForWebElement(driver, SearchInput);
			SearchInput.sendKeys(cus);
			
			//Common_Methods.toShortWait();
			driver.findElement(By.xpath("//li[text()='"+cus+"']")).click();
			}
			SelectProductInWalkin(pname, qua, returnindays, mode);
			
			SelectPaymentInWalkin(modeofpayment,adv);
			
			Common_Methods.waitForWebElement(driver, PayBtn);
			PayBtn.click();
			
			Thread.sleep(2000);
			String getmsg=Common_Methods.getToastMessage();
			//System.out.println("getmsg "+getmsg);
			Assert.assertEquals(getmsg, "Order Successfully Placed.");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void SelectProductInWalkin(String pname,int qua,int returnindays,String mode){
		try {
			Thread.sleep(3000);
			String advamt;
			Common_Methods.selectFromDropDown("//select[@name='productId']//option", pname);
			Common_Methods.waitForWebElement(driver, Quantity);
			Quantity.clear();
			Quantity.sendKeys(String.valueOf(qua));
			
			Common_Methods.waitForWebElement(driver, ReturnIn);
			ReturnIn.clear();
			ReturnIn.sendKeys(String.valueOf(returnindays));
			
			/*boolean flag=Common_Methods.isElementVisible(Advance);
			if(flag){
				String getAdvAmt=driver.findElement(By.xpath("//label[text()='Advance Amount']/ancestor::div[2]//div[2]//label")).getText();
				Assert.assertEquals(getAdvAmt, advamt,"Getting wrong advance amount in walkin");
			}*/
			
			if(!mode.equals("Deposit")){
			boolean status=Common_Methods.isElementVisible(DepositInput);
			System.out.println("status "+status);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+mode+"']/ancestor::div[1]//input")).click();
			}
			
			Common_Methods.waitForWebElement(driver, PaymentBtn);
			PaymentBtn.click();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void SelectPaymentInWalkin(String modeofpayment,String adv){
		try {
			boolean flag=Common_Methods.isElementVisible(Adv);
			if(flag){
				String getActualAdvAmt=driver.findElement(By.xpath("//b[text()='Payment']/ancestor::div[2]//label[contains(text(),'Advance Amount')]/ancestor::div[2]//div[2]//label")).getText();
				//Assert.assertEquals(getActualAdvAmt, adv,"Getting wrong advance amount in walkin");
				
				//Calculate Payable Amount
				int payable=CalculatePayableAmountHavingAdvance(getActualAdvAmt);
				System.out.println("Payable "+payable);
				
				//Compare Calculated Payable & Actual Payable
				String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
				System.out.println("getActualPayableAmt "+getActualPayableAmt);
				Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
				
				String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
				System.out.println("getActualPaid "+getActualPaid);
				Assert.assertEquals(getActualPaid, String.valueOf(payable),"Getting wrong paid amount");
				
				//Select Mode Of Payment
				driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
			
			}else{
				
				//Calculate Payable Amount
				int payable=CalculatePayableAmount();
				System.out.println("Payable "+payable);
				
				//Compare Calculated Payable & Actual Payable
				String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
				System.out.println("getActualPayableAmt "+getActualPayableAmt);
				Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
				
				String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
				System.out.println("getActualPaid "+getActualPaid);
				
				//Select Mode Of Payment
				driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
				
			}
			
			//Calculate Payable Amount
			/*int payable=CalculatePayableAmount();
			System.out.println("Payable "+payable);
			
			//Compare Calculated Payable & Actual Payable
			String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
			System.out.println("getActualPayableAmt "+getActualPayableAmt);
			Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
			
			String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
			System.out.println("getActualPaid "+getActualPaid);
			
			//Select Mode Of Payment
			driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void SelectPaymentInWalkin1(String prevoiusmode,String modeofpayment,String adv){
		try {
			boolean flag=Common_Methods.isElementVisible(Adv);
			if(flag && !prevoiusmode.equals("Deposit")){
				String getActualAdvAmt=driver.findElement(By.xpath("//b[text()='Payment']/ancestor::div[2]//label[contains(text(),'Advance Amount')]/ancestor::div[2]//div[2]//label")).getText();
				//Assert.assertEquals(getActualAdvAmt, adv,"Getting wrong advance amount in walkin");
				
				//Calculate Payable Amount
				int payable=CalculatePayableAmountHavingAdvance(getActualAdvAmt);
				System.out.println("Payable "+payable);
				
				//Compare Calculated Payable & Actual Payable
				String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
				System.out.println("getActualPayableAmt "+getActualPayableAmt);
				Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
				
				String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
				System.out.println("getActualPaid "+getActualPaid);
				Assert.assertEquals(getActualPaid, String.valueOf(payable),"Getting wrong paid amount");
				
				//Select Mode Of Payment
				driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
			
			}else{
				
				//Calculate Payable Amount
				int payable=CalculatePayableAmount();
				System.out.println("Payable "+payable);
				
				//Compare Calculated Payable & Actual Payable
				String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
				System.out.println("getActualPayableAmt "+getActualPayableAmt);
				Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
				
				String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
				System.out.println("getActualPaid "+getActualPaid);
				
				//Select Mode Of Payment
				driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
				
			}
			
			//Calculate Payable Amount
			/*int payable=CalculatePayableAmount();
			System.out.println("Payable "+payable);
			
			//Compare Calculated Payable & Actual Payable
			String getActualPayableAmt=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
			System.out.println("getActualPayableAmt "+getActualPayableAmt);
			Assert.assertEquals(getActualPayableAmt, String.valueOf(payable));
			
			String getActualPaid=driver.findElement(By.xpath("//input[@name='PaidAmount']")).getAttribute("placeholder");
			System.out.println("getActualPaid "+getActualPaid);
			
			//Select Mode Of Payment
			driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void PlaceWalkinOrder(String cus,String pname,int qua,int returnindays,String mode,String adv,String modeofpayment,String payprint){
		try {
			Common_Methods.waitForWebElement(driver, SearchInput);
			SearchInput.sendKeys(cus);
			
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//li[text()='"+cus+"']")).click();
			
			//Select Product In Walkin
			SelectProductInWalkin(pname,qua,returnindays,mode);
			
			//Do Payment in walkin
			SelectPaymentInWalkin(modeofpayment,adv);
			
			if(payprint.equals("Pay & Print")){
				Common_Methods.waitForWebElement(driver, PayAndPrintBtn);
				Common_Methods.ClosePdfOpenedInBrowser(PayAndPrintBtn);
				}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ReturnProductInWakin(String cname,String date,String monthyear,String modeofpayment,int overduecharges){
		
		//driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
		driver.findElement(By.xpath("//input[@name='currentDate']")).click();
		Common_Methods.selectMonthFromCalender(monthyear);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+date+"']")).click();
		
		Common_Methods.mediumWait();
		Common_Methods.waitForWebElement(driver, SearchInputOnReturn);
		SearchInputOnReturn.clear();
		SearchInputOnReturn.sendKeys(cname);
		
		driver.findElement(By.xpath("//u[text()='"+cname+"']/ancestor::span[1]")).click();
		
		//Calculate overdue charges & compare it with actual
		Common_Methods.toShortWait();
		String getqty=Quantity.getAttribute("value");
		System.out.println("getqty "+getqty);
		int duecharegs=CalculateOverDueCharges(Integer.parseInt(getqty),overduecharges);
		
		Common_Methods.toShortWait();
		String getactualoverdue=driver.findElement(By.xpath("//input[@placeholder='Overdue Chrge']")).getAttribute("value");
		System.out.println("getactualoverdue "+getactualoverdue);
		
		Assert.assertEquals(getactualoverdue, String.valueOf(duecharegs),"Getting wrong overdue charges");
		
		Common_Methods.waitForWebElement(driver, PaymentBtn);
		PaymentBtn.click();
		
		//Get actual amount payable & compare it with calculated value
		CalculateRefundAmtOrAmtPayableAtTheTimeOfRefund(duecharegs);
		
		//Select Mode Of Payment
		driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
		
		/*Common_Methods.waitForWebElement(driver, PayBtn);
		PayBtn.click();
		
		Common_Methods.toShortWait();
		String getmsg=Common_Methods.getToastMessage();
		//System.out.println("getmsg "+getmsg);
		Assert.assertEquals(getmsg, "Payment Successfully .");*/
		
	}
	
	public void ReturnProductInWakin(String cname,String date,String monthyear,String modeofpayment,int overduecharges,String renew){
		
		driver.findElement(By.xpath("//input[@name='currentDate']")).click();
		Common_Methods.selectMonthFromCalender(monthyear);
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+date+"']")).click();
		
		Common_Methods.mediumWait();
		Common_Methods.waitForWebElement(driver, SearchInputOnReturn);
		SearchInputOnReturn.clear();
		SearchInputOnReturn.sendKeys(cname);
		
		driver.findElement(By.xpath("//u[text()='"+cname+"']/ancestor::span[1]")).click();
		
		//Calculate overdue charges & compare it with actual
		Common_Methods.toShortWait();
		String getqty=Quantity.getAttribute("value");
		System.out.println("getqty "+getqty);
		int duecharegs=CalculateOverDueCharges(Integer.parseInt(getqty),overduecharges);
		
		String getactualoverdue=driver.findElement(By.xpath("//input[@placeholder='Overdue Chrge']")).getAttribute("value");
		System.out.println("getactualoverdue "+getactualoverdue);
		
		Assert.assertEquals(getactualoverdue, String.valueOf(duecharegs),"Getting wrong overdue charges");
		
		Common_Methods.waitForWebElement(driver, PaymentBtn);
		PaymentBtn.click();
		
		//Get actual amount payable & compare it with calculated value
		CalculateRefundAmtOrAmtPayableAtTheTimeOfRefund(duecharegs);
		
		//Select Mode Of Payment
		driver.findElement(By.xpath("//label[text()='"+modeofpayment+"']/ancestor::div[1]//input")).click();
				
		if(renew.equals("Renew")){
		Common_Methods.waitForWebElement(driver, RenewBtn);
		RenewBtn.click();
		
		Common_Methods.toShortWait();
		String getmsg=Common_Methods.getToastMessage();
		System.out.println("getmsg "+getmsg);
		}
		
		
	}
	
	public int CalculatePayableAmount(){
		int payable;
		String getCurrentBillAmt=driver.findElement(By.xpath("//label[text()='Current Bill Amount:']/ancestor::div[2]//div[2]//label")).getText();
		String getDepositAmt=driver.findElement(By.xpath("//label[text()='Deposit:']/ancestor::div[2]//div[2]//label")).getText();
		
		payable=Integer.parseInt(getCurrentBillAmt)+Integer.parseInt(getDepositAmt);
		return payable;
	}
	
	public int CalculatePayableAmountHavingAdvance(String adv){
		int payable;
		String getCurrentBillAmt=driver.findElement(By.xpath("//label[text()='Current Bill Amount:']/ancestor::div[2]//div[2]//label")).getText();
		String getDepositAmt=driver.findElement(By.xpath("//label[text()='Deposit:']/ancestor::div[2]//div[2]//label")).getText();
		
		payable=Integer.parseInt(getCurrentBillAmt)+Integer.parseInt(getDepositAmt)-Integer.parseInt(adv);
		return payable;
	}
	
	public int CalculateOverDueCharges(int qty,int overduecharge){
		
		//Overdue Formula=qty*overdue*noofdays
		int overdue=0;
		String getfromdate=FromDate.getAttribute("value");
		String gettodate=TestBase.getDate();
		int daysdiff=Common_Methods.CalculateDateDifference(getfromdate, gettodate);
		
		if(daysdiff>0){
		overdue=qty*overduecharge*daysdiff;
		System.out.println("overdue "+overdue);
		return overdue;
		}
		else{
			return overdue;
		}
	}
	
	public void CalculateRefundAmtOrAmtPayableAtTheTimeOfRefund(int overdue){
		int payable;
		String getDepositAmt=driver.findElement(By.xpath("//label[text()='Security Deposit:']/ancestor::div[2]//div[2]//label")).getText();
		String getAmtPayable=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
		
		if(Integer.parseInt(getDepositAmt)>0){
		String getRefundAmt=driver.findElement(By.xpath("//label[text()='Refund Amount:']/ancestor::div[2]//div[2]//label")).getText();
		payable=Integer.parseInt(getDepositAmt)-overdue;
		System.out.println("Payable "+payable);
		
		if(payable<0){
			payable=-payable;
			Assert.assertEquals(String.valueOf(payable), getAmtPayable,"Getting wrong amount Payable at the time of return product if deposit is less than overdue");
		}
		else{
			Assert.assertEquals(getRefundAmt, String.valueOf(payable),"Getting wrong refund amount at the time of return product");
		 }
		
		}
		else{
			//String getActualAmtPayable=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
			Assert.assertEquals(getAmtPayable, String.valueOf(overdue),"Getting wrong amount payable at the time of return");
		}
		
		
	}

}

/*if(overdue > Integer.parseInt(getDepositAmt)){
	//String getAmtPayable=driver.findElement(By.xpath("//label[text()='Payable Amount:']/ancestor::div[2]//div[2]//label")).getText();
	payable=overdue-Integer.parseInt(getDepositAmt);
	System.out.println("Payable "+payable);
	
	Assert.assertEquals(getAmtPayable, String.valueOf(payable),"Getting wrong amount Payable at the time of return product if deposit is less than overdue");
}*/