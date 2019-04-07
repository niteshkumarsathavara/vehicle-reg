package webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import elementActions.ElementAction;

public class InsurancePage {

	private WebDriver driver;
	private ElementAction elementActions;
	private ExtentTest extentTest;
	
	// Enter car registration
	@FindBy(xpath = "//input[@id='vehicleReg']")
	WebElement txtRegistration;

	// Find insurance button
	@FindBy(xpath = "//button[@name='btnfind']")
	WebElement btnFindInsurance;

	// Registration number
	@FindBy(css = ".result")
	WebElement lblRegistration;

	// Insurance details
	@FindAll(@FindBy(css = ".resultDate"))
	List<WebElement> lstOfInsuranceDetails;

	public InsurancePage(WebDriver driver, ExtentTest extentTest) {
		super();
		this.driver = driver;
		this.extentTest = extentTest;
		PageFactory.initElements(this.driver, this);
		elementActions = new ElementAction(this.driver, extentTest);
	}

	public boolean pageLoadedSuccessfully() {
		return elementActions.checkAndWaitForElementToBePresent(txtRegistration, "Registration text");
	}

	public boolean enterRegistration(String regNo) {
		return elementActions.checkElementAndEnterText(txtRegistration, regNo, "Registration No");
	}

	public boolean clickFindVehicalButton() {
		return elementActions.checkElementAndClick(btnFindInsurance, "Find Insurance Button");
	}

	public boolean readInsuranceDetails() {
		if (elementActions.checkAndWaitForElementToBePresent(lblRegistration, "Registration No")) {

			WebElement coverStartDateElement = lstOfInsuranceDetails.get(0);
			WebElement coverEndDateElement = lstOfInsuranceDetails.get(1);

			String coverStartDate = elementActions.getTextIfElementExists(coverStartDateElement, "Cover Start Date");
			String coverEndDate = elementActions.getTextIfElementExists(coverEndDateElement, "Cover End Date");

			if (coverStartDate != null && coverEndDate != null) {
				return true;
			} else {
				extentTest.log(Status.FAIL, "Unable to read cover details, please check RegNo");
				return false;
			}
		} else {
			return false;
		}
	}
}
