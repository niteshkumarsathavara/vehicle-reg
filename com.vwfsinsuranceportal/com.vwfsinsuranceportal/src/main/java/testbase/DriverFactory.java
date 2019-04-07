package testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private WebDriver driver;
	public void getUrl(String url) {
		this.driver.get(url);
	}
	public void maximise() {
		this.driver.manage().window().maximize();
	}
	
	public WebDriver getDriver(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",
					 System.getProperty("user.dir") +
					 "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
			return driver;
		} else if (browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver",
					 System.getProperty("user.dir") +
					 "/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
			return driver;
		} else {
			return null;
		}
	}
	
}
