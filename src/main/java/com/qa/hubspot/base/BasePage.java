package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager opsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/*
	 * this method is to initialize the browser based on browse value from config.properites 
	 */
	
	public WebDriver init_driver(String browser)
	{
		System.out.println("browser value is :"+browser);
		highlight=prop.getProperty("highlight");
		opsManager=new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
			    inti_remoteDriver("chrome");
				
			}
			else {
			tlDriver.set(new ChromeDriver(opsManager.getChromeOptions()));
			}
			
		    

			
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
			    inti_remoteDriver("firefox");
				
			}
			else {
			tlDriver.set(new ChromeDriver(opsManager.getChromeOptions()));
			}
			

		}
		else if(browser.equalsIgnoreCase("safari"))
		{
	
			tlDriver.set(new SafariDriver());

			
		}else
		{
			System.out.println("Please pass the correct browser value: "+browser);
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
	
		return getDriver();
	}

	private void inti_remoteDriver(String browser) {

		System.out.println("Remote WebDriver Started");
		
		if(browser.equals("chrome"))
		{
			
			DesiredCapabilities  cap=new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setCapability(ChromeOptions.CAPABILITY, opsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(browser.equals("firefox"))
		{
			
			DesiredCapabilities  cap=new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, opsManager.getFirefoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * returns properties prop reference
	 * this method is used to load the properties from config.properties file
	 */
	public Properties init_prop()
	{
	 prop=new Properties();
	 try {
		FileInputStream fis=new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
	    prop.load(fis);
	 
	 } catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
		return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	

}
