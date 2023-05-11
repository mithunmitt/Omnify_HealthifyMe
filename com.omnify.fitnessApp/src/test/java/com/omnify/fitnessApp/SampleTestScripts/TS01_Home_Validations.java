package com.omnify.fitnessApp.SampleTestScripts;

import org.testng.annotations.Test;

import com.omnify.fitnessApp.init.InitializePages;
import com.omnify.fitnessApp.library.BaseLib;

public class TS01_Home_Validations extends BaseLib {
	@Test
	public void homeCheck() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.homePage.getAllBottomNavigationText();
		init.homePage.clickOnPlansButtonAndVerifyTitle();
		init.homePage.clickOnChillButtonAndVerifyTitle();
		init.homePage.clickOnMoreButton();
		init.homePage.clickOnHomeButtonAndVerifyTitle();
	}

}
