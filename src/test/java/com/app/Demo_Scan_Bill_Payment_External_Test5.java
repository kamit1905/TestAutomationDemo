package com.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Application_Setting_Page;
import com.app.pages.Billing_Page;
import com.app.pages.Collection_Report;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;
import com.app.pages.Outstanding_Report;
import com.app.pages.Payment_Page;
import com.app.pages.Pending_Cans;
import com.app.pages.Product_Page;
import com.app.pages.Profile;
import com.app.pages.Scan_Barcode;
import com.app.pages.Walkin_Page1;


//This script does scan,bill,payment,verification of pending amt on all screen,verification of pending cans on all screen.
//login with test5auto5

@Listeners(com.app.listner.ListnerDemo1.class)
public class Demo_Scan_Bill_Payment_External_Test5 extends TestBase{

	Login_Page loginpage;
	Product_Page productpage;
	Scan_Barcode scanbarcode;
	Billing_Page billingpage;
	Payment_Page paymentpage;
	Outstanding_Report outstandingreport;
	Collection_Report collectionreport;
	Pending_Cans pendingcans;
	Profile profile;
	Walkin_Page1 walkinpage1;
	Application_Setting_Page applicationsettinpage;
	Customer_Page customerpage;
	
	@BeforeSuite
	public void set() {
		initialize();
		loginpage=new Login_Page();
		scanbarcode = new Scan_Barcode();
		billingpage =new Billing_Page();
		outstandingreport = new Outstanding_Report();
		collectionreport =new Collection_Report();
		paymentpage = new Payment_Page();
		pendingcans = new Pending_Cans();
		profile = new Profile();
		walkinpage1 = new Walkin_Page1();
		applicationsettinpage = new Application_Setting_Page();
		customerpage = new Customer_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("Saba Waterwala");
	}
	
	@Test(priority=3)
	public void scanbarcode() {
		//Common_Methods.navigationClickUsingName("Report");
		//Common_Methods.navigationInnerLink("Pending Cans");
		WebElement ele=driver.findElement(By.xpath("//div[text()='ScanBarcode']/ancestor::a[1]"));
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Pending Cans");
		pendingcans.applyFilterOnPendingCans("30", "April 2020", "All Delivery Boy", "All Product");
		String pendingCans=pendingcans.getCountOfPendingCans("Customer3", "Product1");
		Common_Methods.shortWait();
		//Common_Methods.navigationClickLink("ScanBarcode");
		Common_Methods.ClickNavigation("ScanBarcode");
		scanbarcode.scanInputWithExce2("1","100000135919","October 2019","day-11",pendingCans);
		
		Common_Methods.longWait();
		//Common_Methods.navigationInnerLink("Pending Cans");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Pending Cans");
		pendingcans.applyFilterOnPendingCans("30", "April 2020", "All Delivery Boy", "All Product");
		String pendingCans1=pendingcans.getCountOfPendingCans("Customer3", "Product1");
		Common_Methods.shortWait();
		//Common_Methods.navigationClickLink("ScanBarcode");
		//Common_Methods.ClickNavigation("ScanBarcode");
		Common_Methods.clickByJs(ele);
		scanbarcode.scanInputWithExce2("1","100000135920","October 2019","day-11",pendingCans1);
		
		}
	
	//Make billing
	@Test(priority=4)
	public void generatebill() {
		//Common_Methods.navigationClickLink("Billing");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Tax Configurations");
		String getvalue=Common_Methods.GetFirstSelectedOptionFromDropDown("//select[@id='TaxReturn']");
		System.out.println("getvalue "+getvalue);
		
		Common_Methods.ClickNavigation("Billing");
		billingpage.applyFilterOnBillingScreen("day-1","October 2019","day-10","October 2019","All Delivery boy");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Customer3","getvalue");
		billingpage.makePaymentOnBillingScreen("60", "Remark", "Delivery1", "Pay");	
	}
	
