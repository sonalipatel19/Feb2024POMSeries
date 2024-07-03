package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String productName) {
		sp = accPage.doSearch(searchKey);
		pp = sp.selectProduct(productName);
		Assert.assertEquals(pp.getProductHeader(), productName, AppError.HEADER_NOT_FOUND);
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"macbook", "MacBook Air", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"canon", "Canon EOS 5D", 3}
		};
	}
	
	@DataProvider
	public Object[][] getProductImageSheetData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_IMAGES_SHEET_NAME);
	}
	
	@Test (dataProvider = "getProductImageData")
	public void getProductImagesCountTest(String searchKey, String productName, int imageCount) {
		sp = accPage.doSearch(searchKey);
		pp = sp.selectProduct(productName);
		Assert.assertEquals(pp.getProductImagesCount(), imageCount , AppError.IMAGES_COUNT_MISMATCHED);
		//Assert.assertEquals(pp.getProductImagesCount(), Integer.parseInt(imageCount) , AppError.IMAGES_COUNT_MISMATCHED);
	}
	
	@DataProvider
	public Object[][] getProductQuantityData() {
		return new Object[][] {
			{"macbook", "MacBook Air", "4", "MacBook Air"},
			{"imac", "iMac", "3", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW", "3", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1", "2", "Samsung Galaxy Tab 10.1"},
			{"hp", "HP LP3065", "3", "HP LP3065"}
		};
	}
	
	@Test (dataProvider = "getProductQuantityData") 
	public void getQuantityCountTest(String searchKey, String productName, String qtyValue, String prodname) {
		sp = accPage.doSearch(searchKey);
		pp = sp.selectProduct(productName);
		cart = pp.getQuantityCount(qtyValue);
		Assert.assertEquals(cart.getProductName(prodname), productName);
	}
	
	@Test
	public void productInfoTest() {
		sp = accPage.doSearch("macbook");
		pp = sp.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = pp.getProductInfoMap();
		System.out.println("===============product information=============");
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
	
//	@Test
//	public void getProductTotalCountTest() {
//		cart.getTotalProductCount();
//	}

	//hard assert(Assert) vs soft assert(verify - SoftAssert)
	//Assert --> methods (static)
	//SoftAssert --> methods (non static)
	
	//single assrrtion -- hard assert
	//multiple assertion --> soft assert
}
