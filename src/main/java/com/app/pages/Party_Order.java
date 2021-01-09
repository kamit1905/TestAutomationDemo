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

public class Party_Order extends TestBase{
	
	@FindBy(xpath="//input[@name='CustomerName']")
	WebElement cusname;
	
	@FindBy(xpath="//textarea[@name='address']")
	WebElement address;
	
	@FindBy(xpath="//input[@name='mobile no']")
	WebElement mobNo;
	
	@FindBy(xpath="//input[@name='site manager']")
	WebElement siteManager;
	
	@FindBy(xpath="//textarea[@name='site address']")
	WebElement siteAdd;
	
	@FindBy(xpath="//input[@name='site mobile no']")
	WebElement siteMobNo;
	
	@FindBy(xpath="//input[@name='Quantity']")
	WebElement productQty;
	
	@FindBy(xpath="//input[@name='fromDate']")
	WebElement fdate;
	
	@FindBy(xpath="//input[@id='timeInput']")
	WebElement deliverytime;
	
	@FindBy(xpath="//button[text()='Add']")
	WebElement addBtn;
	
	@FindBy(xpath="//button[text()='New Order']")
	WebElement newOrder;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//button[text()='Dispatched']")
	WebElement dispatchBtn;
	
	@FindBy(xpath="//button[text()='Deliverd']")
	WebElement deliveredBtn;
	
	@FindBy(xpath="//input[@name='searchInput']")
	WebElement searchInput;
	
