package testbase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public final class ExtentFactory {

	private static ExtentFactory extentFactory = null;
	private ExtentReports extentReports;
	// directory where output is to be printed
	String reportPath = "./output/";

	public static ExtentFactory getInstance() {

		if (extentFactory == null) {
			extentFactory = new ExtentFactory();
		}
		return extentFactory;
	}

	private ExtentFactory() {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		
		StringBuilder report = new StringBuilder();
		report.append(reportPath)
		//.append("/")
		//.append(formater.format(calendar.getTime()))
		//.append("/Tabular/")
		.append("Report")
		.append(formater.format(calendar.getTime()))
		.append(".html");

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(report.toString());
		htmlReporter.loadXMLConfig("extent-config.xml");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		extentReports.setSystemInfo("System info: ", System.getProperty("os.arch"));
		extentReports.setSystemInfo("OS: ", System.getProperty("os.name"));
		extentReports.setSystemInfo("Version: ", System.getProperty("os.version"));
		extentReports.setSystemInfo("Author: ", System.getProperty("user.name"));
	}

	public ExtentReports getExtentReports() {
		return extentReports;
	}
}
