package com.qa.opencart.utils;

public class StringUtils {

	public static String getRandomEmailId() {
		String emailId = "auto"+ System.currentTimeMillis() + "@opencart.com";
		System.out.println("user emailId is : " + emailId);
		return emailId;
	}
}
