package com.app;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Booklet_Required_Page;
import com.app.pages.Config_Product;
import com.app.pages.Login_Page;

public class Demo_Config_Pro extends TestBase{

	Login_Page loginpage;
	Config_Product configureproduct;
	Booklet_Required_Page bookletrequiredpage;
	
	
	//@BeforeSuite
	@BeforeTest
	public void set() {
		initialize();
		loginpage=new Login_Page();
		configureproduct = new Config_Product();
		bookletrequiredpage = new Booklet_Required_Page();
	}
	
	@Test(priority=2)
	public void login() {
		//Login_Page	loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test6auto6");
	}
	
	@Test(priority=3)
	public void verifyproductqty() {
		//Config_Product configureproduct = new Config_Product();
		//Booklet_Required_Page bookletrequiredpage = new Booklet_Required_Page();
		
		String [] reqQty= {"1Jar","2Jar"};
		Common_Methods.navigationClickLink("BookletRequired");
		bookletrequiredpage.VerifyRequiredQuantityOfJarsInBookletRequired(reqQty, "Product2");
	}
}
