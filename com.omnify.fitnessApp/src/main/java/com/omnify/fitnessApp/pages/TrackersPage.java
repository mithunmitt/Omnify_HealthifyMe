package com.omnify.fitnessApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.omnify.fitnessApp.library.BasePage;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import com.omnify.fitnessApp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class TrackersPage extends BasePage {

	public TrackersPage(AndroidDriver driver) {
		super(driver);
	}
	
	//Element to identify water tracker add/plus button
	@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.healthifyme.basic:id/ib_water_track_plus']")
	private WebElement waterTrackerPlusBtn;
	
	//Element to identify water tracker minus button
	@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.healthifyme.basic:id/ib_water_track_minus']")
	private WebElement waterTrackerMinusBtn;

	//Element to identify number of glasses of water consumed
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_water_goal']")
	private WebElement waterConsumed;


	
	public void clickOnParticularTracker(String trackerName) throws Exception {
		MobileActionUtil.waitForElementToLoad(3);
		try {
		WebElement reqdTracker = driver.findElement(By.xpath("//android.widget.TextView[@text='"+trackerName+"']"));
		reqdTracker.click();
		MyExtentListeners.test.pass("User clicked on: "+trackerName);
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public void editGlassesOfWaterConsumed(int numberOfGlasses, String addOrsub) throws Exception {
		if(addOrsub.equalsIgnoreCase("add")) {
			for(int i=0; i<numberOfGlasses; i++) {
				MobileActionUtil.waitForElement(waterTrackerPlusBtn, driver, "Plus Button", 3);
				MobileActionUtil.clickElement(waterTrackerPlusBtn, driver, "Plus Button");
			}
		}
		else {
			for(int i=numberOfGlasses; i>0 ; i--) {
				MobileActionUtil.waitForElement(waterTrackerMinusBtn, driver, "Minus Button", 3);
				MobileActionUtil.clickElement(waterTrackerMinusBtn, driver, "Minus Button");
			}
		}
	}
	
	public void validateNumberOfGlassesOfWater(int expectedNumberOfGlasses) {
		  String[] arr = waterConsumed.getText().split(" ");
		  String actualNumberOfGlasses = arr[0];
		  System.out.println("Actual Number of Glasses of Water: "+actualNumberOfGlasses);
		if(expectedNumberOfGlasses==Integer.parseInt(actualNumberOfGlasses)) {
			MyExtentListeners.test.pass("User is able to verfiy Actual Number of Glasses of Water Consumed :"+actualNumberOfGlasses);
		}else {
			MyExtentListeners.test.pass("User is not able to verfiy Actual Number of Glasses of Water Consumed");
		}
	}
	
	public void resetWaterGlassestoZero() throws Exception {
		while(waterTrackerMinusBtn.isEnabled())
		{
			MobileActionUtil.clickElement(waterTrackerMinusBtn, driver, "Minus Button");
		}
		MyExtentListeners.test.pass("User is able to reset water glasses to Zero");
	}
}

