package com.omnify.fitnessApp.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import io.appium.java_client.android.AndroidDriver;

public class MobileActionUtil {

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void scrollToParticularText(AndroidDriver driver, String text) throws Exception {

		try {
			logger.info("---------Verifying the control is scrolling to the particular text or not ---------");
			Thread.sleep(3000);
			driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()"
					+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"))");
			logger.info("After scrolling to the text: " + text);
			MyExtentListeners.test.pass("Verifying user is able to scroll to " + "\'" + text );
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to scroll to " + "\'" + text + "\'"
					+ "  || User is not able to scroll to " + "\'" + text + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, text));
			Assert.fail("unable to scroll to " + "\'" + text + "\'");

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, text));
			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to scroll to " + "\'" + text + "\'"
					+ " || User is not able to scroll to " + "\'" + text + "\'", ExtentColor.RED));
			Assert.fail("unable to scroll to " + "\'" + text + "\'");
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, text));
			throw error;
		}
	}

	public static void waitForElementToLoad(int seconds) throws InterruptedException {
		Thread.sleep(seconds*1000);
	}

	public static void verifyExpectedText(WebElement ele, String expectedText) throws Exception {
		try {
			if(ele.getText().equalsIgnoreCase(expectedText) || ele.getText().contains(expectedText)) {
				System.out.println("Expected Text: "+expectedText+" is found");
				MyExtentListeners.test.pass("Expected Text is found: "+expectedText);
			}
		}catch (Exception e) {
			System.out.println("Expected text could not be verified");
			MyExtentListeners.test.fail("Expected Text is  not found: "+expectedText);
			e.printStackTrace();
		}
	}

	public static String capture(AndroidDriver driver, String screenShotName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String dest = MyExtentListeners.screenShotPath +screenShotName + ".png";
		System.out.println(dest);
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	public static boolean isEleDisplayed(WebElement element, AndroidDriver driver,String elementName, int seconds) throws IOException, InterruptedException {
		boolean flag = false;
		try 
		{
			logger.info("---------Verifying element is displayed or not ---------");
			flag=element.isDisplayed(); 

		}
		catch (RuntimeException e) {
			Thread.sleep(seconds * 1000);
			flag = false;
		}
		MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'" + elementName
				+ "\'" + " is displayed ");

		return flag;
	}


	public static void waitForElement(WebElement element, AndroidDriver driver, String elementName, int seconds)
			throws IOException {
		try {
			logger.info("---------Waiting for visibility of element---------" + elementName);
			MobileActionUtil.isEleDisplayed(element, driver, elementName, 2);
		} catch (Exception e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + elementName);
		} catch (AssertionError e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + elementName);
			throw e;
		}
	}

	public static void clickElement(WebElement element, AndroidDriver driver, String elementName) throws Exception {

		try {			
			logger.info("---------Verifying element is displayed or not ---------");
			element.click();
			logger.info("After Click on: " + elementName);
			MyExtentListeners.test.pass("Verifying if user is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			throw error;
		}
	}

	public static void explicitWaitForElement(AndroidDriver driver, String locator) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}
		catch(Exception e) {
			String timeStamp = LocalDateTime.now().toString().replace(':', '-');
			File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./errorScreenshots/S_"+timeStamp+".png");
			FileUtils.copyFile(tempFile, destFile);
		}
	}

	public static void getTextContentFromElementList(List<WebElement> list) {
		for(WebElement ele:list) {
			System.out.println("Text displayed is : "+ele.getText());
			MyExtentListeners.test.pass("User is able to see Text Content : "+ele.getText());
		}
	}

	public static void verticalSwipeByCoordinates(AndroidDriver driver,int startX, int endX, int startY, int endY) {
		try {
			driver.swipe(startX, startY, endX, endY, 2000);
			MyExtentListeners.test.pass("User able to swipe succesfully");
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}




}
