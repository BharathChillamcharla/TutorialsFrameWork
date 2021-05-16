package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.AccountsPage;
import com.qa.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic: 200: define Accounts page features")
@Story("US 201: Designing accounts page with title, header, account sections and product results")
public class AccountsPageTest extends BaseTest{
	
	
	public AccountsPage accountspage;
	
	
	@BeforeClass
	public void AccountsPageSetuP()
	{
		accountspage=loginpage.dLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	
	@Description("Accounts page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyAccountpageTitleTest()
	{
		String accountspagetitle=accountspage.getAccountsPageTitle();
		System.out.println("accounts page title is "+accountspagetitle);
		Assert.assertEquals(accountspage.getAccountsPageTitle(),Constants.accountsPageTitle);
		
	}
	
	@Description("Accounts page Header test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void VerifyAccountheaderTest()
	{
		
		String headervalue=accountspage.getHeaderValue();
		System.out.println("accounts page header value is "+headervalue);
		Assert.assertEquals(headervalue, Constants.headerlinktext);
	}
	
	@Description("Accounts page Sections count test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void VerifyAccountheaderSectionCountTest()
	{
		Assert.assertTrue(accountspage.getAccountsHeaderCount()== Constants.ACCOUNTS_SECTIONS);
		
	}
	
	@Description("Accounts section list test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void VerifyAccountHeaderSectionListTest()
	{
		
		Assert.assertEquals(accountspage.getAccountsSectionsList(),Constants.getAccountsSectionList());
		
	}
	
	@Description("Accounts page Search test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void searchTest()
	{
		
		Assert.assertTrue(accountspage.doSearch("imac"));
		
	}
	
	

}