	public Party_Order(){
		System.out.println("In Party Order Ctor");
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void VerifyDetailsOfPartyOrderList(String [] date,String [] prolist,String [] pricelist){
		
		List<WebElement> partylist=driver.findElements(By.xpath("//tbody//tr"));
		
		for(int i=1;i<=partylist.size();i++){
			String getdate=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
			String getpname=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[4]")).getText();
			String getpqty=driver.findElement(By.xpath("//tbody//tr["+i+"]//td[5]")).getText();
			
			Assert.assertEquals(getdate, date[i-1],"Getting wrong date");
			
			Assert.assertEquals(getpname, prolist[i-1],"Getting wrong product name");
			
			Assert.assertEquals(getpqty, pricelist[i-1],"Getting wrong product qty");
		}
		
	}
	public void ApplyFilterOnPartyOrderList(String partyname){
		Common_Methods.waitForWebElement(driver, searchInput);
		searchInput.clear();
		searchInput.sendKeys(partyname);
		
		String getpartyname=driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText();
		System.out.println("getpartyname "+getpartyname);
		Assert.assertTrue(getpartyname.contains(partyname), "Getting wrong party order list");
	}
	
	
	public void AddOrderInParty2(String cname,String cusadd,String mobno,String sitemanager,String siteadd,String sitemob,String caterer){
		
		Common_Methods.waitForWebElement(driver, newOrder);
		newOrder.click();
		
		cusname.sendKeys(cname);
		Common_Methods.shortWait();
		
		List<WebElement> chec=driver.findElements(By.xpath("//input[@value='"+cname+"']/ancestor::div[2]//li"));
		System.out.println("listsize "+chec.size());
		if(chec.size()>0){
			
			List<String> cusname1=new ArrayList<String>();
			for(int i=1;i<=cusname1.size();i++){
				String getname=driver.findElement(By.xpath("//input[@value='"+cname+"']/ancestor::div[2]//li["+i+"]")).getText();
				cusname1.add(getname);
			}
			
			
			Common_Methods.waitForWebElement(driver, cusname);
			driver.findElement(By.xpath("//input[@value='"+cname+"']/ancestor::div[2]//li")).click();
			
			Common_Methods.waitForWebElement(driver,saveBtn);
			saveBtn.click();
			
			Common_Methods.toShortWait();
			String getmsg=Common_Methods.getToastMessage();
			Assert.assertEquals(getmsg, "Success");
			
		}else{
			Common_Methods.waitForWebElement(driver, address);
			address.sendKeys(cusadd);
			
			Common_Methods.waitForWebElement(driver, mobNo);
			mobNo.sendKeys(mobno);
			
			Common_Methods.waitForWebElement(driver,siteManager);
			siteManager.sendKeys(sitemanager);
			
			Common_Methods.waitForWebElement(driver, siteAdd);
			siteAdd.sendKeys(siteadd);
			
			Common_Methods.waitForWebElement(driver, siteMobNo);
			siteMobNo.sendKeys(sitemob);
			
			Common_Methods.shortWait();
			driver.findElement(By.xpath("//label[text()='Caterer']/ancestor::div[2]//label[text()='"+caterer+"']/ancestor::div[1]//input[@type='radio']")).click();
			
			Common_Methods.waitForWebElement(driver,saveBtn);
			saveBtn.click();
			
			Common_Methods.toShortWait();
			String getmsg=Common_Methods.getToastMessage();
			Assert.assertEquals(getmsg, "Success");
			
		}
		
		
	}
	
	
	public void AddOrderInPartyAndVerifyErrorMsgIfNotEnteredMandatoryField1(String cname){
		Common_Methods.waitForWebElement(driver, newOrder);
		newOrder.click();
		
		Common_Methods.waitForWebElement(driver, cusname);
		cusname.sendKeys(cname);
		
		
		Common_Methods.waitForWebElement(driver,saveBtn);
		saveBtn.click();
		
		Common_Methods.toShortWait();
		String getmsg=Common_Methods.getToastMessage();
		//System.out.println("getmsg "+getmsg);
		Assert.assertEquals(getmsg, "Please fill mandatory fields");
		
	}
	
	public void AddOrderInPartyAndVerifyErrorMsgIfNotEnteredMandatoryField(String cname){
		
		try {
			Common_Methods.waitForWebElement(driver, newOrder);
			newOrder.click();
			
			boolean status=false;
			
			cusname.sendKeys(cname);
			Common_Methods.shortWait();
			String checkname=driver.findElement(By.xpath("//input[@value='"+cname+"']/ancestor::div[2]//li")).getText();
			System.out.println("checkname "+checkname);
			if(checkname.equalsIgnoreCase(cname)){
				status=true;
			}
			System.out.println("status "+status);
			if(status){
				Common_Methods.waitForWebElement(driver, cusname);
				driver.findElement(By.xpath("//input[@value='"+cname+"']/ancestor::div[2]//li")).click();
				//cusname.sendKeys(cname);
				
				
				Common_Methods.waitForWebElement(driver,saveBtn);
				saveBtn.click();
				
				Common_Methods.toShortWait();
				String getmsg=Common_Methods.getToastMessage();
				//System.out.println("getmsg "+getmsg);
				Assert.assertEquals(getmsg, "Please fill mandatory fields");
			}else{
				Common_Methods.waitForWebElement(driver, cusname);
				cusname.sendKeys(cname);
				
				
				Common_Methods.waitForWebElement(driver,saveBtn);
				saveBtn.click();
				
				Common_Methods.toShortWait();
				String getmsg=Common_Methods.getToastMessage();
				//System.out.println("getmsg "+getmsg);
				Assert.assertEquals(getmsg, "Please fill mandatory fields");
			}
			
			//Select order in party
			//SelectOrder(pname, qty, date, monthyear, deltime, dboy);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
}
	
	public void SelectOrder(String pname,int qty,String date,String year,String deltime,String dboy,List<String> pro,List<String> delboy){
		try {
			
			//Verify product list
			List<WebElement> prod=driver.findElements(By.xpath("//select[@name='productId']//option"));
			System.out.println(prod.size());
			for(int i=1;i<prod.size();i++){
				String getname=driver.findElement(By.xpath("//select[@name='productId']//option["+i+"]")).getText();
				System.out.println("getname "+getname);
				Assert.assertTrue(getname.contains(pro.get(i-1)), "Getting wrong product name");
			}
			
			//Verify delivery boy list
			List<WebElement> delboylist=driver.findElements(By.xpath("//select[@name='shopAgentId']//option"));
			System.out.println(delboylist.size());
			for(int i=1;i<delboylist.size();i++){
				String getdelboyname=driver.findElement(By.xpath("//select[@name='shopAgentId']//option["+i+"]")).getText();
				System.out.println("getdelboyname "+getdelboyname);
				Assert.assertTrue(getdelboyname.contains(delboy.get(i-1)), "Getting wrong product name");
			}
			
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@name='productId']//option",pname);
			
			Common_Methods.waitForWebElement(driver, productQty);
			productQty.clear();
			productQty.sendKeys(String.valueOf(qty));
			
			fdate.click();
			String getsta=Common_Methods.selectMonthFromCalender(year);
			if(getsta.equals("Current month")) {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'datepicker__day--today')][text()='"+date+"']")).click();
			}else {
				driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']/ancestor::div[2]//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')][text()='"+date+"']")).click();
			}
			
			Common_Methods.waitForWebElement(driver, deliverytime);
			deliverytime.clear();
			deliverytime.sendKeys(deltime);
			
			Common_Methods.toShortWait();
			Common_Methods.selectFromDropDown("//select[@name='shopAgentId']//option", dboy);
			
			Common_Methods.waitForWebElement(driver, addBtn);
			addBtn.click();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void SelectOrderAndVerifyErrorMsgIfNotEnteredMandatoryField1(String pname){
		try {
			Common_Methods.shortWait();
			Common_Methods.selectFromDropDown("//select[@name='productId']//option",pname);
			
			Common_Methods.waitForWebElement(driver, addBtn);
			addBtn.click();
			
			Common_Methods.toShortWait();
			String getmsg=Common_Methods.getToastMessage();
			//System.out.println("getmsg "+getmsg);
			Assert.assertEquals(getmsg, "Please fill the all mandatory fields");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void VerifyProductInlist(String [] pname){
		List<String> partyorderlist=new ArrayList<String>();
		
		Common_Methods.toShortWait();
		List<WebElement> li=driver.findElements(By.xpath("//div[text()='Order Product List']/ancestor::div[2]//table//tr"));
		int len=li.size();
		
		//Get product & Add prduct in list
		for(int i=1;i<=len-1;i++){
			String getpname=driver.findElement(By.xpath("//div[text()='Order Product List']/ancestor::div[2]//table//tr["+i+"]//td[2]")).getText();
			partyorderlist.add(getpname);
		}
		
		//Verify products in party order with actual 
		for(int i=0;i<partyorderlist.size()-1;i++){
			Assert.assertTrue(partyorderlist.get(i).contains(pname[i]),"Getting wrong prduct list in party order");
		}
	}
	
	
    public void ChangeStatusOfProductInPartyOrder1(String pname){
		

		String getStatus=driver.findElement(By.xpath("//div[text()='Order Product List']/ancestor::div[2]//table//tr[1]//td[2]//div[contains(text(),'"+pname+"')]/ancestor::tr[1]//u")).getText();
		
		if(getStatus.equals("Approve")){
			driver.findElement(By.xpath("//div[text()='Order Product List']/ancestor::div[2]//table//tr[1]//td[2]//div[contains(text(),'"+pname+"')]/ancestor::tr[1]//u")).click();
			Common_Methods.waitForWebElement(driver, dispatchBtn);
			dispatchBtn.click();
			
			Common_Methods.toShortWait();
			String getmsg=Common_Methods.getToastMessage();
			System.out.println("getmsg "+getmsg);
		}else{
			driver.findElement(By.xpath("//div[text()='Order Product List']/ancestor::div[2]//table//tr[1]//td[2]//div[contains(text(),'"+pname+"')]/ancestor::tr[1]//u")).click();
			Common_Methods.waitForWebElement(driver, deliveredBtn);
			deliveredBtn.click();
			
			Common_Methods.toShortWait();
			String getmsg=Common_Methods.getToastMessage();
			System.out.println("getmsg "+getmsg);
		}
	}
	
}
