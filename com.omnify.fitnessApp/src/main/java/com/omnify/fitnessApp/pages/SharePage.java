package com.omnify.fitnessApp.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.omnify.fitnessApp.library.BasePage;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import com.omnify.fitnessApp.util.MobileActionUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class SharePage extends BasePage {

	public SharePage(AndroidDriver driver) {
		super(driver);
	}

	//Element to identify user details
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.healthifyme.basic:id/user_progress_text']")
	private WebElement userDetails;

	//Element to identify activity details
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_progress_tracker']")
	private List<WebElement> activityDetails;

	//Element to identify share option 
	@FindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	private WebElement shareOption;

	
	public void clickOnAndroidBackBtn() throws Exception {
		MobileActionUtil.explicitWaitForElement(driver, "//android.widget.TextView[@resource-id='android:id/title']");
		driver.pressKeyCode(AndroidKeyCode.BACK);
		MyExtentListeners.test.pass("User Clicked Android Back Button");
	}
	
	public void getUserDetails() throws Exception {
		MobileActionUtil.waitForElement(userDetails, driver, "User Details", 3);
		System.out.println("User Details is: "+userDetails.getText());
		MyExtentListeners.test.pass("User Details is: "+userDetails.getText());
	}
	
	public void getAllActivityProgress() {
		MobileActionUtil.getTextContentFromElementList(activityDetails);
	}
}
