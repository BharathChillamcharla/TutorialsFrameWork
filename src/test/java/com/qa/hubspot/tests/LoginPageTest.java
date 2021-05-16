package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic: 100: define login page features")
@Story("US 101: define login page class feaetures with title, forgotpassword,login page ")
public class LoginPageTest extends BaseTest{
	

	
	@Description("Verify Login page Title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void VerifyLoginPagetitleTest()
	{
		String title=loginpage.getloginPagetitle();
		System.out.println("Login page title is: "+ title);
		Assert.assertEquals(title, Constants.login_title);
		
	}
	
	@Description("Verify Forget Password page Link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void VerifyforgotPasswordLinkTest()
	{
		boolean forgotPasswordlink=loginpage.forgottenPasswordLinkexists();
		Assert.assertEquals(forgotPasswordlink, true);
	
		
	}
	
	@Description("Login page test with username and password")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void logintest()
	{
		loginpage.dLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	
	
	

}
