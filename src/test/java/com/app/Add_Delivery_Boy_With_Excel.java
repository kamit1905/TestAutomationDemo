package com.app;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Billing_Page;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;
import com.app.pages.Scan_Barcode;
import com.app.util.TestUtil;

public class Add_Delivery_Boy_With_Excel extends TestBase{
	
	Login_Page loginpage;
	Delivery_Boy deliveryboy;
	
	@Test(priority=1)
	public void set() {
		initialize();
		loginpage=new Login_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn("test1auto123@gmail.com", prop.getProperty("password"));
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData1(){
		ArrayList<Object[]> testData = TestUtil.getDataFromExcelForDeliveryBoy();
		return testData.iterator();
	}
	
	@Test(priority=3)
	public void navigate() {
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Delivery Boy");
	}
	
	@Test(priority=4,dataProvider="getTestData1")
	public void addDeliveryBoyThroughExcel(String dboyname,String dboymob,String dboyemail,String dboyprofile) {
		deliveryboy = new Delivery_Boy();
		deliveryboy.addDeliveryBoy(dboyname, dboymob, dboyemail, dboyprofile);;
		
	}

}
