package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.pages.Application_Setting_Page;
import com.app.pages.Category_Group_Page;
import com.app.pages.Category_Page;
import com.app.pages.Delivery_Boy;
import com.app.pages.Delivery_Boy_Holiday;
import com.app.pages.Delivery_Route;
import com.app.pages.Feature_Page;
import com.app.pages.Login_Page;
import com.app.pages.Manual_One_Time_Order;
import com.app.pages.Notes_Page;
import com.app.pages.Party_Order;
import com.app.pages.Product_Page;
import com.app.pages.Product_Price_Update;
import com.app.pages.Product_Setup_Page;
import com.app.pages.Shop_Setup_Page;
import com.app.pages.Subcategory_Page;
import com.app.pages.Subscription_Page;
import com.app.pages.Tax_Configuration_Page;



public class Demo123 extends TestBase{
	
	Login_Page loginpage;
	Party_Order partyorder;
	Product_Page productpage;
	Delivery_Boy deliveryboy;
	Feature_Page featurepage;
	Notes_Page notespage;
	Category_Page categorypage;
	Subcategory_Page subcategorypage;
	Shop_Setup_Page shopsetuppage;
	Product_Setup_Page productsetuppage;
	Category_Group_Page categorygrouppage;
	Product_Price_Update productpriceupdate;
	Application_Setting_Page applicationsettingpage;
	Tax_Configuration_Page taxconfigurationpage;
	Delivery_Route deliveryroute;
	Delivery_Boy_Holiday deliveryboyholiday;
	Subscription_Page subscriptionpage;
	Manual_One_Time_Order manualonetimeorder;
	
	
	//@BeforeSuite
	@Test(priority=1)
	public void set() {
		initialize();
		loginpage=new Login_Page();
		featurepage = new Feature_Page();
		notespage = new Notes_Page();
		categorypage = new Category_Page();
		subcategorypage = new Subcategory_Page();
		shopsetuppage = new Shop_Setup_Page();
		productsetuppage = new Product_Setup_Page();
		categorygrouppage = new Category_Group_Page();
		productpriceupdate = new Product_Price_Update();
		applicationsettingpage = new Application_Setting_Page();
		taxconfigurationpage = new Tax_Configuration_Page();
		deliveryroute = new Delivery_Route();
		deliveryboyholiday = new Delivery_Boy_Holiday();
		subscriptionpage = new Subscription_Page();
		manualonetimeorder = new Manual_One_Time_Order();
		
	}
	
	@Test(priority=2)
	public void login() {
		loginpage=new Login_Page();
		loginpage.SignIn(prop.getProperty("email"), prop.getProperty("password"));
		Common_Methods.shortWait();
		//Common_Methods.SelectShop("Gupta");
		Common_Methods.SelectShop("Raju Milk");
	}
	
	@Test(priority=3)
	public void addproduct() {
		Map<String, String> keyvalues= new LinkedHashMap<String, String>();
		keyvalues.put("mrp","80");
		keyvalues.put("mrp1","40");
		keyvalues.put("mrp2","20");
		
		Map<String, String> sellingvalues = new LinkedHashMap<String, String>();
		sellingvalues.put("selling", "72");
		sellingvalues.put("selling1", "36");
		sellingvalues.put("selling2", "18");
		
		Map<String, String> stockvalues = new LinkedHashMap<String, String>();
		stockvalues.put("stock", "1");
		stockvalues.put("stock1", "1");
		stockvalues.put("stock2", "1");
	
	
	String featureName= "Size";
	String [] featureValues= {"1 Kg","500 Gm","250 Gm"};
	String [] subcategory= {"Fresh Vegetable","Less Fresh Vegetable","More Fresh Vegetable"};
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("ProductSetup");
		
		List<String> subproduct = new ArrayList<String>();
		subproduct.add("Butter Ghee");subproduct.add("Product Purcahse2");subproduct.add("Sub Product2");
		
		List<String> timeslot=new ArrayList<String>();
		
		List<String> suppl = new ArrayList<String>();
		suppl.add("Supplier1");suppl.add("Supplier2");
		//productsetuppage.productedit("Brinjal", "Edit",subproduct);
		//List<String> pname=new ArrayList<String>()
		
		Common_Methods.shortWait();
		productsetuppage.AddNewProduct("Kim", "description", "One Time Product", "Fresh Vegetable", "0", "Kim", 
				"C:\\Users\\Amit\\Documents\\Images\\Ladyfinger_Img.jpg", "Veg",false,"One time Order","",timeslot,featureName,featureValues,keyvalues,sellingvalues,stockvalues,subcategory,true,
				"No",subproduct,suppl);
	}
	
	
	/*@Test(priority=3)
	public void VerifyEntry() {
		
		Common_Methods.ClickNavigationListItem("Report");
		Common_Methods.ClickNavigation("Distribution Report");
		
		List<String> pname=new ArrayList<String>();
		pname.add("Berlin Product");pname.add("Tokoyo Fruit");
		manualonetimeorder.SelectAndGenerateDistributionReport("January 2021","day-4");
		manualonetimeorder.VerifyEntryInDistributionReport("TestCustomer", pname);
	}*/
	
