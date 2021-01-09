package com.app;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;

@Listeners(com.app.listner.ListnerDemo1.class)
public class Generate_Random_Time_In_Report extends TestBase{
	Login_Page loginpage;
	
	//@BeforeMethod
	@BeforeSuite
	public void set() {
		initialize();
		loginpage = new Login_Page();
	}
	
	@Test(priority=2)
	public void login() {
		//loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("Shree Water1");
		//Common_Methods.navigationClickLink("ScanBarcode");	
	}
	
	@Test(priority=3)
	public void gethashcode() {
		loginpage=new Login_Page();
		System.out.println("hashcode1 "+loginpage.hashCode());
	}

}
