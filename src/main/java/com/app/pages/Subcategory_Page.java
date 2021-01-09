package com.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Subcategory_Page extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='subCategoryName']")
	WebElement subcategoryInput;
	
	@FindBy(xpath="//input[@name='subCategoryDescription']")
	WebElement subcategoryDesc;
	
	@FindBy(xpath="//input[@type='file']")
	WebElement uploadImg;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-up']")
	WebElement upArrowBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-down']")
	WebElement downArrowBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	public Subcategory_Page() {
		System.out.println("In subcategory page ctor");
		PageFactory.initElements(driver, this);
	}
	
	public void AddSubcategory(String subname,String description,String cname,String imgpath) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, subcategoryInput);
		subcategoryInput.sendKeys(subname);
		
		Common_Methods.waitForWebElement(driver, subcategoryDesc);
		subcategoryDesc.sendKeys(description);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryId']//option", cname);
		
		Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, uploadImg);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		//System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Successfully upload");
	}
	
	public void VerifyErrorMessageIfNotEnteredMandatoryField(String subname) {
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, subcategoryInput);
		subcategoryInput.sendKeys(subname);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		//System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Please fill all the mandatory fields");
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
	}
	
	public void ApplyFilterOnSubcategory(String subname,String action) {
		
		if(action.equals("Edit")) {
			
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(subname);
			
			driver.findElement(By.xpath("//td[text()='"+subname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
			
		}else {
			
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(subname);
			
			driver.findElement(By.xpath("//td[text()='"+subname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			searchInput.clear();
		}
	}
	
	public void VerifyErrorMessageIfSubcategoryAssociatedWithProduct() {
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
	}
	
    public void VerifyErrorMessageWithoutSelectingUpAndDownArrow() {
		
		Common_Methods.waitForWebElement(driver, upArrowBtn);
		upArrowBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Please select any one category");
		
		Common_Methods.waitForWebElement(driver, downArrowBtn);
		downArrowBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg1=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg1, "Please select any one category");
	}
    
    public void VerifyDuplicateErrorMessageWhileAddingSubcategory(String subname,String description,String cname
    		,String imgpath) {
    	
    	Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, subcategoryInput);
		subcategoryInput.sendKeys(subname);
		
		Common_Methods.waitForWebElement(driver, subcategoryDesc);
		subcategoryDesc.sendKeys(description);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryId']//option", cname);
		
		Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, uploadImg);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals("Subcategory name must be unique", getMsg);
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
    	
    }
    
    
    public void DoSequencingOfSubcategory(String [] subcateseq) {
		
		List<String> subcatename = new ArrayList<String>();
		List<String> newseqcate = new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist=driver.findElements(By.xpath("//tbody//tr"));
		
		for(int i=1;i<catelist.size();i++) {
			Common_Methods.toShortWait();
			String getcatename=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			subcatename.add(getcatename);
		}
		System.out.println("catename "+subcatename);
		Common_Methods.toShortWait();
		DoSequence(subcatename.get(3), 3, "Uparrow");
		
		Common_Methods.toShortWait();
		DoSequence(subcatename.get(2), 2, "Downarrow");
		
		Common_Methods.toShortWait();
		DoSequence(subcatename.get(4), 2, "Uparrow");
		
		//Common_Methods.waitForWebElement(driver, saveBtn);
		//saveBtn.click();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist1=driver.findElements(By.xpath("//tbody//tr"));
		for(int i=1;i<catelist1.size();i++) {
			Common_Methods.toShortWait();
			String getcatename1=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			newseqcate.add(getcatename1);
		}
		/*System.out.println("newseqcate "+newseqcate);
		for(int i=0;i<subcatename.size();i++) {
			Assert.assertEquals(newseqcate.get(i),subcateseq[i],"Getting wrong category sequence");
		}*/
	}
    
    public boolean DoSequence(String getsubcatename,int length,String action) {
		driver.findElement(By.xpath("//td[text()='"+getsubcatename+"']/ancestor::tr[1]//input")).click();
		if(action.equals("Uparrow")) {
			for(int i=1;i<=length;i++) {
				upArrowBtn.click();
			}
			return true;
		}else {
			for(int i=1;i<=length;i++) {
				downArrowBtn.click();
			}
			return true;
		}
		
	}

}
