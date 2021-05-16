package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class RegisterPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil elementutil;
	private By firstname=By.cssSelector("#input-firstname");
	private By lastname=By.cssSelector("#input-lastname");
	private By email=By.cssSelector("#input-email");
	private By telephone=By.cssSelector("#input-telephone");
	private By password=By.cssSelector("#input-password");
	private By confirmpassword=By.cssSelector("#input-confirm");
	private By subscribeyes=By.xpath("//label[@class='radio-inline'][position()=1]");
	private By subscribeno=By.xpath("//label[@class='radio-inline'][position()=2]");
	private By agreecheckbox=By.name("agree");
	private By continuebutton=By.xpath("//input[@type='submit' and @value='Continue']");
	private By accountsucessmessgage=By.cssSelector("#content h1");
	private By logutLink=By.linkText("Logout");
	private By RegisterLink=By.linkText("Register");
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
		
		
	}
	
	public boolean AccountRegistration(String firstname,String lastname,String email,String telephone,String password,String subscribe)
	{
		elementutil.doSendKeys(this.firstname, firstname);
		elementutil.doSendKeys(this.lastname, lastname);
		elementutil.doSendKeys(this.email, email);
		elementutil.doSendKeys(this.telephone, telephone);
		elementutil.doSendKeys(this.password, password);
		elementutil.doSendKeys(this.confirmpassword, password);
		if(subscribe.equalsIgnoreCase("yes"))
		{
			elementutil.doClick(subscribeyes);
		}else
		{
			elementutil.doClick(subscribeno);
		}
	
		elementutil.doClick(agreecheckbox);
		elementutil.doClick(continuebutton);
		
		String text=elementutil.doGetText(accountsucessmessgage);
		if(text.contains(Constants.Account_Sucess_Message))
		{
			elementutil.doClick(logutLink);
			elementutil.doClick(RegisterLink);
			
			return true;
		}
		return false;
		
	}
	
	
	
	

}
