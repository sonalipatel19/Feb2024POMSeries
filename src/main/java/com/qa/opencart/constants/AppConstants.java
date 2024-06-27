package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	private AppConstants() {}
	
	

	public static final String CONFIG_FILE_PATH = "./src/test/resource/config/config.properties";
	public static final String DEV_CONFIG_FILE_PATH = "./src/test/resource/config/dev.properties";
	public static final String QA_CONFIG_FILE_PATH = "./src/test/resource/config/qa.properties";
	public static final String STAGE_CONFIG_FILE_PATH = "./src/test/resource/config/stage.properties";
	public static final String UAT_CONFIG_FILE_PATH = "./src/test/resource/config/uat.properties";

	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACCOUNTS_PAGE_FRACTION_URL= "route=account/account";
	
	
	public static final List<String> ACC_PAGE_HEADERS_LIST = 
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

	public static final String USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	
	
	
	//****************** SHEET CONSTANTS*************************
	
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_IMAGES_SHEET_NAME = "productimages";
	
	
}
