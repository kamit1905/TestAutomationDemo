package com.app.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.SysexMessage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	static FileInputStream fip =null;
	
	
	public TestBase() {
		try {
			//System.out.println("In base Constructor");
			prop = new Properties();
		fip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/app/config/config.properties");
			prop.load(fip);
			
		} catch (FileNotFoundException e) {
			System.out.println("In correct file Path"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		String browserName = prop.getProperty("browser");
		System.out.println("browser "+browserName);
		if (browserName.equals("chrome")) {
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			//capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);			
			//System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
			
			//WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			//System.setProperty("webdriver.edge.driver","C:\\Windows\\System32\\MicrosoftWebDriver.exe");
			//driver = new EdgeDriver();
			
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     //implicit wait
		driver.get(prop.getProperty("url"));
		//driver.get("https://gmail.com");
		
	}
	
	
	public void initialize1() {
		String browsername=prop.getProperty("browser");
		System.out.println("browsername "+browsername);
		
		if(browsername.equals("firefox")) {
		  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
	 	  driver=new FirefoxDriver();
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	public static String getDate(){
		/*DateFormat customformat=new SimpleDateFormat("YYYY-MM-dd_HH:mm:ss");   
		Date date=new Date();
		System.out.println(customformat.format(date));
		return customformat.format(date);*/
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		System.out.println(sdf.format(date));
		return sdf.format(date);
		
		
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		   return dtf.format(now);*/
	}
	
	

}
