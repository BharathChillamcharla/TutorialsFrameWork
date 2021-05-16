package com.qa.hubspot.testlisteners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.hubspot.base.BasePage;
import com.tesults.tesults.Results;

public class TesultsListener extends BasePage implements ITestListener{
	
	
	List<Map<String,Object>> testCases=new ArrayList<Map<String,Object>>();
	
	
	public static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static Object[] getTestParams(ITestResult iTestResult)
	{
		return iTestResult.getParameters();
	}
	public byte[] saveScreenshot(WebDriver driver)
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Override
	public void onTestStart(ITestResult iTestresult) {
		
		System.out.println("I am on onTestStart method "+ getTestMethodName(iTestresult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestresult) {
		
		
		Map<String,Object> testcase=new HashMap<String, Object>();
		testcase.put("name:", getTestMethodName(iTestresult));
		testcase.put("Suite:", "Tesults Example");
		testcase.put("result", "Pass");
		testcase.put("params",getTestMethodName(iTestresult));
		
	}

	@Override
	public void onTestFailure(ITestResult iTestresult) {
		Map<String,Object> testcase=new HashMap<String, Object>();
		testcase.put("name:", getTestMethodName(iTestresult));
		testcase.put("Suite:", "Tesults Example");
		testcase.put("result", "Fail");
		testcase.put("params",getTestMethodName(iTestresult));
		List<String> files=new ArrayList<String>();
		files.add(getScreenshot());
		testcase.put("files", files);
		testCases.add(testcase);
		
	}

	@Override
	public void onTestSkipped(ITestResult iTestresult) {
		Map<String,Object> testcase=new HashMap<String, Object>();
		testcase.put("name:", getTestMethodName(iTestresult));
		testcase.put("Suite:", "Tesults Example");
		testcase.put("result", "skip");
		testcase.put("params",getTestMethodName(iTestresult));
		List<String> files=new ArrayList<String>();
		files.add(getScreenshot());
		testcase.put("files", files);
		testCases.add(testcase);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("target", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjZhMDkwYmY0LTRmNjctNGI2OC1iMmViLTczZWQ0MmIwYjllNi0xNjEwNzk0MTU2NjQ0IiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiYmI0MDNiYjUtNDg4OS00ZWJmLTllZWUtOWQwM2Q3ZWVlYmViIiwidHlwZSI6InQifQ.qhoR2ZZpGBM3rxSy_uLGsDVHqMatA5mfAQ_luFegsWk");
		Map<String,Object> results=new HashMap<String,Object>();
		results.put("cases", testCases);
		
		//upload
		Map<String,Object> response=Results.upload(data);
		System.out.println("success: "+response.get("success"));
		System.out.println("message: "+response.get("message"));
		
		System.out.println("warnings: "+((List<String>) response.get("warnings")).size());
		System.out.println("errors: "+((List<String>) response.get("errors")).size());
	
	}
	
	
	
	
	
	

}
