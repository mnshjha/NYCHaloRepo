package com.nyc.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nyc.qa.pages.ClientsPage;
import com.nyc.qa.pages.DashboardPage;
import com.nyc.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath = "//*[@id='header']/div/ul/li[3]/a/span")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(@href, '/HaloWeb#/Dashboard/AftercareDashboard/')]")
	WebElement dashboardLink;
	
	@FindBy(xpath="//a[contains(@href, '/HaloWeb#/Client/ListingClient')]")
	WebElement clientsLink;
	
	//Initializing the page Objects:
		public HomePage(){
			PageFactory.initElements(driver, this);
		}
		
		public String verifyHomePageTitle(){
			return driver.getTitle();
		}
		
		public String verifyCorrectUserName(){
			return userNameLabel.getText();
		}
		
		public DashboardPage clickOnDashboardLink(){
			dashboardLink.click();
			return new DashboardPage();
		}
		
		public ClientsPage clickOnClientsLink(){
			clientsLink.click();
			return new ClientsPage();
		}
}
