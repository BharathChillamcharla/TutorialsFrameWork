package com.qa.hubspot.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage extends BasePage {
	
	private WebDriver driver;
	public LoginPage loginpage;
	private ElementUtil elementutil;
	private By Accountsectionheaders=By.cssSelector("div#content h2");
	private By Search=By.xpath("//input[@type='text' and @name='search']");
	private By SearchButton=By.cssSelector("div#search button[type='button']");
	private By header=By.linkText("Your Store");
	private By SearchitemResults=By.cssSelector(".product-layout .product-thumb");
	private By SearchItemsResultsList=By.cssSelector(".product-thumb h4 a");
	
	
	public AccountsPage(WebDriver driver)
	{
		
		this.driver=driver;
		loginpage=new LoginPage(driver);
		elementutil=new ElementUtil(driver);
		
	}
	
	
	@Step("getting account page title")
	public String getAccountsPageTitle()
	{
		return elementutil.waitForTitlePresent(Constants.accountsPageTitle, 10);
		
	}
	
	@Step("getting header value")
	public String getHeaderValue()
	{
		if(elementutil.doIsDisplayed(header))
		{
			return elementutil.doGetText(header);
		}
		return null;
	}
	@Step("getting the Accounts Header count")
	public int getAccountsHeaderCount()
	{
		return elementutil.getElements(Accountsectionheaders).size();
		
	}
	
	@Step("getting the Accounts Sections List")
	public List<String> getAccountsSectionsList()
	{
		List<String> AccountsectionList= new ArrayList<String>();
		
		List<WebElement> Acccountsectionheaderlist=elementutil.getElements(Accountsectionheaders);
		
		for(WebElement e:Acccountsectionheaderlist)
		{
			AccountsectionList.add(e.getText());
			
		}
		return AccountsectionList;
		
	}
	
	@Step("Searching a product with name :{0}")
	public boolean doSearch(String productName) {
		
		elementutil.doSendKeys(Search, productName);
		elementutil.doClick(SearchButton);
		if(elementutil.getElements(SearchitemResults).size()>0)
		{
			return true;
		}
		return false;
	}
	@Step("Clearing the search field text")
	 public void doClearSearchtext() {
		
		elementutil.getElement(Search).clear();
		
	}
	
	@Step("Selecting product from Results : {0}")
	public ProductsInfoPage selectProductfromResults(String productName)
	{
		List<WebElement> searchresultsList=elementutil.getElements(SearchItemsResultsList);
		System.out.println("Total number of items displayed "+searchresultsList.size());
		for(WebElement e:searchresultsList)
		{
			if(e.getText().equalsIgnoreCase(productName))
			{
				e.click();
				break;
			}
		}
		
		return new ProductsInfoPage(driver);
	}

}
