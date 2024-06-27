package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage lp;
	protected AccountsPage accPage;
	protected SearchResultsPage sp;
	protected ProductInfoPage pp;
	protected ShoppingCartPage cart;
	protected RegistrationPage register;
	
	protected SoftAssert softAssert;
	
	
	@Step("setup for the test, initalizing browser : {0}")
	@Parameters ({"browser"})
	@BeforeTest
	public void setUp(String browserName) {   //String browserName
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		lp = new LoginPage(driver);
		cart = new ShoppingCartPage(driver);
		softAssert = new SoftAssert();
	}

	@Step("closing browser....")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
