package com.app.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;

public class LoginTest_2 extends TestBase{
	
	Login_Page loginpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
		System.out.println("LoginTest_2");
		Assert.assertEquals(true, true);
		//loginpage=new Login_Page();
	}
	
//	@Test(priority=2)
//	public void login() {
//		loginpage=new Login_Page();
//		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
//		Common_Methods.shortWait();
//		//Common_Methods.SelectShop("Gupta");
//		Common_Methods.SelectShop("Raju Milk");
//		
//	}
	
	@Test(priority=3)
	public void clos() {
		driver.quit();
	}

}
