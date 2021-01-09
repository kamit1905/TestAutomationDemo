package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;

public class TestCustomer extends TestBase{

	Login_Page loginpage;
	Customer_Page customerpage;
	Delivery_Boy deliveryboy;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		
	}
	
	@Test(priority=3)
	public void verifyErrorMsg() {
		//customerpage = new Customer_Page();
		//customerpage.deleteCustomerAndVerifyErrorMessage("Ankita");
		deliveryboy = new Delivery_Boy();
		deliveryboy.deleteDeliveryBoy("Shah");
	
	}
	
}
