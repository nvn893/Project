package pom_demoqa;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.ScreenshotUtil;
import gnrl.WebBase;
import gnrl.genXLS;

public class TextBoxPage extends WebBase {

	ExtentTest test;

	public TextBoxPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Text Box']")
	WebElement TextBoxHeader;

	////

	@FindBy(xpath = "//input[@id='userName']")
	WebElement fullName;
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement email;
	@FindBy(xpath = "//textarea[@id='currentAddress']")
	WebElement currentAddress;
	@FindBy(xpath = "//textarea[@id='permanentAddress']")
	WebElement permanentAddress;
	@FindBy(xpath = "//button[@id='submit']")
	WebElement submitBtn;

	////

	@FindBy(xpath = "//label[@id = 'userName-label']")
	WebElement NameLabel;
	@FindBy(xpath = "//label[@id = 'userEmail-label']")
	WebElement MailLabel;
	@FindBy(xpath = "//label[@id = 'currentAddress-label']")
	WebElement CurrAddrLabel;
	@FindBy(xpath = "//label[@id = 'permanentAddress-label']")
	WebElement PermAddrLabel;

	////
	
	@FindBy(xpath = "//div[@id='output']//p[@id='name']")
	WebElement subDataName;
	@FindBy(xpath = "//div[@id='output']//p[@id='permanentAddress']")
	WebElement subDataPermAdd;

	
	@FindBy(xpath = "//input[@class='mr-sm-2 field-error form-control']")
	WebElement error_email_box;

	// Methods 
	public void navigateToTextBoxPage() {
		ElemHeader.click();
		TextBoxHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}

	public void verifyLabels()
    {
 	   if (NameLabel.getText().equalsIgnoreCase("Full Name")) {
 		  Assert.assertTrue(true, "Name Label is Correct");
 		  test.pass("Name label Verified");
 	    } else {
 		  Assert.assertFalse(false, "Name Label is Wrong");	
 		 test.fail("Name label Incorrect");
 	    }
 	    
 	   if (MailLabel.getText().equalsIgnoreCase("Email")) {
 		  Assert.assertTrue(true, "Mail Label is Correct");
 		 test.pass("Mail label Verified");
 		 } else {
 		  Assert.assertFalse(false, "Mail Label is Wrong");	
 		 test.fail("Mail label Incorrect");
 		 }
 	   
 	  if (CurrAddrLabel.getText().equalsIgnoreCase("Current Address")) {
		  Assert.assertTrue(true, "Current Address Label is Correct");
		  test.pass("Current Address label Verified");
		 } else {
		  Assert.assertFalse(false, "Current Address Label is Wrong");
		  test.fail("Current Address label Incorrect");
		 }
 	  
 	  if (PermAddrLabel.getText().equalsIgnoreCase("Permanent Address")) {
		  Assert.assertTrue(true, "Permanent Address Label is Correct");
		  test.pass("Permanent address label Verified");
		 } else {
		  Assert.assertFalse(true, "Permanent Address Label is Wrong");	
		  test.fail("Permanent Address label Incorrect");
		 }
    }

	public void enterValues() throws InterruptedException, IOException {
		try {
		genXLS r1 = new genXLS();
		Map<String, String> h1 = r1.xlsreader("John Doe");
		String nam = h1.get("Fname");
		String mail = h1.get("Email");
		String addc = h1.get("CurrAdd");
		String addp = h1.get("PermAdd");
        viewfinder(fullName);
		fullName.sendKeys(nam);
		email.sendKeys(mail);
		currentAddress.sendKeys(addc);
		permanentAddress.sendKeys(addp);
		Thread.sleep(3000);
		test.pass("Form Details entered from Excel file successfully");
		} catch (Exception e)
		{
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TextFormDataEntryfail");
	       	test.addScreenCaptureFromPath(screenshotPath);
	        test.fail("Field data Entry failed " + e.getMessage());
		}
	}

	public void clickSubmit() throws InterruptedException {
		submitBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		test.pass("Form Data submitted successfully");
		Thread.sleep(3000);
	}
	
	public void SubmitCheck() {
		viewfinder(submitBtn);
		System.out.println(subDataName.getText());
		System.out.println(subDataPermAdd.getText());
		if ( (subDataName.isDisplayed()) && (subDataPermAdd.isDisplayed()) )
     	{
     		Assert.assertTrue(true, "Data submitted successfully");
     		test.pass("Submitted Data visible as intended");
     	} else
     	{
     		Assert.assertFalse(false, "Data submission failed");
     		test.fail("Submitted Data visible as intended");
     	}
	}
	
	public void validateEmail() throws InterruptedException
	   { 
		 Thread.sleep(3000);
		 viewfinder(email);
		 email.sendKeys("mail");
		 submitBtn.click();
		 if (error_email_box.isDisplayed()) 
		 	{
			 Assert.assertTrue(true, "Mail field formatting is active");
			 test.pass("Mail formatting working as intended");
		 } else
		 	{
		 	Assert.assertFalse(false, "Mail field formatting is not active");
 	        test.fail("Mail formatting not working as intended");
		 }  
		 email.clear();
	  }
}
