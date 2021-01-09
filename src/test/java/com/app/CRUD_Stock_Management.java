package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;
import com.app.pages.Payment_Page;
import com.app.pages.Stock_Management;
import com.app.pages.Stock_Report;

public class CRUD_Stock_Management extends TestBase{
	
	Login_Page loginpage;
	Stock_Management stockmanagement;
	Stock_Report stockreport;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	@Test(priority=3)
	public void updatestock() {
		stockmanagement = new Stock_Management();
		Common_Methods.navigationClickUsingName("Inventory");
		Common_Methods.navigationInnerLink("Stock Management");
		
		stockmanagement.addNewStock("Aqua Jar","5","2");
		//stockmanagement.verifyStockAfterUpdate("Aqua Jar", "25");
	}
	
	/*@Test(priority=4)
	public void verifystokreport() {
		stockreport = new Stock_Report();
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Stock Report");
		stockreport.verifyStockReport("Aqua Jar", 10,0,10);
	}*/

}
