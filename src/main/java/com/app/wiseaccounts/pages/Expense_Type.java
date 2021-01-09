package com.app.wiseaccounts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Expense_Type extends TestBase{
	
	@FindBy(xpath="//input[@name='expenseType']")
	WebElement expenseTypeInput;
	
	@FindBy(xpath="//input[@name='defaultAmount']")
	WebElement defaultAmount;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement expenseSearch;
	
	@FindBy(xpath="//b[text()='Save']/ancestor::button[1]")
	WebElement saveBtn;
	
	@FindBy(xpath="//b[text()='Update']/ancestor::button[1]")
	WebElement updateBtn;
	
	
	public Expense_Type() {
		System.out.println("In Expense Type Constructor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddExpenseType(String expname,String freq,String defamt) {
		try {
			Common_Methods.shortWait();
			Common_Methods.waitForWebElement(driver, expenseTypeInput);
			expenseTypeInput.sendKeys(expname);
			
			Common_Methods.selectFromDropDown("//select[@name='frequency']//option", freq);
			
			Common_Methods.waitForWebElement(driver, defaultAmount);
			defaultAmount.clear();
			//defaultAmount.sendKeys(String.valueOf(defamt));
			defaultAmount.sendKeys(defamt);
			
			Common_Methods.waitForWebElement(driver,saveBtn);
			saveBtn.click();
			
			//Common_Methods.shortWait();
			Thread.sleep(2000);
			String verifyMsg=Common_Methods.getToastMessage();
			//Assert.assertEquals(verifyMsg, "Expenses Type Added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//searchInput
	
	public void EditExpenseType(String expname,int defamt,String updatedexpname) {
		try {
			Common_Methods.waitForWebElement(driver, expenseSearch);
			expenseSearch.sendKeys(expname);
			
			driver.findElement(By.xpath("//td[text()='"+expname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
			Common_Methods.waitForWebElement(driver, expenseTypeInput);
			expenseTypeInput.clear();
			expenseTypeInput.sendKeys(updatedexpname);
			
			Common_Methods.waitForWebElement(driver, defaultAmount);
			defaultAmount.clear();
			defaultAmount.sendKeys(String.valueOf(defamt));
			
			Common_Methods.waitForWebElement(driver, updateBtn);
			updateBtn.click();
			
			//Common_Methods.shortWait();
			Thread.sleep(2000);
			String verifyMsg=Common_Methods.getToastMessage();
			//Assert.assertEquals(verifyMsg, "Expense Types Updated");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void DeleteExpenseType(String expname) {
		Common_Methods.waitForWebElement(driver, expenseSearch);
		expenseSearch.sendKeys(expname);
		
		driver.findElement(By.xpath("//td[text()='"+expname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
	}
}