	/*@Test(priority=3)
	public void placeorder() {
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Subscription");
		//Common_Methods.ClickNavigation("Orders");
		
		driver.findElement(By.xpath("//b[text()='One Time Order']/ancestor::button[1]")).click();
		
		List<String> pname=new ArrayList<String>();
		pname.add("Brinjal");pname.add("Spinach");
		List<String> feature=new ArrayList<String>();
		feature.add("500 Gm");
		manualonetimeorder.SelectCustomerAndDeliveryBoy("Test_7", "Delivery20","January 2021","day-1");
		manualonetimeorder.AddManualOneTimeOrder(pname, feature, 2);
		
		List<String> pname1=new ArrayList<String>();
		pname1.add("Brinjal");
		Common_Methods.shortWait();
		manualonetimeorder.ChangeStatusAgainstOrder("Test_7", "Pending", "January 2021", "day-1", "Ready",pname1,4);
		Common_Methods.toShortWait();
		manualonetimeorder.ChangeStatusAgainstOrder("Test_7", "Pending", "January 2021", "day-1", "Dispatch",pname1,4);
		Common_Methods.toShortWait();
		manualonetimeorder.ChangeStatusAgainstOrder("Test_7", "Pending", "January 2021", "day-1", "Update",pname1,3);
		
	}*/
	
	/*@Test(priority=3)
	public void addsubscription() {
		List<String> slots = new ArrayList<String>();
		slots.add("Morning");
		//slots.add("Evening");
		
		List<String> slotsforalternate = new ArrayList<String>();
		slotsforalternate.add("Morning");
		
		List<String> holiday = new ArrayList<String>();
		holiday.add("Mon");holiday.add("Thu");holiday.add("Sat");
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Subscription");
		
		List<String> week1h = new ArrayList<String>();
		week1h.add("Sun");week1h.add("Tue");
		
		List<String> week2h = new ArrayList<String>();
		week2h.add("Mon");week2h.add("Wed");
		
		List<String> week3h = new ArrayList<String>();
		week3h.add("Fri");week3h.add("Sat");
		
		List<String> week4h = new ArrayList<String>();
		week4h.add("Sun");week4h.add("Wed");
		subscriptionpage.AddIndividualSubscription("Kiran", "Achalpur Del Group", "Ghee Pro", "December 2020", "day-23", "Till Cancelation","December 2020", "day-30", 
			    slots, "Daily", holiday,"",week1h,week2h,week3h,week4h);
		
		Common_Methods.shortWait();
		subscriptionpage.AddIndividualSubscription("Kiran", "Achalpur Del Group", "Sugar Milk", "December 2020", "day-23", "Till Cancelation","December 2020", "day-30", 
				slotsforalternate, "Alternate day", holiday,"",week1h,week2h,week3h,week4h);
		
		Common_Methods.shortWait();
		List<String> holi = new ArrayList<String>();
		subscriptionpage.AddIndividualSubscription("Kiran", "Achalpur Del Group", "Goat Milk.", "December 2020", "day-23", "Till Cancelation","December 2020", "day-30", 
				slotsforalternate, "Daily", holi,"Customize",week1h,week2h,week3h,week4h);
	}*/
	
