package com.app.pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Scan_Barcode extends TestBase{
	
	@FindBy(xpath="//input[@placeholder='Date']")
	WebElement dateInput;
	
	@FindBy(xpath="//input[@name='scanInput']")
	WebElement scanInput;
	
	@FindBy(xpath="//span[text()='Scan Barcode']/ancestor::a[1]")
	WebElement scanBarcodeLink;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='returnQuantity']")
	WebElement returnQtyDialogue;
	
	public Scan_Barcode() {
		System.out.println("In Scan Barcode Constructor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void scanInputWithExcel(String barcodeno,String yermonth,String day) {
		Common_Methods.waitForWebElement(driver, scanBarcodeLink);
		scanBarcodeLink.click();
		System.out.println("barcodeno "+barcodeno);
		scanInput.sendKeys(barcodeno);
		
		
		//Common_Methods.shortWait();
		//Common_Methods.waitForWebElement(driver, dateInput);
		//dateInput.click();
		//dateInput.sendKeys("2019-09-11");
		//dateInput.clear();
		//driver.findElement(By.xpath("//div[@class='ant-calendar-date-input-wrap']//input[@placeholder='Date']")).sendKeys("2019-09-11");
		
		//Common_Methods.waitForWebElement(driver, scanInput);
		//Common_Methods.shortWait();
		//scanInput.sendKeys(String.valueOf(barcodeno));
		//driver.findElement(By.xpath("//div[contains(text(),' Scan Barcode')]")).click();
		//scanInput.sendKeys(Keys.ENTER);
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).sendKeys("");
		driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']")).click();
		scanInput.sendKeys(Keys.ENTER);
	}
	
	public void scanInputWithExce2(String barcodeno,String barcodeno1,String yermonth,String day,String pending) {
		//Common_Methods.waitForWebElement(driver, scanBarcodeLink);
		//scanBarcodeLink.click();
		
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
		//System.out.println("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']");
		Common_Methods.toShortWait();
		String getsta=Common_Methods.selectMonthFromCalender(yermonth);
		if(getsta.equals("Current month")) {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+day+"']")).click();
		}else {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']")).click();
		}
			
		System.out.println("barcodeno1 "+barcodeno1);
		String barcode3=BigDecimal.valueOf(Double.parseDouble(barcodeno1)).toPlainString();
		//System.out.println("Barcode Parced: "+BigDecimal.valueOf(Double.parseDouble(barcodeno1)).toPlainString());
		System.out.println("barcode3 "+barcode3);
		scanInput.sendKeys(barcode3);
		//8329122386
		Common_Methods.toShortWait();
		scanInput.sendKeys(Keys.ENTER);
		Common_Methods.shortWait();
		
		/*String getPendingcans=driver.findElement(By.xpath("//td[@id='canswithcust']")).getText();
		System.out.println("getPendingcans "+getPendingcans);
		Assert.assertEquals(getPendingcans, pending,"Getting wrong pending cans");*/
		
		boolean flag1=Common_Methods.isElementVisible(returnQtyDialogue);
		
		if(!flag1) {
			System.out.println("Return Dialogue is not present");
		}	
		else {
			System.out.println("Return Dialogue is present");
		}
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		Common_Methods.shortWait();
	}
	
	public void scanInputWithExce2FastScan(String barcodeno,String barcodeno1,String yermonth,String day,String dateflag) {
		Common_Methods.waitForWebElement(driver, scanBarcodeLink);
		scanBarcodeLink.click();
		
		if(dateflag.equals("True")) {
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']//input")).click();
		//System.out.println("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']");
		String getsta=Common_Methods.selectMonthFromCalender(yermonth);
		if(getsta.equals("Current month")) {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='datepicker__day--today']//div[@aria-label='"+day+"']")).click();
		}else {
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']")).click();
		  }
		
		}
		
		System.out.println("barcodeno1 "+barcodeno1);
		String barcode3=BigDecimal.valueOf(Double.parseDouble(barcodeno1)).toPlainString();
		
		System.out.println("barcode3 "+barcode3);
		scanInput.sendKeys(barcode3);
		scanInput.sendKeys(Keys.ENTER);
		Common_Methods.shortWait();
		

	}


}