	//verify bal in collection,payment customerwise & calculate total pending & collected amount against particular delivery boy
	@Test(priority=5)
	public void verifyoutstandingAmt() {
		//Common_Methods.navigationClickUsingName("Report");
		//Common_Methods.navigationInnerLink("Outstanding Report");
		//outstandingreport = new Outstanding_Report();
		//outstandingreport.verifyOutstandingReportCustomerWise("Customer5", 660);
		//outstandingreport.verifyAndCalculateTotalBalanceIfCustomerHaveManyBills("Customer5", 660);
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Outstanding Report");
		String getBalAmt=outstandingreport.getAndVerifyBalAmountOnAllScreen("Customer3");
		
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("October 2019","May 2020","1", "1", "Delivery1");
		Common_Methods.shortWait();
		collectionreport.verifyPendingAmtCustomerWise("Customer3", "Delivery1", getBalAmt);
		Common_Methods.shortWait();
		//Common_Methods.navigationClickLink("Payment");
		Common_Methods.ClickNavigation("Payment");
		paymentpage.verifyBalAmtInPayment("Customer3", getBalAmt);
		
		//Do Payment from Payment Screen & verify it on outstanding report & collection report
		Common_Methods.shortWait();
		paymentpage.searchCustomerAndPayAmoount("Customer3", "Delivery1", 20, "Remark");
		
		Common_Methods.shortWait();
		String getbalamtfrompayment=paymentpage.GetBalanceAmtForParticularCustomer("Customer3");
		//Common_Methods.navigationInnerLink("Outstanding Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Outstanding Report");
		String getbalamtfromoutstanding=outstandingreport.getAndVerifyBalAmountOnAllScreen("Customer3");
		Assert.assertEquals(getbalamtfrompayment, getbalamtfromoutstanding,"Getting wrong balance amt after payment");
		
		Common_Methods.shortWait();
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("October 2019","May 2020","1", "1", "Delivery1");
		collectionreport.verifyPendingAmtCustomerWise("Customer3", "Delivery1", getbalamtfromoutstanding);
		
		Common_Methods.shortWait();
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("October 2019","May 2020","1", "1", "Delivery1");
		collectionreport.calculateTotalCollectedAmtAndPendingAmountForParticularDeliveryBoy();    //In this will get totalcollected & pending amt from particular delivery boy
		
	}
	
