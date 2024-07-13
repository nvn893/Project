package pom_demoqa;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.WebBase;

public class BrokenElementsPage extends WebBase{

	ExtentTest test;
	
	public BrokenElementsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Broken Links - Images']")
	WebElement BrokenHeader;
	
	@FindBy(xpath = "//p[text()='Valid image']/following::img[1]")
	WebElement validImg;
	@FindBy(xpath = "//p[text()='Broken image']/following::img[1]")
	WebElement brokenImg;
	
	@FindBy(xpath = "//p[text()='Valid Link']/following::a[1]")
	WebElement validLink;
	@FindBy(xpath = "//p[text()='Broken Link']/following::a[1]")
	WebElement brokenLink;
	
	// Methods
	public void navigateToBrokenElementsPage() {
	//	ElemHeader.click();
        viewfinder(BrokenHeader);
		BrokenHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		viewfinder(validImg);
	}
	
	public void imageCheck() {
		if (validImg.isDisplayed())
		{
			Assert.assertTrue(true, "Valid Image visible");
			test.pass("Valid Image visible as expected");
		} else {
			Assert.assertFalse(false, "Valid Image not visible");
			test.fail("Valid Image NOT working as expected");
		}
		
		if (!brokenImg.isDisplayed())
		{
			Assert.assertTrue(true, "Broken Image as expected");
			test.pass("Broken Image not visible as expected");
		} else {
			Assert.assertFalse(false, "Broken Image not as expected");
			test.fail("Broken Image NOT working as expected");
		}	
	}
	
	public void linkCheck() throws InterruptedException {
		validLink.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String newtab1 = driver.getTitle();
		if (newtab1.equalsIgnoreCase("DEMOQA")) {
			Assert.assertTrue(true, "Valid Link opened successfully");
			test.pass("Valid Link working as expected");
		} else {
			Assert.assertFalse(false, "Valid Link not working");
			test.pass("Valid Link NOT working as expected");
		}
	    driver.navigate().back();
	    Thread.sleep(2000);
		
	    viewfinder(validLink);
	    brokenLink.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String newtab2 = driver.getTitle();
		if (newtab2.equalsIgnoreCase("The Internet")) {
			Assert.assertTrue(true, "Broken Link working as expected");
			test.pass("Broken Link working as expected");
		} else {
			Assert.assertFalse(false, "Broken Link not working as expected");
			test.pass("Broken Link NOT working as expected");
		}
	    driver.navigate().back();
	    Thread.sleep(2000);
	}
}
