package pom_demoqa;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.WebBase;

public class LinksPage extends WebBase {
	
	ExtentTest test;

	public LinksPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Links']")
	WebElement LinksHeader;
	
	@FindBy(xpath = "//a[@id='simpleLink']")
	WebElement newTabHomeSimple;
	@FindBy(xpath = "//a[@id='dynamicLink']")
	WebElement newTabHomeDynamic;
	
	@FindBy(xpath = "//a[@id='created']")
	WebElement apiCreated;
	@FindBy(xpath = "//a[@id='no-content']")
	WebElement apiNoContent;
	@FindBy(xpath = "//a[@id='moved']")
	WebElement apiMoved;
	@FindBy(xpath = "//a[@id='bad-request']")
	WebElement apiBadRequest;
	@FindBy(xpath = "//a[@id='unauthorized']")
	WebElement apiUnauthorized;
	@FindBy(xpath = "//a[@id='forbidden']")
	WebElement apiForbidden;
	@FindBy(xpath = "//a[@id='invalid-url']")
	WebElement apiNotFound;
	
	@FindBy(xpath = "//p[@id='linkResponse']")
	WebElement apiCallText;

	
	// Methods 
	public void navigateToLinksPage() {
	//	ElemHeader.click();
		viewfinder(LinksHeader);
		LinksHeader.click();
		viewfinder(newTabHomeSimple);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}
	
	public void newTabLinks() throws InterruptedException {
		newTabHomeSimple.click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(3000);
		String newtab1 = driver.getTitle();
		if (newtab1.equalsIgnoreCase("DEMOQA")) {
			Assert.assertTrue(true, "Simple New Tab Link opened successfully");
			test.pass("Simple New Tab Link working as expected");
		} else {
			Assert.assertFalse(false, "Simple New Tab Link not working");
			test.fail("Simple New Tab Link not functional");
		}
	    driver.close();
	    driver.switchTo().window(tabs.get(0));
	    Thread.sleep(3000);
	    
	    newTabHomeDynamic.click();
	    ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs1.get(1));
	    Thread.sleep(3000);
		String newtab2 = driver.getTitle();
		if (newtab2.equalsIgnoreCase("DEMOQA")) {
			Assert.assertTrue(true, "Dynamic New Tab Link opened successfully");
			test.pass("Dynamic New Tab Link working as expected");
		} else {
			Assert.assertFalse(false, "Dynamic New Tab Link not working");
			test.fail("Dynamic New Tab Link not functional");
		}
		driver.close();
	    driver.switchTo().window(tabs.get(0));
	}
	
	public void apiCallLinks() throws InterruptedException {
		apiCreated.click();
		Thread.sleep(3000);
		String msg1 = apiCallText.getText();
		if (msg1.contains("Created"))
		{
			Assert.assertTrue(true, "Created Link API call working fine");
			test.pass("Created link working as expected");
		} else {
			Assert.assertFalse(false, "Created Link API call not working");
			test.fail("Created link not functional");
		}
		
		apiNoContent.click();
		Thread.sleep(3000);
		String msg2 = apiCallText.getText();
		if (msg2.contains("No Content"))
		{
			Assert.assertTrue(true, "No Content Link API call working fine");
			test.pass("No Content Link working as expected");
		} else {
			Assert.assertFalse(false, "No Content Link API call not working");
			test.fail("No Content Link not functional");
		}
		
		apiMoved.click();
		Thread.sleep(3000);
		String msg3 = apiCallText.getText();
		if (msg3.contains("Moved Permanently"))
		{
			Assert.assertTrue(true, "Moved Link API call working fine");
			test.pass("Moved Link working as expected");
		} else {
			Assert.assertFalse(false, "Moved Link API call not working");
			test.fail("Moved Link not functional");
		}
		
		apiBadRequest.click();
		Thread.sleep(3000);
		String msg4 = apiCallText.getText();
		if (msg4.contains("Bad Request"))
		{
			Assert.assertTrue(true, "Bad Request API call working fine");
			test.pass("Bad Request link working as expected");
		} else {
			Assert.assertFalse(false, "Bad Request API call not working");
			test.fail("Bad Request link not functional");
		}
		
		apiUnauthorized.click();
		Thread.sleep(3000);
		String msg5 = apiCallText.getText();
		if (msg5.contains("Unauthorized"))
		{
			Assert.assertTrue(true, "Unauthorized API call working fine");
			test.pass("Unauthorized link working as expected");
		} else {
			Assert.assertFalse(false, "Unauthorized API call not working");
			test.fail("Unauthorized link not functional");
		}
		
		apiForbidden.click();
		Thread.sleep(3000);
		String msg6 = apiCallText.getText();
		if (msg6.contains("Forbidden"))
		{
			Assert.assertTrue(true, "Forbidden API call working fine");
			test.pass("Forbidden link working as expected");
		} else {
			Assert.assertFalse(false, "Forbidden API call not working");
			test.fail("Forbidden link not functional");
		}
		
		apiNotFound.click();
		Thread.sleep(3000);
		String msg7 = apiCallText.getText();
		if (msg7.contains("Not Found"))
		{
			Assert.assertTrue(true, "Not Found API call working fine");
			test.pass("Not Found link working as expected");
		} else {
			Assert.assertFalse(false, "Not Found API call not working");
			test.fail("Not Found link not functional");
		}
	}
}
