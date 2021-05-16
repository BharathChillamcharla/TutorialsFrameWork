package com.qa.hubspot.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsutil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsutil=new JavaScriptUtil(driver);
	}
	
	public By getLocator(String value)
	{
		return By.id(value);
		
		
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
		
		
	}
	
	public WebElement getElement(By locator)
	{
		WebElement element= driver.findElement(locator);
		
		if(BasePage.highlight.equals("true"))
		{
		 jsutil.flash(element);
		}
		return element;
	}

	public void doSendKeys(By locator,String value)
	{
		
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator)
	{
	  
		getElement(locator).click();
		
	}
	
	public void doActionsSendKeys(By locator,String value)
	{
		Actions action=new Actions(driver);
		action.sendKeys(getElement(locator),value).perform();
	}
	public void doActionsClick(By locator)
	{
		Actions action =new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	public void doSendKeyWithMoveToElement(By locator,String value)
	{
		Actions action =new Actions(driver);
		action.moveToElement(getElement(locator)).sendKeys(value).build().perform();
	}
	
	public void doClickWithMoveToElement(By locator)
	{
		Actions action=new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
		
	}
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public int getElementsCount(String tagName)
	{
		return driver.findElements(By.tagName(tagName)).size();
	}
	
	public List<String> getAttributesList(String tagName,String attributeName)
	{
		List<String> attrList =new ArrayList<String>();
		
		List<WebElement> elementList= driver.findElements(By.tagName(tagName));
		for(WebElement e: elementList)
		{
			
			String text=e.getAttribute(attributeName);
			attrList.add(text);
		}
		return attrList;
	}
	
	public void doClickFromList(By locator,String linktext)
	{
		List<WebElement> footerList=getElements(locator);
		for(int i=0;i<footerList.size();i++)
		{
			String text=footerList.get(i).getText();
			if(text.equals(linktext))
			{
				footerList.get(i).click();
				break;
			}
		}
		
	}
	
	// ************* Drop Down Utils*************
	public void doSelectDropDownByVisibleText(By locator,String text)
	{
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByIndex(By locator,int index)
	{
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);
	}	
	
	public void doSelectDropDownByValue(By locator,String value)
	{
		
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void selectDropDownValueWithoutSelectClass(By locator, String value)
	{
		List<WebElement> optionsList=getElements(locator);
		for(WebElement e: optionsList)
		{
			String text=e.getText();
			if(text.equals(value))
			{
				e.click();
				break;
			}
		    }
		
	}
	
	//**************** waitUtils******************
	
	public List<WebElement> visibilityofAllElements(By locator,int timeOut)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public void getPageLinksText(By locator,int timeOut)
	{
		visibilityofAllElements(locator, timeOut).stream().forEach(ele-> System.out.println(ele.getText()));
		
		
	}
	
	public int getPageLinksCount(By locator,int timeOut)
	{
		return visibilityofAllElements(locator, timeOut).size();
	}
	public String waitForTitlePresent(String titleValue,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
		
	}
	public String waitForTitlePresent(String titleValue,int timeOut,int intervalTime)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut,intervalTime);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
		
	}
	
	public Alert waitforAlertTobePresent(int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public boolean waitForUrl(String urlValue,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
		
	}
	
	public WebElement waitforElementToBeLocated(By locator,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	public WebElement waitforElementToBeVisible(By locator,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		
	}
	public void clickWhenReady(By locator,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
		
	}
	
	public WebElement waitforElementWithFluentWait(By locator,int timeOut,int pollingTime)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//******* Custom Dynamic Wait to find element*************
	
	public WebElement retryElement(By locator)
	{
		WebElement element=null;
		int attempts=0;
		while(attempts<30)
		{
			try
			{
				element= getElement(locator);
				break;
			}
			catch(StaleElementReferenceException e)
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e1)
				{
					
				}
				System.out.println("element is not found in given attempts :"+(attempts +1));
			}
			catch(NoSuchElementException e)
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e1)
				{
					
				}
				System.out.println("element is not found in given attempts :"+(attempts +1));
			}
			
			attempts++;
		}
		return element;
	}
}
