package com.app;

import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Delivery_Boy;
import com.app.pages.Login_Page;
import com.app.pages.Product_Page;

public class CRUD_Product extends TestBase{
	
	Login_Page loginpage;
	Product_Page productpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Product");
		
	}
	
	@Test(priority=3)
	public void addProduct() {
		productpage = new Product_Page();
		productpage.addProduct("Jackson", "desc", 35, "Jar", "Yes");
	}
	
	public void verifyError() {
		productpage = new Product_Page();
		productpage.verifyErrorMessageIfNotEnteredMandatoryField("Any Product");
	}
	
	@Test(priority=4)
	public void editProduct() {
		Common_Methods.navigationInnerLink("Product");
		productpage = new Product_Page();
		productpage.editProduct("Jackson", "No", "Jackson1");
	}
	
	@Test(priority=5)
	public void deleteProduct() {
		productpage = new Product_Page();
		productpage.deleteProduct("Jackson1");
	}

}
