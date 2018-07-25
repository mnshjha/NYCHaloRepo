package com.nyc.qa.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nyc.qa.base.TestBase;
import com.nyc.qa.pages.ClientsPage;
import com.nyc.qa.pages.DashboardPage;
import com.nyc.qa.pages.HomePage;
import com.nyc.qa.pages.LoginPage;
import com.nyc.qa.util.AppConstants;
//import com.nyc.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	DashboardPage dashboardPage;
	ClientsPage clientsPage;
	
	public HomePageTest(){
		super();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser){
		initialization(browser);		
		dashboardPage = new DashboardPage();
		clientsPage = new ClientsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1,description="This TC will verify Home page title")
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, AppConstants.homePageTitle, "Home page title did not match");
	}
	
	@Test(priority=2,description="This TC will verify logged in user on Home page")
	public void verifyUserNameTest(){
		String userNameLabel = homePage.verifyCorrectUserName();
		System.out.println("Welcom user label: " + userNameLabel);
		Assert.assertEquals(userNameLabel, AppConstants.userNameLabel, "User name did not match");
		//Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3,description="This TC will verify click on Dashboard link")
	public void verifyDashboardLinkTest(){		
		dashboardPage = homePage.clickOnDashboardLink();
		//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Test(priority=4,description="This TC will verify click on Clients link")
	public void verifyClientsLinkTest(){		
		clientsPage = homePage.clickOnClientsLink();
		
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
