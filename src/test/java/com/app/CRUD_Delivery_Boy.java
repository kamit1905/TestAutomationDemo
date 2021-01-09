package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;

public class CRUD_Delivery_Boy extends TestBase{
	
	Login_Page loginpage;
	//Customer_Page customerpage;
	Delivery_Boy deliveryboy;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		
	}
	
	@Test(priority=3)
	public void addDelivery() {
		deliveryboy = new Delivery_Boy();
		deliveryboy.addDeliveryBoy("Harmesh", "9733445566", "harmesh23@gmail.com", "Delievery Boy");
	}
	
	@Test(priority=4)
	public void editDelivery() {
		//Common_Methods.navigationClick("Configuration");
		//Common_Methods.navigationInnerLink("Delivery Boy");
		
		deliveryboy = new Delivery_Boy();
		deliveryboy.editDeliveryBoy("Harmesh", "9855667788", "Harmesh1");
	}
	
	@Test(priority=5)
	public void deleteDelivery() {
		//Common_Methods.navigationClick("Configuration");
		//Common_Methods.navigationInnerLink("Delivery Boy");
		
		deliveryboy = new Delivery_Boy();
		deliveryboy.deleteDeliveryBoy("Harmesh1");
	}

}
