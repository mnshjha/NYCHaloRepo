package com.nyc.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nyc.qa.base.TestBase;

public class DashboardPage extends TestBase{
	
	@FindBy(xpath = ".//*[@ng-show='!EntrantsCompare']//div[@class='stats-number ng-binding']")
	WebElement entrantTileCount;
	
	@FindBy(xpath = ".//*[@ng-show='!ExitsCompare']//div[@class='stats-number ng-binding']")
	WebElement exitsTileCount;
	
	@FindBy(xpath = ".//*[@id='content']/div[1]/div/div/div/div[1]/div[2]/div[1]/div/div/div[5]/div/div/a/b")
	WebElement detailsEntrantButton;
	
	@FindBy(xpath = ".//*[@id='content']/div[1]/div/div/div/div[1]/div[2]/div[2]/div/div/div[5]/div/div/a/b")
	WebElement detailsExitsButton;
	
	//Initializing the page objects:
		public DashboardPage(){
			PageFactory.initElements(driver, this);
		}
		
		public String verifyShelterEntrantsCount(){
			return entrantTileCount.getText();
		}
		
		public String verifyShelterExitsCount(){
			return exitsTileCount.getText();
		}
		
		public void clickOnDetailsEntrantButton(){
			detailsEntrantButton.click();
			
		}
		
		public void clickOnDetailsExitsButton(){
			detailsExitsButton.click();
			
		}
		
}
