package com.app.util;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;

import com.app.base.TestBase;
import com.app.common.Xls_Reader;

public class TestUtil extends TestBase{

	//public static Workbook book;
	//public static Sheet sheet;
	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getDataFromExcelForScanning(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testdata/Barcode.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Barcode_Details"); rowNum++) {
			String barcodeno1=reader.getCellData("Barcode_Details", "BarcodeNo", rowNum);
			String yearmonth=reader.getCellData("Barcode_Details", "Yr_Mo", rowNum);
			String day=reader.getCellData("Barcode_Details", "Day", rowNum);
			
			System.out.println("barcodeno1 "+barcodeno1);
			System.out.println("yearmonth "+yearmonth);
			System.out.println("day "+day);
			
			Object ob1[] = {barcodeno1,yearmonth,day};
			myData.add(ob1);
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromExcelForScanning1(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			//reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testData/Barcode_New_1_6_10.xlsx");
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testdata1/Barcode_For_Customer12_New_18_02_2.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Barcode_Details"); rowNum++) {

			String barcodeno1=reader.getCellData("Barcode_Details", "BarcodeNo", rowNum);
			String barcodeno2=reader.getCellData("Barcode_Details", "BarcodeNo1", rowNum);
			String yearmonth=reader.getCellData("Barcode_Details", "Yr_Mo", rowNum);
			String day=reader.getCellData("Barcode_Details", "Day", rowNum);
			String dateflag=reader.getCellData("Barcode_Details", "Date_Flag", rowNum);
			
			Object ob1[] = {barcodeno1,barcodeno2,yearmonth,day,dateflag};
			myData.add(ob1);
		}
		return myData;
	}
	
