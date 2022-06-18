package com.app.AW;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Login_Page;
import com.app.pages.Product_Page;
import com.app.util.TestUtil;
import com.app.wiseaccounts.pages.Expense_Type;
import com.app.wiseaccounts.pages.Expenses_Page;

//Added new line here
@Listeners(com.app.listner.ListnerDemo.class)
public class DemoTypes extends TestBase{
	
	Login_Page loginpage;
	Expense_Type expensetype;
	Expenses_Page expensespage;
	
	@Test(priority=1)
	public void set() {
		initialize();
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.SelectShop("autotest140");
		Common_Methods.navigationClickLink("Expenses");
		//Common_Methods.navigationClickUsingName("Configuration");
		//Common_Methods.navigationInnerLink("Expense Type");
	}
	
	/*@Test(priority=3)
	public void addexpensetype() {
		expensetype = new Expense_Type();
		expensetype.AddExpenseType("Travel", "Daily", 100);
	}
	
	@Test(priority=4)
	public void editexpensetype() {
		//Common_Methods.navigationInnerLink("Expense Type");	
		expensetype = new Expense_Type();
		expensetype.EditExpenseType("Travel1", 200, "Travel1");
	}*/
	
	/*@Test(priority=3)
	public void addexpenses() {
		expensespage = new Expenses_Page();
		expensespage.AddExpenses("November 2019", "day-11", "Mumbai-Pune", "Travel1", "remark added");
	}
	
	@Test(priority=4)
	public void editexpenses() {
		Common_Methods.navigationClickLink("Expenses");
		expensespage = new Expenses_Page();
		expensespage.EditExpenses("Mumbai-Pune",500, "Solapue-Pune");
	}*/
	
	
	//Add Expense Types
	/*@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> testData = TestUtil.getDataFromExcelForExpenseType();
		return testData.iterator();
	}
	
	@Test(priority=3,dataProvider="getTestData")
	public void addexpensetype(String expname,String freq,String defamt) {
		expensetype = new Expense_Type();
		expensetype.AddExpenseType(expname,freq, defamt);
	}*/
	
	
	//Add Expenses
	/*@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> testData = TestUtil.getDataFromExcelForExpenses();
		return testData.iterator();
	}
	
	@Test(priority=3,dataProvider="getTestData")
	public void addexpense(String year,String day,String expname,String exptype,String remark) {
		expensespage = new Expenses_Page();
		expensespage.AddExpenses(year,day,expname,exptype,remark);
	}*/
	
	//Verify Total Amt of All Expenses
	@Test(priority=3)
	public void verifytotalexpenseamount() {
		expensespage = new Expenses_Page();
		expensespage.calculateTotalExpenses(84861);
	}

}
