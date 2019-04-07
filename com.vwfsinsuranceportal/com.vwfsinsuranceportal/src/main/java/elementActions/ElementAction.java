package elementActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testbase.ConstantProjectValues;

public class ElementAction {

	private WebDriverWait wait;
	private WebDriver driver;
	private ExtentTest extentTest;
	private Logger log = Logger.getLogger(getClass().getName());

	public ElementAction(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		this.extentTest = extentTest;
		wait = new WebDriverWait(this.driver, ConstantProjectValues.TIMEOUT_60_SECONDS);
	}

	public boolean checkElementAndEnterText(WebElement element, String value, String elementName) {
		if (checkAndWaitForElementToBePresent(element, elementName)) {
			String message = "Entering " + value + " in to " + elementName;
			extentTest.log(Status.PASS, message);
			log.info(message);
			element.sendKeys(value);
			return true;
		} else {
			String message = "Unable to enter " + value + " in to " + elementName;
			extentTest.log(Status.FAIL, message);
			log.error(message);
			return false;
		}
	}

	public boolean checkElementAndClick(WebElement element, String elementName) {
		if (checkAndWaitForElementToBePresent(element, elementName)) {
			String message = "Clicking on " + elementName;
			extentTest.log(Status.PASS, message);
			log.info(message);
			element.click();
			return true;
		} else {
			String message = "Unable to click " + elementName;
			extentTest.log(Status.FAIL, message);
			log.error(message);
			return false;
		}
	}

	public String getTextIfElementExists(WebElement element, String elementName) {

		if (checkAndWaitForElementToBePresent(element, elementName)) {
			String textInElement = element.getText();
			if (textInElement != null) {
				String message = elementName + " having value: " + textInElement;
				extentTest.log(Status.PASS, message);
				log.info(message);
				return textInElement;
			}
		}
		String message = "Unable to read from  " + elementName;
		extentTest.log(Status.FAIL, message);
		log.error(message);
		return null;
	}

	public boolean checkAndWaitForElementToBePresent(WebElement element, String elementName) {

		try {
			log.info("Waiting for " + elementName + " to be present on the web");
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (TimeoutException e) {
			log.error(elementName + " is not visible");
			extentTest.fail(e);
			return false;
		}
	}
}
