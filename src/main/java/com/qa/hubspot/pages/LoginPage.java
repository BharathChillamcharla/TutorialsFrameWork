package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	
	//1. By locators or OR-Object repository
	private ElementUtil elementutil;
	
	private By emailid=By.name("email");
	private By password=By.name("password");
	private By Loginbutton=By.xpath("//input[@type='submit' and @value='Login']");
	private By forgotPasswordLink=By.linkText("Forgotten Password");
	private By header=By.linkText("Your Store");
	private By RegisterLink=By.linkText("Register");
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
		
	}
	
	//1. page actions - features of the page
	
	@Step("getting the login page title")
	public String getloginPagetitle()
	{
		
		return elementutil.waitForTitlePresent(Constants.login_title, 20);
	}
	
	
	@Step("Checking forgetPassword link exists")
	public boolean forgottenPasswordLinkexists()
	{
		//return driver.findElement(forgotPasswordLink).isDisplayed();
		return elementutil.doIsDisplayed(forgotPasswordLink);
		
		
	}
	
	@Step("Login with the username: {0} and password: {1}")
	public AccountsPage dLogin(String un,String Pwd)
	{
	
		System.out.println("Login with "+un+" : "+Pwd);
		elementutil.doSendKeys(emailid, un);
		elementutil.doSendKeys(password, Pwd);
		elementutil.doClick(Loginbutton);
		return new AccountsPage(driver);
	}
	
	@Step("Navigate to RegisterPage")
	public RegisterPage navigateToRegisterPage()
	{
		elementutil.doClick(RegisterLink);
		
		return new RegisterPage(driver);
		
		
	}
	
	
	

}
