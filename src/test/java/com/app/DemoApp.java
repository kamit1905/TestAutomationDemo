package com.app;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Application_Setting_Page;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;


public class DemoApp extends TestBase{
	
	Login_Page loginpage;
	Application_Setting_Page applicationsettinpage;
	Customer_Page customerpage;
	
	
	@BeforeSuite
	public void set() {
		initialize();
		loginpage=new Login_Page();
		applicationsettinpage = new Application_Setting_Page();
		customerpage = new Customer_Page();
	}
	
	@Test(priority=1)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
	}
	
	@Test(priority=5)
	public void addcus(){
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Customer");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Customer");
		//customerpage.verifyErrorMessageIfNotEnteredMandatoryField("Japan");    //In this verify error msg if not entered any mandatory field as well as cancel button functionality	
		customerpage.addCustomerAndPlaceRecurringThroughExcel("Japan", "9746124512", "japan56@gmail.com", "Airoli Navi Mumbai", "Mumbai", "Monthly", "Active", "Product1", "Delivery1", "1","0250", "1","October 2019","1");
		//customerpage.DeleteRecurringOrder("Product1");    //delete recurring order
		//Common_Methods.shortWait();
		//customerpage.AddRecurringOrder("Product2", "Delivery1", "1","0350","1","October 2019","1");  //place recurring order once again
	}
	
	/*@Test(priority=2)
	public void applicaitonsetting(){
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Application Setting");
		
		applicationsettinpage.ApplicationSettingSave("Generate Bill", "Yes", 0, 25,
				"Bill Summary", "Yes", 10, 5, "Yes", 90, 150, "1000", "1100", "Online");
	}*/

}
