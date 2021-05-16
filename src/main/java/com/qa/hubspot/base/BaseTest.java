package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.AccountsPage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.ProductsInfoPage;
import com.qa.hubspot.pages.RegisterPage;

public class BaseTest {
	
	public BasePage basepage;
	public LoginPage loginpage;
	public AccountsPage accountspage;
	public ProductsInfoPage productsinfopage;
	public RegisterPage registerpage;
	public Properties prop;
	public WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void SetUp(String browserName)
	{
		basepage=new BasePage();
		prop=basepage.init_prop();
		String browser=prop.getProperty("browser");
		if(browserName!=null)
		{
			browser=browserName;
			
		}
		driver=basepage.init_driver(browser);
		loginpage=new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		
		driver.quit();
		
		
	}
	
	

}
