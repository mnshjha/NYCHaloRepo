package com.nyc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nyc.qa.base.TestBase;
import com.nyc.qa.pages.DashboardPage;
import com.nyc.qa.pages.HomePage;
import com.nyc.qa.pages.LoginPage;

public class DashboardPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	DashboardPage dashboardPage;
	
	public DashboardPageTest(){
		super();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser){
		initialization(browser);		
		dashboardPage = new DashboardPage();		
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboardPage = homePage.clickOnDashboardLink();
	}
	
	@Test(priority=1,description="This TC will verify Shelter Entrants count on Dashboard page")
	public void verifyShelterEntrantsCount(){
		String shelterEntrantsCount = dashboardPage.verifyShelterEntrantsCount();
		Assert.assertEquals(shelterEntrantsCount, "0", "Shelter Entrants Count did not match");
	}
	
	@Test(priority=2,description="This TC will verify Shelter Exits count on Dashboard page")
	public void verifyShelterExitsCountTest(){
		String shelterExitsCount = dashboardPage.verifyShelterExitsCount();
		Assert.assertEquals(shelterExitsCount, "0", "Shelter Exits Count did not match");
	}
	
	@Test(priority=3,description="This TC will verify click on Details Entrant button")
	public void verifyclickOnDetailsEntrantButtonTest(){		
		dashboardPage.clickOnDetailsEntrantButton();
		
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Test(priority=4,description="This TC will verify click on Details Exits button")
	public void verifyclickOnDetailsExitsButtonTest(){		
		dashboardPage.clickOnDetailsExitsButton();
		
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
