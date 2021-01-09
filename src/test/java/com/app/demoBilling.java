package com.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Billing_Page;
import com.app.pages.Collection_Report;
import com.app.pages.Login_Page;
import com.app.pages.Outstanding_Report;
import com.app.pages.Payment_Page;
import com.app.pages.Pending_Cans;
import com.app.pages.Profile;

public class demoBilling extends TestBase{
	
	Login_Page loginpage;
	Billing_Page billingpage;
	Payment_Page paymentpage;
	Outstanding_Report outstandingreport;
	Collection_Report collectionreport;
	Pending_Cans pendingcans;
	Profile profile;
	
	@BeforeSuite
	public void set() {
		initialize();
		billingpage =new Billing_Page();
		outstandingreport = new Outstanding_Report();
		collectionreport =new Collection_Report();
		paymentpage = new Payment_Page();
		profile = new Profile();
		pendingcans = new Pending_Cans();
	}
	
	
	@Test(priority=2)
	public void billing() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
		
		/*Common_Methods.ClickNavigation("Billing");
		billingpage.applyFilterOnBillingScreen("day-1","October 2019","day-3","March 2020","All Delivery boy");
		billingpage.calculateTotalAmtForSingleCusAndVerify1("Customer 15");
		billingpage.makePaymentOnBillingScreen("60", "Remark", "Delivery1", "Pay");	*/
	}
	
	@Test(priority=3)
	public void verifyoutstandingamt() {
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Oustanding Report");
		String getBalAmt=outstandingreport.getAndVerifyBalAmountOnAllScreen("Customer 15");
		
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","day-1", "day-2", "Delivery1");
		Common_Methods.shortWait();
		collectionreport.verifyPendingAmtCustomerWise("Customer 15", "Delivery1", getBalAmt);
		Common_Methods.shortWait();
		//Common_Methods.navigationClickLink("Payment");
		Common_Methods.ClickNavigation("Payment");
		paymentpage.verifyBalAmtInPayment("Customer 15", getBalAmt);
		
		
		//Do Payment from Payment Screen & verify it on outstanding report & collection report
		Common_Methods.shortWait();
		paymentpage.searchCustomerAndPayAmoount("Customer 15", "Delivery1", 20, "Remark");
				
		Common_Methods.shortWait();
		String getbalamtfrompayment=paymentpage.GetBalanceAmtForParticularCustomer("Customer 15");
		//Common_Methods.navigationInnerLink("Outstanding Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Oustanding Report");
		String getbalamtfromoutstanding=outstandingreport.getAndVerifyBalAmountOnAllScreen("Customer 15");
		Assert.assertEquals(getbalamtfrompayment, getbalamtfromoutstanding,"Getting wrong balance amt after payment");
				
		Common_Methods.shortWait();
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","day-1", "day-2", "Delivery1");
		collectionreport.verifyPendingAmtCustomerWise("Customer 15", "Delivery1", getbalamtfromoutstanding);
				
		Common_Methods.shortWait();
		//Common_Methods.navigationInnerLink("Collection Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Collection Report");
		collectionreport.applyFilterForCollectionReport("January 2020","May 2020","day-1", "day-2", "Delivery1");
		collectionreport.calculateTotalCollectedAmtAndPendingAmountForParticularDeliveryBoy();    //In this will get totalcollected & pending amt from particular delivery boy
		
	}
	
	//verify bal amt in payment and outstanding
	@Test(priority=6)
	public void verifyBalAmtOnPaymentAndOutstanding(){
		Common_Methods.ClickNavigation("Payment");
		int getbalfrompayment=paymentpage.getBalForAllCustomerOnPaymentScreen();
			
		//Common_Methods.navigationClickUsingName("Report");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.navigationInnerLink("Oustanding Report");
		int getbalfromoutstanding=outstandingreport.calculateTotalBalAmt();
			
		Assert.assertEquals(getbalfrompayment, getbalfromoutstanding,"Gettting wrong balance amount");
	}
	
	
	//Edit profile & verify pending cans 
	@Test(priority=7)
	public void editprofile(){
		profile.EditProfile("test5auto5", "8329122708", "400705", "Sector2 Near Railway station airoli");
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Pending Cans");
		pendingcans.applyFilterOnPendingCans("day-2", "January 2020", "All Delivery Boy", "All Product");
		pendingcans.getTotalPendingCans();
	}
	
	//7744153699   amit.wiselap-watersupplier
	//9738131946   test5auto5-watersupplier
	
	/*@Test(priority=3)
	public void verifyfinalAmt() {
		billingpage =new Billing_Page();
		billingpage.applyFilterOnBillingScreen("day-1", "day-14", "Ritesh");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Kajal");
		billingpage.makePaymentOnBillingScreenAndVerifyErrorMessage(1300, "Remark", "Ritesh","Entered amount cannot be greater than pending amount","");
	}*/
	
	/*@Test(priority=3)
	public void verifyBillingFilterBasedOnCustomer() {
		billingpage =new Billing_Page();
		driver.findElement(By.xpath("//input[@id='fromDate']")).click();
		Common_Methods.selectMonthFromCalender("October 2019");
		billingpage.filterBasedOnCustomerNameOnBillingScreen("day-1", "day-14", "Delivery2","Customer11");
	}*/
	
	/*@Test(priority=3)
	public void verifyfinalAmt1() {
		billingpage =new Billing_Page();
		billingpage.applyFilterOnBillingScreen("day-1", "day-14", "All Delivery boy");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Customer4");
		billingpage.makePaymentOnBillingScreen("20", "Remark", "All Delivery Boy", "Pay");
	}*/

	/*@Test(priority=3)
	public void verifyfinalAmt2() {
		billingpage =new Billing_Page();
		billingpage.applyFilterOnBillingScreen("day-1", "day-14", "Ritesh");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Diksha");
	}

	@Test(priority=3)
	public void verifyfinalAmt3() {
		billingpage =new Billing_Page();
		billingpage.applyFilterOnBillingScreen("day-1", "day-14", "Ritesh");
		billingpage.calculateTotalAmtForSingleCusAndVerify("Farukh");
	}*/
	
	/*@Test(priority=4)
	public void useselectall() {
		billingpage.applyFilterOnBillingScreen("day-1", "day-25", "All Delivery boy");
		billingpage.useSelectAllOptionToGenerateBill();
	}*/

}
