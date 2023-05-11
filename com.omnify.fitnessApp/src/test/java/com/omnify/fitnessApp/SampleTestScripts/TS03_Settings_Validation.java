package com.omnify.fitnessApp.SampleTestScripts;

import org.testng.annotations.Test;
import com.omnify.fitnessApp.init.InitializePages;
import com.omnify.fitnessApp.library.BaseLib;

public class TS03_Settings_Validation extends BaseLib {
	@Test
	public void reminderCheck() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.homePage.clickOnMoreButton();
		init.settingsPage.clickOnSettingsBtn();
		init.settingsPage.getAllOptionsInSettings();

	}


}