	//verify bal amt in payment and outstanding
	/*@Test(priority=6)
	public void verifyBalAmtOnPaymentAndOutstanding(){
		Common_Methods.navigationClickLink("Payment");
		int getbalfrompayment=paymentpage.getBalForAllCustomerOnPaymentScreen();
		
		//Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Outstanding Report");
		int getbalfromoutstanding=outstandingreport.calculateTotalBalAmt();
		
		Assert.assertEquals(getbalfrompayment, getbalfromoutstanding,"Getting wrong balance amount");
	}
	
	//Edit profile & verify pending cans 
	@Test(priority=7)
	public void editprofile(){
		profile.EditProfile("Saba Waterwala", "7845895612", "400705", "Sector2 Near Railway station airoli");
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Pending Cans");
		pendingcans.applyFilterOnPendingCans("26", "February 2020", "All Delivery Boy", "All Product");
		pendingcans.getTotalPendingCans();
	}
		
		
		//Walkin related scenerio
		@Test(priority=8)
		public void walkingcustomer() {
			Common_Methods.navigationClickUsingName("Configuration");
			Common_Methods.navigationInnerLink("Application Setting");
			int getoverduecharges=Common_Methods.GetOverDueCharges();
			
			Common_Methods.navigationInnerLink("Customer");
			String advamt=customerpage.GetCustomerAdvanceAmount("Kajal");
			Common_Methods.navigationClickUsingName("Walk In Customer");
			Common_Methods.navigationInnerLink("Add WalkIn");
			
			
			//Customer not having deposit and advance
			walkinpage1.PlaceWalkinOrder("Kajal","Test_Youth", 2, 0,"Id",advamt,"UPI");
			Common_Methods.navigationInnerLink("Return Product");
			walkinpage1.ReturnProductInWakin("Kajal", "26", "February 2020", "UPI", getoverduecharges);
			
			//Walkin with advance & deposit
			Common_Methods.navigationInnerLink("Add WalkIn");
			walkinpage1.PlaceWalkinOrder("Cust705","Test_Youth", 2, 0,"Deposit",advamt,"UPI");
			Common_Methods.mediumWait();
			Common_Methods.navigationInnerLink("Return Product");
			walkinpage1.ReturnProductInWakin("Cust705", "26", "February 2020", "UPI", getoverduecharges,"Renew");					//change it to the current date here in this line
			
			
			//Common_Methods.navigationInnerLink("Add WalkIn");
			walkinpage1.PlaceWalkinOrder("Cust705","Chlled_Water", 1, 0,"Id",advamt,"Cash");
			Common_Methods.navigationInnerLink("Return Product");
			walkinpage1.ReturnProductInWakin("Cust705", "26", "February 2020", "UPI", getoverduecharges,"Renew");					//change it to the current date here in this line
			
			walkinpage1.PlaceWalkinOrder("Cust705", "Test_Yout", 3, 0, "Deposit", advamt, "UPI");
			
			Common_Methods.navigationInnerLink("Add WalkIn");
			walkinpage1.PlaceWalkinOrderAndVerifyErrorMessage("Kajal", "Bisleri Jar", 2, 0);				//Verify error message if we miss any mandatory field
		
			
			//Overdue charges related to calculation
			//Common_Methods.navigationClickUsingName("Walk In Customer");
			Common_Methods.navigationInnerLink("Return Product");
			walkinpage1.ReturnProductInWakin("Cust501", "12", "February 2020", "Cash", getoverduecharges);			//Overdue charges is grater than deposit at the time of return		
			driver.findElement(By.xpath("//a[@class='close']")).click();
			Common_Methods.navigationInnerLink("Return Product");
			
			
			walkinpage1.ReturnProductInWakin("Test_Cus800", "5", "February 2020", "Cash", getoverduecharges);		//Overdue charges other than deposit
			driver.findElement(By.xpath("//a[@class='close']")).click();
			Common_Methods.navigationInnerLink("Return Product");
			
			walkinpage1.ReturnProductInWakin("Ola", "5", "February 2020", "Cash", getoverduecharges);				//overdue charges is less than deposit at the time of return
			driver.findElement(By.xpath("//a[@class='close']")).click();
			Common_Methods.navigationInnerLink("Return Product");
		
		}
	
	
		//Application settting save
		@Test(priority=10)
		public void applicaitonsetting(){
			//Common_Methods.navigationClickUsingName("Configuration");
			Common_Methods.navigationInnerLink("Application Setting");
			
			applicationsettinpage.ApplicationSettingSave("Generate Bill", "Yes", 0, 25,
					"Bill Summary", "Yes", 10, 5, "Yes", 90, 150, "1000", "1100", "Online");
		}
	
	
	//To verify print functionality of bill
    @Test(priority=8)
	public void testgenerateandprint(){
		Common_Methods.navigationClickLink("Billing");
		Common_Methods.shortWait();
		billingpage.applyFilterOnBillingScreen("day-1","October 2019", "day-15", "October 2019","All Delivery boy","Customer8");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Customer8");
		billingpage.makePaymentOnBillingScreen("60", "Remark", "Delivery1", "Pay");	
		driver.findElement(By.xpath("//input[@name='searchInput']")).clear();
		Common_Methods.navigationClickLink("Payment");
		
		Common_Methods.navigationClickLink("Billing");
		//Common_Methods.shortWait();
		billingpage.applyFilterOnBillingScreen("day-1","October 2019", "day-15", "October 2019","All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("Bill summary",false);
		billingpage.GenerateBillAndPrint();
		
		Common_Methods.mediumWait();
		billingpage.applyFilterOnBillingScreen("day-1", "October 2019" ,"day-15","October 2019" ,"All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("bill details",false);
		billingpage.GenerateBillAndPrint();	
		
		Common_Methods.mediumWait();
		billingpage.applyFilterOnBillingScreen("day-1", "October 2019" ,"day-15","October 2019" ,"All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("both",false);
		billingpage.GenerateBillAndPrint();
		
		Common_Methods.mediumWait();
		billingpage.applyFilterOnBillingScreen("day-1", "October 2019" ,"day-15","October 2019" ,"All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("Bill summary",false);
		billingpage.GenerateBillAndPrint(true);									//Use to regenerate biil
		
		Common_Methods.shortWait();
		billingpage.applyFilterOnBillingScreen("day-1", "October 2019" ,"day-15","October 2019" ,"All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("Bill summary", true);			//Use to select all options from billing		
	}*/
	
}
