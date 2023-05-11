package com.omnify.fitnessApp.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.omnify.fitnessApp.library.BasePage;
import com.omnify.fitnessApp.listener.MyExtentListeners;
import com.omnify.fitnessApp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class HomePage extends BasePage {

	public HomePage(AndroidDriver driver) {
		super(driver);
	}


	// Element to identify reminder button
	@FindBy(xpath = "//android.widget.Button[@content-desc='Reminders']")
	private WebElement reminderBtn;

	//Element to identify share button 
	@FindBy(xpath="//android.widget.Button[@content-desc='Share']")
	private WebElement shareBtn;

	// Element to identify bottom navigation button text
	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.healthifyme.basic:id/bnv_dashboard']//android.widget.TextView")
	private List<WebElement> allBottomNavText;

	//Element to identify home button in  bottom navigation
	@FindBy(xpath="//android.widget.TextView[@text='Home']")
	private WebElement homeBtn;

	//Element to identify plans button in  bottom navigation
	@FindBy(xpath="//android.widget.TextView[@text='Plans']")
	private WebElement plansBtn;

	//Element to identify chill button in  bottom navigation
	@FindBy(xpath="//android.widget.TextView[@text='Chill']")
	private WebElement chillBtn;

	//Element to identify more button in  bottom navigation
	@FindBy(xpath="//android.widget.TextView[@text='More']")
	private WebElement moreBtn;

	//Element to fetch Screen Title
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_tb_title']")
	private WebElement screenTitle;

	//Element to fetch Screen Title for Chill
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_actionbar_dashboard']")
	private WebElement screenTitleDashboard;

	//Element to identify View All Trackers tile
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.healthifyme.basic:id/card_view_all_tracker']")
	private WebElement viewAllTrackerTile;



	public void clickOnReminderButton() throws Exception {
		MobileActionUtil.waitForElement(reminderBtn, driver, "Reminder Button", 3);
		MobileActionUtil.clickElement(reminderBtn, driver, "Reminder Button");
	}

	public void clickOnShareButton() throws Exception {
		MobileActionUtil.waitForElement(shareBtn, driver, "Share Button", 3);
		MobileActionUtil.clickElement(shareBtn, driver, "Share Button");
	}



	public void getAllBottomNavigationText() {
		try {
			for(WebElement navText:allBottomNavText) {
				System.out.println("Navigaton Bar Options include: "+navText.getText());
				MyExtentListeners.test.pass("Navigaton Bar Options include: "+navText.getText());
			}
		}catch (Exception e) {
			System.out.println("Bottom Navigation text could not be verified");
			MyExtentListeners.test.fail("Bottom Navigation text could not be verified");
			e.printStackTrace();
		}
	}

	public void clickOnHomeButtonAndVerifyTitle() throws Exception{
		MobileActionUtil.waitForElement(homeBtn, driver, "Home Button", 3);
		MobileActionUtil.clickElement(homeBtn, driver, "Home Button");
		MobileActionUtil.explicitWaitForElement(driver, "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_tb_title']");
		MobileActionUtil.verifyExpectedText(screenTitleDashboard, "Today");
	}

	public void clickOnPlansButtonAndVerifyTitle() throws Exception{
		MobileActionUtil.waitForElement(plansBtn, driver, "Plans Button", 3);
		MobileActionUtil.clickElement(plansBtn, driver, "Plans Button");
		MobileActionUtil.explicitWaitForElement(driver, "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_tb_title']");
		MobileActionUtil.verifyExpectedText(screenTitle, "My Plans");
	}

	public void clickOnChillButtonAndVerifyTitle() throws Exception{
		MobileActionUtil.waitForElement(chillBtn, driver, "Chill Button", 3);
		MobileActionUtil.clickElement(chillBtn, driver, "Chill Button");
		MobileActionUtil.explicitWaitForElement(driver, "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_actionbar_dashboard']");
		MobileActionUtil.verifyExpectedText(screenTitleDashboard, "Chill");
	}

	public void clickOnMoreButton() throws Exception{
		MobileActionUtil.waitForElement(moreBtn, driver, "More Button", 3);
		MobileActionUtil.clickElement(moreBtn, driver, "More Button");	
	}

	public void clickOnViewAllTrackersTile() throws Exception {
		MobileActionUtil.explicitWaitForElement(driver, "//android.widget.TextView[@resource-id='com.healthifyme.basic:id/tv_tb_title']");
		MobileActionUtil.verticalSwipeByCoordinates(driver, 480, 480, 1800, 500);
		MobileActionUtil.waitForElement(viewAllTrackerTile, driver, "View All Tracker Tile", 3);
		MobileActionUtil.clickElement(viewAllTrackerTile, driver, "View All Tracker Tile");
	}

}
