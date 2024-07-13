package gnrl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebBase {
	
	public WebDriver driver;
	genProp obj1 = new genProp();
	static String browsername;
	static String url;
	
	ExtentTest test;
	ExtentReports extent = ExtentConfig.getReportObj();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@BeforeMethod
	public void setup (Method method) {
		String testname = method.getName();
		ExtentTest test = extent.createTest(testname);
		extentTest.set(test);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
			extentTest.get().log(Status.FAIL , "Exception Occured");
			if (screenshotPath != null)
			{
				test.addScreenCaptureFromPath(screenshotPath);
			}
		} else if (result.getStatus() == ITestResult.SKIP)
		{
			extentTest.get().log(Status.SKIP , "Test Skipped");
		} 
		else if (result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.get().log(Status.PASS , "Test Passed");
		}
	}
	
	@AfterSuite 
	public void teardownsuite() 
	{
		extent.flush();
	}
	
	protected ExtentTest getTest()
	{
		return extentTest.get();
	}
	
	public void launchbrowser() throws IOException
	{   
		browsername = obj1.getval("dbrowser");
		url = obj1.getval("durl");
		if (browsername.equalsIgnoreCase("chrome"))
		{
			launchchrome();
		} else if (browsername.equalsIgnoreCase("edge")) 
		{
			launchedge();
		} else if (browsername.equalsIgnoreCase("firefox"))
		{
			launchfirefox();
		}
	}
	public void launchchrome()
	{
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void launchedge()
	{
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void launchfirefox()
	{
		driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void viewfinder(WebElement PLACE)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", PLACE);
	}
	public void gotoUrl(String url)
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	public void textentry(By loc, String text)
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((loc))));
		element.sendKeys(text);
	}
	public void click(By lc)
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lc)));
		elem.click();
	}
	public void verifyelement(By lct)
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(lct));
		boolean bn = elem.isDisplayed();
		if (bn)
		{
			System.out.println("Element is Visible");
		} else {
			System.out.println("Element is not visible");
		}
	}
	public void enterText (By lct, String str)
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(lct));
		elem.sendKeys(str);
	}
}
