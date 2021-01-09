package com.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.common.Common_Methods;

public class Frame_Assignment extends TestBase{
	
	@Test(priority=1)
	public void swichfram() throws MalformedURLException, IOException, InterruptedException {
		
		initialize();
		
		//driver.get("https://the-internet.herokuapp.com/");
		driver.navigate().to("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Thread.sleep(-2000);
		
		//https://the-internet.herokuapp.com/    frame example
		//https://rahulshettyacademy.com/AutomationPractice/
		
		WebElement ele=driver.findElement(By.xpath("//a[text()='Frames']"));
		Common_Methods.toElement(ele);
		
		ele.click();
		//div[]
		driver.findElement(By.xpath("//a[text()='Nested Frames']")).click();
		
		//System.out.println(driver.findElements(By.ta));
		
		//driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));  //switch to outer frame
		driver.switchTo().frame("frame-top");    //using frame name switch to outer frame
		
		//driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));   //switch to inner frame 
		driver.switchTo().frame("frame-middle");
		
		String getText =driver.findElement(By.xpath("//div[@id='content']")).getText();
		System.out.println("gettext "+getText);
		
		driver.switchTo().defaultContent();
		driver.quit();
		
		//what  is HTML DOM
		//format of HTML DOM   xml
		//which one is loaded first element or DOM   -DOM will be loaded first
		//22.00
		//what is javascript executor in sel
		//normal click & javascript click(in which case ur using 
		
		System.out.println(driver.getTitle());
		
		/*driver.switchTo().parentFrame();
		HttpURLConnection huc = null;
		int responseCode=200;
		
		List<WebElement> ele =driver.findElements(By.xpath("//tbody//a"));
		
		Iterator<WebElement> itr=ele.iterator();
		
		while(itr.hasNext()) {
			String url=itr.next().getAttribute("href");
			
			if(url.isEmpty() || url==null) {
				System.out.println("URL is not configured");
			}
			
			//huc=(HttpURLConnection)(new URL(url).openConnection());
			huc = (HttpURLConnection)(new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			
			responseCode=huc.getResponseCode();
			if(responseCode>400)
				System.out.println("Url "+url +"is broken");
			else {
				System.out.println("Url "+url +"is not broken");
			}
		}*/
		
		
		
	}
	
	

}
