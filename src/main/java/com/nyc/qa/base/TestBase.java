package com.nyc.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.testng.annotations.Parameters;

import com.nyc.qa.util.WebEventListener;
import com.nyc.qa.util.TestUtil;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	private static final Logger logger = Logger.getLogger(TestBase.class.getName()); 
	
	public TestBase(){
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/"
					+ "nyc/qa/config/config.properties");	
			prop.load(ip);
			
			//PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			logger.error("Error while loading properties file: "+e.getMessage());
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	
	
	public static void initialization(String browserName){
		//String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.gecko.driver", "D:/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")){
			
			System.setProperty("webdriver.ie.driver", "D:/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//driver.get(prop.getProperty("url"));
		if(browserName.equalsIgnoreCase("FireFox")){
			driver.get(prop.getProperty("urlFireFox"));
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(prop.getProperty("windowsUsername")+ Keys.TAB.toString()+prop.getProperty("windowsPassword"));
			alert.accept();
			}
		else if(browserName.equalsIgnoreCase("chrome")){
			driver.get(prop.getProperty("urlChrome"));
			}
		
		/*else if(browserName.equalsIgnoreCase("ie")){
			
			driver.get(prop.getProperty("urlFireFox"));
			Alert alert = driver.switchTo().alert();
			alert.sendKeys("manish1185" + Keys.TAB.toString()+"$Nagarro2053");
			alert.accept();
			try{
				Thread.sleep(3000);
				
					Runtime.getRuntime().exec("D:/AutoIT/AutoIT_Scripts/HandleAuthentication.exe");
				
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}*/
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		logger.info("Required drivers initialized by inheriting TestBase: ");
	}
}
