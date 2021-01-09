package com.app;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Customer_Page;
import com.app.pages.Delivery_Boy;
import com.app.pages.Delivery_Group;
import com.app.pages.Login_Page;
import com.app.pages.Product_Page;
import com.app.pages.Stock_Management;
import com.app.pages.Stock_Report;

@Listeners(com.app.listner.ListnerDemo1.class)
public class Test_Product extends TestBase {
	
	Login_Page loginpage;
	Product_Page productpage;
	Delivery_Boy deliverboy;
	Stock_Management stockmanagement;
	Stock_Report stockreport;
	Customer_Page customerpage;
	Delivery_Group deliverygroup;
	
	@BeforeSuite
	public void set() {
		initialize();
		loginpage=new Login_Page();
		productpage=new Product_Page();
		deliverboy = new Delivery_Boy();
		stockmanagement = new Stock_Management();
		stockreport = new Stock_Report();
		customerpage = new Customer_Page();
		deliverygroup = new Delivery_Group();
	}
	
	@Test(priority=2)
	public void logingoogletest() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
		
	}

	/*@Test(priority=3)
	public void productCRUD() {
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Product");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Product");
		productpage.addProduct("Water Jar", "product description", 60, "Bottle", "Yes");
		
		productpage.verifyProductSearch("Water Jar");
		productpage.verifyAddedProduct("Water Jar","Product Description", 60);
		
		productpage.editProduct("Water Jar", "No", "Water Jar1");
		productpage.ApplyProductFilter("Water Jar1","");
		productpage.deleteProduct("Water Jar1");
		
		Common_Methods.longWait();
		productpage.ApplyProductFilter("Product1","");
		productpage.deleteProduct("Product1");
		
		Common_Methods.navigationInnerLink("Product");
		productpage.verifyErrorMessageIfNotEnteredMandatoryField("Test Pro");
	}*/
	
	
	@Test(priority=4)
	public void deliveryboyCRUD() {
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Delivery Boy");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("DeliveryBoy");
		deliverboy.addDeliveryBoy("Kamlesh", "9730398956", "KAMLESH56@GMAIL.COM", "Delievery Boy");
		
		deliverboy.ApplyDeliveryBoyFilter("Kamlesh");
		deliverboy.editDeliveryBoy("Kamlesh", "8329122708","Karthik");
		
		deliverboy.ApplyDeliveryBoyFilter("Karthik");
		deliverboy.verifyDeliveryBoy("Karthik", "8329122708");
		deliverboy.deleteDeliveryBoy("Karthik");
		
		deliverboy.ApplyDeliveryBoyFilter("Delivery1");
		deliverboy.deleteDeliveryBoy("Delivery1");
		
		deliverboy.VerifyErrorMessageIfDuplicateDeliveryBoy("Delivery3", "9787455565", "delivery1@gmail.com");		//verify error messgae if delivery boy is duplicate
		
	}
	
	/*@Test(priority=5)
	public void addcus(){
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Customer");
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Customer");
		customerpage.verifyErrorMessageIfNotEnteredMandatoryField("Japan");    //In this verify error msg if not entered any mandatory field as well as cancel button functionality	
		customerpage.addCustomerAndPlaceRecurringThroughExcel("Japan", "9746124512", "japan56@gmail.com", "Airoli Navi Mumbai", "Mumbai", "Monthly", "Active", "Product1", "Delivery1", "1","0250", "1","October 2019","1");
		customerpage.DeleteRecurringOrder("Product1");    //delete recurring order
		Common_Methods.shortWait();
		customerpage.AddRecurringOrder("Product2", "Delivery1", "1","0350","1","October 2019","1");  //place recurring order once again
	}*/
	
	//Add stock & verify in stock report
	@Test(priority=6)
	public void stockCRUD(){
		try {
			String pname="Product2";
			//Common_Methods.navigationClickUsingName("Inventory");
			//Common_Methods.navigationInnerLink("Stock Management");
			Common_Methods.ClickNavigationListItem("Inventory");
			Common_Methods.navigationClickLink("StockManagement");
			
			stockmanagement.addNewStock("Product2","3","2");
			Common_Methods.shortWait();
			int getstockqtyinstockmanagement=stockmanagement.GetLatestStockQty(pname);
			//Common_Methods.navigationClickUsingName("Report");
			//Common_Methods.navigationInnerLink("Stock Report");
			Common_Methods.ClickNavigationListItem("Report");
			Common_Methods.ClickNavigation("Stock Report");
			Common_Methods.shortWait();
			int getstockqtyinstockreport=stockreport.GetTotalProductInStockReport(pname);
			Assert.assertEquals(String.valueOf(getstockqtyinstockmanagement), String.valueOf(getstockqtyinstockreport),"Getting wrong stock quantity against product");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//Delivery group related activity
	/*@Test(priority=7)
	//mahi,mario,mohan,cust test2,palash
	//Customer3,Customer1,Customer6,Customer7,Customer8
	public void deliverygroup() {
		//String [] cusarr= {"Cust Test2","Mario","Mahi","Palash","Mohan"};
		String [] cusarr= {"Customer7","Customer1","Customer3","Customer8","Customer6"};
		//Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Delivery Group");
		Common_Methods.shortWait();
		deliverygroup.VerifyErrorMessageWithoutSelectingCustomer("CST");
		deliverygroup.maintainDeliveryGroupSequence("CST",cusarr);
		deliverygroup.verifyErrorMessageOnDeleteOfDeliveryGroup("Borivali");
	}*/
	
	
	

}