	  //@Test(priority=4)
	public void verifysubscriptionlist() {
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Subscription");
		
		subscriptionpage.GoToLinkedSubscription("test_21");
		
		List<String> holilist=new ArrayList<String>();
		String holiday="Sa,S";
		subscriptionpage.VerifySubscriptionAgainstCustomer("Sugar Milk", "25-Nov-2020", "27-Nov-2020",holilist,holiday);
		
		String holiday1="S,Sa";
		subscriptionpage.VerifySubscriptionAgainstCustomer("Ghee Pro", "24-Nov-2020", "10-Dec-2020",holilist,holiday1);

		List<String> holilist1=new ArrayList<String>();
		holilist1.add("Week1:- S,F,");holilist1.add("Week2:- M,F,");holilist1.add("Week3:- M,F,");holilist1.add("Week4:- M,F,");
		subscriptionpage.VerifySubscriptionAgainstCustomer("Goat Milk.", "24-Nov-2020", "Till Cancellation",holilist1,holiday);
		
		String holiday2="M,F";
		subscriptionpage.VerifySubscriptionAgainstCustomer("Panner ", "25-Nov-2020", "Till Cancellation",holilist,holiday2);
	}
	
	  //@Test(priority=5)
	public void stopsubscription() {
		//Common_Methods.toShortWait();
		//Common_Methods.ClickNavigationListItem("Configuration");
		//Common_Methods.ClickNavigation("Subscription");
		//subscriptionpage.GoToLinkedSubscription("Test_21");
		
		subscriptionpage.StopSubscription("Ghee Pro", "Remark added", "day-31", "December 2020");
		
	}
	
	/*@Test(priority=6)
	public void update() {
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Subscription");
		
		subscriptionpage.GoToLinkedSubscription("Test_7");
		
		List<String> slots = new ArrayList<String>();
		slots.add("08 To 09");
		
		subscriptionpage.UpdateSubscription("Butter Chas", "day-31", "December 2020", "day-2", "January 2021", "Select Date", slots, "Daily", "3");
	}*/
	
	/*@Test(priority=3)
	public void adddeliveryboyholiday() {
		
		List<String> delroute = new ArrayList<String>();
		delroute.add("Airoli-Rabale");delroute.add("Ghansoli-Turbhe");
		
		List<String> delgroup = new ArrayList<String>();
		delgroup.add("Airoli Sector1");delgroup.add("Airoli Sector5");
		delgroup.add("Rabale Sector1");delgroup.add("Rabale Sector5");
		
		List<String> delgroup1 = new ArrayList<String>();
		delgroup1.add("Ghansoli Sector1");delgroup1.add("Ghansoli Sector5");
		delgroup1.add("Turbhe Sector1");
		
		List<String> delboylist = new ArrayList<String>();
		delboylist.add("Delivery2");delboylist.add("Delivery2");
		delboylist.add("Delivery2");delboylist.add("Delivery2");
		
		List<String> delboylist1 = new ArrayList<String>();
		delboylist1.add("Delivery 3");delboylist1.add("Delivery 3");
		delboylist1.add("Delivery 3");delboylist1.add("Delivery 3");
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigation("Delivey Boy Holiday");
		
		deliveryboyholiday.AddDeliveryBoyHoliday("Delivery1", "November 2020", "day-12", "November 2020", "day-12", "One day leave", 
				delroute, delgroup, delgroup1, delboylist, delboylist1);
	}*/
	
	/*@Test(priority=3)
	public void deliveryroute() {
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Delivery");
		Common_Methods.ClickNavigation("Delivery route");
		
		List<String> delgroup1 = new ArrayList<String>();
		delgroup1.add("Achalpur Group");delgroup1.add("Vashi Group");delgroup1.add("Airoli Group");
		
		List<String> delgroup2 = new ArrayList<String>();
		delgroup2.add("Vashi Sector1");delgroup2.add("Vashi Sector5");delgroup2.add("Vashi Sector10");
		String [] serno= {"1","2","3"};
		List<String> delgroup = new ArrayList<String>();
		delgroup.add("Airoli Group");delgroup.add("Vashi Group");delgroup.add("Achalpur Group");
		
		deliveryroute.VerifyErrorMessageIfMissMandatoryField("Airoli");
		
		Common_Methods.shortWait();
		deliveryroute.EditDeliveryRoute("Airoli Vashi", "9730", delgroup1,serno);
		
		Common_Methods.shortWait();
		String [] serno1= {"2","3","1"};
		deliveryroute.AddDeliveryRoute("Vashi", "2000", delgroup2,serno1);
	}*/
	
	/*@Test(priority=3)
	public void addcategrp() {		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Assign Category Group");
		
		List<String> catelist = new ArrayList<String>();
		catelist.add("Exotic Vegetable");catelist.add("VEGETABLE");
		categorygrouppage.DeselectCategoryFromCategorygroup("Fresh Vegetables", catelist);
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category");
		List<String> category = new ArrayList<String>();
		category.add("Test_Category_Vegetables");category.add("Fruits");
		categorygrouppage.VerifyCategoryUnderCategoryGroups("Fresh Vegetables",category);
			
		//categorygrouppage.EditCategoryGroup("Fresh Vegetable","Fresh Vegetables" ,"description");
		//categorygrouppage.VerifyErrorMessageIfNotEnteredMandatoryField("Nothing");
		
	}*/
	
	
	
