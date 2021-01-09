package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;


public class Test_Recurring_Order_Place extends TestBase{
	
	Login_Page loginpage;
	Customer_Page customerpage;

	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn("amit.wiselap@gmail.com", prop.getProperty("password"));
	}
	
	@Test(priority=3)
	public void placeRecurring() {
		customerpage = new Customer_Page();
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Customer");
		customerpage.addEditCustomer(true, "Balochistan", "9730398988", "adity@saba.com", "Sector A 124 Airoli Navi Mumbai", "Navi Mumbai", null, 0, 0, 0, "Monthly", "Active");
		//customerpage.placeRecurringOrder("Bisleri Jar", "Shah", 1, 2, 0, 0);
		customerpage.verifyRecurringOrder("Bisleri Jar", 2);
			
	}
}
