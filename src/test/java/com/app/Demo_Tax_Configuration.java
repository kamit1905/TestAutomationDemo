package com.app;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Booklet_Required_Page;
import com.app.pages.Config_Product;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;
import com.app.pages.Product_Page;
import com.app.pages.Tax_Configuration_Page;

public class Demo_Tax_Configuration extends TestBase{
	
	
	Login_Page loginpage;
	Tax_Configuration_Page taxconfigurationpage;
	Product_Page productpage;
	Config_Product configureproduct;
	Booklet_Required_Page bookletrequiredpage;
	
	@BeforeSuite
	public void set() {
		initialize();
		loginpage=new Login_Page();
		taxconfigurationpage = new Tax_Configuration_Page();
		productpage = new Product_Page();
		configureproduct = new Config_Product();
		bookletrequiredpage = new Booklet_Required_Page();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("test5auto5");
	}
	
	@Test(priority=3)
	public void addTax() {
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Tax Configurations");
		//taxconfigurationpage.AddTaxConfiguration("GST 15%", "15", "15", true, "No");
	}
	
	@Test(priority=4)
	public void check() {
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Product");
		productpage.ApplyProductFilter("Product2","Edit");
		productpage.verifyAddedTaxInProduct("GST 15%",true);
		
		//Unselect tax configuration & verify it in product
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Tax Configurations");
		taxconfigurationpage.UnselectTaxConfiguration("Tax_3");
		
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Product");
		productpage.ApplyProductFilter("Product2", "Edit");
		productpage.verifyAddedTaxInProduct("GST 15%",false);
	}
	
	@Test(priority=5)
	public void selectconfigureproduct() {
		
		String [] reqQty= {"1Unit","2Unit"};
		String [] reqQty1= {"1Unit"};
		
		//Select qty in configured product & verify it in booklet required
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Configure Product");
		configureproduct.SaveConfigureProduct("Product1", "2","Select");
		
		Common_Methods.ClickNavigation("BookletRequired");
		bookletrequiredpage.VerifyRequiredQuantityOfJarsInBookletRequired(reqQty, "Product1");
		
		//Deselect qty in configured product & verify it in bookltet required
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Configure Product");
		configureproduct.SaveConfigureProduct("Product1", "2","Deselect");
		
		Common_Methods.ClickNavigation("BookletRequired");
		bookletrequiredpage.VerifyRequiredQuantityOfJarsInBookletRequired(reqQty1, "Product1");
		
		//Verify error if not enter mandatory field
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.toShortWait();
		Common_Methods.ClickNavigation("Configure Product");
		configureproduct.VerifyErrorMessgeIfNotSelected("1");
	}
}
