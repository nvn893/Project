package pom_demoqa;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.ScreenshotUtil;
import gnrl.WebBase;

public class DynPropertiesPage extends WebBase{

	ExtentTest test;

	public DynPropertiesPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Dynamic Properties']")
	WebElement DPropsHeader;
	
	@FindBy(xpath = "//div[text()='Forms']")
	WebElement FormsHeader;
	@FindBy(xpath = "//h1[@class='text-center']")
	WebElement Header;
	
	@FindBy(xpath = "//button[@id='enableAfter']")
	WebElement DelayedEnableBtn;
	@FindBy(xpath = "//button[@Class='mt-4 text-danger btn btn-primary']")
	WebElement ColorChangeBtn;
	@FindBy(xpath = "//button[@id='visibleAfter']")
	WebElement DelayedVisibleBtn;
	
	// Methods 
	public void navigateToDynPropertiesPage() throws InterruptedException {
	//	ElemHeader.click();
		viewfinder(FormsHeader);
		Thread.sleep(3000);
		DPropsHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		viewfinder(Header);
		Thread.sleep(6000);
	}
	
	public void propertiesCheck() {
		try {
		viewfinder(DelayedEnableBtn);
		
		if (DelayedEnableBtn.isEnabled())
		{
			Assert.assertTrue(true, "Delayed enable button working as expected");
			test.pass("Delayed Enable Button working as intended");
		} else {
			Assert.assertFalse(false, "Delayed enable button not wokring as expected");
			test.fail("Delayed Enable Button NOT functional");
		}
		
		if (ColorChangeBtn.isDisplayed())
		{
			Assert.assertTrue(true, "Color change button working as expected");
			test.pass("Color Change Button working as intended");
		} else {
			Assert.assertFalse(false, "Color Change button not wokring as expected");
			test.fail("Color Change Button NOT functional");
		}
		
		if (DelayedVisibleBtn.isEnabled())
		{
			Assert.assertTrue(true, "Delayed visibility button working as expected");
			test.pass("Delayed Visible Button working as intended");
		} else {
			Assert.assertFalse(false, "Delayed visibility button not wokring as expected");
			test.fail("Delayed Visible Button NOT functional");
		}
	} catch (Exception e)
		{
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "DynamicPropertiesfail");
	       	test.addScreenCaptureFromPath(screenshotPath);
	        test.fail("Dynamic Properties Verification failed " + e.getMessage());
		}
	}
}
