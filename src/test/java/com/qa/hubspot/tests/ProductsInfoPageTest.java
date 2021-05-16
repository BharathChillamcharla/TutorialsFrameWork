package com.qa.hubspot.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.AccountsPage;
import com.qa.hubspot.pages.ProductsInfoPage;

public class ProductsInfoPageTest extends BaseTest{
	
	

	public AccountsPage accountspage;
	public ProductsInfoPage productinfopage;
	
	
	@BeforeClass
	public void ProductsInfoSetUp()
	{
		accountspage=loginpage.dLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	
	@Test(priority=1)
	public void productinfotitleTest_iMac()
	{
		Assert.assertTrue(accountspage.doSearch("iMac"));
		productinfopage=accountspage.selectProductfromResults("iMac");
		
		Assert.assertEquals(productinfopage.getproductinfotitle("iMac"), "iMac");
		
	}
	
	@Test(priority=2)
	public void verifyproductinfoTest_Macbook()
	{
		String productName="Macbook";
		accountspage.doClearSearchtext();
		Assert.assertTrue(accountspage.doSearch(productName));
		productinfopage=accountspage.selectProductfromResults("MacBook Pro");
		
		Assert.assertTrue(productinfopage.getproductImages()==4);
		Map<String,String> productinforMap=productinfopage.getProductInformation();
		System.out.println(productinforMap);
		Assert.assertEquals(productinfopage.getProductInformation().get("Price"), "$2,000.00");
		Assert.assertEquals(productinfopage.getProductInformation().get("Brand"), "Apple");
		Assert.assertEquals(productinfopage.getProductInformation().get("Product Code"), "Product 18");
		Assert.assertEquals(productinfopage.getProductInformation().get("Reward Points"), "800");
		Assert.assertEquals(productinfopage.getProductInformation().get("name"), "MacBook Pro");
		accountspage.doClearSearchtext();
		
	}
	
	
	@Test(priority=3)
	public void verifyproductinfoTest_iMac()
	{
		String productName="iMac";
		accountspage.doClearSearchtext();
		
		Assert.assertTrue(accountspage.doSearch(productName));
		productinfopage=accountspage.selectProductfromResults("iMac");
		
		Assert.assertTrue(productinfopage.getproductImages()==3);
		Map<String,String> productinforMap=productinfopage.getProductInformation();
		System.out.println(productinforMap);
		Assert.assertEquals(productinfopage.getProductInformation().get("Price"), "$100.00");
		Assert.assertEquals(productinfopage.getProductInformation().get("Brand"), "Apple");
		Assert.assertEquals(productinfopage.getProductInformation().get("Product Code"), "Product 14");
		Assert.assertEquals(productinfopage.getProductInformation().get("name"), "iMac");
	}
	
	@Test(priority=4)
	public void VerifyAddToCartTest()
	{
		
		productinfopage.selectQuantity("1");
		
		boolean results=productinfopage.addToCart();
		Assert.assertEquals(results, true);
		
	}
	

}
