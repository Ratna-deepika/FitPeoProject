package com.FitPeo.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.FitPeo.Base.BaseClass;

public class Login_PageObject extends BaseClass {

	ControlFunctionality cf = new ControlFunctionality();

	@FindBy(xpath = "//div[text()='Revenue Calculator']")
	@CacheLookup
	public WebElement revenueCalLink;

	@FindBy(xpath = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInp')]")
	@CacheLookup
	public WebElement revenueCalScrollBarTxtBx;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-6 css-iol86l']")
	@CacheLookup
	public WebElement revenueGrossCard;

	@FindBy(xpath = "//span[@style='left: 0%; width: 41%;']")
	@CacheLookup
	public WebElement targetValue;

	@FindBy(xpath = "//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMedium M')]")
	@CacheLookup
	public WebElement scrollIcon;

	@FindBy(xpath = "//p[text()='CPT-99091']/following-sibling::label/span/input")
	@CacheLookup
	public WebElement cpt99091CheckBx;

	@FindBy(xpath = "//p[text()='CPT-99453']/following-sibling::label/span/input")
	@CacheLookup
	public WebElement cpt99453CheckBx;

	@FindBy(xpath = "//p[text()='CPT-99454']/following-sibling::label/span/input")
	@CacheLookup
	public WebElement cpt99454CheckBx;

	@FindBy(xpath = "//p[text()='CPT-99474']/following-sibling::label/span/input")
	@CacheLookup
	public WebElement cpt99474CheckBx;

	@FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month']/following-sibling::p")
	@CacheLookup
	public WebElement totalRecurringReimb;

	@FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
	@CacheLookup
	public WebElement totalRecurringReimbusrsementForAllPatientsField;

	@FindBy(xpath = "//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']")
	public WebElement tt;

	WebDriverWait wait;
	Actions act = new Actions(driver);

	public Login_PageObject() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void clickOnRevenueLink() throws Throwable {

		try {
			
			Actions act = new Actions(driver);
			//wait for Revenue Link visible
			wait.until(ExpectedConditions.visibilityOf(revenueCalLink));
			//Once visible check it is displaying or not
			if (revenueCalLink.isDisplayed()) {
				System.out.println("=====Driver is in Home Page=======");
				act.moveToElement(revenueCalLink).perform();
				//click on revenue link
				revenueCalLink.click();
				if (revenueGrossCard.isDisplayed()) {
					System.out.println("======Driver is on Revenue Calculater Page======");
				}

				Thread.sleep(2000);

			}

		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			System.err.println("Error: Stale Element Reference Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Stale Element Reference Exception: Element is no longer attached to the DOM.", e);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Error: No Such Element Exception occurred.");
			e.printStackTrace();
			throw new Throwable("No Such Element Exception: Could not find the specified element.", e);
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Error: Timeout Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Timeout Exception: Element not visible or clickable within the timeout period.", e);
		} catch (InterruptedException e) {
			System.err.println("Error: Interrupted Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Interrupted Exception: The thread was interrupted during execution.", e);
		} catch (Exception e) {
			System.err.println("An unexpected error occurred.");
			e.printStackTrace();
			throw new Throwable("An unexpected error occurred during the execution of the test.", e);
		}
	}

	public void adjustSliderAndTextBox() throws Throwable {
		try {
			act.scrollToElement(revenueCalScrollBarTxtBx);
			//wait until slider
			wait.until(ExpectedConditions.visibilityOf(scrollIcon));

			WebElement slider = driver.findElement(By.cssSelector("span.MuiSlider-thumb"));
			WebElement sliderInput = driver.findElement(By.cssSelector("input[aria-valuenow]"));
			String targetLeft = "41.10%";
			String targetValue = "820";
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(
					"arguments[0].style.left = arguments[1]; "
							+ "arguments[0].setAttribute('aria-valuenow', arguments[2]); "
							+ "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); "
							+ "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
					slider, targetLeft, targetValue);
			jsExecutor.executeScript("arguments[0].click();", slider);

			act.moveToElement(slider).click().perform();
			act.sendKeys(slider, Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).perform();
			// Verify the slider moved to the correct value
			String sliderValue = sliderInput.getAttribute("aria-valuenow");
			System.out.println("Slider moved to value: " + sliderValue);
			wait.until(ExpectedConditions.visibilityOf(revenueCalScrollBarTxtBx));
			Thread.sleep(2000);
			//clear the exixiting value and give new value
			act.moveToElement(revenueCalScrollBarTxtBx).click().perform();
			act.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).perform();
			revenueCalScrollBarTxtBx.sendKeys("560");
			Thread.sleep(2000);
			System.out.println("Slider moved to value: " + sliderValue);
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			System.err.println("Error: Stale Element Reference Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Stale Element Reference Exception: Element is no longer attached to the DOM.", e);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Error: No Such Element Exception occurred.");
			e.printStackTrace();
			throw new Throwable("No Such Element Exception: Could not find the specified element.", e);
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Error: Timeout Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Timeout Exception: Element not visible or clickable within the timeout period.", e);
		} catch (InterruptedException e) {
			System.err.println("Error: Interrupted Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Interrupted Exception: The thread was interrupted during execution.", e);
		} catch (Exception e) {
			System.err.println("An unexpected error occurred.");
			e.printStackTrace();
			throw new Throwable("An unexpected error occurred during the execution of the test.", e);
		}
	}

