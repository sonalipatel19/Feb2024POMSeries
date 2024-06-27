package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	private By searchResult = By.cssSelector("div.caption");
	
	public int getSearchResultsCount() {
		List<WebElement> list = 
				elUtil.waitForVisibilityOfAllElementsLocated(searchResult, TimeUtil.DEFAULT_MEDIUM_TIME);
		int resultCount = list.size();
		System.out.println("product search results count === " + resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		elUtil.doClickWithWait(By.linkText(productName), TimeUtil.DEFAULT_TIME);
		return new ProductInfoPage(driver);
	}

}
