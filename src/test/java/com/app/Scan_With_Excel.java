package com.app;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Billing_Page;
import com.app.pages.Login_Page;
import com.app.pages.Scan_Barcode;
import com.app.util.TestUtil;

public class Scan_With_Excel extends TestBase{
	
	Login_Page loginpage;
	Billing_Page billingpage;
	Scan_Barcode scanbarcode;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> testData = TestUtil.getDataFromExcelForScanning1();
		return testData.iterator();
	}
	
	@Test(priority=3,dataProvider="getTestData")
	public void excel1(String bno,String bno1,String ym,String d,String nu) {
		scanbarcode = new Scan_Barcode();
		//scanbarcode.scanInputWithExce2(bno,bno1,ym,d,false);
		scanbarcode.scanInputWithExce2(bno,bno1,ym,d,nu);
	}
	
	/*@Test(priority=3)
	public void excel2() {
		scanbarcode = new Scan_Barcode();
		scanbarcode.scanInputWithExce2("1","100007760900","November 2019","day-7", null);
		//scanbarcode.scanInputWithExce2("1","100000002730","November 2019","day-7");
		}*/
}
