package com.app;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.app.common.Common_Methods;

public class GetBigBasketData {
	
	
	
	WebDriver driver;
	XSSFWorkbook workbook = new XSSFWorkbook(); 
	  
    // Create a blank sheet 
    XSSFSheet sheet = workbook.createSheet("Order_details");
    
    @Test(priority=1)
	public void test() throws InterruptedException {
		
		
        
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		Common_Methods.toShortWait();
		driver.get("https://www.bigbasket.com/");
		//driver.get("https://www.bigbasket.com/pc/foodgrains-oil-masala/edible-oils-ghee");
		
		Common_Methods.shortWait();
		//WebElement eleDailyVegetables=driver.findElement(By.xpath("//img[@src='//www.bigbasket.com/media/customPage/c9309c26-4fb9-4233-b2b3-1d0a11af6c92/11735fde-fa2f-465e-b9f8-ecbc74b8d2d4/e66ed185-997e-400d-8c87-9ca190be56bf/T2_All_Veggies_DT_2_480x360_25june.jpg']"));	  //Daily Vegetables		
		//eleDailyVegetables.click();
		
		WebElement eleBiscuitCookies=driver.findElement(By.xpath("//img[@src='//www.bigbasket.com/media/customPage/c9309c26-4fb9-4233-b2b3-1d0a11af6c92/11735fde-fa2f-465e-b9f8-ecbc74b8d2d4/ba3825a0-052c-4793-a04c-66e83f3c06aa/2007034_biscuits-cookies_184_T2.jpg']"));
		eleBiscuitCookies.click();
		
		//WebElement eleFreshFruits=driver.findElement(By.xpath("//img[@src='//www.bigbasket.com/media/customPage/c9309c26-4fb9-4233-b2b3-1d0a11af6c92/11735fde-fa2f-465e-b9f8-ecbc74b8d2d4/e66ed185-997e-400d-8c87-9ca190be56bf/T2_All_fruits_DT_1_480x360_25june.jpg']"));	  //Fresh Fruits
		//eleFreshFruits.click();
		//Common_Methods.toElement(ele);
		
		
		Common_Methods.toShortWait();
		for(int j=0;j<20;j++) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			Common_Methods.shortWait();
			js.executeScript("window.scrollBy(0,1000)");
			
			WebElement elee=driver.findElement(By.xpath("//button[text()='Show More']"));
			Common_Methods.toShortWait();
			boolean sts=Common_Methods.isElementVisible(elee);
			if(sts) {
				//Common_Methods.clickByJs(elee);
				boolean status=elee.isDisplayed();
				  System.out.println("Status in clickbyJs "+status);
				  if(status){
				  js.executeScript("arguments[0].click();",elee);
				  System.out.println("click worked");
				}
			System.out.println("j "+j);
			}else if(!sts && j==5) {
				break;
			}
		}
			
		
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>(); 
	        data.put(0, new Object[]{"ProductName","Pqty","Pmrp","Pselling"});
	
	        //Common_Methods.toShortWait();
	        //((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	       
			List<WebElement> li=driver.findElements(By.xpath("//div[@class='items']//div[@qa='product']"));
			int l=li.size();
			System.out.println("outerlist"+l);
			
			for(int i=1;i<=l;i++){
				String getMrp="";String qtyDesc;
				Common_Methods.toShortWait();
				String getPname=driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a")).getText();
				
				Common_Methods.toShortWait();
				String getQty=driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[1]//span[@ng-bind='vm.selectedProduct.w']")).getText();
				String getQty1=driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[1]//span[@ng-bind='vm.selectedProduct.pack_desc']")).getText();
				qtyDesc=getQty+getQty1;
				//WebElement ele1 = driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[2]//div[@qa='price']//span[@class='mp-price ng-scope']//span[@class='ng-binding']"));
				boolean mrpSts=driver.findElements(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[2]//div[@qa='price']//span[@class='mp-price ng-scope']//span[@class='ng-binding']")).size() > 0;
				System.out.println("mrpSts "+mrpSts);		
				if(mrpSts) {
				Common_Methods.toShortWait();	
				getMrp=driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[2]//div[@qa='price']//span[@class='mp-price ng-scope']//span[@class='ng-binding']")).getText();
				}else {
					getMrp="0";
				}
				
				Common_Methods.toShortWait();
				String 	getSelling=driver.findElement(By.xpath("//div[@class='items']//div[@qa='product']["+i+"]//div[@qa='product_name']//a/ancestor::div[1]/following-sibling::div[2]//div[@qa='price']//span[@class='discnt-price']//span[@class='ng-binding']")).getText();
				
				data.put(i, new Object[]{getPname,qtyDesc,getMrp,getSelling});
				
				System.out.println("getPname "+getPname);
				System.out.println("getQty "+qtyDesc);
				System.out.println("getMrp "+getMrp);
				System.out.println("getSelling "+getSelling);
			}
		
			System.out.println("data "+data.values());
			 Set<Integer> keyset = data.keySet(); 
			 System.out.println("keyset "+keyset);
		        int rownum = 0; 
		        for (Integer key : keyset) { 
		            // this creates a new row in the sheet 
		            Row row = sheet.createRow(rownum++); 
		            Object[] objArr = data.get(key);
		            //System.out.println("objarray "+objArr.toString());
		            //System.out.println("objarr "+objArr[key]);
		            int cellnum = 0; 
		            for (Object obj : objArr) { 
		                // this line creates a cell in the next column of that row 
		                Cell cell = row.createCell(cellnum++); 
		                if (obj instanceof String) 
		                    cell.setCellValue((String)obj); 
		                else if (obj instanceof Integer) 
		                    cell.setCellValue((Integer)obj); 
		            } 
		        } 
		        try { 
		            // this Writes the workbook gfgcontribute 
		            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/app/testdata/checkwebscrapper.xlsx")); 
		            workbook.write(out); 
		            out.close(); 
		            System.out.println("Order written successfully in csv"); 
		        } 
		        catch (Exception e) { 
		            e.printStackTrace(); 
		        } 
	}
	/*@Test(priority=2)
	public void tearDown(){
		driver.quit();
	}*/
    
}
