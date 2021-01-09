package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;
import com.app.pages.Payment_Page;
import com.app.pages.Walkin_Page;
import com.app.pages.Walkin_Page1;

public class DemoWalkin extends TestBase{
	
	Login_Page loginpage;
	//Payment_Page paymentpage;
	Walkin_Page walkinpage;
	Walkin_Page1 walkinpage1;
	Customer_Page customerpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
		loginpage=new Login_Page();
		walkinpage = new Walkin_Page();
		walkinpage1 = new Walkin_Page1();
		customerpage = new Customer_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		//Common_Methods.SelectShop("Shree Water");
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Application Setting");
		int getoverduecharges=Common_Methods.GetOverDueCharges();
		
		/*Common_Methods.navigationInnerLink("Customer");
		String advamt=customerpage.GetCustomerAdvanceAmount("Kajal");
		
		Common_Methods.navigationClickUsingName("Walk In Customer");
		Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrder("Kajal","Test_Youth", 2, 0,"Id",advamt,"UPI");
		Common_Methods.navigationInnerLink("Return Product");
		walkinpage1.ReturnProductInWakin("Kajal", "5", "February 2020", "UPI", getoverduecharges,"Renew");
		walkinpage1.PlaceWalkinOrder("Kajal","Chlled_Water", 2, 0,"Deposit","0","UPI");*/
		
		
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
		//walkinpage.ReturnProductInWakin("Cust705", "21", "January 2020", "UPI", getoverduecharges);
		walkinpage1.ReturnProductInWakin("Cust705", "26", "February 2020", "UPI", getoverduecharges,"Renew");
		
		//Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrder("Cust705","Chlled_Water", 1, 0,"Id",advamt,"Cash");
		Common_Methods.navigationInnerLink("Return Product");
		walkinpage1.ReturnProductInWakin("Cust705", "26", "February 2020", "UPI", getoverduecharges,"Renew");
		
		walkinpage1.PlaceWalkinOrder("Cust705", "Test_Yout", 3, 0, "Deposit", advamt, "UPI");
		
		Common_Methods.navigationInnerLink("Add WalkIn");
		walkinpage1.PlaceWalkinOrderAndVerifyErrorMessage("Kajal", "Bisleri Jar", 2, 0);				//Verify error message if we miss any mandatory field
		//Common_Methods.navigationInnerLink("Return Product");
		
		Common_Methods.navigationClickUsingName("Walk In Customer");
		Common_Methods.navigationInnerLink("Return Product");
		//walkinpage.ReturnProductInWakin("Cust501", "12", "February 2020", "Cash", getoverduecharges);			//Overdue charges is grater than deposit at the time of return		
		//walkinpage.ReturnProductInWakin("Test_Cus800", "5", "February 2020", "Cash", getoverduecharges);		//Overdue charges other than deposit
		//walkinpage.ReturnProductInWakin("Ola", "5", "February 2020", "Cash", getoverduecharges);				//overdue charges is less than deposit at the time of return
		
		//Walkin with using id on order details 
		//walkinpage.PlaceWalkinOrder("Cus110","Test_Youth", 2, 0,"Id","0","UPI");
		//Common_Methods.longWait();
		//Common_Methods.navigationInnerLink("Return Product");
		//walkinpage.ReturnProductInWakin("Cus110", "day-16", "2020", "Cash", getoverduecharges);
	
	}

}

































/*walkinpage.ReturnProductInWakin("Cust701", "day-16", "2020", "UPI", getoverduecharges,"Renew");
Common_Methods.shortWait();
walkinpage.PlaceWalkinOrder("Cust701","Mineral_Test", 1, 0,"Deposit",advamt,"Cash");*/

/*Common_Methods.navigationClickUsingName("Configuration");
Common_Methods.navigationInnerLink("Application Setting");
int getoverduecharges=Common_Methods.GetOverDueCharges();

Common_Methods.navigationInnerLink("Customer");
String advamt=customerpage.GetCustomerAdvanceAmount("Balochistan");

Common_Methods.navigationClickUsingName("Walk In Customer");
Common_Methods.navigationInnerLink("Add WalkIn");
//walkinpage1.PlaceWalkinOrder("Cust501","Sun_Aqua", 2, 0,"Deposit","UPI");
walkinpage1.PlaceWalkinOrder("Balochistan","Sun_Aqua", 2, 0,advamt,"Deposit","UPI","Pay & Print");
Common_Methods.longWait();
Common_Methods.navigationInnerLink("Return Product");
//walkinpage.ReturnProductInWakin("Balochistan", "day-16", "2020", "UPI", getoverduecharges);
walkinpage1.ReturnProductInWakin("Balochistan", "day-16", "2020",getoverduecharges,"UPI","Renew");    //Return product s& Renew same order
walkinpage1.PlaceWalkinOrder("Balochistan","Bisleri Jar", 2, 0,advamt,"Id","Cash"); 					//Place walkin order */