	/*@Test(priority=4)
	public void AddKeywordInProduct() {
		List<String> catelist = new ArrayList<String>();
		catelist.add("Fruits");catelist.add("Exotic Vegetable");catelist.add("Test_Category_Vegetables");
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category");


		List<String> taxlabel = new ArrayList<String>();
		taxlabel.add("GST 5%");
		taxlabel.add("GST 10%");
		
		List<String> taxdrop = new ArrayList<String>();
		taxdrop.add("Less Than");
		taxdrop.add("Greater Than");
		
		List<String> taxvalue = new ArrayList<String>();
		taxvalue.add("100");
		taxvalue.add("100");
		categorypage.EditCategory("GROCERY", "Edit", taxlabel, taxdrop, taxvalue);
	}*/
	
	/*@Test(priority=4)
	public void Assignandverify() {
		List<String> catelist = new ArrayList<String>();
		catelist.add("Fruits");catelist.add("Exotic Vegetable");catelist.add("Test_Category_Vegetables");
		
		List<String> category = new ArrayList<String>();
		category.add("Fruits");category.add("Exotic Vegetable");category.add("Test_Category_Vegetables");
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Assign Category Group");
		categorygrouppage.AssignCategoryGroup("Fresh Vegetables",catelist);
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category");
		categorygrouppage.VerifyCategoryUnderCategoryGroups("Fresh Vegetables",category);
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category Group");
		categorygrouppage.AddCategoryGroup("Snacks Item", "veg description", "C:\\Users\\Amit\\Documents\\Images\\Apple_Img.jpg");
	}*/
	
	/*@Test(priority=3)
	public void featureandnotes() {	
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Notes");
		notespage.AddNotes("Less vegetable");
		
		Common_Methods.mediumWait();
		notespage.NotesSearch("Less vegetable", "Delete");
		
		Common_Methods.mediumWait();
		notespage.VerifyErrorMessageOnNotes();
	
		String [] categrpseq = {"Atta","Masale Items", "Fresh Fruits","Safety Kits", "Fresh Vegetables"};
		categorygrouppage.DoSequencingOfCategoryGroup(categrpseq);
		
		Common_Methods.toShortWait();
		List<String> ActualCateGrp=categorygrouppage.GetAllCategoryGroup();
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category");
		categorygrouppage.VerifyCategoryGroupSeqInCategory(ActualCateGrp);
	}
	
	@Test(priority=4)
	public void Feature() {
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Feature");
		
		featurepage.AddFeature("Test_Feature", "1 Packet", "2 Packet", "3 Packet");
		Common_Methods.mediumWait();
		
		featurepage.SearchFeature("Test_Feature", "Delete");
		Common_Methods.mediumWait();
		
		featurepage.AddFeatureAndVerifyErrorMessage("Featurea");
		
	}*/
	
	/*@Test(priority=3)
	public void addc() {
		String [] feature= {"PACKET SIZE","Bunch"};
		String [] cateseq = {"Test_Category_Fruits", "Fruits","VEGETABLE", "Test_Category_Vegetables", "Category1"};
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("Category");	
		
		//Sequencing category
		categorypage.VerifyErrorMessageWithoutSelectingUpAndDownArrow();
		Common_Methods.toShortWait();
		String [] cateseq = {"Fruits","VEGETABLE", "Exotic Vegetable", "Test_Category_Vegetables"};
		categorypage.DoSequencingOfCategory1(cateseq,"Fresh Vegetables");				//before[Exotic Vegetable,Fruits,Test_Category_Vegetable,VEGETABLE]
		
		//Verify erro message while deleting category if it is associated with subcategory or product	
		Common_Methods.toShortWait();
		categorypage.ApplyFilterOnCategory("Test_Category_Vegetables", "Delete");
		categorypage.VerifyErrorMessageIfItIsAssociatedWithSubcategoryProduct();
		
		//Verify error message when we try to add duplicate category	
		Common_Methods.toShortWait();
		categorypage.VerifyDuplicateErrorMessageWhileAddingCategory("Vegetables1", "description", "Kg", "Yes", feature, "C:\\Users\\Amit\\Documents\\Images\\Vegetable_Basket.jpg");
		
	
		//Add category
		Common_Methods.toShortWait();
		categorypage.AddCategory("Test_Category_Fruits23", "description", "Kg", "Yes", feature, "C:\\Users\\Amit\\Documents\\Images\\Vegetable_Basket.jpg");
	
		//Delete Category
		Common_Methods.shortWait();
		categorypage.ApplyFilterOnCategory("Test_Category_Fruits23", "Delete");
		
	}*/
	