	public void selectTheCheckBoxes() throws Throwable {

		try {
			//select the check boxes once those are visible
			act.moveToElement(cpt99474CheckBx).perform();
			act.moveToElement(cpt99474CheckBx).click(cpt99474CheckBx).perform();
			Thread.sleep(2000);
			act.moveToElement(cpt99091CheckBx).perform();
			act.moveToElement(cpt99091CheckBx).click(cpt99091CheckBx).perform();
			act.moveToElement(cpt99453CheckBx).perform();
			act.moveToElement(cpt99453CheckBx).click(cpt99453CheckBx).perform();
			act.moveToElement(cpt99454CheckBx).perform();
			act.moveToElement(cpt99454CheckBx).click(cpt99454CheckBx).perform();
			Thread.sleep(3000);
			//WebElement slider = driver.findElement(By.cssSelector("span.MuiSlider-thumb"));
			WebElement sliderInput = driver.findElement(By.cssSelector("input[aria-valuenow]"));
			String sliderValue = sliderInput.getAttribute("aria-valuenow");

			System.out.println("The Total Recurring Reimbursment Amount when the " + sliderValue + "is :"
					+ totalRecurringReimb.getText());
			System.out.println("The Total Recurring Reimbusrment for all the Patients Field "
					+ totalRecurringReimbusrsementForAllPatientsField.getText());
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			System.err.println("Error: Stale Element Reference Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Stale Element Reference Exception: Element is no longer attached to the DOM.", e);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Error: No Such Element Exception occurred.");
			e.printStackTrace();
			throw new Throwable("No Such Element Exception: Could not find the specified element.", e);
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Error: Timeout Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Timeout Exception: Element not visible or clickable within the timeout period.", e);
		} catch (InterruptedException e) {
			System.err.println("Error: Interrupted Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Interrupted Exception: The thread was interrupted during execution.", e);
		} catch (Exception e) {
			System.err.println("An unexpected error occurred.");
			e.printStackTrace();
			throw new Throwable("An unexpected error occurred during the execution of the test.", e);
		}
	}

	public void adjustTheSlider() throws Throwable {
		try {
			// WebElement slider =
			// driver.findElement(By.cssSelector("span.MuiSlider-thumb"));
			WebElement sliderInput = driver.findElement(By.cssSelector("input[aria-valuenow]"));
			String sliderValue = sliderInput.getAttribute("aria-valuenow");
			act.moveToElement(revenueCalScrollBarTxtBx).click().perform();
			//enter new value by clearing the existing value
			act.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).perform();
			revenueCalScrollBarTxtBx.sendKeys("820");
			Thread.sleep(2000);
			sliderValue = sliderInput.getAttribute("aria-valuenow");
			System.out.println("The Slider value is adjusted to " + sliderValue);
			if (sliderValue.equalsIgnoreCase("820")) {
				act.moveToElement(cpt99474CheckBx).perform();
				wait.until(ExpectedConditions.visibilityOf(totalRecurringReimb));
				System.out.println("The Total Recurring Reimbursment Amount when the slider value is " + sliderValue
						+ " :" + totalRecurringReimb.getText());

			

			}
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			System.err.println("Error: Stale Element Reference Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Stale Element Reference Exception: Element is no longer attached to the DOM.", e);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("Error: No Such Element Exception occurred.");
			e.printStackTrace();
			throw new Throwable("No Such Element Exception: Could not find the specified element.", e);
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Error: Timeout Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Timeout Exception: Element not visible or clickable within the timeout period.", e);
		} catch (InterruptedException e) {
			System.err.println("Error: Interrupted Exception occurred.");
			e.printStackTrace();
			throw new Throwable("Interrupted Exception: The thread was interrupted during execution.", e);
		} catch (Exception e) {
			System.err.println("An unexpected error occurred.");
			e.printStackTrace();
			throw new Throwable("An unexpected error occurred during the execution of the test.", e);
		}
	}

}
