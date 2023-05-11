package com.omnify.fitnessApp.SampleTestScripts;

import org.testng.annotations.Test;
import com.omnify.fitnessApp.init.InitializePages;
import com.omnify.fitnessApp.library.BaseLib;

public class TS05_ShareActivity_Validation extends BaseLib {
	@Test
	public void shareActivityCheck() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.homePage.clickOnShareButton();
		init.sharePage.clickOnAndroidBackBtn();
		init.sharePage.getUserDetails();
		init.sharePage.getAllActivityProgress();
	}
}
