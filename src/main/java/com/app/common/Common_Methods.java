package com.app.common;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hc.core5.http2.frame.FrameType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.base.TestBase;
import com.app.constant.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Common_Methods extends TestBase{
	
	public Common_Methods(){
		System.out.println("In common method ctor");
		PageFactory.initElements(driver, this);
	}
	
	public static void RefreshPage() {
		driver.navigate().refresh();
	}
	
	//Explicit wait
	public static WebElement waitForWebElement(WebDriver driver,
			WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			toElement(element);
			wait.until(ExpectedConditions.visibilityOf(element));
			//wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("element is not visible within time");
		}
		return element;
	}
	
	
	public static void switchToWindow(){
	    String mainWindow=driver.getWindowHandle();
		System.out.println("mainwindow "+mainWindow);
		Set<String> set =driver.getWindowHandles();
		System.out.println("window "+set);
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			if(!mainWindow.equals(childWindow)){
			driver.switchTo().window(childWindow);
			System.out.println("childwindow "+childWindow);
			System.out.println(driver.switchTo().window(childWindow).getTitle());
			//driver.close();
			}
	    }
	}
	
	//final,finalize,static,abstract,interface,java8,oops,strings
	
	public static void selectFromDropDown(String xpathv, String contryName) {

		List<WebElement> li = driver.findElements(By.xpath(xpathv));
		int list_size = li.size();
		System.out.println("contryName "+contryName);
		for (int i = 0; i < list_size; i++) {
			String country_value = li.get(i).getText();
			//System.out.println("country_value "+country_value);
			if (contryName.equals(country_value)) {
				//Common_Methods.clickByJs(li.get(i));
				li.get(i).click();
				//System.out.println(li.get(i));
				break;
			}
		}
	}
	
	
	
	
	public static boolean isAlertPresent(){
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//arraylist,linkedlist,vector,stack
	//hashset,linked hashset,treeset,sortedset
	//hashmap,hashtable,linked hashmap,treemap
	
	//exception,checked and unchecked,error,throw,throws
	
	 public static String daysAfter(int sdate){
		    String daysafter = "";
		    Date date = new Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    

		    cal.add(Calendar.DAY_OF_YEAR, +sdate);
		    Date before = cal.getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    daysafter = formatter.format(before);
		    System.out.println("daysafter "+daysafter);
		    return daysafter;
		}
	  
	  
	  public static String daysBefore(int sdate){
		    String daysbefore = "";
		    Date date = new Date();
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    

		    cal.add(Calendar.DAY_OF_YEAR, -sdate);
		    Date before = cal.getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy");
		    daysbefore = formatter.format(before);
		    System.out.println("daysbefore "+daysbefore);
		    return daysbefore;
		}
	  
	  public static void clickByJs(WebElement ele) {
		 try {
			 JavascriptExecutor js=(JavascriptExecutor)driver;
			 Common_Methods.toShortWait();
			  boolean status=ele.isDisplayed();
			  System.out.println("Status in clickbyJs "+status);
			  if(status){
			  js.executeScript("arguments[0].click();",ele);
			  System.out.println("click worked");
			  //js.executeScript("document.getElementById('some id').value='someValue';");
			  }
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	  }
	  
	  public static void toShortWait() {
			try {
				Thread.sleep(Constants.TO_SHORT_WAIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	  
	  public static void shortWait() {
			try {
				Thread.sleep(Constants.SHORT_WAIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static void mediumWait() {
			try {
				Thread.sleep(Constants.MEDIUM_WAIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static void longWait() {
			try {
				Thread.sleep(Constants.LONG_WAIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public static void selectDeliveryBoyFilter(String deliveryboy) {
			try {
				Thread.sleep(2000);
				selectFromDropDown("//select[@name='shopAgentId']//option", deliveryboy);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		//select * into new from name where 1=0;
		//select ename,count(deptno) cnt from emp group by deptno having cnt <2
		public static void selectProduct(String pname) {
			try {
				Thread.sleep(2000);
				selectFromDropDown("//select[@name='productId']//option", pname);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
		public static void filterBasedOnPaymentScreen(String cusname,String deliveryboy) {
			try {
				driver.findElement(By.xpath("//input[@name='searchInput']")).sendKeys(cusname);
				Thread.sleep(2000);
				selectFromDropDown("//select[@name='shopAgentId']//option", deliveryboy);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		public static String getToastMessage() {
			//String getToast=driver.findElement(By.xpath("//div[contains(@class,'toasts-container')]")).getText();
			String getToast=driver.findElement(By.xpath("//div[@class='Toastify__toast-body']")).getText();
			System.out.println("getToast "+getToast);
			return getToast;
		}
		
		public static void navigationClickLink(String linkName) {
			shortWait();
			//driver.findElement(By.xpath("//span[text()='"+linkName+"']")).click();
			//driver.findElement(By.xpath("//a[@href='/"+linkName+"']")).click();
			driver.findElement(By.xpath("//div[text()='"+linkName+"']/ancestor::a[1]")).click();
		}
		
		public static void navigationClickUsingName(String linkName) {
			shortWait();
			//driver.findElement(By.xpath("//span[text()='"+linkName+"']")).click();
			driver.findElement(By.xpath("//li[text()='"+linkName+"']")).click();
			//driver.findElement(By.xpath("//a[@href='/"+linkName+"']")).click();
		}

		public static void navigationInnerLink(String innerLinkName) {
			shortWait();
			//driver.findElement(By.xpath("//span[text()='"+innerLinkName+"']/ancestor::a[1]")).click();
			driver.findElement(By.xpath("//li[text()='"+innerLinkName+"']")).click();
		}
		
		public static void ClickNavigationListItem(String ItemListName) {
			shortWait();
			driver.findElement(By.xpath("//li[text()='"+ItemListName+"']")).click();
		}
		
		public static void ClickNavigation(String navigationname) {
			shortWait();
			driver.findElement(By.xpath("//div[text()='"+navigationname+"']/ancestor::li[1]")).click();
			//driver.findElement(By.xpath("//div[text()='"+navigationname+"']/ancestor::a[1]")).click();		//External
		}
		
		
		
		
		public static boolean isElementVisible(WebElement locator) {

			try {
				return locator.isDisplayed();
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		public static boolean isElementPresent(String locator) {
			if(driver.findElements(By.xpath(locator)).size() > 0) {
				return true;
			}else {
				return false;
			}
		}
		
		public static String selectMonthFromCalender(String monthyear) {
			Common_Methods.toShortWait();
			String getMonth=driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
			System.out.println("getMonth "+getMonth);
			
			if(!getMonth.equals(monthyear)) {
				boolean flag = false;
				 while(flag==false) {
					 System.out.println("entered in while loop");
					 driver.findElement(By.xpath("//button[text()='Previous Month']")).click();
					 String getMonth1=driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
					 if(!getMonth1.equals(monthyear)) {
						  flag=false;
					 }
					 else {
						flag=true;
					 }
				 }
				 return "Other month";
			}
			else {
				System.out.println("Current month");
				return "Current month";
				
			}
			
		}
		
		
		public static String selectMonthFromCalenderForDeliveryBoy(String monthyear) {
			Common_Methods.toShortWait();
			String getMonth=driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
			System.out.println("getMonth "+getMonth);
			
			if(!getMonth.equals(monthyear)) {
				boolean flag = false;
				 while(flag==false) {
					 System.out.println("entered in while loop");
					 driver.findElement(By.xpath("//button[text()='Next Month']")).click();
					 String getMonth1=driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
					 if(!getMonth1.equals(monthyear)) {
						  flag=false;
					 }
					 else {
						flag=true;
					 }
				 }
				 return "Other month";
			}
			else {
				System.out.println("Current month");
				return "Current month";
				
			}
			
		}
		
		
		public static void uploadFile(String fileLocation,WebElement element) {
			try {
				System.out.println("webele "+element);
				Common_Methods.toShortWait();
				Common_Methods.clickByJs(element);
				// StringSelection is a class that can be used for copy and paste
				// operations.
				StringSelection stringSelection = new StringSelection(fileLocation);
				Toolkit.getDefaultToolkit().getSystemClipboard()
						.setContents(stringSelection, null);

				// native key strokes for CTRL, V and ENTER keys
				//https://www.w3schools.com/sql/sql_ref_mysql.asp
				Robot robot = new Robot();
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				Thread.sleep(1000);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				//Common_Methods.toElement(driver.findElement(By.xpath("//div[text()='Add Category Group']")));
				Common_Methods.toShortWait();
				
				boolean ale=Common_Methods.isAlertPresent();
				System.out.println("ale "+ale);
					if(ale) {
						driver.switchTo().alert().accept();
					}
					
				/*String getMsg=Common_Methods.getToastMessage();
				System.out.println("getMsg "+getMsg);
				
				if(getMsg.equals("File should alwys less than 100 KB")) {
					Common_Methods.shortWait();
					WebElement Inputele=driver.findElement(By.xpath("//input[@name='categoryGroupImageUrl']"));
			
					uploadFile1("C:\\Users\\Amit\\Documents\\Images\\Apple_Img.jpg", Inputele);
					
				}else {
				boolean ale=Common_Methods.isAlertPresent();
				System.out.println("ale "+ale);
					if(ale) {
						driver.switchTo().alert().accept();
					}
				}*/
				//driver.findElement(By.xpath("//input[@id='file-submit']")).click();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		
		
		public static void uploadFile1(String fileLocation,WebElement element) {
			try {
				System.out.println("webele "+element);
				//element.click();
				Common_Methods.clickByJs(element);
				//element.click();
				//driver.findElement(By.xpath("//input[@id='file-upload']")).click();
				// StringSelection is a class that can be used for copy and paste
				// operations.
				StringSelection stringSelection = new StringSelection(fileLocation);
				Toolkit.getDefaultToolkit().getSystemClipboard()
						.setContents(stringSelection, null);

				// native key strokes for CTRL, V and ENTER keys
				Robot robot = new Robot();
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				Thread.sleep(1000);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} catch (Exception exp) {
				exp.getCause();
			}
		}
		
		
		public static void SelectShop(String shopname) {
			Common_Methods.shortWait();
			boolean sts1=Common_Methods.isElementPresent("//b[text()='"+shopname+"']");
			if(sts1) {
				driver.findElement(By.xpath("//b[text()='"+shopname+"']")).click();	
			}
			Common_Methods.toShortWait();
			boolean sts=Common_Methods.isElementPresent("//button[text()='Back To Menu']");
			if(sts) {
				driver.findElement(By.xpath("//button[text()='Back To Menu']")).click();
			}
		}
		
		
		
		//creates dynamic xpath by replacing %replaceable% with required data
		public static By get(String xpath,String data)
		{
			String rawXpath=xpath.replaceAll("%replacable%", data);
			return By.xpath(rawXpath);
		}
		
		
		public  static void ClosePdfOpenedInBrowser(WebElement ele){
			try {
				Thread.sleep(2000);
				String parent=driver.getWindowHandle();
				ele.click();
				
				Thread.sleep(2000);
				Set<String>s1=driver.getWindowHandles();
				int windowcount=s1.size();
				System.out.println("windowcount "+windowcount);
				for(String winchild : s1){
					if(!parent.equalsIgnoreCase(winchild)){
						driver.switchTo().window(winchild);
						 System.out.println("winchild "+driver.switchTo().window(winchild).getTitle());
						 driver.close();
					  }
				}
				driver.switchTo().window(parent);
				System.out.println("Parent window "+driver.switchTo().window(parent).getTitle());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//Internal Working of hashmap
		/*1110110 && 0001111 = 1110110
		-First calculate hashcode of key
		-calculate index by using formula  (hashcode) & (n-1)  where n is the number of buckets or size of arraylist
		-place object on calculated index if there is no object present there
		--In case of collision 
		-In this case a node object is found at the index 6 – this is a case of collision.
		-In that case check via hashcode & equal method that if both the keys are same 
		-If keys are same then replace the value with current value
		-otherwise connect this node with previous node via linked list & both are stored at index 6
		*/
		
		public static int CalculateDateDifference(String fdate,String tdate){
			int days = 0;
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");
			   try{
			       Date dateBefore = myFormat.parse(fdate);
			       Date dateAfter = myFormat.parse(tdate);
			       long difference = dateAfter.getTime() - dateBefore.getTime();
			       float daysBetween = (difference / (1000*60*60*24));
			       days=(int) daysBetween;
		               /* You can also convert the milliseconds to days using this method
		                * float daysBetween = 
		                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
		                */
			       System.out.println("Number of Days between dates: "+days);
			   }
			   catch(Exception e){
				   System.out.println(e.getMessage());
			   }
			return days;
			   	
		}
		
		public static int GetOverDueCharges(){
			Common_Methods.toShortWait();
			String getDue=driver.findElement(By.xpath("//input[@name='overDueChargePerQuantity']")).getAttribute("value");
			System.out.println("getDue "+getDue);
			return Integer.parseInt(getDue);
		}
		
		
		//Scrolls till element view
		public static void toElement(WebElement element)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		
		//Scroll up
		public static void ScrollUp() {
			//((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-1000)");
		}
		
		
		public static String GetFirstSelectedOptionFromDropDown(String xpath) {
			  Select sel=new Select(driver.findElement(By.xpath(xpath)));
			  WebElement option=sel.getFirstSelectedOption();
			  String getdefaultitem=option.getText();
			  System.out.println("defaultitem "+getdefaultitem);
			  return getdefaultitem;
		}
		
		public static void SendKeysUsingJavascriptExecutor(WebElement ele,String val) {
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='"+val+"';", ele);
		}
		
		public static void ClearTextUsingJavascriptExecutor(WebElement element) {
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value ='';", element);
		}
		
		
		public static void ScrollDownComplete() {
			//Reporter.log("Scrolling down to load complete Page content..");
			JavascriptExecutor js=(JavascriptExecutor)driver;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Scroll down completly to load all elements...");

			for (int i = 0; i < 10; i++) {

				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			}
		}
		
		public static void ScrollForGrofers() {
			Common_Methods.toShortWait();
			for(int j=0;j<=30;j++) {
				JavascriptExecutor js=(JavascriptExecutor)driver;
				
				Common_Methods.shortWait();
				js.executeScript("window.scrollBy(0,1000)");
				
				
				boolean sts=Common_Methods.isElementPresent("//div[text()='Payment Options']");
				System.out.println("sts "+sts);
				
				/*if(sts) {
					break;
					}*/
				System.out.println("j "+j);
				}
		}
		
		public static void LaunchBrowser() {
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
			driver.manage().window().maximize();
			Common_Methods.toShortWait();
		}
			
		//parsetInt(str)
		public static boolean DoSequence1(String getcatename,int length,String action) {
			
			WebElement ele=driver.findElement(By.xpath("//td[text()='"+getcatename+"']/ancestor::tr[1]//input"));
			Common_Methods.toElement(ele);
			Common_Methods.toShortWait();
			ele.sendKeys(String.valueOf(length));
			Common_Methods.toShortWait();
			ele.sendKeys(Keys.TAB);
			
			return true;	
		}
	
		
		public static boolean GetSelectedValueFromDropdown(String xpath) {
			
			 Select sel=new Select(driver.findElement(By.xpath(xpath)));
			  WebElement option=sel.getFirstSelectedOption();
			  String getdefaultitem=option.getText();
			  System.out.println("defaultitem "+getdefaultitem);
			  
			  if(getdefaultitem.equals("Yes")) {
				  return true;
			  }else {
				  return false;
			  }
			  
		}
		//basic concept,programs,spring boot interview qustion,diff spring spring boot,jpa,sql
	//Hope every one is good
}
