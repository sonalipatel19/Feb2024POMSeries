package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elUtil;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By quantityCount = By.name("quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successfulMsg = By.cssSelector("div#product-product div.alert-dismissible");
	private By shoppingCartLink = By.xpath("(//div[@id='top-links']//li)[9]//span[text()='Shopping Cart']");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> mapList;
	
	
	
	public String getProductHeader() {
		String header = elUtil.doGetText(productHeader);
		System.out.println("product header === " + header);
		return header;
		
	}
	
	public int getProductImagesCount() {
		int imagesCount = 
				elUtil.waitForVisibilityOfAllElementsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("total images == " + imagesCount);
		return imagesCount;
	}
	
	public ShoppingCartPage getQuantityCount(String value) {
		elUtil.doSendKeys(quantityCount, value);
		elUtil.doClick(addToCartBtn);
		elUtil.doClick(shoppingCartLink);
		return new ShoppingCartPage(driver);	
	}
	
	public Map<String, String> getProductInfoMap() {
		mapList = new HashMap<String, String>();
	//	mapList = new LinkedHashMap<String, String>();
	//	mapList = new TreeMap<String, String>();
		
		mapList.put("productname",getProductHeader());
		mapList.put("productImages",String.valueOf(getProductImagesCount()));
		
		getProductMetaData();
		getProductPriceData();
		return mapList;
	}
	
	public void getProductMetaData() {
		List<WebElement> metaList = elUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			mapList.put(metaKey, metaValue);			
		}
	}
	
	public void getProductPriceData() {
		List<WebElement> priceList = elUtil.getElements(productPriceData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		mapList.put("productprice", productPrice);
		mapList.put("exTaxprice", exTaxPrice);
	}

}
