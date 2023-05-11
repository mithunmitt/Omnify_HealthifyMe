package com.omnify.fitnessApp.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.omnify.fitnessApp.library.BasePage;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import com.omnify.fitnessApp.util.MobileActionUtil;
import io.appium.java_client.android.AndroidDriver;

public class SettingsPage extends BasePage {

	public SettingsPage(AndroidDriver driver) {
		super(driver);
	}

	// Element to identify Settings button
	@FindBy(xpath = "//android.widget.TextView[@text='Settings']")
	private WebElement settingsBtn;

	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.healthifyme.basic:id/parent_layout']//android.widget.Button")
	private List<WebElement> allSettingOptions;


	public void clickOnSettingsBtn() throws Exception {
		MobileActionUtil.scrollToParticularText(driver, "Settings");
		MobileActionUtil.waitForElement(settingsBtn, driver, "Settings Button", 3);
		MobileActionUtil.clickElement(settingsBtn, driver, "Settings Button");
	}

	public void getAllOptionsInSettings() {
		try {
			for(WebElement option : allSettingOptions) {
				System.out.println("Settings Option include : "+option.getText());
				MyExtentListeners.test.pass("Settings Option displayed : "+option.getText());
			}
		}catch (Exception e) {
			System.out.println("No Setting Options found");
			MyExtentListeners.test.pass("Settings Option not found");
			e.printStackTrace();
		}
	}
}
