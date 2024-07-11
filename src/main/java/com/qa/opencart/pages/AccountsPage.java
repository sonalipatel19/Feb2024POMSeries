package com.qa.opencart.pages;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");	
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getAccPageTitle() {
		
		String title = elUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Account page Title : " + title);
		return title;
	}
	
	public String getAccPageURL() {
		String url = elUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Account page URL : " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return elUtil.doIsDisplayed(logoutLink);	
	}
	
	public List<String> getAccPageHeaders(){
		
		List<WebElement> headerList = elUtil.waitForVisibilityOfAllElementsLocated(headers, TimeUtil.DEFAULT_TIME);
		
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e : headerList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public boolean isSearchExist() {
		return elUtil.doIsDisplayed(search);	
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching for product : " + searchKey);	
		
		if(isSearchExist()) {
			elUtil.doSendKeys(search, searchKey);
			elUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("search field is not present on the page");
			return null;
		}
	}
	
}
