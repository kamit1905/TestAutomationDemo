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

public class Category_Group_Page extends TestBase{

	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='categoryGroupName']")
	WebElement categoryGroupName;
	
	@FindBy(xpath="//input[@name='categoryGroupDescription']")
	WebElement categoryGroupDescription;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//input[@type='file']")
	WebElement uploadImg;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement cateSearchInCateGrp;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement categoryGroupSearch;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-up']")
	WebElement upArrowBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-down']")
	WebElement downArrowBtn;
	
	
	public Category_Group_Page() {
		System.out.println("In category group ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddCategoryGroup(String categrpname,String categrpdesc,String imgpath) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, categoryGroupName);
		categoryGroupName.sendKeys(categrpname);
		
		Common_Methods.waitForWebElement(driver, categoryGroupDescription);
		categoryGroupDescription.sendKeys(categrpdesc);
		
		//Common_Methods.toShortWait();
		Common_Methods.uploadFile(imgpath, uploadImg);
		
		Common_Methods.toElement(saveBtn);
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
	}
	
	public void VerifyErrorMessageIfNotEnteredMandatoryField(String categrpname) {
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, categoryGroupName);
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		//@RequestParams extract values from the query string, @PathVariables extract values from the URI path:
		
		WebElement ele=driver.findElement(By.xpath("//div[text()='Add Category Group']"));
		Common_Methods.toElement(ele);
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		Assert.assertEquals("Please fill all the mandatory fields", getMsg);	
		
		Common_Methods.waitForWebElement(driver, cancelBtn);
		cancelBtn.click();
	}
	//////
	//////
	//https://www.geeksforgeeks.org/concurrenthashmap-in-java/
	public void EditCategoryGroup(String categrpname,String newcategrp,String categrpdesc) {
		Common_Methods.waitForWebElement(driver, categoryGroupSearch);
		categoryGroupSearch.clear();
		categoryGroupSearch.sendKeys(categrpname);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//td[text()='"+categrpname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		
		Common_Methods.waitForWebElement(driver, categoryGroupName);
		categoryGroupName.clear();
		categoryGroupName.sendKeys(newcategrp);
		
		Common_Methods.waitForWebElement(driver, categoryGroupDescription);
		categoryGroupDescription.clear();
		categoryGroupDescription.sendKeys(categrpdesc);
		
		Common_Methods.toElement(updateBtn);
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
		Common_Methods.toShortWait();
		categoryGroupSearch.clear();
	}
	
	public void AssignCategoryGroup(String categrpname,List<String> category) {
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryGroupId']//option", categrpname);
		
		for(int i=0;i<category.size();i++) {
		Common_Methods.toShortWait();
		WebElement ele=driver.findElement(By.xpath("//td[text()='"+category.get(i)+"']/ancestor::tr[1]//input[@type='checkbox']"));
		Common_Methods.toElement(ele);
		ele.click();
		}	
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		WebElement ele=driver.findElement(By.xpath("//div[text()='Assign Category Group']"));
		Common_Methods.toElement(ele);
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		Assert.assertEquals("Successfully save", getMsg);
		
	}
	
	public void VerifyCategoryUnderCategoryGroups(String categrp,List<String> cate) {
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//button[text()='"+categrp+"']")).click();
		
		List<WebElement> getCategory=driver.findElements(By.xpath("//button[text()='"+categrp+"']/ancestor::div[1]/following-sibling::div[2]//tbody//tr"));
		int len = getCategory.size();
		
		List<String> catename = new ArrayList<String>();
		
		for(int i=1;i<=len;i++) {
			String getCateName=driver.findElement(By.xpath("//button[text()='"+categrp+"']/ancestor::div[1]/following-sibling::div[2]//tbody//tr["+i+"]//td[2]")).getText();
			catename.add(getCateName);
		}
		
		for(int j=0;j<catename.size();j++) {
			Assert.assertEquals(catename.get(j), cate.get(j),"Category is not present in category group");
		}
	}
	
	//[Fresh Vegetables, Fresh Fruits, Masale Items, Atta, Safety Kits]---before sequencing
	//[Masale Items, Safety Kits, Fresh Vegetables, Fresh Fruits]---after sequencing
	public void DoSequencingOfCategoryGroup(String [] categrpseq) {
		
		List<String> catename = new ArrayList<String>();
		List<String> newseqcate = new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist=driver.findElements(By.xpath("//tbody//tr"));
		
		for(int i=1;i<=catelist.size();i++) {
			Common_Methods.toShortWait();
			String getcatename=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			catename.add(getcatename);
		}
		System.out.println("catename "+catename);
		Common_Methods.toShortWait();
		DoSequence(catename.get(3), 3, "Uparrow");
		
		Common_Methods.toShortWait();
		DoSequence(catename.get(2), 2, "Uparrow");
		
		Common_Methods.toShortWait();
		DoSequence(catename.get(0), 2, "Downarrow");
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		List<WebElement> catelist1=driver.findElements(By.xpath("//tbody//tr"));
		for(int i=1;i<=catelist1.size();i++) {
			Common_Methods.toShortWait();
			String getcatename1=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			newseqcate.add(getcatename1);
		}
		System.out.println("newseqcate "+newseqcate);
		for(int i=0;i<catename.size();i++) {
			Assert.assertEquals(newseqcate.get(i),categrpseq[i],"Getting wrong category sequence");
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
	
	public List<String> GetAllCategoryGroup(){
		List<String> categrp = new ArrayList<String>();
		
		 Common_Methods.toShortWait();
		 List<WebElement> cateGrpList=driver.findElements(By.xpath("//tbody/tr"));
		 
		 for(int i=1;i<=cateGrpList.size();i++) {
			 String getCateGrpName=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			 System.out.println("getCateGrpName "+getCateGrpName);
			 categrp.add(getCateGrpName);
		 }
		 
		 return categrp; 	 
	}
	
	public void VerifyCategoryGroupSeqInCategory(List<String> categrpname) {
		
		List<String> getActualCateGrp = new ArrayList<String>();
		Common_Methods.toShortWait();
		List<WebElement> categrp=driver.findElements(By.xpath("//div[contains(@style,'margin-left: 1%')]//button"));
	
		for(int i=3;i<=categrp.size();i++) {
			Common_Methods.toShortWait();
			String getcategrpnaem=driver.findElement(By.xpath("//div[contains(@style,'margin-left: 1%')]//button["+i+"]")).getText();
			System.out.println("getcategrpnaem "+getcategrpnaem);
			getActualCateGrp.add(getcategrpnaem);
		}
		
		for(int i=0;i<categrpname.size();i++) {
			Assert.assertEquals(getActualCateGrp.get(i), categrpname.get(i),"Getting wrong sequence of category grp in category");
		}
	}
	
	public void DeselectCategoryFromCategorygroup(String categrp,List<String> cate) {
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryGroupId']//option", categrp);
		
		for(int i=0;i<cate.size();i++) {
			WebElement ele=driver.findElement(By.xpath("//td[text()='"+cate.get(i)+"']/ancestor::tr[1]//i[@class='fa fa-close']"));
			Common_Methods.toShortWait();
			Common_Methods.toElement(ele);
			ele.click();
		}
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
	}
}