	//Fast scanning purpose
	public static ArrayList<Object[]> getDataFromExcelForScanning2(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testdata1/Barcode_For_Customer_Test5_New1.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("count "+reader.getRowCount("Barcode_Details"));
		for (int rowNum = 2; rowNum <= reader.getRowCount("Barcode_Details"); rowNum++) {

			String barcodeno1=reader.getCellData("Barcode_Details", "BarcodeNo", rowNum);
			String barcodeno2=reader.getCellData("Barcode_Details", "BarcodeNo1", rowNum);
			String yearmonth=reader.getCellData("Barcode_Details", "Yr_Mo", rowNum);
			String day=reader.getCellData("Barcode_Details", "Day", rowNum);
			String dateflag=reader.getCellData("Barcode_Details", "Date_Flag", rowNum);
			
			Object ob1[] = {barcodeno1,barcodeno2,yearmonth,day,dateflag};
			myData.add(ob1);
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromExcelForDeliveryBoy(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testData/Delivery_Boy_Sheet.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Delivery_Boy_Details"); rowNum++) {

			String deliveryboyname=reader.getCellData("Delivery_Boy_Details", "DeliveryBoyName", rowNum);
			String deliveryboymob=reader.getCellData("Delivery_Boy_Details", "DeliveryBoyMob", rowNum);
			String deliveryboyemail=reader.getCellData("Delivery_Boy_Details", "DeliveryBoyEmail", rowNum);
			String deliveryboyprofile=reader.getCellData("Delivery_Boy_Details", "DeliveryProfile", rowNum);
			
			Object ob1[] = {deliveryboyname,deliveryboymob,deliveryboyemail,deliveryboyprofile};
			myData.add(ob1);
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromExcelForCustomerAndRecurring(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testdata1/Customer_Sheet_1_New.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Customer_Details"); rowNum++) {

			String customername=reader.getCellData("Customer_Details", "CustomerName", rowNum);
			String customermob=reader.getCellData("Customer_Details", "CustomerMob", rowNum);
			String customeremail=reader.getCellData("Customer_Details", "CustomerEmail", rowNum);
			String customeradd=reader.getCellData("Customer_Details", "CustomerAdd", rowNum);
			String customercity=reader.getCellData("Customer_Details", "CustomerCity", rowNum);
			String customerpaymentcycle=reader.getCellData("Customer_Details", "CustomerPaymentCycle", rowNum);
			String customerstatus=reader.getCellData("Customer_Details", "CustomerStatus", rowNum);
			String productname=reader.getCellData("Customer_Details", "ProductName", rowNum);
			String deliveryboy=reader.getCellData("Customer_Details", "DeliveryBoy", rowNum);
			String quantity=reader.getCellData("Customer_Details", "Quantity", rowNum);
			String deliverytime=reader.getCellData("Customer_Details", "DeliveryTime", rowNum);
			String fdate=reader.getCellData("Customer_Details", "FromDate", rowNum);
			String monthyear=reader.getCellData("Customer_Details", "MonthYear", rowNum);
			String returnin=reader.getCellData("Customer_Details", "ReturnIn", rowNum);
			
			
			Object ob1[] = {customername,customermob,customeremail,customeradd,customercity,customerpaymentcycle,customerstatus,productname,deliveryboy,quantity,deliverytime,fdate,monthyear,returnin};
			myData.add(ob1);
		}
		System.out.println(myData);
		return myData;
		
	}
	
	public static ArrayList<Object[]> getDataFromExcelForExpenseType(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testData/Expense_Type.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Expense_Types"); rowNum++) {

			String expensename=reader.getCellData("Expense_Types", "ExpenseName", rowNum);
			String frequency=reader.getCellData("Expense_Types", "Frequency", rowNum);
			String defamt=reader.getCellData("Expense_Types", "DefAmt", rowNum);
			
			Object ob1[] = {expensename,frequency,defamt};
			myData.add(ob1);
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromExcelForExpenses(){

		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testData/Add_Expenses.xlsx");
			System.out.println("getting xlsx file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum = 2; rowNum <= reader.getRowCount("Expenses"); rowNum++) {

			String year=reader.getCellData("Expenses", "Year", rowNum);
			String day=reader.getCellData("Expenses", "Day", rowNum);
			String expname=reader.getCellData("Expenses", "ExpenseName", rowNum);
			String exptype=reader.getCellData("Expenses", "ExpenseType", rowNum);
			String remark=reader.getCellData("Expenses", "Remark", rowNum);
			
			Object ob1[] = {year,day,expname,exptype,remark};
			myData.add(ob1);
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromExcelForProduct(){
		
		//String pname,String pdesc,String pcategory,String psubcategory,String hsn,String pcode,String pimgpath,String vegoption,boolean hassubcategoryflag,String subcate1,String subcate2,String subcate3,String subcate4,String subcate5,
		
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/app/testdata1/default_product_1.xlsx");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for(int rowNum=2; rowNum <= reader.getRowCount("Product"); rowNum++) {
			
			String pname=reader.getCellData("Product", "pname", rowNum);
			String pdesc=reader.getCellData("Product", "pdesc", rowNum);
			String pcategory=reader.getCellData("Product", "pcategory", rowNum);
			String psubcategory=reader.getCellData("Product", "psubcategory", rowNum);
			String hsn=reader.getCellData("Product", "hsn", rowNum);
			String pcode=reader.getCellData("Product", "pcode", rowNum);
			String pimgpath=reader.getCellData("Product", "pimgpath", rowNum);
			String vegoption=reader.getCellData("Product", "vegoption", rowNum);
			String hassubcategoryflag=reader.getCellData("Product", "hassubcategoryflag", rowNum);
			String subcate1=reader.getCellData("Product", "subcate1", rowNum);
			String subcate2=reader.getCellData("Product", "subcate2", rowNum);
			String subcate3=reader.getCellData("Product", "subcate3", rowNum);
			String subcate4=reader.getCellData("Product", "subcate4", rowNum);
			String subcate5=reader.getCellData("Product", "subcate5", rowNum);
			
			Object ob1[] = {pname,pdesc,pcategory,psubcategory,hsn,pcode,pimgpath,vegoption,hassubcategoryflag,
					subcate1,subcate2,subcate3,subcate4,subcate5};
			myData.add(ob1);
			
		}
		System.out.println("myData "+myData);
		
		return myData;
	}
	
	
}
