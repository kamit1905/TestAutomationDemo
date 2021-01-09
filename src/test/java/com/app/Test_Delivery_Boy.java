package com.app;

import org.testng.annotations.Test;

import com.app.common.Common_Methods;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;

public class Test_Delivery_Boy extends Delivery_Boy {
	
	Login_Page loginpage;
	Delivery_Boy deliverboy;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	
	@Test(priority=2)
	public void logingoogletest() {
		loginpage=new Login_Page();
		loginpage.SignIn("amit.wiselap@gmail.com", prop.getProperty("password"));
		
	}
	
	@Test(priority=3)
	public void addDeliverBoy() {
		deliverboy = new Delivery_Boy();
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		deliverboy.addDeliveryBoy("Kamlesh", "9730398956", "KAMLESH56@GMAIL.COM", "Delievery Boy");
	}
	
	@Test(priority=4)
	public void editDeliveryBoy() {
		deliverboy = new Delivery_Boy();
		//Common_Methods.navigationClick("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		deliverboy.editDeliveryBoy("Kamlesh", "8329122708","Karthik");
	}

	@Test(priority=5)
	public void verify() {
		deliverboy = new Delivery_Boy();
		//Common_Methods.navigationClick("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		deliverboy.searchDeliveryBoy("Karthik");
		deliverboy.verifyDeliveryBoy("Karthik", "8329122708");
	}
	
	@Test(priority=6)
	public void deleteDeliveryBoy() {
		deliverboy = new Delivery_Boy();
		//Common_Methods.navigationClick("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
		//deliverboy.searchDeliveryBoy("Karthik");
		deliverboy.deleteDeliveryBoy("Karthik");
	}

}
