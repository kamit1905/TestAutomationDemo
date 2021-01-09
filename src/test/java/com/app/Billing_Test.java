package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.pages.Billing_Page;
import com.app.pages.Login_Page;

public class Billing_Test extends TestBase{
		
	Login_Page loginpage;
	Billing_Page billingpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn("amit.wiselap@gmail.com", prop.getProperty("password"));
	}
	
	/*@Test(priority=3)
	public void generateBilling() {
		billingpage = new Billing_Page();
		billingpage.doBilling("Customer Name:Akshay",5,4,"Amit","Aqua Jar",3,60);
	}*/
	
	@Test(priority=3)
	public void generateBilling() {
		billingpage = new Billing_Page();
		String productArray[]= {"Sun_Aqua","Turbo","Sun_Aqua","Turbo","Turbo","Sun_Aqua","Sun_Aqua"};
		int [] rateArray= {50,30,45,55};
		//billingpage.doBilling("Customer Name:Gismando",5,4,"Shah","Bisleri Jar",3,80,productArray,rateArray);
	}
	
}
