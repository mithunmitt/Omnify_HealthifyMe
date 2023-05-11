package com.omnify.fitnessApp.library;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;



public class Emulator {

	public static String userHomePath = System.getProperty("user.home");
	public static final String NODEJSPATH = "C:\\Program Files\\nodejs\\node.exe";
	public static final String NPMPATH = System.getProperty("user.home")
			+ "\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	public static final String ADBPATH = userHomePath + "\\AppData\\Local\\Android\\Sdk\\platform-tools"
			+ File.separator + "adb";
	public static final String EMULATORPATH = userHomePath + "\\AppData\\Local\\Android\\Sdk\\emulator" + File.separator
			+ "emulator";


	public static URL startServer() {
		URL appiumServerURL;
		// Set Capabilities
		System.out.println("Appium Server Starting..!");
		DesiredCapabilities cap = new DesiredCapabilities();
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		cap.setCapability("noReset", "false");
		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File(NODEJSPATH));
		builder.withAppiumJS(new File(NPMPATH));
		builder.withIPAddress(BaseLib.properties.getProperty("hostAddress"));
		builder.usingPort(Integer.parseInt(BaseLib.properties.getProperty("port")));

		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
		service.start();
		appiumServerURL = service.getUrl();
		System.out.println(appiumServerURL);
		System.out.println("Appium Server Started Successfully...!");
		return appiumServerURL;
	}



	public static void launchEmulator() {
		System.out.println("Start emulator...!");
		String avd = BaseLib.properties.getProperty("avd");

		String[] aCommand = new String[] { EMULATORPATH, "-avd", avd };
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(50, TimeUnit.SECONDS);
			System.out.println("Emulator launched successfully..!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeEmulator() {
		System.out.println(("Killing emulator...!"));
		String[] aCommand = new String[] { ADBPATH, "emu", "kill" };
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(10, TimeUnit.SECONDS);
			System.out.println("Emulator closed successfully..!");
		} catch (Exception e) {
			System.out.println("Unable to close the emulator");
		}
	}

	public static void stopAppiumServer() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			System.out.println("Appium server stopped..!");
		} catch (Exception e) {
			System.out.println("Unable to stop the appium server");
		}
	}

}
