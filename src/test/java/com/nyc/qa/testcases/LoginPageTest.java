package com.nyc.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.nyc.qa.pages.HomePage;
import com.nyc.qa.pages.LoginPage;
import com.nyc.qa.util.AppConstants;
import com.nyc.qa.util.TestUtil;
import com.nyc.qa.base.TestBase;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	String sheetName = "loginData";
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest(){
		super();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser){
		initialization(browser);
		
		loginPage = new LoginPage();
	}
	
	@Test(priority=1,description="This TC will verify Login page title")
	public void loginPageTitleTest(){
		log.info("************************  loginPageTitleTest **************************");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, AppConstants.loginPageTitle);
	}
	
	@Test(priority=2,description="This TC will verify NYC logo on login page")
	public void nycLogoImageTest(){
		boolean flag = loginPage.validateNYCImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3,description="This TC will verify login functionality for HALO app")
	public void loginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getNYCTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4,dataProvider="getNYCTestData",description="This TC will verify login functionality multiple users from Test Data sheet")
	public void loginWithUsersFromTestDataSheetTest(String username, String password){
		loginPage.login(username, password);
		
	}

	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
