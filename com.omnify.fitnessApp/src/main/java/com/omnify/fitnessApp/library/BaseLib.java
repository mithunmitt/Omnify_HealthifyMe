
package com.omnify.fitnessApp.library;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.omnify.fitnessApp.init.GlobalVariables;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseLib {
	public static GlobalVariables globalVar = new GlobalVariables();
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	protected URL appiumServerURL;
	public static Properties properties;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String CONFIGPATHEN = sDirPath + "\\src\\main\\resources\\config.properties";
	public static final String apkPath= sDirPath+ "\\src\\test\\resources\\app\\healthifyme-v19-9.apk";

	static {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATHEN);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@BeforeSuite(alwaysRun = true)
	public void launchAppiumAndEmulator() throws IOException {

		// Launching Appium Server
		appiumServerURL = Emulator.startServer();
		System.out.println("Appium Server URL: " + appiumServerURL);
		System.out.println("Appium Server started Sucessfully..!");

		// Launching Emulator
		Emulator.launchEmulator();
	}


	@BeforeMethod(alwaysRun = true)
	public void _LaunchApp() throws Exception {

		//Launch Application
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("PlatformName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("Version"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("DeviceName"));
		cap.setCapability(MobileCapabilityType.UDID, properties.getProperty("DeviceUDID"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("systemPort", 8200);
		cap.setCapability(MobileCapabilityType.FULL_RESET, false);
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability("waitForIdleTimeout", 5000);
		cap.setCapability("newCommandTimeout", 450000);
		cap.setCapability("adbExecTimeout", 450000);
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("autoAcceptAlert", true);
		cap.setCapability("allowDenyPermission", true);		
		cap.setCapability(MobileCapabilityType.APP, apkPath);

		//com.healthifyme.basic/com.healthifyme.basic.activities.LaunchActivity
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.healthifyme.basic");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.healthifyme.basic.activities.LaunchActivity");


		globalVar.driver = new AndroidDriver(new URL("http://127.0.0.1:" + properties.getProperty("port") + "/wd/hub"), cap);
		globalVar.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


	}

	@AfterMethod(alwaysRun = true)
	public void result(ITestResult res) throws Exception {
		globalVar.driver.closeApp();
	}

	@AfterSuite(alwaysRun = true)
	public void finish(ITestContext context) throws Exception {

		// Closing Emulator
		Emulator.closeEmulator();

		// Stopping Appium Server
		Emulator.stopAppiumServer();
	}

}
