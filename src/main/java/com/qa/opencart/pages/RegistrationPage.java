package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	private By firstname = By.id("input-firstname");
	private By lastName = By.name("lastname");
	private By email = By.xpath("//*[@id=\"input-email\"]");
	private By telephone = By.cssSelector("#input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	
	private By agreeCheckbox = By.name("agree");
	private By continuebtn = By.xpath("//input[@type='submit' and @value='Continue']");

	private By registeredSuccessfully = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public boolean userRegister(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
		
	elUtil.doSendKeysWithWait(this.firstname, firstname, TimeUtil.DEFAULT_TIME);
	elUtil.doSendKeys(this.lastName, lastname);
	elUtil.doSendKeys(this.email, email);
	elUtil.doSendKeys(this.telephone, telephone);
	elUtil.doSendKeys(this.password, password);
	elUtil.doSendKeys(this.confirmPwd, password);
	
	if(subscribe.equalsIgnoreCase("yes")) {
		elUtil.doClick(subscribeYes);
	} else {
		elUtil.doClick(subscribeNo);
	}
	
	elUtil.doClick(agreeCheckbox);
	elUtil.doClick(continuebtn);
	
	String successMsg = elUtil.waitForElementVisible(registeredSuccessfully, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
			System.out.println(successMsg);
	
	if(successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
		elUtil.doClick(logoutLink);
		elUtil.doClick(registerLink);
		return true;
	} else {
		return false;
	}
	}

}
