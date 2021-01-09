package com.app;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;
import com.app.util.TestUtil;

public class Add_Customer_And_Recurring_Order_Through_Excel extends TestBase {
	
	Login_Page loginpage;
	Customer_Page customerpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Configuration");
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData2(){
		ArrayList<Object[]> testData = TestUtil.getDataFromExcelForCustomerAndRecurring();
		return testData.iterator();
	}
	
	@Test(priority=4,dataProvider="getTestData2")
	public void addCustomerAndRecurringThroughExcel(String custname,String custmob,String custemail,String custadd,String custcity,String custpayment,String custstatus,
			String pname,String dboy,String qty,String deltime,String fdate,String monthyear,String returnin) {
		
		customerpage = new Customer_Page();
		Common_Methods.navigationInnerLink("Customer");
		customerpage.addCustomerAndPlaceRecurringThroughExcel(custname, custmob, custemail, custadd, custcity, custpayment, custstatus,pname, dboy, qty,deltime,fdate,monthyear,returnin);

	   }

}
