package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.base.TestBase;
import com.app.common.Common_Methods;
import com.app.util.TestUtil;

public class Login_Page extends TestBase{
	

	@FindBy(xpath="//input[@type='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//span[contains(text(),'Next')]/ancestor::div[1]")
	WebElement nextBtn;
	
	//@FindBy(xpath="//button[text()='Sign In']")
	@FindBy(xpath="//span[text()='LOGIN']/ancestor::button[1]")
	WebElement signInBtn;
	
	@FindBy(xpath="//span[text()='Billing']")
	WebElement billingLink;
	
	
	public Login_Page(){
		System.out.println("In Sign In Page Constructor");
		//PageFactory.initElements(driver, this);
	}
	
	
	public void SignIn(String emailid,String pass/*,String BhawanName*/){
		
		try {
			String mainwin=driver.getWindowHandle();
			System.out.println("mainwin "+mainwin);
			signInBtn.click();
			Common_Methods.switchToWindow();
			Common_Methods.toShortWait();
			email.sendKeys(emailid);
			nextBtn.click();
			//Thread.sleep(1000);
			Common_Methods.waitForWebElement(driver, password);
			Common_Methods.toShortWait();
			password.sendKeys(pass);
			nextBtn.click();
			driver.switchTo().window(mainwin);
			//driver.manage().deleteAllCookies();
			/*String getBhavanName=bhavanName.getText();
			System.out.println("getBhavanName "+getBhavanName);
			Assert.assertEquals(getBhavanName, BhawanName);*/
			/*//Thread.sleep(1000);
			boolean status=TestUtil.isElementPresent("//input[@name='ootpPin']");
			System.out.println("status "+status);
			if(status){
			driver.findElement(By.xpath("//input[@name='ootpPin']")).sendKeys("2565469371");
			Thread.sleep(1000);
			nextBtn.click();
			}*/
			//Common_Methods.clickByJs(billingLink);
			//Thread.sleep(10000);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
