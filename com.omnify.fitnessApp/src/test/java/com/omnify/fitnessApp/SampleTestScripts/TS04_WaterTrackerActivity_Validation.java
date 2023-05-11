package com.omnify.fitnessApp.SampleTestScripts;

import org.testng.annotations.Test;
import com.omnify.fitnessApp.init.InitializePages;
import com.omnify.fitnessApp.library.BaseLib;

public class TS04_WaterTrackerActivity_Validation extends BaseLib {
	@Test
	public void waterActivityCheck() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.homePage.clickOnViewAllTrackersTile();
		init.trackersPage.clickOnParticularTracker("Water Tracker");
		init.trackersPage.resetWaterGlassestoZero();
		init.trackersPage.editGlassesOfWaterConsumed(5, "add");
		init.trackersPage.validateNumberOfGlassesOfWater(5);
		init.trackersPage.editGlassesOfWaterConsumed(2, "sub");
		init.trackersPage.validateNumberOfGlassesOfWater(3);

	}	
}
