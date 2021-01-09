package com.app;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Collection_Report;
import com.app.pages.Login_Page;
import com.app.pages.Pending_Cans;

public class Demo_Pending_Cans extends TestBase {
	
	Login_Page loginpage;
	//Collection_Report collectionreport;
	Pending_Cans pendingcans;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Pending Cans");
		
	}
	
	/*@Test(priority=3)
	public void verifyPendingReportCustomerWise() {
		pendingcans = new Pending_Cans();
		//driver.findElement(By.xpath("//input[@name='currentDate']")).click();
		//Common_Methods.selectMonthFromCalender("October 2019");
		pendingcans.applyFilterOnPendingCans("16", "November 2019", "All Delivery Boy", "Bisleri Jar");
		Common_Methods.shortWait();
		pendingcans.verifyPendingCansCustomerwise("Anup", "Bisleri Jar", 3);
		//pendingcans.verifyPendingCansCustomerwise("Customer3", "Product1", 3);
		//pendingcans.verifyTotalPendingCansForParticularProduct(18);
	}
	
	@Test(priority=4)
	public void verifyTotalPendingCans() {
		pendingcans = new Pending_Cans();
		pendingcans.verifyTotalPendingCansForParticularProduct(18);
	}*/
	
	@Test(priority=4)
	public void verifypendingcans(){
		pendingcans = new Pending_Cans();
		pendingcans.applyFilterOnPendingCans("18", "December 2019", "All Delivery Boy", "All Product");
		pendingcans.getTotalPendingCans();
	}

}
