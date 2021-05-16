package com.qa.hubspot.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String login_title="Account Login";
	public static final String headerlinktext="Your Store";
	public static final String accountsPageTitle="My Account";
	public static final int ACCOUNTS_SECTIONS=4;
	public static final String Sheet_Name="registration";
	public static final String Account_Sucess_Message="Your Account Has Been Created";
	
	public static List<String> getAccountsSectionList()
	{
		
		List<String> accountsList= new ArrayList<String>();
		accountsList.add("My Account");
		accountsList.add("My Orders");
		accountsList.add("My Affiliate Account");
		accountsList.add("Newsletter");
		
		return accountsList;
	}
	
   

	
	
}
