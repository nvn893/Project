package pom_demoqa;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.WebBase;

public class ButtonsPage extends WebBase {
	
	ExtentTest test;
	
	public ButtonsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
		
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Buttons']")
	WebElement ButtonsHeader;
	
	///
	
	@FindBy(xpath = "//button[@id='doubleClickBtn']")
	WebElement DoubleClickButton;
	@FindBy(xpath = "//button[@id='rightClickBtn']")
	WebElement RightClickButton;
	@FindBy(xpath = "//div[@Class='mt-4']//button[text()='Click Me']")
	WebElement ClickButton;

	///
	
	@FindBy(xpath = "//p[@id='doubleClickMessage']")
	WebElement DoubleClickMessage;
	@FindBy(xpath = "//p[@id='rightClickMessage']")
	WebElement RightClickMessage;
	@FindBy(xpath = "//p[@id='dynamicClickMessage']")
	WebElement ClickMessage;

	// Methods
	public void navigateToButtonsPage() {
	//	ElemHeader.click();
	    viewfinder(ButtonsHeader);
		ButtonsHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		viewfinder(DoubleClickButton);
	}
	
	public void doubleClick()
	{   Actions act = new Actions(driver);
		act.doubleClick(DoubleClickButton).perform();
		if (DoubleClickMessage.isDisplayed())
		{
			Assert.assertTrue(true, "Double click button working fine");
			test.pass("Double Click Button Working as intended");
		} else {
			Assert.assertFalse(false, "Double Click button not working correctly");
			test.fail("Double Click Button not functional");
		}
	}
	
	public void rightClick()
	{   Actions act = new Actions(driver);
		act.contextClick(RightClickButton).perform();
		if (RightClickMessage.isDisplayed())
		{
			Assert.assertTrue(true, "Right click button working fine");
			test.pass("Right Click Button Working as intended");
		} else {
			Assert.assertFalse(false, "Right Click button not working correctly");
			test.fail("Right Click Button not functional");
		}
	}
	
	public void dynamicClick()
	{   Actions act = new Actions(driver);
		act.click(ClickButton).perform();
		if (ClickMessage.isDisplayed())
		{
			Assert.assertTrue(true, "Dynamic click button working fine");
			test.pass("Dynamic Click Button Working as intended");
		} else {
			Assert.assertFalse(false, "Dynamic Click button not working correctly");
			test.fail("Dynamic Click Button not functional");
		}
	}
}
