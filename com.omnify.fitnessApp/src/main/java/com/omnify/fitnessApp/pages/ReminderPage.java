package com.omnify.fitnessApp.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.omnify.fitnessApp.library.BasePage;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import com.omnify.fitnessApp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ReminderPage extends BasePage {

	public ReminderPage(AndroidDriver driver) {
		super(driver);
	}

	// Element to identify reminder titles
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_reminder_title']")
	private List<WebElement> allReminderTitles;

	// Element to identify all reminder activities
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_reminder']")
	private List<WebElement> allReminderActivities;

	public void validateReminderTitles() throws Exception {
		for(WebElement reminderTitle : allReminderTitles) {
			MobileActionUtil.waitForElementToLoad(3);
			if(reminderTitle.getText().equalsIgnoreCase("Active Reminders") || reminderTitle.getText().equalsIgnoreCase("Reminders you can set")) {
				MyExtentListeners.test.pass("Reminder Title is displayed: "+reminderTitle.getText());
			}
			else {
				MyExtentListeners.test.fail("Reminder Titles are not as expected");
				Assert.fail("Reminder Titles are not as expected");
			}
		}
	}

	public void getAllReminderActivities() {
		for(WebElement reminderActivity: allReminderActivities) {
			System.out.println("Activity includes : "+reminderActivity.getText());
			MyExtentListeners.test.pass("Activities displayed: "+reminderActivity.getText());
		}
	}


}
