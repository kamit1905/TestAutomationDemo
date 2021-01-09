package com.app;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Collection_Report;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;
import com.app.util.TestUtil;


//test5auto5
@Listeners(com.app.listner.ListnerDemo1.class)
public class DemoCollectionReport extends TestBase{
	
	Login_Page loginpage;
	Collection_Report collectionreport;
	
	//@Test(priority=1)
	@BeforeSuite
	public void set() {
		initialize();
		collectionreport =new Collection_Report();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Report");
		Common_Methods.navigationInnerLink("Collection Report");
		
	}
	
	@Test(priority=2)
	public void verifyamtcustomerwise() {
		collectionreport =new Collection_Report();
		Common_Methods.shortWait();
		//Common_Methods.selectMonthFromCalender("August 2019");
		collectionreport.applyFilterForCollectionReport("October 2019","November 2019","1", "16", "Delivery1");
		Common_Methods.shortWait();
		//collectionreport.calculateTotalCollectedAmt();
		//collectionreport.calculateTotalAmtPending();
		collectionreport.verifyCollectedAndPendingAmtCustomerWise("Customer1","Delivery1",50, 430);
		
	}
	
	@Test(priority=3)
	public void verifytotalamtincollectionreport() {
		Common_Methods.navigationInnerLink("Collection Report");
		//collectionreport =new Collection_Report();
			
		collectionreport.applyFilterForCollectionReport("October 2019","November 2019","1", "16", "All Delivery Boy");
		Common_Methods.shortWait();
		collectionreport.calculateTotalCollectedAmt();
		Common_Methods.shortWait();
		collectionreport.calculateTotalAmtPending();
		
	}

}
