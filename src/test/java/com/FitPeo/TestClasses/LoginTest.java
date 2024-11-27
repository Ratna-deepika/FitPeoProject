package com.FitPeo.TestClasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FitPeo.Base.BaseClass;
import com.FitPeo.PageObjects.Login_PageObject;

public class LoginTest extends BaseClass {

	Login_PageObject LoginPg;

	public LoginTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		Initialization();
		LoginPg = new Login_PageObject();
	}

	@Test(priority = 1)
	public void TestHomePage() throws Throwable {
		LoginPg.clickOnRevenueLink();
	}
	
	@Test(priority = 2)
	public void TestSlider() throws Throwable {
		LoginPg.adjustSliderAndTextBox();
	}
	
	@Test (priority = 3)
	public void TestCheckBoxes() throws Throwable {
		LoginPg.selectTheCheckBoxes();
	}
	
	@Test(priority = 4)
	public void TestAdjustSliderAgain() throws Throwable {
		LoginPg.adjustTheSlider();
	}

	@AfterClass
	public void tearDown()  {
		driver.quit();
	}

}
