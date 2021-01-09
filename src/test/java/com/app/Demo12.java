package com.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.app.base.TestBase;
import com.app.pages.Login_Page;

public class Demo12 extends TestBase{
	
	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver","C:\\Windows\\System32\\MicrosoftWebDriver.exe");
		
		WebDriver driver = new EdgeDriver();
		driver.navigate().to("http://www.google.com");
		driver.manage().window().maximize();
	}
}