	/*@Test(priority=4)
	public void addsubcategory() {
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("SubCategory");	
		
		//Add subcategory 
		subcategorypage.AddSubcategory("Test sub", "description", "Vegetable", "C:\\Users\\Amit\\Documents\\Images\\Vegetable_Basket.jpg");
		
		//Verify error message if not entered any mandatory field while adding
		Common_Methods.shortWait();
		subcategorypage.VerifyErrorMessageIfNotEnteredMandatoryField("subcategory test");
		
		//Verify error message when we try to add duplicate subcategory
		Common_Methods.toShortWait();
		subcategorypage.VerifyDuplicateErrorMessageWhileAddingSubcategory("Test sub", "description", "Vegetable", "C:\\Users\\Amit\\Documents\\Images\\Vegetable_Basket.jpg");
		
		//Verify deleted message after deletion
		Common_Methods.shortWait();
		subcategorypage.ApplyFilterOnSubcategory("Test sub", "Delete");
		Common_Methods.toShortWait();
		String getMsg=Common_Methods.getToastMessage();
		Assert.assertEquals(getMsg, "Subcategory Deleted");
		//String [] subcateseq= {};
		//subcategorypage.DoSequencingOfSubcategory(subcateseq);
	}*/
	
	/*@Test(priority=3)	
	public void orderp(){
		Common_Methods.SelectShop("Shree Water");                                                        
		
		Common_Methods.navigationClickUsingName("Configuration");
		Common_Methods.navigationInnerLink("Product");
		List<String> pro=productpage.ReturnProductName();
		Common_Methods.shortWait();
		Common_Methods.navigationInnerLink("Delivery Boy");
		List<String> delboy=deliveryboy.GetDeliveryBoy(); 
		
		Common_Methods.navigationClickLink("Order_list");  
		partyorder.AddOrderInParty2("Test_Pra", "Sector2 navi mumbai", "9895556874", "Aditya", "Sector 19 kharghar mumbai", "7854556234", "Yes");
		partyorder.SelectOrder("Check1", 10, "14", "February 2020", "0230", "Ritesh",pro,delboy);
		partyorder.SelectOrder("Foregin_Exchange_Pro", 5, "14", "February 2020", "1230", "Ritesh",pro,delboy);
		partyorder.SelectOrder("Mineral_Test", 8, "14", "February 2020", "0830", "Ritesh",pro,delboy);
		
		String [] pname={"Check1","Foregin_Exchange_Pro","Mineral_Test"};
		partyorder.VerifyProductInlist(pname);
		
		Common_Methods.mediumWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Check1");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Foregin_Exchange_Pro");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Mineral_Test");
		
		Common_Methods.mediumWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Check1");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Foregin_Exchange_Pro");
		Common_Methods.toShortWait();
		partyorder.ChangeStatusOfProductInPartyOrder1("Mineral_Test");
		
		Common_Methods.mediumWait();
		Common_Methods.navigationClickLink("Order_list");
		partyorder.AddOrderInParty2("Test_Pra", "Sector2 navi mumbai", "9895556874", "Aditya", "Sector 19 kharghar mumbai", "7854556234", "Yes");
		partyorder.SelectOrderAndVerifyErrorMsgIfNotEnteredMandatoryField1("Test_Youth");				//Verify error message if not entered mandatory field in product
		
		Common_Methods.shortWait();
		Common_Methods.navigationClickLink("Order_list");
		partyorder.AddOrderInPartyAndVerifyErrorMsgIfNotEnteredMandatoryField1("Quarty");	//Verify error messsage if not entered mandatory field in customer details
		
		String [] date={"04-Feb-2020","04-Feb-2020","13-Feb-2020","13-Feb-2020","13-Feb-2020","14-Feb-2020","14-Feb-2020","14-Feb-2020"};
		String [] prolist={"Can_Return12","Bisleri Jar","Sun_Aqua","Bisleri Jar","Test_Youth","Check1","Foregin_Exchange_Pro","Mineral_Test"};
		String [] proqty={"3","5","10","5","8","10","5","8"};
		
		Common_Methods.navigationClickLink("Order_list");  
		driver.findElement(By.xpath("//button[text()='Past']")).click();
		partyorder.ApplyFilterOnPartyOrderList("Test_Pra");
		partyorder.VerifyDetailsOfPartyOrderList(date, prolist, proqty);
		
		
		categorypage.VerifyErrorMessageWithoutSelectingUpAndDownArrow();
		Common_Methods.toShortWait();
		categorypage.DoSequencingOfCategory(cateseq);
		
	}*/
	
