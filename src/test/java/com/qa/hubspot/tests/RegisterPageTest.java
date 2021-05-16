package com.qa.hubspot.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	
	
	@BeforeClass
	public void RegisterPageSetup()
	{
		
		registerpage=loginpage.navigateToRegisterPage();
		
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] Data=ExcelUtil.getData(Constants.Sheet_Name);
		return Data;
		
	}
	
	
	@Test(dataProvider="getData")
	public void UserRegisterationTest(String firstname,String lastname,String email,String telephone,String password,String subscribe)
	{
		registerpage.AccountRegistration(firstname, lastname, email, telephone, password, subscribe);
	}
	

}
