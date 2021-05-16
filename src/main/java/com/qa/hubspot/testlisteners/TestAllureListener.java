package com.qa.hubspot.testlisteners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.hubspot.base.BasePage;

import io.qameta.allure.Attachment;

public class TestAllureListener extends BasePage implements ITestListener {
	
	
	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		
	}
	
	@Attachment(value= "Page Screenshot", type="image/png")
	public byte[] saveScreenshot(WebDriver driver)
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value="{0}",type="text/plain")
	public static String saveTextlog(String message)
	{
		return message;
	}
	
	@Attachment(value="{0}",type="text/html")
	public static String attachHtml(String html)
	{
		return html;
	}

	@Override
	public void onTestStart(ITestResult iTestresult) {
		
		System.out.println("I am on onTestStart method "+ getTestMethodName(iTestresult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestresult) {
		System.out.println("I am on onTestSuccess method "+ getTestMethodName(iTestresult));
		
	}

	@Override
	public void onTestFailure(ITestResult iTestresult) {
		System.out.println("I am on onTestFailure method "+ getTestMethodName(iTestresult));
		Object testClass=iTestresult.getInstance();
		if(getDriver() instanceof WebDriver)
		{
			System.out.println("Screenshot captured for test case:" +getTestMethodName(iTestresult));
			saveScreenshot(getDriver());
			
		}
		
		saveTextlog(getTestMethodName(iTestresult) + "failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult iTestresult) {
		System.out.println("I am on onTestSkipped method "+ getTestMethodName(iTestresult));
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestresult) {
		System.out.println("Test failed but it is in defined success ratio "+ getTestMethodName(iTestresult));
		
	}

	@Override
	public void onStart(ITestContext iTestContext) {

		System.out.println("I am in onStart method "+ iTestContext.getName());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method "+ iTestContext.getName());
		
	}

}
