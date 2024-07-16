package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() {
		register = lp.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegisterTestData() {
		return new Object[][] {
			{"rinkal" , "sharma" , "5487216952", "rinkal@123", "yes"},
			{"pankaj" , "shah" , "5487258882", "pankaj@123", "no"},
			{"rudra" , "singh" , "5854416952", "rudra@123", "no"},
			{"neeta" , "patel" , "5488576952", "neeta@123", "yes"},
			
		};
	}
	@DataProvider
	public Object[][] userRegTestDataFromSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "userRegisterTestData")
	public void userRegisterTest(String firstname, String lastname, String telephone, String pwd, String subs) {
		
		Assert.assertTrue
		(register.userRegister(firstname, lastname, StringUtils.getRandomEmailId(), telephone, pwd, subs), 
				AppError.USER_REG_NOT_DONE);
	}
}
