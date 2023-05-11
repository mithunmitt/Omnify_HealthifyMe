package com.omnify.fitnessApp.init;

import com.omnify.fitnessApp.pages.HomePage;
import com.omnify.fitnessApp.pages.ReminderPage;
import com.omnify.fitnessApp.pages.SettingsPage;
import com.omnify.fitnessApp.pages.SharePage;
import com.omnify.fitnessApp.pages.TrackersPage;

import io.appium.java_client.android.AndroidDriver;

public class InitializePages {

	public HomePage homePage;
	public ReminderPage reminderPage;
	public SettingsPage settingsPage;
	public TrackersPage trackersPage;
	public SharePage sharePage;

	public  InitializePages(AndroidDriver driver) {
		
		homePage = new HomePage(driver);
		reminderPage = new ReminderPage(driver);
		settingsPage = new SettingsPage(driver);
		trackersPage = new TrackersPage(driver);
		sharePage = new SharePage(driver);
	}
}
