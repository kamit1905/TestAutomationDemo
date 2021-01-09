package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Notes_Page extends TestBase{
	
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//button[text()='Save']")	
	WebElement saveBtn;
	
	@FindBy(xpath="//textarea[@name='productNotes']")
	WebElement notesinput;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//a[@class='close']")
	WebElement closeBtn;
	
	public Notes_Page() {
		System.out.println("In notes page ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void AddNotes(String notes) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, notesinput);
		notesinput.sendKeys(notes);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		
	}
	 
	public void VerifyErrorMessageOnNotes() {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Please fill all the mandatory fields");
		
		Common_Methods.waitForWebElement(driver, closeBtn);
		closeBtn.click();
	}
	
	public void NotesSearch(String notesname) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.sendKeys(notesname);
	}
	
	public void NotesSearch(String notesname,String aname) {
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.clear();
		searchInput.sendKeys(notesname);
		
		if(aname.equals("Edit")) {
			driver.findElement(By.xpath("//td[text()='"+notesname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		}else {
			driver.findElement(By.xpath("//td[text()='"+notesname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			searchInput.clear();
		}
		
		
	}
	

}
