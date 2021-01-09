package com.app.pages;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.util.TestUtil;

public class Category_Page extends TestBase{

	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='categoryName']")
	WebElement categoryInput;
	
	@FindBy(xpath="//input[@name='categoryDescription']")
	WebElement categoryDescription;
	
	//@FindBy(xpath="//input[@name='categoryImageUrl']")
	@FindBy(xpath="//input[@type='file']")
	WebElement uploadImg;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-up']")
	WebElement upArrowBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-down']")
	WebElement downArrowBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	public Category_Page() {
		System.out.println("In category page ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddCategory(String cname, String cdescription, String unit, String hassub, String[] feature,
			String imgpath) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, categoryInput);
		categoryInput.sendKeys(cname);
		
		Common_Methods.waitForWebElement(driver, categoryDescription);
		categoryDescription.sendKeys(cdescription);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='unitId']//option", unit);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='hasSubCategory']//option", hassub);
		
		
		for(int i=0;i<feature.length;i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='"+feature[i]+"']/ancestor::div[1]//input")).click();
		}
		
		
		//Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, uploadImg);
		
		Common_Methods.toElement(saveBtn);
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Category Saved Successfully");
	}
	
	public void ApplyFilterOnCategory(String cname,String action) {
		if(action.equals("Edit")) {
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(cname);
			
			driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		}else {
			
			Common_Methods.waitForWebElement(driver, searchInput);
			searchInput.clear();
			searchInput.sendKeys(cname);
			
			driver.findElement(By.xpath("//td[text()='"+cname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			Common_Methods.toShortWait();
			searchInput.clear();
		}
	}
	
	public void VerifyErrorMessageIfNotEnteredMandatoryField(String cname) {
			Common_Methods.waitForWebElement(driver, addBtn);
			addBtn.click();
			
			Common_Methods.waitForWebElement(driver, categoryInput);
			categoryInput.sendKeys(cname);
			
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			//Assert.assertEquals(getMsg, "");
			
			Common_Methods.waitForWebElement(driver, cancelBtn);
			cancelBtn.click();
	}
	
	public void VerifyErrorMessageWithoutSelectingUpAndDownArrow() {
		
		Common_Methods.waitForWebElement(driver, upArrowBtn);
		upArrowBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg, "Please select any one category");
		
		Common_Methods.waitForWebElement(driver, downArrowBtn);
		downArrowBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg1=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg1, "Please select any one category");
	}
	
	
	
	
	public void DoSequencingOfCategory1(String [] cateseq,String categrp) {
		
		List<String> catename = new ArrayList<String>();
		List<String> newseqcate = new ArrayList<String>();
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//button[text()='"+categrp+"']")).click();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist=driver.findElements(By.xpath("//tbody//tr"));
		System.out.println("catelist "+catelist.size());
		for(int i=1;i<=catelist.size();i++) {
			Common_Methods.toShortWait();
			String getcatename=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			catename.add(getcatename);
		}
		System.out.println("catename "+catename);
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(catename.get(1), 1, "Uparrow");
		
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(catename.get(3), 2, "Downarrow");
		
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(catename.get(0), 3, "Uparrow");
		
		//Common_Methods.toShortWait();
		//DoSequence1(catename.get(1), 3, "Uparrow");
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist1=driver.findElements(By.xpath("//tbody//tr"));
		System.out.println("catelist1 "+catelist1.size());
		for(int i=1;i<=catelist1.size();i++) {
			Common_Methods.toShortWait();
			String getcatename1=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			newseqcate.add(getcatename1);
		}
		System.out.println("newseqcate "+newseqcate);
		for(int i=0;i<catename.size();i++) {
			Assert.assertEquals(newseqcate.get(i),cateseq[i],"Getting wrong category sequence");
		}
	}
	
	//Vegetables, Test_Category_Vegetable, Vegetables1, Added Category, Category_New_Vegetables,Tst Vegetable    --before sequence for test_29
	// Added Category, Category1, Vegetables, Test_Category_Vegetable, Category2      						 --after squence 
	public void DoSequencingOfCategory(String [] cateseq) {
		
		List<String> catename = new ArrayList<String>();
		List<String> newseqcate = new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist=driver.findElements(By.xpath("//tbody//tr"));
		
		for(int i=1;i<catelist.size();i++) {
			Common_Methods.toShortWait();
			String getcatename=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			catename.add(getcatename);
		}
		System.out.println("catename "+catename);
		Common_Methods.toShortWait();
		DoSequence(catename.get(3), 3, "Uparrow");
		
		Common_Methods.toShortWait();
		DoSequence(catename.get(2), 2, "Downarrow");
		
		Common_Methods.toShortWait();
		DoSequence(catename.get(4), 2, "Uparrow");
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist1=driver.findElements(By.xpath("//tbody//tr"));
		for(int i=1;i<catelist1.size();i++) {
			Common_Methods.toShortWait();
			String getcatename1=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			newseqcate.add(getcatename1);
		}
		System.out.println("newseqcate "+newseqcate);
		for(int i=0;i<catename.size();i++) {
			Assert.assertEquals(newseqcate.get(i),cateseq[i],"Getting wrong category sequence");
		}
	}
	
	public boolean DoSequence(String getcatename,int length,String action) {
		driver.findElement(By.xpath("//td[text()='"+getcatename+"']/ancestor::tr[1]//input")).click();
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
	
	public void VerifyErrorMessageIfItIsAssociatedWithSubcategoryProduct() {
		Common_Methods.toShortWait();
		//Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		//Assert.assertEquals(getMsg, "Category associate with Subcategory");
	}
	
	public void VerifyDuplicateErrorMessageWhileAddingCategory(String cname, String cdescription, String unit, String hassub, String[] feature,
			String imgpath) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, categoryInput);
		categoryInput.sendKeys(cname);
		
		Common_Methods.waitForWebElement(driver, categoryDescription);
		categoryDescription.sendKeys(cdescription);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='unitId']//option", unit);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='hasSubCategory']//option", hassub);
		
		
		for(int i=0;i<feature.length;i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='"+feature[i]+"']/ancestor::div[1]//input")).click();
		}
		
		
		Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, uploadImg);
		
		Common_Methods.toElement(saveBtn);
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		Assert.assertEquals(getMsg,"Category name must be unique");
		
	}
	
	public void EditCategory(String cname,String action,List<String> taxlabel,List<String> taxdrop,List<String> taxvalue) {
		
		ApplyFilterOnCategory(cname, action);
		
		WebElement ele = driver.findElement(By.xpath("//label[text()='GST 5%']"));
		Common_Methods.toElement(ele);
		
		Actions action1 = new Actions(driver); 
		WebElement list =driver.findElement(By.xpath("//label[text()='GST 5%']/ancestor::div[2]//select[@name='condition']"));
		action1.moveToElement(list);
		//action1.click(list);
		
		//List<WebElement> list1=driver.findElements(By.xpath("//label[text()='GST 5%']/ancestor::div[2]//select[@name='condition']//option"));
		
		//for(WebElement option : list1) {
			//System.out.println(option.getText());
		    //if (option.getText().equals("Less Than")) {
		    	//action1.doubleClick(list).build().perform();
		        //option.click();
		    //}
		//}
		/*Select select = new Select(driver.findElement(By.name("//label[text()='GST 5%']/ancestor::div[2]//select[@name='condition']//option")));
		List<WebElement> options = select.getOptions();
		
		for(WebElement option: options) {
			String getText=option.getText();
			System.out.println("getText "+getText);
			if(getText.equals("Less Than")) {
				select.selectByVisibleText("Less Than");
			}
		}
          */  
	
		//WebElement ele2 = driver.findElement(By.xpath("//label[text()='GST 5%']/ancestor::div[2]//select[@name='condition']"));
			//Common_Methods.clickByJs(ele2);
			
		//driver.findElement(By.xpath("//label[text()='GST 5%']/ancestor::div[2]//select[@name='condition']//option")).click();

		//Common_Methods.selectFromDropDown("//select[@name='condition']//option", "Less Than");
		
		/*WebElement month_dropdown=driver.findElement(By.id("//div[@name='taxConditionDiv']//label[contains(text(),'GST 5')]/ancestor::div[2]//select[@name='condition']"));
		Select month=new Select(month_dropdown);
		month.selectByValue("Lessthan");
		
		WebElement month_dropdown1=driver.findElement(By.id("//div[@name='taxConditionDiv']//label[contains(text(),'GST 10')]/ancestor::div[2]//select[@name='condition']"));
		Select month1=new Select(month_dropdown1);
		month1.selectByValue("Greaterthan");*/
		
		/*Common_Methods.shortWait();
		AddTaxesInCategory(taxlabel, taxdrop, taxvalue);
		
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
		
		boolean altstatus=Common_Methods.isAlertPresent();
		System.out.println("altstatus "+altstatus);
		if(altstatus){
			String getmsg=driver.switchTo().alert().getText();
			System.out.println("getmsg "+getmsg);
			driver.switchTo().alert().accept();
		}*/
	}
	
	public void AddTaxesInCategory(List<String> taxlabel,List<String> taxdrop,List<String> taxvalue) {
		
		for(int i=0;i<taxlabel.size();i++) {
		Common_Methods.toShortWait();
		WebElement ele = driver.findElement(By.xpath("//label[text()='"+taxlabel.get(i)+"']"));
		Common_Methods.toElement(ele);
		Common_Methods.selectFromDropDown("//label[text()='"+taxlabel.get(i)+"']/ancestor::div[2]//select[@name='condition']//option", taxdrop.get(i));
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//label[text()='"+taxlabel.get(i)+"']/ancestor::div[2]//input[@name='amount']")).clear();
		driver.findElement(By.xpath("//label[text()='"+taxlabel.get(i)+"']/ancestor::div[2]//input[@name='amount']")).sendKeys(taxvalue.get(i));
		
		}
	}
}
