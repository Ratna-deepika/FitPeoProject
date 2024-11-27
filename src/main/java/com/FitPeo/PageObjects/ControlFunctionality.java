package com.FitPeo.PageObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ControlFunctionality {

	public boolean setValue(WebElement prmElement, String prmValue, long Timeout) {

		try {
			waitforElement(prmElement, Timeout);

			if (iselementExists(prmElement, 2000) == true) {
				String s = Keys.chord(Keys.CONTROL, "a");
				prmElement.sendKeys(s);
				prmElement.sendKeys(Keys.DELETE);
				waitforElement(prmElement, Timeout);
				prmElement.sendKeys(prmValue, Keys.TAB);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean selectElement(WebElement prmElement, long Timeout) throws InterruptedException {

		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			prmElement.click();
			return true;
		} else {
			return false;
		}
	}

	public boolean uploadFile(WebElement prmElement, long Timeout) throws InterruptedException {

		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			prmElement.sendKeys("C:\\Users\\100013\\Desktop\\TicketHistory.xlsx");

			return true;
		} else {
			return false;
		}

	}

	public String getValue(WebElement prmElement, long Timeout) throws InterruptedException {
		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			return prmElement.getText();
		} else {
			return "";
		}

	}

	public boolean SelectCombobyVisibleText(WebElement prmElement, String prmValue, long Timeout)
			throws InterruptedException {
		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			Select cmbElement = new Select(prmElement);
			cmbElement.selectByVisibleText(prmValue);
			return true;
		} else {
			return false;
		}

	}

	public boolean SelectCombobyIndex(WebElement prmElement, int prmindexvalue, long Timeout)
			throws InterruptedException {
		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			Select cmbElement = new Select(prmElement);
			cmbElement.selectByIndex(prmindexvalue);
			return true;
		} else {
			return false;
		}

	}

	public boolean SelectCombobyValue(WebElement prmElement, String prmvalue, long Timeout)
			throws InterruptedException {
		waitforElement(prmElement, Timeout);
		if (prmElement.isDisplayed() == true) {
			Select cmbElement = new Select(prmElement);
			cmbElement.selectByValue(prmvalue);
			return true;
		} else {
			return false;
		}

	}

	public boolean iselementExists(WebElement prmElement) {
		try {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}

			prmElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public boolean iselementExists(WebElement prmElement, long maxwaitTime) {
		boolean Elementfound;
		long timeout;
		Date Starttime = new Date();
		do {

			try {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}

				Elementfound = prmElement.isDisplayed();
				if (Elementfound == true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					break;
				}

			} catch (NoSuchElementException e) {
				Elementfound = false;
			}
			Date Endtime = new Date();
			timeout = Endtime.getTime() - Starttime.getTime();

		} while (timeout < maxwaitTime);
		return Elementfound;
	}

	public void waitforElement(WebElement prmElement) {
		long timeout;
		long Timedout = 20000;
		Date Starttime = new Date();
		do {
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			}

			try {
				if (prmElement.isDisplayed() == true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}

				}

			} catch (NoSuchElementException e) {

			}
			Date Endtime = new Date();
			timeout = Endtime.getTime() - Starttime.getTime();
		} while (timeout < Timedout);
	}

	public void waitforElement(WebElement prmElement, long timeinmilliseconds) throws InterruptedException {
		long timeout;
		long Timedout = timeinmilliseconds;
		Date Starttime = new Date();
		do {
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			}

			try {
				if (prmElement.isDisplayed() == true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}

					return;
				}

			} catch (NoSuchElementException e) {

			}
			Date Endtime = new Date();
			timeout = Endtime.getTime() - Starttime.getTime();
		} while (timeout < Timedout);
	}

	public void scrollIntoView(WebDriver driver, String prmElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prmElement);
	}

	public void validate(WebDriver driver, String prmElement) {
		List<WebElement> Rows = driver.findElements(By.xpath("//table/tbody/tr"));
		for (WebElement row : Rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement cellData : cols) {
				System.out.println(cellData.getText());
				if (cellData.getText().equals(prmElement)) {
					System.out.println(prmElement);
					System.out.println("test Passed");
				}
			}
		}
	}

	/*
	 * driver.findElement(By.id("first_date_picker")).click(); selectDate(driver,
	 * "05/June/2015");
	 * 
	 * driver.findElement(By.id("second_date_picker")).click(); selectDate(driver,
	 * "05/June/2025");
	 */

	public void selectDate(WebDriver driver, String date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-mm-yyyy");
			targetDateFormat.setLenient(false);
			Date formattedTargetDate = targetDateFormat.parse(date);
			calendar.setTime(formattedTargetDate);
		} catch (Exception e) {
			throw new Exception("Invalid date is provided, please check the input date!!");
		}

		int targetMonth = calendar.get(Calendar.MONTH);
		int targetYear = calendar.get(Calendar.YEAR);
		int targetDay = calendar.get(Calendar.DAY_OF_MONTH);

		String currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
		calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentYear = calendar.get(Calendar.YEAR);

		while (currentMonth < targetMonth || currentYear < targetYear) {
			driver.findElement(By.className("ui-datepicker-next")).click();
			currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentMonth = calendar.get(Calendar.MONTH);
			currentYear = calendar.get(Calendar.YEAR);
		}

		while (currentMonth > targetMonth || currentYear > targetYear) {
			driver.findElement(By.className("ui-datepicker-prev")).click();
			currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentMonth = calendar.get(Calendar.MONTH);
			currentYear = calendar.get(Calendar.YEAR);
		}

		if (currentMonth == targetMonth && currentYear == targetYear)
			driver.findElement(By.xpath(
					"//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="
							+ targetDay + "]"))
					.click();
		else
			throw new Exception("unable to select the date because of current and target dates mismatch");
	}

	public void retrysendKeys(WebElement element, String text, long timeinmilliseconds) {

		int attempts = 0;

		while (attempts > 3) {
			try {
				element.wait(timeinmilliseconds);
				element.clear();
				element.sendKeys(text);
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
				attempts++;
			}

		}

	}

	public boolean setMultiValue(WebElement prmElement, String prmValue, long Timeout) {

		try {
			waitforElement(prmElement, Timeout);

			if (iselementExists(prmElement, 2000) == true) {
				// String s = Keys.chord(Keys.CONTROL, "a");
				// prmElement.sendKeys(s);
				// prmElement.sendKeys(Keys.DELETE);
				waitforElement(prmElement, Timeout);
				prmElement.sendKeys(prmValue, Keys.TAB);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

}
