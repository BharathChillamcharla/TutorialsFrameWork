package com.qa.hubspot.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class ProductsInfoPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	private By ProductNameHeader=By.cssSelector("div#content h1");
	private By ProductMetadata=By.cssSelector("div#content .list-unstyled:nth-of-type(1) li");
	private By ProductPrice=By.cssSelector("div#content .list-unstyled:nth-of-type(2) li");
	
	private By quantity=By.id("input-quantity");
	private By addToCartbtn=By.id("button-cart");
	private By ProductImages=By.cssSelector(".thumbnails li img");
	private By alertMessage=By.cssSelector(".container .alert");
	
	
	public ProductsInfoPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
		
	}
	
	public Map<String,String> getProductInformation()
	{
		
		Map<String,String> productinfoMap=new HashMap<>();
		List<WebElement> productmetadataList=elementutil.getElements(ProductMetadata);
		productinfoMap.put("name", elementutil.doGetText(ProductNameHeader).trim());
		for(WebElement e:productmetadataList)
		{
			productinfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
			
			
		}
		
		List<WebElement> productpriceList=elementutil.getElements(ProductPrice);
		productinfoMap.put("Price", productpriceList.get(0).getText().trim());
		productinfoMap.put("ExcludingPrice", productpriceList.get(1).getText().split(":")[1].trim());
		
		
		return productinfoMap;
		
	}
	
	
	public void selectQuantity(String qty)
	{
		elementutil.getElement(quantity).clear();
		elementutil.doSendKeys(quantity,qty);
		
	}
	
	
	public boolean addToCart()
	{
		
		elementutil.doClick(addToCartbtn);
		WebElement element=elementutil.retryElement(addToCartbtn);
		element.isDisplayed();
		WebElement alertMsg=elementutil.retryElement(alertMessage);
		return alertMsg.isDisplayed();
		
	}
	
	public int getproductImages()
	{
		int imagesCount=elementutil.getElements(ProductImages).size();
		return imagesCount;
		
		
	}
	
	public String getproductinfotitle(String productName)
	{
		return elementutil.waitForTitlePresent(productName, 10);
		
	}
	

}
