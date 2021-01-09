package com.app.GetData;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

//Issue Billing Module Application Hang

public class Grofers_Getdata extends TestBase{
	
	//WebDriver driver;
	XSSFWorkbook workbook = new XSSFWorkbook(); 
	  
    // Create a blank sheet 
    XSSFSheet sheet = workbook.createSheet("Order_details");
    
    @Test(priority=1)
	public void test() throws InterruptedException {
	
    	Common_Methods.LaunchBrowser();
		driver.get("https://grofers.com/");
		
		Common_Methods.shortWait();
		
		WebElement ele1=driver.findElement(By.xpath("//img[@alt='Mumbai']"));
		Common_Methods.clickByJs(ele1);
		
		Common_Methods.mediumWait();
		WebElement ele=driver.findElement(By.xpath("//img[@alt='Grocery & Staples']"));
		Common_Methods.clickByJs(ele);
		
		Common_Methods.ScrollForGrofers();
		
		List<WebElement> lisize=driver.findElements(By.xpath("//div[@class='products products--grid']//a"));
		int len=lisize.size();
		System.out.println("len "+len);
		
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>(); 
        data.put(0, new Object[]{"ProductName","Pqty","Pmrp","Pselling"});
		
		for(int i=1;i<=len;i++) {
			String getPqty="";String getSelling="";String getMrp="";
			
			String getPname=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']")).getText();
			
			boolean getSts=driver.findElements(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[@class='plp-product__variant']//div[@class='plp-product__variant-units']")).size() > 0;
			//boolean getSts=Common_Methods.isElementVisible(elestatus);
			System.out.println("getSts "+getSts);
			if(getSts) {
				Common_Methods.toShortWait();
				getPqty=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[@class='plp-product__variant']//div[@class='plp-product__variant-units']")).getText();
				
				Common_Methods.toShortWait();
				getSelling=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//span[@class='plp-product__price--new']")).getText();
				
				Common_Methods.toShortWait();
				boolean mrpSts=driver.findElements(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[contains(@class,'plp-product__price--old')]")).size() > 0;
				if(mrpSts) {
					getMrp=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[contains(@class,'plp-product__price--old')]")).getText();
				}else {
					getMrp="0";
				}
				
				
			}else {
				Common_Methods.toShortWait();
				getPqty=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[@class='plp-product__quantity']//span")).getText();
				
				Common_Methods.toShortWait();
				getSelling=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//span[@class='plp-product__price--new']")).getText();
				
				Common_Methods.toShortWait();
				boolean mrpSts=driver.findElements(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[contains(@class,'plp-product__price--old')]")).size() > 0;
				if(mrpSts) {
					getMrp=driver.findElement(By.xpath("//div[@class='products products--grid']//a["+i+"]//div[@class='plp-product__name--box']/ancestor::a[1]//div[contains(@class,'plp-product__price--old')]")).getText();
				}else {
					getMrp="0";
				}
			}
			
			
			data.put(i, new Object[]{getPname,getPqty,getMrp,getSelling});
			
			/*System.out.println("getPname "+getPname);
			System.out.println("getQty "+getPqty);
			System.out.println("getMrp "+getMrp);
			System.out.println("getSelling "+getSelling);*/
			
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
		            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/app/testdata/checkwebscrapper_grofres.xlsx")); 
		            workbook.write(out); 
		            out.close(); 
		            System.out.println("Order written successfully in csv"); 
		        } 
		        catch (Exception e) { 
		            e.printStackTrace(); 
		        }
		}
		
    
}
