package com.omnify.fitnessApp.SampleTestScripts;

import org.testng.annotations.Test;

import com.omnify.fitnessApp.init.InitializePages;
import com.omnify.fitnessApp.library.BaseLib;

public class TS02_Reminder_Validations extends BaseLib {
	@Test
	public void reminderCheck() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.homePage.clickOnReminderButton();
		init.reminderPage.validateReminderTitles();
		init.reminderPage.getAllReminderActivities();

	}
}
