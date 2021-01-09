package com.app.pages;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Billing_Page extends TestBase{
	
	@FindBy(xpath="//input[@id='fromDate']")
	WebElement fromDate;
	
	@FindBy(xpath="//input[@id='toDate']")
	WebElement toDate;
	
	@FindBy(xpath="//button[text()='View']")
	WebElement view;
	
	@FindBy(xpath="//span[text()='Billing']/ancestor::a[1]")
	WebElement billingLink;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-down']/ancestor::td[1]")
	WebElement expandLink;
	
	@FindBy(xpath="//th[text()='product Name']/ancestor::table[1]//td[1]")
	WebElement getProductName;
	
	@FindBy(xpath="//th[text()='Quantity']/ancestor::table[1]//td[2]")
	WebElement getQuantity;
	
	@FindBy(xpath="//th[text()='Amount']/ancestor::table[1]//td[3]")
	WebElement getAmount;
	
	@FindBy(xpath="//b[text()='customer Name:']/ancestor::td[1]")
	WebElement getCustName;
	
	@FindBy(xpath="//b[text()='Amount']/ancestor::td[1]")
	WebElement getFinalCustAmt;
	
	@FindBy(xpath="//input[@id='fromDate']")
	WebElement fromDateIn;
	
	@FindBy(xpath="//input[@id='toDate']")
	WebElement toDateIn;
	
	@FindBy(xpath="//label[contains(text(),'Bhaskar')]/ancestor::tr[2]//i[@class='fa fa-chevron-down']")
	WebElement expandLinkOnBillingForCus;
	
	@FindBy(xpath="//button[text()='Bill']")
	WebElement generateBillBtn;
	
	@FindBy(xpath="//button[contains(text(),'Bill & Print ')]")
	WebElement generatePrintBtn;
	
	@FindBy(xpath="//button[text()='Skip']")
	WebElement skipBtn;
	
	@FindBy(xpath="//input[@name='payment_amount']")
	WebElement payInputOnBilling;
	
	@FindBy(xpath="//input[@name='remarks']")
	WebElement remarkInput;
	
	@FindBy(xpath="//button[text()='Pay']")
	WebElement payBtn;
	
	@FindBy(xpath="//input[@placeholder='Customer Name']")
	WebElement cusSearchInputOnBillin;
	
	@FindBy(xpath="//b[text()='Select All']/ancestor::div[1]//input")
	WebElement selectAllOption;
	
	@FindBy(xpath="//button[text()='Re Generate Bill']")
	WebElement RegenerateButton;
	
	public Billing_Page() {
		System.out.println("In Billing Page Constructor");
		PageFactory.initElements(driver, this);
	}
	
	public void doBilling(String customername,int fdate,int tdate,String dboy,String pname,int qty,int rate,String proarray [],int ratearr) {
		try {
			Thread.sleep(1000);
			Common_Methods.waitForWebElement(driver, billingLink);
			billingLink.click();
			
			Thread.sleep(1000);
			Common_Methods.waitForWebElement(driver, fromDate);
			fromDate.sendKeys(Common_Methods.daysBefore(fdate));
			
			Thread.sleep(1000);
			Common_Methods.waitForWebElement(driver, toDate);
			fromDate.sendKeys(Common_Methods.daysBefore(fdate));
			
			
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",dboy);
			
			Common_Methods.waitForWebElement(driver, view);
			view.click();
			
			Common_Methods.waitForWebElement(driver, expandLink);
			expandLink.click();
			
			Common_Methods.shortWait();
			String getCustomerName=getCustName.getText();
			Assert.assertEquals(getCustomerName, customername);
			
			Common_Methods.shortWait();
			String getPname=getProductName.getText();
			Common_Methods.shortWait();
			String getQty=getQuantity.getText();
			Common_Methods.shortWait();
			String getamt=getAmount.getText();
			
			//Verify Product Name
			System.out.println("getProductName "+getPname);
			Assert.assertEquals(getPname, pname);
			
			//Verify Qty
			System.out.println("getQty "+getQty);
			Assert.assertEquals(getQty,String.valueOf(qty));
			
			//Verify Amount
			System.out.println("getAmount "+getamt);
			int calculateamt=calculateAmt(rate,qty);
			System.out.println("calculateamt "+calculateamt);
			Assert.assertEquals(getamt, String.valueOf(calculateamt));
			
			//Verify amt
			Common_Methods.shortWait();
			String getAmount1=getFinalCustAmt.getText();
			//Assert.assertEquals(getAmount1, String.valueOf(calculateamt));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	private int calculateAmt(int rate,int qty) {
		int total=rate*qty;
		System.out.println("Total "+total);
		return total;
	}
	
	public void inCaseOfAllDeliveryBoyBilling() {
		List<WebElement> li = driver.findElements(By.xpath("//table[@class='table']//tbody//tr[@style='text-align: left;"));
		int lenght=li.size();
		System.out.println("size "+lenght);
		
		String customerArray[]= {"Akshay","Anup"};
		String productName[]= {"Aqua Jar","Bisleri Jar"};
		int qty []= {3,3};
		int rate []= {60,80};
		
		for(int i=0;i<lenght;i++) {
			//String getpname=driver.findElement(By.xpath("//b[text()='customer Name:']/ancestor::td[contains(text(),'"++"')]/ancestor::tr[2]//tbody//td[2]"))
					////b[text()='customer Name:']/ancestor::td[contains(text(),'Anup')]/ancestor::tr[2]//tbody//td[2]
			
		}
	}
	
	public void applyFilterOnBillingScreen(String fdate,String fyear,String tdate,String tyear,String deliveryBoy) {
		//Common_Methods.waitForWebElement(driver, billingLink);
		//billingLink.click();
		
		Common_Methods.waitForWebElement(driver, fromDateIn);
		fromDateIn.click();
		String getsta=Common_Methods.selectMonthFromCalender(fyear);
		if(getsta.equals("Current month")) {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+fdate+"']")).click();
		}else {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'react-datepicker__day')][@aria-label='"+fdate+"']")).click();
		}
		
		
		Common_Methods.waitForWebElement(driver, toDateIn);
		toDateIn.click();
		String getsta1=Common_Methods.selectMonthFromCalender(tyear);
		
		if(getsta1.equals("Current month")) {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'datepicker__day--today')][@aria-label='"+tdate+"']")).click();
		}else {
			//driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[contains(@class,'react-datepicker__day')][@aria-label='"+tdate+"']")).click();
		}
	
		Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",deliveryBoy);
		//aria-label="month-2020-01"
		Common_Methods.waitForWebElement(driver, view);
		view.click();
	}
	
	public void applyFilterOnBillingScreen(String fdate,String fyear,String tdate,String tyear,String deliveryBoy,String cusname) {
		try {
			//Common_Methods.waitForWebElement(driver, billingLink);
			//billingLink.click();
			
			Common_Methods.waitForWebElement(driver, fromDateIn);
			fromDateIn.click();
			String getsta=Common_Methods.selectMonthFromCalender(fyear);
			if(getsta.equals("Current month")) {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			}else {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			}
			
			Common_Methods.waitForWebElement(driver, toDateIn);
			toDateIn.click();
			String getsta1=Common_Methods.selectMonthFromCalender(tyear);
			
			if(getsta1.equals("Current month")) {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+fdate+"']")).click();
			}else {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+fdate+"']")).click();
			}
			
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option",deliveryBoy);
			
			Common_Methods.waitForWebElement(driver, view);
			view.click();
			
			Common_Methods.longWait();
			cusSearchInputOnBillin.sendKeys(cusname);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void calculateTotalAmtForSingleCusAndVerify1(String cusName) {
		driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//input[@type='checkbox']")).click();
		
		Common_Methods.waitForWebElement(driver, generateBillBtn);
		generateBillBtn.click();
	}
	
	public void calculateTotalAmtForSingleCusAndVerify(String cusName,String status) {
		//String getActualFinalAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[1]//b[contains(text(),'Amount')]/ancestor::td[1]")).getText();	
	
		if(status.equals("Yes")) {
			Common_Methods.toShortWait();
			String getActualFinalAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[1]//td[2]")).getText();
			System.out.println("GetFinalAmt "+getActualFinalAmt);
			
			
			//Expand link for customer on billing screen
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//i[@class='fa fa-chevron-down']")).click();
			Common_Methods.shortWait();
			//List<WebElement> li=driver.findElements(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody"));   ///for external use this
			List<WebElement> li=driver.findElements(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody"));
			int length=li.size();
			System.out.println("length "+length);
			double getamount1=0;double getgstamt=0;
			
			for(int i=1;i<=length;i++) {
				//String getAmount=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody["+i+"]//td[4]")).getText();    //for external use this
				//String getTaxPer=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody["+i+"]//td[3]")).getText();	//for external use this
				
				String getAmount=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody["+i+"]//td[4]")).getText();
				System.out.println("getAmount "+getAmount);
				String getTaxPer=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody["+i+"]//td[3]")).getText();
				String [] getTaxPer1=getTaxPer.split("%");
				System.out.println("getTaxPer[0] "+getTaxPer1[0]);
				double getTaxPer2=((Double.parseDouble(getTaxPer1[0]))*Double.parseDouble(getAmount))/100;
				getgstamt = getgstamt+getTaxPer2;
				System.out.println("getgstamt "+getgstamt);
				System.out.println("getTaxPer2 "+getTaxPer2);
				double getAmountInInt=Double.parseDouble(getAmount)-getTaxPer2;
				getamount1=getamount1+getAmountInInt;
				//System.out.println("amtinfor "+getamount1);
			}
			System.out.println("CalculateFinalAmt "+getamount1);
			
			//Assert.assertEquals(Double.parseDouble(getActualFinalAmt), getamount1, "Getting wrong final amount");
			
			String getActualSubTotal=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//label[text()='SubTotal']/ancestor::td[1]//span")).getText();
			Assert.assertTrue(String.valueOf(getamount1).contains(getActualSubTotal), "Getting wrong sub total");
			
			//Get and comapare GST & Total Amt value
			Common_Methods.toShortWait();
			String getGSTAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//label[text()='GST']/ancestor::td[1]//span")).getText();
			System.out.println("getGSTAmt "+getGSTAmt);
			Common_Methods.toShortWait();
			String getTotalAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//label[text()='Total Amount']/ancestor::td[1]//span")).getText();
			System.out.println("getTotalAmt "+getTotalAmt);
			
			//Assert.assertEquals(String.valueOf(getgstamt),getGSTAmt,"Getting wrong GST Amount");				//compare actual gst value & calculated gst value.
			Assert.assertEquals(getgstamt, Double.parseDouble(getGSTAmt));
			
			//Assert.assertEquals(String.valueOf(getamount1), getTotalAmt,"Getting wrong total amount after expanding");		//compare actual total with expected
			Assert.assertEquals(getamount1, Double.parseDouble(getTotalAmt));
			
			if(getActualFinalAmt.contains(String.valueOf(getamount1))) {
				Common_Methods.shortWait();
				driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//i[@class='fa fa-chevron-down']")).click();
				
				driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//input[@type='checkbox']")).click();
				
				//Common_Methods.waitForWebElement(driver, generateBillBtn);
				//generateBillBtn.click();
				//Entered amount cannot be greater than pending amount
			}
			
		}else {
			
			Common_Methods.toShortWait();
			String getActualFinalAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[1]//td[2]")).getText();
			System.out.println("GetFinalAmt "+getActualFinalAmt);
			
			
			//Expand link for customer on billing screen
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//i[@class='fa fa-chevron-down']")).click();
			Common_Methods.shortWait();
			//List<WebElement> li=driver.findElements(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody"));   ///for external use this
			List<WebElement> li=driver.findElements(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody"));
			int length=li.size();
			System.out.println("length "+length);
			double getamount1=0;double getgstamt=0;
			
			for(int i=1;i<=length;i++) {
				//String getAmount=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody["+i+"]//td[4]")).getText();    //for external use this
				//String getTaxPer=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//tr[2]//tbody["+i+"]//td[3]")).getText();	//for external use this
				
				String getAmount=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody["+i+"]//td[4]")).getText();
				System.out.println("getAmount "+getAmount);
				String getTaxPer=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//div[@class='collapse show']//tbody["+i+"]//td[3]")).getText();
				String [] getTaxPer1=getTaxPer.split("%");
				System.out.println("getTaxPer[0] "+getTaxPer1[0]);
				double getTaxPer2=((Double.parseDouble(getTaxPer1[0]))*Double.parseDouble(getAmount))/100;
				getgstamt = getgstamt+getTaxPer2;
				System.out.println("getgstamt "+getgstamt);
				System.out.println("getTaxPer2 "+getTaxPer2);
				double getAmountInInt=Double.parseDouble(getAmount)+getTaxPer2;
				getamount1=getamount1+getAmountInInt;
				//System.out.println("amtinfor "+getamount1);
			}
			System.out.println("CalculateFinalAmt "+getamount1);
			//Assert.assertEquals(getActualFinalAmt, String.valueOf(getamount1),"Getting wrong Final Amount");
			//Assert.assertTrue(getActualFinalAmt.contains(String.valueOf(getamount1)), "Getting wrong Final Amount");
			Assert.assertEquals(Double.parseDouble(getActualFinalAmt), getamount1, "Getting wrong final amount");
			
			//Get and comapare GST & Total Amt value
			Common_Methods.toShortWait();
			String getGSTAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//label[text()='GST']/ancestor::td[1]//span")).getText();
			System.out.println("getGSTAmt "+getGSTAmt);
			Common_Methods.toShortWait();
			String getTotalAmt=driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//label[text()='Total Amount']/ancestor::td[1]//span")).getText();
			System.out.println("getTotalAmt "+getTotalAmt);
			
			//Assert.assertEquals(String.valueOf(getgstamt),getGSTAmt,"Getting wrong GST Amount");				//compare actual gst value & calculated gst value.
			Assert.assertEquals(getgstamt, Double.parseDouble(getGSTAmt));
			
			//Assert.assertEquals(String.valueOf(getamount1), getTotalAmt,"Getting wrong total amount after expanding");		//compare actual total with expected
			Assert.assertEquals(getamount1, Double.parseDouble(getTotalAmt));
			
			if(getActualFinalAmt.contains(String.valueOf(getamount1))) {
				Common_Methods.shortWait();
				driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//i[@class='fa fa-chevron-down']")).click();
				
				driver.findElement(By.xpath("//label[contains(text(),'"+cusName+"')]/ancestor::tr[2]//input[@type='checkbox']")).click();
				
				//Common_Methods.waitForWebElement(driver, generateBillBtn);
				//generateBillBtn.click();
				//Entered amount cannot be greater than pending amount
			}
			
		}
		
		
	}
	
	public void GenerateBillAndPrint(){
		try {
			Thread.sleep(2000);
			String parent=driver.getWindowHandle();
			driver.findElement(By.xpath("//table//tr[2]//td[4]//input")).click();
			Common_Methods.waitForWebElement(driver, generatePrintBtn);
			generatePrintBtn.click();
			
			Thread.sleep(2000);
			Set<String>s1=driver.getWindowHandles();
			int windowcount=s1.size();
			System.out.println("windowcount "+windowcount);
			for(String winchild : s1){
				if(!parent.equalsIgnoreCase(winchild)){
					driver.switchTo().window(winchild);
					 System.out.println("winchild "+driver.switchTo().window(winchild).getTitle());
					 driver.close();
				  }
			}
			driver.switchTo().window(parent);
			System.out.println("Parent window "+driver.switchTo().window(parent).getTitle());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void GenerateBillAndPrint(boolean flagRegenerate){
		try {
			
			if(flagRegenerate){
				Thread.sleep(2000);
				String parent=driver.getWindowHandle();
				Common_Methods.waitForWebElement(driver, RegenerateButton);
				RegenerateButton.click();
				
				Thread.sleep(2000);
				Set<String>s1=driver.getWindowHandles();
				int windowcount=s1.size();
				
				for(String winchild : s1){
					if(!parent.equalsIgnoreCase(winchild)){
						driver.switchTo().window(winchild);
						 System.out.println("winchild "+driver.switchTo().window(winchild).getTitle());
						 driver.close();
					  }
				}
				driver.switchTo().window(parent);
				System.out.println("Parent window "+driver.switchTo().window(parent).getTitle());
			}	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void makePaymentOnBillingScreen(String payi,String remark,String deliveryboy,String payflag) {
		if(payflag.contains("Skip")) {
			skipBtn.click();
		}
		else if(payflag.contains("Pay")){
			Common_Methods.waitForWebElement(driver, payInputOnBilling);
			Common_Methods.toShortWait();
			payInputOnBilling.clear();
			payInputOnBilling.sendKeys(payi);
			
			Common_Methods.waitForWebElement(driver, remarkInput);
			remarkInput.sendKeys(remark);
			
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//div[text()='Make Payment']/ancestor::div[2]//select[@name='shopAgentId']//option", deliveryboy);
			
			Common_Methods.toElement(payBtn);
			Common_Methods.waitForWebElement(driver, payBtn);
			payBtn.click();
			
		}
	}
	
	public void makePaymentOnBillingScreenAndVerifyErrorMessage(int payi,String remark,String deliveryboy,String errormsg,String payflag) {
		try {
			if(payflag.contains("Skip")) {
				skipBtn.click();
			}
			else {
				Common_Methods.waitForWebElement(driver, payInputOnBilling);
				Thread.sleep(2000);
				payInputOnBilling.clear();
				payInputOnBilling.sendKeys(String.valueOf(payi));
				
				Common_Methods.waitForWebElement(driver, remarkInput);
				remarkInput.sendKeys(remark);
				
				Common_Methods.selectFromDropDown("//h5[text()='Make Payment']/ancestor::div[2]//select[@name='shopAgentId']//option", deliveryboy);
				
				Common_Methods.waitForWebElement(driver, payBtn);
				payBtn.click();
				
				Thread.sleep(2000);
				
				Assert.assertEquals(Common_Methods.getToastMessage(), errormsg,"Not getting Error Message If Amt is grater than Actual");
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void useSelectAllOptionToGenerateBill() {
		Common_Methods.waitForWebElement(driver, selectAllOption);
		selectAllOption.click();
	}
	
	public void SelectBillDetailsOrSummary(String parametername,boolean flag){
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='"+parametername+"']/ancestor::div[1]//input")).click();
			
			if(flag){
				Common_Methods.waitForWebElement(driver, selectAllOption);
				selectAllOption.click();
				Common_Methods.waitForWebElement(driver, generateBillBtn);
				generateBillBtn.click();
				Thread.sleep(2000);
				String msg=Common_Methods.getToastMessage();
				System.out.println("MSG "+msg);
				Assert.assertEquals(msg, "For selected date range, Bill for all customers is already generated.Bill Generated Successfully","Getting wrong message after generation of bill");
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		
	}

}
