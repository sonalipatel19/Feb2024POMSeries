package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
//	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This is used to init the driver basis on the browser name.
	 * @param browserName
	 * @return 
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is : " +browserName);
		
	//	highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("plz pass the right browser... " + browserName);
			throw new BrowserExceptions(AppError.BROWSER_NOT_FOUND);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	
	/**
	 * get the local thread copy of the driver.
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this is used to init the properties from the properties file
	 * @return this returns properties(prop)
	 */
	public Properties initProp() {
		
		prop = new Properties();
		FileInputStream ip = null;
		
		//mvn clean install -Denv="qa"
		
		String envName = System.getProperty("env");
		System.out.println("running test suite on env --->" + envName);
		
		if(envName == null) {
			System.out.println("env name is not given, hence running it on QA environment....");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
		try {
		switch (envName.trim().toLowerCase()) {
		case "qa":
			ip = new FileInputStream(AppConstants.QA_CONFIG_FILE_PATH);
			break;
		case "stage":
			ip = new FileInputStream(AppConstants.STAGE_CONFIG_FILE_PATH);
			break;
		case "dev":
			ip = new FileInputStream(AppConstants.DEV_CONFIG_FILE_PATH);
			break;
		case "uat":
			ip = new FileInputStream(AppConstants.UAT_CONFIG_FILE_PATH);
			break;
		case "prod":
			ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;

		default:
			System.out.println("please pass the right env name.." + envName);
			throw new FrameworkException("WRONG ENV PASSED");
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			}
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
        
        // Take the screenshot and save it to a temporary location
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        
        // Define the path for the screenshots folder
        String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";
        
        // Create the screenshots folder if it doesn't exist
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            if (screenshotsDir.mkdirs()) {
                System.out.println("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
            } else {
                System.out.println("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
            }
        }

        // Define the destination path for the screenshot
        String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(screenshotPath);

        // Copy the screenshot to the destination path
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination.getAbsolutePath();
	}
}
