package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Application_Setting_Page extends TestBase{
	
	@FindBy(xpath="//input[@name='deliveryCharge']")
	WebElement deliveryCharge;
	
	@FindBy(xpath="//input[@name='overDueChargePerQuantity']")
	WebElement overDueCharge;
	
	@FindBy(xpath="//input[@name='paymentWithin']")
	WebElement paymentWithin;

	@FindBy(xpath="//input[@name='discount']")
	WebElement discount;
	
	@FindBy(xpath="//input[@name='EstimtaedDeliveryFrom']")
	WebElement estimatedDeliveryFrom;
	
	@FindBy(xpath="//input[@name='EstimtaedDeliveryTo']")
	WebElement estimatedDeliveryTo;
	
	@FindBy(xpath="//input[@id='timeInput1']")
	WebElement shopOpeningTime;
	
	@FindBy(xpath="//input[@id='timeInput2']")
	WebElement shopClosingTime;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	public Application_Setting_Page(){
		PageFactory.initElements(driver, this);
		System.out.println("In application page constructor");
	}
	
	
	public void ApplicationSettingSave(String cartbutton,String retdialogue,int delcharge,int overdue,String billrelparam,
			String trip,int paywithin,int disc,String samedaydelivery,int estdelfrom,int estdelto,String opening,String closing,String shopmode){
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='oneTimeOrderButtonPreference']//option", cartbutton);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='returnCanDialogeChoiceOnTrackDeliveryScreen']//option", retdialogue);
		
		Common_Methods.waitForWebElement(driver, deliveryCharge);
		deliveryCharge.clear();
		deliveryCharge.sendKeys(String.valueOf(delcharge));
		
		Common_Methods.waitForWebElement(driver, overDueCharge);
		overDueCharge.clear();
		overDueCharge.sendKeys(String.valueOf(overdue));
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='billPrint']//option", billrelparam);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='tripReportPrint']//option", trip);
		
		/*Common_Methods.waitForWebElement(driver, paymentWithin);
		paymentWithin.clear(); 
		paymentWithin.sendKeys(String.valueOf(paywithin));
		
		Common_Methods.waitForWebElement(driver, discount);
		discount.clear();
		discount.sendKeys(String.valueOf(disc));*/
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='SameDeliveryDay']//option", samedaydelivery);
		
		Common_Methods.waitForWebElement(driver, estimatedDeliveryFrom);
		estimatedDeliveryFrom.clear();
		estimatedDeliveryFrom.sendKeys(String.valueOf(estdelfrom));
		
		Common_Methods.waitForWebElement(driver, estimatedDeliveryTo);
		estimatedDeliveryTo.clear();
		estimatedDeliveryTo.sendKeys(String.valueOf(estdelto));
		
		Common_Methods.waitForWebElement(driver, shopOpeningTime);
		shopOpeningTime.clear();
		shopOpeningTime.sendKeys(opening);
		
		Common_Methods.waitForWebElement(driver, shopClosingTime);
		shopClosingTime.clear();
		shopClosingTime.sendKeys(closing);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='isAvialabel']//option", shopmode);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getmsg=Common_Methods.getToastMessage();
		Assert.assertEquals(getmsg, "Success");
	}
	
	public boolean GetSubscriptionValue() {
		WebElement ele = driver.findElement(By.xpath("//label[text()='Subscription:']"));
		Common_Methods.toElement(ele);
		
		boolean flag=Common_Methods.GetSelectedValueFromDropdown("//select[@name='subscription']");
		System.out.println("flag "+flag);
		return flag;
	}

}
