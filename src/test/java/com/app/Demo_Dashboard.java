package com.app;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Dashboard_Page;
import com.app.pages.Login_Page;

public class Demo_Dashboard extends TestBase{
	
	Login_Page loginpage;
	Dashboard_Page dashboardpage;
	
	@BeforeTest
	public void set() {
		initialize();
		loginpage=new Login_Page();
		dashboardpage = new Dashboard_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("Shree Sabjiwala");
	}
	
	@Test(priority=3)
	public void verifyorderdetails() {
		Common_Methods.ClickNavigation("DashBoard");
		//7588913202
		//dashboardpage.ApplyFilterOnDashboard("Custom", "March 2020", "May 2020", "1", "2");
		/*dashboardpage.OpenOrdeDetails("cst12@gmail.com");
		dashboardpage.VerifyOrderDetails();*/
		
		String [] emails= {"Test5 Auto5","Test4 Auto4"};
		dashboardpage.ApplyFilterOnDashboard("Today", "March 2020", "May 2020", "1", "2");
		dashboardpage.VerifyOrdersInStatus("New", emails,"1");
		
		Common_Methods.toShortWait();
		String [] emails1= {"Test6 Auto6 Wiselap"};
		dashboardpage.ApplyFilterOnDashboard("Today", "March 2020", "May 2020", "1", "2");
		dashboardpage.VerifyOrdersInStatus("Approved", emails1,"2");
		
		Common_Methods.toShortWait();
		String [] emails2= {"Test1 Automation1"};
		dashboardpage.ApplyFilterOnDashboard("Today", "March 2020", "May 2020", "1", "2");
		dashboardpage.VerifyOrdersInStatus("Rejected", emails2,"2");
		
		dashboardpage.OpenOrdeDetails("test4 auto4");
		dashboardpage.VerifyOrderDetails("test4 auto4","Order");
		
		dashboardpage.OpenOrdeDetails("test6 auto6 wiselap");
		dashboardpage.VerifyOrderDetails("test6 auto6 wiselap","Approve");
		
		dashboardpage.OpenOrdeDetails("test1 automation1");
		dashboardpage.VerifyOrderDetails("test1 automation1","Reject");
		
		
	}

}
