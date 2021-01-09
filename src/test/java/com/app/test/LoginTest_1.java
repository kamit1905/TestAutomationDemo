package com.app.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;

class super1{
	
	public void show() {
		
	}
}

class sub1 extends super1{
	
	public void print() {
		super.show();
	}
}

public class LoginTest_1 extends TestBase{
	
	Login_Page loginpage;
	
	@Test(priority=1)
	public void set() {
		try {
			initialize();
			driver.close();
			System.out.println("LoginTest_1");
			//Assert.assertEquals(true, false);
		}finally {
			System.out.println("Inside finally block");
		}
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
	
	/*@Test(priority=3)
	public void clos() {
		driver.quit();
	}*/

}
