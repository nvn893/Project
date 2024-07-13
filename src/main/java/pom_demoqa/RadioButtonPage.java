package pom_demoqa;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.WebBase;

public class RadioButtonPage extends WebBase {
	
	ExtentTest test;
	
	public RadioButtonPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
		
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Radio Button']")
	WebElement RadioButtonHeader;
	
	@FindBy(xpath = "//div[@Class='mb-3']")
	WebElement QuestionHeader;
	
	@FindBy(xpath = "//label[text()='Yes']")
	WebElement yesBtn;
	@FindBy(xpath = "//label[text()='Impressive']")
	WebElement impressiveBtn;
	@FindBy(xpath = "//input[@class='custom-control-input disabled']")
	WebElement noBtn;
	
	@FindBy(xpath = "//p['mt-3']")
	WebElement SelText;
	
	// Methods
	public void navigateToRadioButtonPage() {
	//  ElemHeader.click();
		RadioButtonHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        viewfinder(QuestionHeader);
	}
	
	public void YesButttonStatus() {
		if ((yesBtn.isDisplayed()) && (yesBtn.isEnabled()))
		{
			yesBtn.click();
			if (SelText.getText().equalsIgnoreCase("You have selected "+yesBtn.getText()))
			{
				Assert.assertTrue(true, "'Yes' Button functionality is correct");
				test.pass("'Yes' Radio Button working as intended");
			}  else {
				System.out.println(SelText.getText());
				Assert.assertFalse(false, "'Yes' Button text is incorrect" );
			}
		} else {
			Assert.assertFalse(false, "'Yes' Button is not functioning properly");
			test.fail("'Yes' Radio Button Functionality Incorrect");
		}
	}
	
	public void ImpressiveButttonStatus() {
		if ((impressiveBtn.isDisplayed()) && (impressiveBtn.isEnabled()))
		{
			impressiveBtn.click();
			if (SelText.getText().equalsIgnoreCase("You have selected "+impressiveBtn.getText()))
			{
				Assert.assertTrue(true, "'Impressive' Button functionality is correct");
				test.pass("'Impressive' Radio Button working as intended");
			}  else {
				Assert.assertFalse(false, "'Impressive' Button text is incorrect" );
			}
		} else {
			Assert.assertFalse(false, "'Impressive' Button is not functioning properly");
			test.fail("'Impressive' Radio Button Functionality Incorrect");
		}
	}
	
	public void NoButttonStatus() {
		if (!(noBtn.isDisplayed()) && !(noBtn.isEnabled()))
		{
			Assert.assertTrue(true, "'No' button is functioning properly");
			test.pass("'No' Radio Button working as intended");
		} else {
			Assert.assertFalse(false, "'No' Button is not functioning properly");
			test.fail("'No' Radio Button Functionality Incorrect");
		}
	}
}