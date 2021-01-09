package com.app;

import java.util.List;

import org.openqa.selenium.By;
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
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;
import com.app.pages.Outstanding_Report;
import com.app.pages.Party_Order;
import com.app.pages.Payment_Page;
import com.app.pages.Pending_Cans;
import com.app.pages.Product_Page;
import com.app.pages.Profile;
import com.app.pages.Scan_Barcode;
import com.app.pages.Walkin_Page1;


//This script does scan,bill,payment,verification of pending amt on all screen,verification of pending cans on all screen.
//login with test5auto5

@Listeners(com.app.listner.ListnerDemo1.class)
public class Demo_Scan_Bill_Payment_Internal extends TestBase{

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
	Customer_Page customerpage;
	Party_Order partyorder;
	Delivery_Boy deliveryboy;
	Application_Setting_Page applicationsettinpage;
	
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
		customerpage = new Customer_Page();
		partyorder= new Party_Order();
		deliveryboy= new Delivery_Boy();
		applicationsettinpage = new Application_Setting_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
	}
	
	/*@Test(priority=3)
	public void scanbarcode() {
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Pending Cans");
		//Common_Methods.navigationClickUsingName("Report");
		//Common_Methods.navigationInnerLink("Pending Cans");
		pendingcans.applyFilterOnPendingCans("30", "April 2020", "All Delivery Boy", "All Product");
		String pendingCans=pendingcans.getCountOfPendingCans("Customer3", "Product1");
		Common_Methods.shortWait();
		Common_Methods.ClickNavigation("ScanBarcode");
		//Common_Methods.navigationClickLink("ScanBarcode");
		scanbarcode.scanInputWithExce2("1","100007761882","January 2020","day-1",pendingCans);
		
		//Common_Methods.navigationInnerLink("Pending Cans");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Pending Cans");
		pendingcans.applyFilterOnPendingCans("30", "April 2020", "All Delivery Boy", "All Product");
		String pendingCans1=pendingcans.getCountOfPendingCans("Customer3", "Product1");
		Common_Methods.shortWait();
		//Common_Methods.navigationClickLink("ScanBarcode");	
		Common_Methods.ClickNavigation("ScanBarcode");
		scanbarcode.scanInputWithExce2("1","100007761883","January 2020","day-1",pendingCans1);
		
		}*/
	
	//Make billing
	@Test(priority=4)
	public void generatebill() {
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Tax Configurations");
		String getvalue=Common_Methods.GetFirstSelectedOptionFromDropDown("//select[@id='TaxReturn']");
		System.out.println("getvalue "+getvalue);
		
		//Common_Methods.navigationClickLink("Billing");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Billing");
		billingpage.applyFilterOnBillingScreen("day-1","January 2020","day-28","January 2020","All Delivery boy");
		//billingpage.calculateTotalAmtForSingleCusAndVerify("Customer3",getvalue);
		billingpage.calculateTotalAmtForSingleCusAndVerify1("Customer3");
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
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","1", "1", "Delivery1");
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
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","1", "1", "Delivery1");
		collectionreport.verifyPendingAmtCustomerWise("Customer3", "Delivery1", getbalamtfromoutstanding);
		
		Common_Methods.shortWait();
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","1", "1", "Delivery1");
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
		
		Assert.assertEquals(getbalfrompayment, getbalfromoutstanding,"Gettting wrong balance amount");
	}*/
	
	//Edit profile & verify pending cans 
	/*@Test(priority=7)
	public void editprofile(){
		profile.EditProfile("test5auto5", "8329122708", "400705", "Sector2 Near Railway station airoli");
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Pending Cans");
		pendingcans.applyFilterOnPendingCans("28", "January 2020", "All Delivery Boy", "All Product");
		pendingcans.getTotalPendingCans();
	}*/
	
	
	
	//Walkin related scenerio
	/*@Test(priority=8)
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
		walkinpage1.ReturnProductInWakin("Kajal", "17", "February 2020", "UPI", getoverduecharges);
		
		//Walkin with advance & deposit
		Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrder("Cust705","Test_Youth", 2, 0,"Deposit",advamt,"UPI");
		Common_Methods.mediumWait();
		Common_Methods.navigationInnerLink("Return Product");
		walkinpage1.ReturnProductInWakin("Cust705", "17", "February 2020", "UPI", getoverduecharges,"Renew");					//change it to the current date here in this line
		
		
		//Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrder("Cust705","Chlled_Water", 1, 0,"Id",advamt,"Cash");
		Common_Methods.navigationInnerLink("Return Product");
		walkinpage1.ReturnProductInWakin("Cust705", "17", "February 2020", "UPI", getoverduecharges,"Renew");					//change it to the current date here in this line
		
		walkinpage1.PlaceWalkinOrder("Cust705", "Test_Yout", 3, 0, "Deposit", advamt, "UPI");
		
		Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrderAndVerifyErrorMessage("Kajal", "Bisleri Jar", 2, 0);				//Verify error message if we miss any mandatory field
	
		
		//Overdue charges related to calculation
		//Common_Methods.navigationClickUsingName("Walk In Customer");
		/*Common_Methods.navigationInnerLink("Return Product");
		walkinpage1.ReturnProductInWakin("Cust501", "12", "February 2020", "Cash", getoverduecharges);			//Overdue charges is grater than deposit at the time of return		
		driver.findElement(By.xpath("//a[@class='close']")).click();
		Common_Methods.navigationInnerLink("Return Product");
		
		
		walkinpage1.ReturnProductInWakin("Test_Cus800", "5", "February 2020", "Cash", getoverduecharges);		//Overdue charges other than deposit
		driver.findElement(By.xpath("//a[@class='close']")).click();
		Common_Methods.navigationInnerLink("Return Product");
		
		walkinpage1.ReturnProductInWakin("Ola", "5", "February 2020", "Cash", getoverduecharges);				//overdue charges is less than deposit at the time of return
		driver.findElement(By.xpath("//a[@class='close']")).click();
		Common_Methods.navigationInnerLink("Return Product");    			//
	
	}*/
	
	/*@Test(priority=9)	
	public void partyorder(){
		Common_Methods.navigationInnerLink("Product");
		List<String> pro=productpage.ReturnProductName();
		Common_Methods.shortWait();
		Common_Methods.navigationInnerLink("Delivery Boy");
		List<String> delboy=deliveryboy.GetDeliveryBoy();
		
		Common_Methods.navigationClickLink("Order_list");  
		partyorder.AddOrderInParty2("Test_Pra", "Sector2 navi mumbai", "9895556874", "Aditya", "Sector 19 kharghar mumbai", "7854556234", "Yes");
		partyorder.SelectOrder("Check1", 10, "17", "February 2020", "0230", "Ritesh",pro,delboy);
		partyorder.SelectOrder("Foregin_Exchange_Pro", 5, "17", "February 2020", "1230", "Ritesh",pro,delboy);
		partyorder.SelectOrder("Mineral_Test", 8, "17", "February 2020", "0830", "Ritesh",pro,delboy);
		
		String [] pname={"Check1","Foregin_Exchange_Pro","Mineral_Test"};
		partyorder.VerifyProductInlist(pname);
		
		Common_Methods.mediumWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Check1");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Foregin_Exchange_Pro");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Mineral_Test");
		
		Common_Methods.mediumWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Check1");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Foregin_Exchange_Pro");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Mineral_Test");
		
		Common_Methods.mediumWait();
		Common_Methods.navigationClickLink("Order_list");
		partyorder.AddOrderInParty2("Test_Pra", "Sector2 navi mumbai", "9895556874", "Aditya", "Sector 19 kharghar mumbai", "7854556234", "Yes");
		partyorder.SelectOrderAndVerifyErrorMsgIfNotEnteredMandatoryField1("Test_Youth");				//Verify error message if not entered mandatory field in product
		
		Common_Methods.shortWait();
		Common_Methods.navigationClickLink("Order_list");
		partyorder.AddOrderInPartyAndVerifyErrorMsgIfNotEnteredMandatoryField1("Quarty");	//Verify error messsage if not entered mandatory field in customer details
		
		/*String [] date={"04-Feb-2020","04-Feb-2020","13-Feb-2020","13-Feb-2020","13-Feb-2020","14-Feb-2020","14-Feb-2020","14-Feb-2020"};
		String [] prolist={"Can_Return12","Bisleri Jar","Sun_Aqua","Bisleri Jar","Test_Youth","Check1","Foregin_Exchange_Pro","Mineral_Test"};
		String [] proqty={"3","5","10","5","8","10","5","8"};
		
		Common_Methods.navigationClickLink("Order_list");  
		driver.findElement(By.xpath("//button[text()='Past']")).click();
		partyorder.ApplyFilterOnPartyOrderList("Test_Pra");
		partyorder.VerifyDetailsOfPartyOrderList(date, prolist, proqty);//
		
	}*/
	
	//Application settting save
	/*@Test(priority=10)
	public void applicaitonsetting(){
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Application Setting");
		
		applicationsettinpage.ApplicationSettingSave("Generate Bill", "Yes", 0, 25,
				"Bill Summary", "Yes", 10, 5, "Yes", 90, 150, "1000", "1100", "Online");
	}*/
	
	
	//To verify print functionality of bill
	/*@Test(priority=8)
	public void testgenerateandprint(){
		Common_Methods.navigationClickLink("Billing");
		billingpage.applyFilterOnBillingScreen("day-1","October 2019", "day-8", "January 2020","All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("Bill summary",false);
		billingpage.GenerateBillAndPrint();
		
		Common_Methods.mediumWait();
		billingpage.applyFilterOnBillingScreen("day-1", "October 2019" ,"day-8","January 2020" ,"All Delivery boy");
		billingpage.SelectBillDetailsOrSummary("bill details",false);
		billingpage.GenerateBillAndPrint();	
	}*/
	

}