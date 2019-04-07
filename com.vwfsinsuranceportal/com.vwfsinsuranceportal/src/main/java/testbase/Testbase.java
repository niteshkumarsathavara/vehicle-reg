package testbase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Testbase {

	protected WebDriver driver;
	private Logger log = Logger.getLogger(getClass().getName());
	protected ExtentTest extentTest;
	
	@BeforeClass
	public void reportSetUp() {
		// Setting log4j configuration
		PropertyConfigurator.configure("log4j.properties");
		ExtentFactory.getInstance();
	}

	@BeforeMethod
	@Parameters({ "url", "browser" })
	public void setupTestEnvironment(String url, String browser) {
		
		extentTest = ExtentFactory.getInstance().getExtentReports().createTest("Find Insurance");
		extentTest.log(Status.INFO, "Test started");
		
		log.info("Creating " + browser + " browser");
		DriverFactory driverFactory = new DriverFactory();
		this.driver = driverFactory.getDriver(browser);

		log.info("Navigating to " + url);
		driverFactory.getUrl(url);
		extentTest.log(Status.PASS, "Navigate to " + url);
		
		log.info("Maximising browser window ");
		driverFactory.maximise();
	}
	
	@AfterMethod()
	public void endExtentReport(ITestResult result) {
		getResult(result);
		log.info("Closing browser window ");
		this.driver.quit();
	}
	
	@AfterClass
	public void endReport() {
		ExtentFactory.getInstance().getExtentReports().flush();
	}
	
	public void getResult(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " test passed.", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " test failed :  " + result.getThrowable(), ExtentColor.RED));
		}else {
			extentTest.log(Status.SKIP,"Test skipped");
		}
	}
}
