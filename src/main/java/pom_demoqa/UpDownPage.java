package pom_demoqa;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.ScreenshotUtil;
import gnrl.WebBase;

public class UpDownPage extends WebBase {

	ExtentTest test;
	
	public UpDownPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Upload and Download']")
	WebElement UpDownHeader;
	
	@FindBy(xpath = "//a[@id='downloadButton']")
	WebElement downloadBtn;
	@FindBy(xpath = "//input[@id='uploadFile']")
	WebElement uploadBtn;
	
	@FindBy(xpath = "//p[@id='uploadedFilePath']")
	WebElement finalPath;
	
	String downloadPath = "C://Users//N//Downloads";
	String uploadPath = System.getProperty("user.dir")+"//resources//testupload.txt";

	// Methods
	public void navigateToLoadPage() {
	//	ElemHeader.click();
		viewfinder(UpDownHeader);
		UpDownHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		viewfinder(downloadBtn);
	}

	public void downloadCheck() throws InterruptedException {
		try {
		downloadBtn.click();
		Thread.sleep(3000);
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		for (int i = 0; i < dirContents.length; i++) {
		    if (dirContents[i].getName().equals("sampleFile.jpeg"))
		     {		    
		         dirContents[i].delete();
		         Assert.assertTrue(true, "File has been downloaded");
		         test.pass("File download working as expected");
		     } 
		 }	
		} catch (Exception e)
		{
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "DownloadFail");
	       	test.addScreenCaptureFromPath(screenshotPath);
	        test.fail("Download Check failed " + e.getMessage());
		}
	}
	
	public void uploadCheck() throws InterruptedException {
		try {
		 uploadBtn.sendKeys(uploadPath);
		 Thread.sleep(3000);
		 if (finalPath.getText().equalsIgnoreCase("C:\\fakepath\\testupload.txt"))
		 {
			 Assert.assertTrue(true, "File upload working");
			 test.pass("File upload working as expected");
		 } else {
			 Assert.assertFalse(false, "File upload not working");
			 test.fail("File upload NOT working as expected");
		 }
		 uploadBtn.submit();
		} catch (Exception e)
			{
				String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "UploadFail");
		       	test.addScreenCaptureFromPath(screenshotPath);
		        test.fail("Upload Check failed " + e.getMessage());
			}
	}
}
