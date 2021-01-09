package com.app.GetData;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;

public class Demo_1 extends TestBase{
	
	Login_Page loginpage;
	
	@Test(priority=1)
	public void launchbrower() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Product");		
	}
	
	@Test(priority=3)
	public void close() {
		driver.quit();
	}

}
