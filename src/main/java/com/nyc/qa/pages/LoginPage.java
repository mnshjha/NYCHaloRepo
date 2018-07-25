package com.nyc.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nyc.qa.pages.HomePage;
import com.nyc.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(name="UserName")
	WebElement username;
	
	@FindBy(name="Password")
	WebElement password;
	
	@FindBy(id="chkBxRmbMe")
	WebElement rememberMe;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//img[contains(@src, '/HaloWeb/assets/img/dhs_logo_blue.png')]")
	WebElement nycLogo;
	
	//Initializing the page Objects:
			public LoginPage(){
				PageFactory.initElements(driver, this);
			}
			
			//Actions:
			public String validateLoginPageTitle(){
				return driver.getTitle();
			}
			
			public boolean validateNYCImage(){
				return nycLogo.isDisplayed();
			}
			
			public HomePage login(String un, String pwd){
				
				username.sendKeys(un);
				password.sendKeys(pwd);
				//loginBtn.click();
				loginBtn.sendKeys(Keys.ENTER);
				
				return new HomePage();
			}
}
