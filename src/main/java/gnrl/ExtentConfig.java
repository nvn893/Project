package gnrl;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentConfig {
	
	public static ExtentReports getReportObj()
	{
	String path = System.getProperty("user.dir")+"//ExtentReports//test.html";

	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("DEMOQA Project Report");
	reporter.config().setDocumentTitle("Test Run Results");

	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tested By", "Naveen");
	return extent;
	}
}
