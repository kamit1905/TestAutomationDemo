package com.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Login_Page;
import com.app.pages.Product_Setup_Page;
import com.app.pages.Scan_Barcode;
import com.app.util.TestUtil;


//getDataFromExcelForScanning2
public class Demo_Fast_Scanning_Of_Barcode extends TestBase{

	Login_Page loginpage;
	Scan_Barcode scanbarcode;
	Product_Setup_Page productsetuppage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("Gupta");
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("ProductSetup");
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData1(){
		//ArrayList<Object[]> testData1 = TestUtil.getDataFromExcelForScanning2();
		ArrayList<Object[]> testData1 = TestUtil.getDataFromExcelForProduct();
		return testData1.iterator();
	}
	
	@Test(priority=3,dataProvider="getTestData1")
	public void excel1fastscan(String pname,String pdesc,String pcategory,String psubcategory,String hsn,String pcode,
			String pimgpath,String vegoption,String hassubcategoryflag,String subcate1,String subcate2,String subcate3,String subcate4,String subcate5) {
		scanbarcode = new Scan_Barcode();
		productsetuppage = new Product_Setup_Page();
		
		 Map<String, String> keyvalues= new LinkedHashMap<String, String>();
			keyvalues.put("mrp","80");
			keyvalues.put("mrp1","40");
			keyvalues.put("mrp2","20");
			
			Map<String, String> sellingvalues = new LinkedHashMap<String, String>();
			sellingvalues.put("selling", "72");
			sellingvalues.put("selling1", "36");
			sellingvalues.put("selling2", "18");
			
			Map<String, String> stockvalues = new LinkedHashMap<String, String>();
			stockvalues.put("stock", "1");
			stockvalues.put("stock1", "1");
			stockvalues.put("stock2", "1");
		
		
		String featureName= "PACKET SIZE";
		String [] featureValues= {"1 KG","500 GMS","250 GMS"};
		
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("ProductSetup");
		
		//scanbarcode.scanInputWithExce2FastScan(barcodeno1,barcodeno2,yearmonth,day,dateflag);
		productsetuppage.AddNewProductUsingExcel(pname, pdesc, pcategory, psubcategory, hsn, pcode, pimgpath, vegoption, hassubcategoryflag, subcate1, subcate2, subcate3, subcate4, subcate5, featureName, featureValues, keyvalues, sellingvalues, stockvalues);
	}
}