	/*@Test(priority=9)
	public void addvendor() {
		
		shopsetuppage.VerifyErrorMessageIfNotEnteredMandatoryField("amit");
		
		driver.findElement(By.xpath("//span[text()='LOGIN']/ancestor::button[1]")).click();
		
		shopsetuppage.CreateNewShop("Amit_Ice_Cream", "Ice-Cream", "Amit", "Sector2 navi mumbai", "Achalpur", 
				"413005", "9933356789","C:\\Users\\Amit\\Documents\\Images\\Resto_Images.jpg", "", "99999");
		
	}*/
	
	/*@Test(priority=10)
	public void AddEditAndDeleteProduct() {
		
		 Map<String, String> keyvalues= new LinkedHashMap<String, String>();
			keyvalues.put("mrp","80");
			keyvalues.put("mrp1","40");
			keyvalues.put("mrp2","20");
			
			Map<String, String> sellingvalues = new LinkedHashMap<String, String>();
			sellingvalues.put("selling", "72");
			sellingvalues.put("selling1", "36");
			sellingvalues.put("selling2", "18");
			
			Map<String, String> stockvalues = new LinkedHashMap<String, String>();
			stockvalues.put("stock", "1");
			stockvalues.put("stock1", "1");
			stockvalues.put("stock2", "1");
		
		
		String featureName= "PACKET SIZE";
		String [] featureValues= {"1 KG","500 GMS","250 GMS"};
		
		
		Common_Methods.ClickNavigationListItem("Configuration");
		Common_Methods.ClickNavigationListItem("Product");
		Common_Methods.ClickNavigation("ProductSetup");	
		
		//Verified subcategory while addding product
		String [] subcategory= {"Fresh Vegetable","Less Fresh Vegetable","More Fresh Vegetable"};
		productsetuppage.AddNewProduct("Bavachi", "description", "Test_Category_Vegetables", "Fresh Vegetable", "0", "bavachi7", 
				"C:\\Users\\Amit\\Documents\\Images\\Ladyfinger_Img.jpg", "Veg",featureName,featureValues,keyvalues,sellingvalues,stockvalues,subcategory,true);

		//Verified categoey which having has Subcateogry having 'No' value 
		Common_Methods.shortWait();
		productsetuppage.AddNewProduct("Dragon", "description", "VEGETABLE", "Fresh Vegetable", "0", "Dragon", 
				"C:\\Users\\Amit\\Documents\\Images\\Ladyfinger_Img.jpg", "Veg",featureName,featureValues,keyvalues,sellingvalues,stockvalues,subcategory,true);
		
		//Verify error message while adding duplicate product
		Common_Methods.shortWait();
		productsetuppage.VerifyErrorMessgaeWhileAddingDuplicateProduct("Capsicum", "description", "Test_Category_Vegetables", "Fresh Vegetable", "0", "capsi", 
				"C:\\Users\\Amit\\Documents\\Images\\Ladyfinger_Img.jpg", "Veg",featureName,featureValues,keyvalues,sellingvalues,stockvalues,subcategory,true);
		
		
		//Common_Methods.shortWait();
		//productsetuppage.EditProduct("Capsicum", "Edit", "C:\\Users\\Amit\\Documents\\Images\\Carrot_Img.jpg");
		
		//Common_Methods.toShortWait();
		//productsetuppage.ApplyFilterOnProduct("Carrot_1", "Delete");
		
	}*/
	

}



















/*partyorder= new Party_Order();
productpage= new Product_Page();
deliveryboy= new Delivery_Boy();*/

//productsetuppage.ApplyFilterOnProduct("Potato", "Delete");

//String [] proseq = {"Brinjal","Beetroot","Carrot","Ladyfinger","Bootle Gourd","Carrot_1"};
		//productsetuppage.ProductSequencing(proseq,"Test_Category_Vegetables","Fresh Vegetable");