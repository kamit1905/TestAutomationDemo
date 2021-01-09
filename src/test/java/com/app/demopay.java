package com.app;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Billing_Page;
import com.app.pages.Login_Page;
import com.app.pages.Payment_Page;

public class demopay extends TestBase {
	
	Login_Page loginpage;
	Payment_Page paymentpage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	/*@Test(priority=3)
	public void makepayment() {
		paymentpage = new Payment_Page();
		//Common_Methods.navigationClick("Payment");
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//a[@href='/Payment']")).click();
		Common_Methods.selectDeliveryBoyFilter("Ritesh");
		
		paymentpage.doPayment("Farukh", "Ritesh",20, "Remark");
	}
	
	@Test(priority=4)
	public void searchcustomerandpay() {
		paymentpage = new Payment_Page();
		Common_Methods.selectDeliveryBoyFilter("Ritesh");
		paymentpage.searchCustomerAndPayAmoount("Farukh", "Ritesh",30, "Remark");
	}
	
	
	@Test(priority=5)
	public void verifypaymentdetailsforcus() {
		paymentpage = new Payment_Page();
		paymentpage.verifyPaymentDetailsForCustomer("Farukh",450,430,50);
		
	}*/
	
	@Test(priority=6)
	public void verifyadvanceamt() {
		Common_Methods.navigationClickLink("Payment");
		paymentpage = new Payment_Page();
		//paymentpage.verifyAdvanceAmtAgaingParticularCus("Akshay ", 230);
		paymentpage.getBalForAllCustomerOnPaymentScreen();
	}

}



//paymentpage.doPaymentAndVerifyErrorMessage("Customer105", "Delivery5",900, "Remark");
