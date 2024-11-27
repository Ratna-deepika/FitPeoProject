package com.FitPeo.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop = new Properties(); 

	public BaseClass() {

		try {
			String configFilePath = "D:/FitPeo/src/main/java/com/FitPeo/Config/FitPeo_Config";
			FileInputStream ip = new FileInputStream(configFilePath);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error reading properties file: " + e.getMessage());
		}

	}

	public void Initialization() {
		String browserName = prop.getProperty("browserName");
		if ("chrome".equalsIgnoreCase(browserName)) {
			String chromedriverPath = System.getProperty("user.dir") + "/src/main/java/Chrome_Driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		} else if ("edge".equalsIgnoreCase(browserName)) {
			String edgedriverPath = System.getProperty("user.dir") + "/src/main/java/Chrome_Driver/msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", edgedriverPath);
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(options);

		} else {
			throw new RuntimeException("Unsupported browser: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to open URL: " + prop.getProperty("url"));
		}
	}

	public static void ScreenShots(ITestResult result) throws Throwable {
		if (ITestResult.FAILURE == result.getStatus()) {
			WebDriver webDriver = driver;
			File screenShotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String testMethodName = result.getMethod().getMethodName();
			String screenshotPath = "D:/FitPeo/FailureSs"
					+ testMethodName + "_" + timestamp + ".png";
			FileUtils.copyFile(screenShotFile, new File(screenshotPath));
			System.out.println("Screenshot saved to: " + screenshotPath);
		}
	}

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
