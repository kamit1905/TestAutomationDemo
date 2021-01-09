package com.app;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.app.common.Common_Methods;

public class Reliance_1 {

	
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
   		driver.get("https://www.jiomart.com/");
   		
   		Common_Methods.toShortWait();
   		driver.findElement(By.xpath("//input[@placeholder='Enter your Pincode']")).sendKeys("411038");
   		
   		Common_Methods.toShortWait();
   		driver.findElement(By.xpath("//button[text()='APPLY']")).click();
   		
   		Common_Methods.shortWait();
   		driver.findElement(By.xpath("//span[text()=' Dals & Pulses ']")).click();
   		
   		List<WebElement> pageList = driver.findElements(By.xpath("//div[@class='pages']//ul[@class='pages-items']//a"));
   		int pageSize=pageList.size();
   		System.out.println("pageSize "+pageSize);
   		
   		for(int j=1;j<=pageSize;j++) {
   			
   			Common_Methods.toShortWait();
   	   		List<WebElement> lis = driver.findElements(By.xpath("//div[@class='row product-list']//div[@class='col-md-3 p-0']"));
   	   		int len=lis.size();
   	   		System.out.println("len "+len);
   	   		
   			for(int i=1;i<=len;i++) {
   	   			
   	   			System.out.println("i "+i);
   	   			if(i==len && j!=pageSize) {
   	   				System.out.println("i in if "+i);
   	   				WebElement nextBtn=driver.findElement(By.xpath("//a[text()='NEXT']"));
   	   				boolean sts=Common_Methods.isElementVisible(nextBtn);
   	   				System.out.println("sts "+sts);
   	   				if(sts) {
   	   					nextBtn.click();
   	   				}else {
   	   					break;
   	   				}
   	   			}
   	   		}

   		}
   		   		
    }
}
