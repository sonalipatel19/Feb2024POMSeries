package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage {

	private WebDriver driver;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}

	private By quantityCount = By.xpath("(//table[@class='table table-bordered']//tbody)[2]//td[@class='text-left'][3]");
	private By unitPrice = By.xpath("(//table[@class='table table-bordered']//tbody)[2]//td[@class='text-right'][1]");
	private By shoppingCartLink = By.xpath("(//div[@id='top-links']//li)[9]//span[text()='Shopping Cart']");
	
	public String getProductName(String productName) {
		By proName = 
			By.xpath("(//table[@class='table table-bordered'])[2]//td[@class='text-left']/a[text()='"+productName+"']");
		
		String text = driver.findElement(proName).getText();
		System.out.println(text);
		return text;
	}
	
	public String ShoppingCartTitle() {
		String title = driver.getTitle();
		System.out.println("Shopping Cart Title : " + title);
		return title;
	}
	
	public int getTotalProductCount() {
		driver.findElement(shoppingCartLink).click();
		List<WebElement> unitList = driver.findElements(unitPrice);
		for(WebElement e : unitList) {
			String unitText = e.getText();
			System.out.println(unitText);
		}
		List<WebElement> quantList = driver.findElements(quantityCount);
		for(WebElement e : quantList) {
			String quantText = e.getText();
			System.out.println(quantText);
		}
		int sum = Integer.parseInt("unitText" + "*" + "quantText");
		System.out.println("Total price of the each product : " + sum);
		return sum;
	}
}
