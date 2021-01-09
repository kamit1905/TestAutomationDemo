package com.app;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Collection_Report;
import com.app.pages.Login_Page;
import com.app.pages.Outstanding_Report;

@Listeners(com.app.listner.ListnerDemo1.class)
public class Demo_Test_Outstanding_Report extends TestBase{
	
	Login_Page loginpage;
	Outstanding_Report outstandingreport;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Outstanding Report");
		
	}
	
	@Test(priority=3)
	public void verifyoutstandingAmt() {
		outstandingreport = new Outstanding_Report();
		outstandingreport.verifyOutstandingReportCustomerWise("Customer5", 660);
		outstandingreport.verifyAndCalculateTotalBalanceIfCustomerHaveManyBills("Customer5", 660);
	}
}
