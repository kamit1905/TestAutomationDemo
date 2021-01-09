package com.app.wiseaccounts.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Expenses_Page extends TestBase{
	
	@FindBy(xpath="//input[@name='expenseName']")
	WebElement expenseName;
	
	@FindBy(xpath="//input[@id='fromDate']")
	WebElement fromDateIn;
	
	@FindBy(xpath="//b[text()='Save']/ancestor::button[1]")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//input[@name='expenseAmount']")
	WebElement expensesAmount;
	
	@FindBy(xpath="//b[text()='Update']/ancestor::button[1]")
	WebElement updateBtn;
	
	public Expenses_Page() {
		System.out.println("In expenses page constructor");
		PageFactory.initElements(driver, this);
	}
	
	public void AddExpenses(String year,String day,String expensesname,String exptype,String remark) {
		
		try {
			Common_Methods.waitForWebElement(driver, fromDateIn);
			fromDateIn.click();
			Common_Methods.selectMonthFromCalender(year);
			driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[@aria-label='"+day+"']")).click();
			
			Common_Methods.waitForWebElement(driver, expenseName);
			expenseName.sendKeys(expensesname);
			
			Thread.sleep(2000);
			Common_Methods.selectFromDropDown("//select[@name='expenseTypeId']//option", exptype);
			
			Common_Methods.waitForWebElement(driver, saveBtn);
			saveBtn.click();
			
			//Common_Methods.shortWait();
			Thread.sleep(2000);
			String verifyMsg=Common_Methods.getToastMessage();
			//Assert.assertEquals(verifyMsg, "New Expense added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void EditExpenses(String expensesname,int expamt,String updatedname) {
		
		try {
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.sendKeys(expensesname);
			
			driver.findElement(By.xpath("//td[text()='"+expensesname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
			Common_Methods.waitForWebElement(driver, expenseName);
			expenseName.clear();
			expenseName.sendKeys(updatedname);
			
			Common_Methods.waitForWebElement(driver, expensesAmount);
			expensesAmount.clear();
			expensesAmount.sendKeys(String.valueOf(expamt));
			
			Common_Methods.waitForWebElement(driver, updateBtn);
			updateBtn.click();
			
			//Common_Methods.shortWait();
			Thread.sleep(2000);
			String verifyMsg=Common_Methods.getToastMessage();
			//Assert.assertEquals(verifyMsg, "Expense Updated");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void CheckErrorMessageIfNotFilledMandatoryField() {
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		String getErrorMsg=Common_Methods.getToastMessage();
		System.out.println("getErrorMsg "+getErrorMsg);
		Assert.assertEquals(getErrorMsg, "Please Fill All Mandatory Fields");
	}
	
	public void calculateTotalExpenses(int totalamt) {
		int total=0;
		String getTotalAmt=driver.findElement(By.xpath("//td[text()='Total']/ancestor::tr[1]//td[2]")).getText();
		System.out.println("getTotalAmt "+getTotalAmt);
		Common_Methods.shortWait();
		List<WebElement> li=driver.findElements(By.xpath("//table//tbody//tr"));
		
		for(int i=1;i<li.size();i++) {
			String getAmt=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[3]")).getText();
			//System.out.println("getAmt "+getAmt);
			int getAmt1=Integer.parseInt(getAmt);
			total=total+getAmt1;
		}
		
		System.out.println("total "+total);
		Assert.assertEquals(getTotalAmt, String.valueOf(total),"Getting wrong total calculated amt");
		
		Assert.assertEquals(getTotalAmt, String.valueOf(totalamt),"Getting wrong total amt");
	}

}
