package pom_demoqa;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.WebBase;

public class CheckBoxPage extends WebBase{

	ExtentTest test;

	public CheckBoxPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Check Box']")
	WebElement CheckBoxHeader;
	
	
	@FindBy(xpath = "//label[@for='tree-node-home']/preceding::button[@class='rct-collapse rct-collapse-btn']")
	WebElement homeDrilldown;
	@FindBy(xpath = "//label[@for='tree-node-desktop']/preceding::button[@class='rct-collapse rct-collapse-btn'][1]")
	WebElement desktopDrilldown;
	@FindBy(xpath = "//label[@for='tree-node-documents']/preceding::button[@class='rct-collapse rct-collapse-btn'][1]")
	WebElement documentsDrilldown;
	@FindBy(xpath = "//label[@for='tree-node-downloads']/preceding::button[@class='rct-collapse rct-collapse-btn'][1]")
	WebElement downloadsDrilldown;
	
	
	@FindBy(xpath = "//label[@for='tree-node-home']//span[@class='rct-checkbox']")
	WebElement homeCheckbox;
	@FindBy(xpath = "//label[@for='tree-node-desktop']//span[@class='rct-checkbox']")
	WebElement desktopCheckbox;
	@FindBy(xpath = "//label[@for='tree-node-documents']//span[@class='rct-checkbox']")
	WebElement documentsCheckbox;
	@FindBy(xpath = "//label[@for='tree-node-downloads']//span[@class='rct-checkbox']")
	WebElement downloadsCheckbox;
	
	@FindBy(xpath = "//span[@class='text-success']")
	WebElement selectionText;
	
//	String [] allHome = {"home", "desktop", "notes", "commands", "documents", "workspace", "react", "angular", "veu", 
//	                   "office",  "public", "private", "classified", "general", "downloads", "wordFile", "excelFile"};
//	boolean flag;
	
	// Methods 
	public void navigateToCheckBoxPage() throws InterruptedException {
	//	ElemHeader.click();
		CheckBoxHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		Thread.sleep(5000);
	}
	
	public void expandHeaders() throws InterruptedException {
        viewfinder(homeDrilldown);
		homeDrilldown.click();
		desktopDrilldown.click();
		documentsDrilldown.click();
		downloadsDrilldown.click();
		Thread.sleep(3000);
		test.pass("Header Accordion arrows working as intended");
	}
	
	public void selectionCheck() {
		homeCheckbox.click();
		if (selectionText.getText().contains("home"))
		{
			Assert.assertTrue(true, "Home node selected");
			test.pass("Home node selection working as intended");
		} else {
			Assert.assertFalse(false, "Home node selection failed");
			test.fail("Home node selection NOT working as intended");
		}
		homeCheckbox.click();
		
		desktopCheckbox.click();
		if (selectionText.getText().contains("desktop"))
		{
			Assert.assertTrue(true, "Desktop node selected");
			test.pass("Desktop node selection working as intended");
		} else {
			Assert.assertFalse(false, "Desktop node selection failed");
			test.fail("Desktop node selection NOT working as intended");
		}
		desktopCheckbox.click();
		
		documentsCheckbox.click();
		viewfinder(documentsCheckbox);
		if (selectionText.getText().contains("documents"))
		{
			Assert.assertTrue(true, "Documents node selected");
			test.pass("Documents node selection working as intended");
		} else {
			Assert.assertFalse(false, "Documents node selection failed");
			test.fail("Documents node selection NOT working as intended");
		}
		documentsCheckbox.click();
		
		downloadsCheckbox.click();
		if (selectionText.getText().contains("downloads"))
		{
			Assert.assertTrue(true, "Downloads node selected");
			test.pass("Downloads node selection working as intended");
		} else {
			Assert.assertFalse(false, "Downloads node selection failed");
			test.fail("Downloads node selection NOT working as intended");
		}
		downloadsCheckbox.click();
	}
}
