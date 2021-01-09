package com.app.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Product_Setup_Page extends TestBase{
	
	@FindBy(xpath="//img[@class='plusImg']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@name='productName']")
	WebElement productName;
	
	@FindBy(xpath="//input[@name='productDescription']")
	WebElement productDesc;
	
	@FindBy(xpath="//input[@name='hsnCode']")
	WebElement Hsn;
	
	@FindBy(xpath="//input[@name='productCode']")
	WebElement productCode;
	
	@FindBy(xpath="//input[@type='file']")
	WebElement browseImg;
	
	@FindBy(xpath="//button[text()='Save and Next']")
	WebElement saveAndNextBtn;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement productSearch;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-up']")
	WebElement upArrowBtn;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-down']")
	WebElement downArrowBtn;
	
	@FindBy(xpath="//button[text()='Deactivate']")
	WebElement deactivateBtn;
	
	@FindBy(xpath="//button[text()='Multiple Delete']")
	WebElement multipleDeleteBtn;
	
	@FindBy(xpath="//button[text()='Delete']")
	WebElement deleteBtn;
	
	@FindBy(xpath="//label[text()='Sub Product:']/ancestor::div[1]/following-sibling::div[1]")
	WebElement isSubProduct;
	
	@FindBy(xpath="//label[text()='Supplier:']/ancestor::div[1]/following-sibling::div[1]")
	WebElement supplier;
	
	@FindBy(xpath="//button[text()='Save & Next']")
	WebElement saveAndNextBtn1;
	
	
	 
	
	
	public void AddProductInformation(String isSub,String hsn,List<String> subproduct,List<String> suppl) {
		
		//Operation to enter AMIT in capital letter
		//Actions a = new Actions(driver);
		//a.moveToElement(driver.findElement(By.xpath(""))).click().keyDown(Keys.SHIFT).sendKeys("amit").build().perform();
		//a.moveToElement(driver.findElement(By.xpath(""))).click().keyDown(Keys.SHIFT).doubleClick().build().perform();
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='isSubProduct']//option", isSub);
		
		Common_Methods.waitForWebElement(driver, Hsn);
		Hsn.sendKeys(hsn);
		
		if(isSub.equals("Yes")) {
		
			Common_Methods.waitForWebElement(driver, supplier);
			supplier.click();
			
			for(int i=0;i<suppl.size();i++) {
				Common_Methods.toShortWait();
				WebElement ele=driver.findElement(By.xpath("//span[text()='"+suppl.get(i)+"']/ancestor::div[1]//input"));
				Common_Methods.toElement(ele);
				ele.click();
			}
			
			Common_Methods.waitForWebElement(driver, saveAndNextBtn);
			saveAndNextBtn.click();
			
		}else {
			
			Common_Methods.waitForWebElement(driver, isSubProduct);
			isSubProduct.click();
			
			for(int i=0;i<subproduct.size();i++) {
				Common_Methods.toShortWait();
				WebElement ele=driver.findElement(By.xpath("//span[text()='"+subproduct.get(i)+"']/ancestor::div[1]//input"));
				Common_Methods.toElement(ele);
				ele.click();
			}
			isSubProduct.click();
			
			Common_Methods.waitForWebElement(driver, supplier);
			supplier.click();
			
			for(int i=0;i<suppl.size();i++) {
				Common_Methods.toShortWait();
				WebElement ele=driver.findElement(By.xpath("//span[text()='"+suppl.get(i)+"']/ancestor::div[1]//input"));
				Common_Methods.toElement(ele);
				ele.click();
			}
			supplier.click();
			
			Common_Methods.waitForWebElement(driver, saveAndNextBtn);
			saveAndNextBtn.click();
			
		}
		
	}
	
	public void productedit(String pname,String hsn,String action,List<String> subproduct,List<String> supplier) {
		ApplyFilterOnProduct(pname, action);
		
		Common_Methods.toShortWait();
		Common_Methods.waitForWebElement(driver, saveAndNextBtn1);
		saveAndNextBtn1.click();
		Common_Methods.toShortWait();
		AddProductInformation("No",hsn,subproduct,supplier);
		
	}
	
	
	
	public Product_Setup_Page() {
		System.out.println("In product setup ctor");
		PageFactory.initElements(driver, this);
	}
	
	//collection,exception,string,pgm string,
	public void AddNewProductUsingExcel(String pname,String pdesc,String pcategory,String psubcategory,
			String hsn,String pcode,String pimgpath,String vegoption,String hassubcategoryflag,String subcate1,String subcate2,String subcate3,String subcate4,String subcate5,
			String fname,String [] fvalues,Map<String, String> hashmrp,Map<String, String> hashselling,Map<String, String> hashstock) {
		
		
		List<String> subcatlist = new ArrayList<String>();
		List<String> subcatlistwithnonempty = new ArrayList<String>();
		
		subcatlist.add(subcate1);
		subcatlist.add(subcate2);
		subcatlist.add(subcate3);
		subcatlist.add(subcate4);
		subcatlist.add(subcate5);
		
		Iterator<String> itr = subcatlist.iterator();
		while(itr.hasNext()) {
			String getname=itr.next();
			if(getname.isEmpty()) {
				
			}else {
				subcatlistwithnonempty.add(getname);
			}
		}
		
		
		System.out.println("subcatlist "+subcatlistwithnonempty);
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, productName);
		productName.sendKeys(pname);
		
		Common_Methods.waitForWebElement(driver, productDesc);
		productDesc.sendKeys(pdesc);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryId']//option", pcategory);
		
		if(hassubcategoryflag.equals("False")) {
			String getsubcategory=driver.findElement(By.xpath("//select[@name='subcategoryId']//option")).getText();
			
			if(getsubcategory.equals("No Subcategory")) {
				System.out.println("Verified that category is not having any subcategory");
			}
			
		}else {
			//Verify subcategory for that category
			List<WebElement> sub=driver.findElements(By.xpath("//select[@name='subcategoryId']//option"));
			for(int i=2;i<=sub.size();i++) {
				Common_Methods.toShortWait();
				String getsubcategory=driver.findElement(By.xpath("//select[@name='subcategoryId']//option["+i+"]")).getText();
				System.out.println("getsubcategory "+getsubcategory);
				Assert.assertEquals(getsubcategory, subcatlistwithnonempty.get(i-2));
			}
			
			System.out.println("Verified all subcategory");
		
			//Select subcategory
			Common_Methods.toShortWait();
			Common_Methods.selectFromDropDown("//select[@name='subcategoryId']//option", psubcategory);
		}
		
		
		for(int i=0;i<fvalues.length;i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='"+fname+"']/ancestor::div[1]//label[text()='"+fvalues[i]+"']/ancestor::div[1]//input")).click();
		}
		
		Common_Methods.waitForWebElement(driver, Hsn);
		Hsn.sendKeys(hsn);
		
		Common_Methods.waitForWebElement(driver, productCode);
		productCode.sendKeys(pcode);
		
		Common_Methods.uploadFile(pimgpath, browseImg);
		
		Common_Methods.toShortWait();
		driver.findElement(By.xpath("//label[text()='"+vegoption+"']/ancestor::div[1]//input")).click();
		
		Common_Methods.waitForWebElement(driver, saveAndNextBtn);
		saveAndNextBtn.click();
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		
		if(getMsg.equals("Product name must be unique")) {
			Assert.assertEquals(getMsg, "Product name must be unique");
			
			Common_Methods.waitForWebElement(driver, cancelBtn);
			cancelBtn.click();
		}else {
			Common_Methods.toShortWait();
			AddMrpSellingPriceForProduct(pname, fvalues, hashmrp, hashselling, hashstock);			
		}
		
		
		
		
	}
	
	public void AddKeywordInProduct(List<String> keylist) {
		for(int i=0;i<keylist.size();i++) {
			if(i==0) {
				driver.findElement(By.xpath("//label[contains(text(),'KeyWord:')]/ancestor::div[2]//input")).sendKeys(keylist.get(i));
				Common_Methods.toShortWait();
				Common_Methods.waitForWebElement(driver, addBtn);
				//addBtn.click();
			}else {
				addBtn.click();
				driver.findElement(By.xpath("//label[contains(text(),'KeyWord')][text()='"+i+"']/ancestor::div[2]//input")).sendKeys(keylist.get(i));
				Common_Methods.toShortWait();
				Common_Methods.waitForWebElement(driver, addBtn);
				
			}
		}
		
	}
	
	public void AddEnableForSubscription(String delfreq,List<String> slot) {
		WebElement ele = driver.findElement(By.xpath("//label[text()='Time Slot:']"));
		Common_Methods.toElement(ele);
		
		if(delfreq.equals("Alternate day")) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='"+delfreq+"']/ancestor::div[1]//input")).click();
			
			for(int i=0;i<slot.size();i++) {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='"+slot.get(i)+"']/ancestor::div[1]//input")).click();
			}
		}else {
			for(int i=0;i<slot.size();i++) {
				Common_Methods.toShortWait();
				driver.findElement(By.xpath("//label[text()='"+slot.get(i)+"']/ancestor::div[1]//input")).click();
			}
		}
		
	}
	
	
	public void EditProductSetup(String pname,String action,boolean Subflag,String EnableFor,String delfreq,List<String> slot) {
		
		ApplyFilterOnProduct(pname, action);
		
		Common_Methods.shortWait();
		
		if(Subflag) {
			driver.findElement(By.xpath("//label[text()='"+EnableFor+"']/ancestor::div[1]//input")).click();
			Common_Methods.shortWait();
			AddEnableForSubscription(delfreq,slot);
		}else if(EnableFor.equals("One Time Order")){
			WebElement ele =driver.findElement(By.xpath("//label[text()='Enable For']"));
			Common_Methods.toElement(ele);
				System.out.println("Only one time order gets selected");
		}else {
			
			WebElement ele =driver.findElement(By.xpath("//label[text()='Enable For']"));
			Common_Methods.toElement(ele);
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='One time Order']/ancestor::div[1]//input")).click();
			
			//Common_Methods.toShortWait();
			//driver.findElement(By.xpath("//label[text()='"+EnableFor+"']/ancestor::div[1]//input")).click();
			
			AddEnableForSubscription(delfreq, slot);
			}
		
		
		Common_Methods.waitForWebElement(driver, updateBtn);
		updateBtn.click();
	}
	
	
	//String [] subcategory,    AddProductInformation(isSub, hsn, subproduct, suppl);
	//label[contains(text(),'KeyWord')][text()='2']/ancestor::div[2]//input
	public void AddNewProduct(String pname, String pdesc, /* List<String>keyword, */String pcategory,String psubcategory,
			String hsn,String pcode,String pimgpath,String vegoption,boolean Subflag,String EnableFor,String delfreq,List<String> slot,String fname,String [] fvalues
			,Map<String, String> hashmrp,Map<String, String> hashselling,Map<String, String> hashstock,String  [] subcategory,boolean hassubcategoryflag,
			String isSub,List<String> subproduct,List<String> suppl) {
		
		
		Common_Methods.waitForWebElement(driver, addBtn);
		addBtn.click();
		
		Common_Methods.waitForWebElement(driver, productName);
		productName.sendKeys(pname);
		
		Common_Methods.waitForWebElement(driver, productDesc);
		productDesc.sendKeys(pdesc);
		
		//AddKeywordInProduct(keyword);
		
		Common_Methods.toShortWait();
		Common_Methods.selectFromDropDown("//select[@name='categoryId']//option", pcategory);
		
		if(!hassubcategoryflag) {
			String getsubcategory=driver.findElement(By.xpath("//select[@name='subcategoryId']//option")).getText();
			
			if(getsubcategory.equals("No Subcategory")) {
				System.out.println("Verified that category is not having any subcategory");
			}
			
			//major functional
			//selenium architecture working
			
		}else {
			//Verify subcategory for that category
			List<WebElement> sub=driver.findElements(By.xpath("//select[@name='subcategoryId']//option"));
			for(int i=2;i<=sub.size();i++) {
				Common_Methods.toShortWait();
				String getsubcategory=driver.findElement(By.xpath("//select[@name='subcategoryId']//option["+i+"]")).getText();
				System.out.println("getsubcategory "+getsubcategory);
				Assert.assertEquals(getsubcategory, subcategory[i-2]);
			}
			
			System.out.println("Verified all subcategory");
		
			//Select subcategory
			Common_Methods.toShortWait();
			Common_Methods.selectFromDropDown("//select[@name='subcategoryId']//option", psubcategory);
		}
		
		for(int i=0;i<fvalues.length;i++) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='"+fname+"']/ancestor::div[1]//label[text()='"+fvalues[i]+"']/ancestor::div[1]//input")).click();
		}
		
		Common_Methods.waitForWebElement(driver, productCode);
		productCode.sendKeys(pcode);
		
		Common_Methods.uploadFile(pimgpath, browseImg);
		
		//Common_Methods.toShortWait();
		//driver.findElement(By.xpath("//label[text()='"+vegoption+"']/ancestor::div[1]//input")).click();
		
		if(Subflag) {
			driver.findElement(By.xpath("//label[text()='"+EnableFor+"']/ancestor::div[1]//input")).click();
			AddEnableForSubscription(delfreq,slot);
		}else if(EnableFor.equals("One time Order")){
				System.out.println("Only one time order gets selected");
		}else {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[text()='One time Order']/ancestor::div[1]//input")).click();
			
			//Common_Methods.toShortWait();
			//driver.findElement(By.xpath("//label[text()='"+EnableFor+"']/ancestor::div[1]//input")).click();
			
			AddEnableForSubscription(delfreq, slot);
			}
		
		
		//Common_Methods.waitForWebElement(driver, saveBtn);
		//saveBtn.click();
		
		Common_Methods.waitForWebElement(driver, saveAndNextBtn1);
		saveAndNextBtn1.click();
		
		AddProductInformation(isSub, hsn, subproduct, suppl);
		
		AddMrpSellingPriceForProduct(pname, fvalues, hashmrp, hashselling, hashstock);
		
		/*Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		
		Common_Methods.toShortWait();
		Common_Methods.ScrollUp();
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		
		if(getMsg.equals("Product name must be unique")) {
			Assert.assertEquals(getMsg, "Product name must be unique");
			
			Common_Methods.waitForWebElement(driver, cancelBtn);
			cancelBtn.click();
		}else {
			Common_Methods.toShortWait();
			AddMrpSellingPriceForProduct(pname, fvalues, hashmrp, hashselling, hashstock);			
		}	*/	
	}
	
	public void AddMrpSellingPriceForProduct(String pname,String [] fvalues,Map<String, String> hashmrp,Map<String, String> hashselling,Map<String, String> hashstock) {
		int i=0;
		for(Entry<String, String> mrpvalue : hashmrp.entrySet()) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[contains(text(),'"+pname+"')][contains(.,'"+fvalues[i]+"')]/ancestor::div[1]/following-sibling::div/input[@name='mrp']")).sendKeys(mrpvalue.getValue());
			i++;
		}
		
		int j=0;
		for(Entry<String, String> sellingvalues : hashselling.entrySet()) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[contains(text(),'"+pname+"')][contains(.,'"+fvalues[j]+"')]/ancestor::div[1]/following-sibling::div/input[@name='currentPrice']")).sendKeys(sellingvalues.getValue());
			j++;
		}
		
		int k=0;
		for(Entry<String, String> stockvalues : hashstock.entrySet()) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//label[contains(text(),'"+pname+"')][contains(.,'"+fvalues[k]+"')]/ancestor::div[1]/following-sibling::div/input[@name='productQuantities']")).sendKeys(stockvalues.getValue());
			k++;
		}
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		String getMsg=Common_Methods.getToastMessage();
		System.out.println("getMsg "+getMsg);
		
	}
	
	public void ApplyFilterOnProduct(String pname,String action) {
		Common_Methods.waitForWebElement(driver, productSearch);
		productSearch.clear();
		productSearch.sendKeys(pname);
		
		if(action.equals("Edit")) {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-edit']")).click();
		}else {
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//td[text()='"+pname+"']/ancestor::tr[1]//i[@class='fa fa-trash']")).click();
			
			Common_Methods.toShortWait();
			boolean sta=Common_Methods.isAlertPresent();
			System.out.println("sta "+sta);
			
			//In hashmap if we are not implementing/adding hashcode method & trying to add duplicate key in that duplicate records gets inserted
			
			//a,b,c
			//a   add("a method")
			//b a  add("b method")
			//c b  add("c method")
			if(sta) {
			String getText=driver.switchTo().alert().getText();
			System.out.println("getText "+getText);
			//Product will deactivate for new orders, you need to manage ongoing orders manually, Do you want to continue?
			//driver.switchTo().alert().accept();
			driver.switchTo().alert().dismiss();
			productSearch.clear();
			}else {
			Common_Methods.toShortWait();
			String getMsg=Common_Methods.getToastMessage();
			System.out.println("getMsg "+getMsg);
			productSearch.clear();
			}
		}
		
	}
	
	
	public void EditProduct(String pname,String action,String imgpath) {
		
		ApplyFilterOnProduct(pname, action);
		
		Common_Methods.toElement(browseImg);
		Common_Methods.uploadFile(imgpath,browseImg);
		
		Common_Methods.waitForWebElement(driver,updateBtn);
		updateBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		//Product updated Successfully
	}
	
	
	//Carrot, Ladyfinger, Carrot_1, Brinjal, Beetroot, Bootle Gourd    --before sequence
	//Brinjal, Beetroot, Carrot, Ladyfinger, Bootle Gourd, Carrot_1    --After sequence
	public void ProductSequencing(String [] proseq,String category,String subcat) {
		
		driver.findElement(By.xpath("//button[text()='"+category+"']")).click();
		
		driver.findElement(By.xpath("//button[text()='"+subcat+"']")).click();
		
		List<String> proname = new ArrayList<String>();
		List<String> newseqpro = new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> prolist=driver.findElements(By.xpath("//tbody//tr"));
		
		for(int i=1;i<prolist.size();i++) {
			Common_Methods.toShortWait();
			WebElement ele=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]"));
			Common_Methods.toElement(ele);
			String getcatename=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			proname.add(getcatename);
		}
		System.out.println("catename "+proname);
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(proname.get(3), 3, "Uparrow");
		
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(proname.get(2), 2, "Downarrow");
		
		Common_Methods.toShortWait();
		Common_Methods.DoSequence1(proname.get(4), 2, "Uparrow");
		
		Common_Methods.waitForWebElement(driver, saveBtn);
		saveBtn.click();
		
		Common_Methods.shortWait();
		driver.findElement(By.xpath("//button[text()='"+category+"']")).click();
		
		driver.findElement(By.xpath("//button[text()='"+subcat+"']")).click();
		
		Common_Methods.toShortWait();
		List<WebElement> prolist1=driver.findElements(By.xpath("//tbody//tr"));
		for(int i=1;i<prolist1.size();i++) {
			Common_Methods.toShortWait();
			String getcatename1=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			newseqpro.add(getcatename1);
		}
		System.out.println("newseqpro "+newseqpro);
		for(int i=0;i<proname.size();i++) {
			Assert.assertEquals(newseqpro.get(i),proseq[i],"Getting wrong category sequence");
		}
	}
	
	public boolean DoSequence(String getproname,int length,String action) {
		driver.findElement(By.xpath("//td[text()='"+getproname+"']/ancestor::tr[1]//input")).click();
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
	
	
	public void VerifyErrorMessgaeWhileAddingDuplicateProduct(String pname,String pdesc,List<String> keyword,String pcategory,String psubcategory,
			String hsn,String pcode,String pimgpath,String vegoption,boolean Subflag,String EnableFor,String delfreq,List<String> slot,String fname,
			String [] fvalues,Map<String, String> hashmrp,Map<String, String> hashselling,Map<String, String> hashstock,String [] subcategory,boolean flag,
			String isSub,List<String> subproduct,List<String> suppl) {
		
		//AddNewProduct(pname, pdesc,keyword ,pcategory, psubcategory, hsn, pcode, pimgpath, vegoption,Subflag,EnableFor,delfreq,slot,fname, 
			//	fvalues, hashmrp, hashselling, hashstock,subcategory,flag,isSub,subproduct,suppl);
		
	}
	
	
	public void MultipleDeleteAndDeactivateOfProduct(List<String> pname) {
		Common_Methods.waitForWebElement(driver, multipleDeleteBtn);
		multipleDeleteBtn.click();
		
		for(int i=0;i<pname.size();i++) {
			productSearch.clear();
			Common_Methods.waitForWebElement(driver, productSearch);
			productSearch.sendKeys(pname.get(i));
			
			driver.findElement(By.xpath("//td[text()='"+pname.get(i)+"']/ancestor::tr[1]//input[@name='MultipleDelete']")).click();
		}
		
		Common_Methods.waitForWebElement(driver, deleteBtn);
		deleteBtn.click();
		
		Common_Methods.waitForWebElement(driver, deactivateBtn);
		deactivateBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		
	}
	
	public void VerifyErrorMessageIfNotSelectingProductAtMultipleDelete() {
		Common_Methods.waitForWebElement(driver, multipleDeleteBtn);
		multipleDeleteBtn.click();
		
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		//Assert.assertEquals(getMsg, "");
	}
	
	public void ActivateProduct(List<String> pname) {
		
		for(int i=0;i<pname.size();i++) {
			productSearch.clear();
			Common_Methods.waitForWebElement(driver, productSearch);
			
			productSearch.sendKeys(pname.get(i));
			
			Common_Methods.toShortWait();
			driver.findElement(By.xpath("//td[text()='"+pname.get(i)+"']/ancestor::tr[1]//button[text()='Activate']")).click();
			Common_Methods.toShortWait();
			String getMsg=Common_Methods.getToastMessage();
			//Assert.assertEquals(getMsg, "Product activated");
		}
	}
	
}
