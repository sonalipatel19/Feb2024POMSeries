package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import lombok.experimental.StandardException;

@Epic("Epic 100: Design Open Cart Application with Shopping WorkFLow")
@Story("US 101: Design login page for Open Cart Application")
public class LoginPageTest extends BaseTest{
	
	
	@Description("checking login page title----")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sonali")
	@Issue("Login-123")
	@Feature("login page title features")
	@Test (priority = 1)
	public void loginPageTitleTest() {
		String actTitle = lp.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Description("checking login page url----")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = lp.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
	@Description("checking forgot pwd link exist on the login page----")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(lp.checkForgotPwdLinkExist(), AppError.Element_NOT_FOUND);
	}
	
	@Description("checking user is able to login successfully ----")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	

}
