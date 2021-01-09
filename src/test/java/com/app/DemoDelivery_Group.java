package com.app;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Delivery_Group;
import com.app.pages.Login_Page;
import com.app.pages.Stock_Management;
import com.app.pages.Stock_Report;

@Listeners(com.app.listner.ListnerDemo1.class)
public class DemoDelivery_Group extends TestBase{
	
	Login_Page loginpage;
	Delivery_Group deliverygroup;
	
	@BeforeSuite
	public void set() {
		initialize();
		deliverygroup = new Delivery_Group();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("Shree Water");
	}
	
	@Test(priority=3)
	//mahi,mario,mohan,cust test2,palash
	//Customer3,Customer1,Customer6,Customer7,Customer8
	public void deliverygroup() {
		String [] cusarr= {"Cust Test2","Mario","Mahi","Palash","Mohan"};
		//String [] cusarr= {"Customer7","Customer1","Customer3","Customer8","Customer6"};
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Delivery Group");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Delivery Group");
		Common_Methods.shortWait();
		deliverygroup.VerifyErrorMessageWithoutSelectingCustomer("CST");
		deliverygroup.maintainDeliveryGroupSequence("CST",cusarr);
		deliverygroup.verifyErrorMessageOnDeleteOfDeliveryGroup("Borivali");
	}

}
