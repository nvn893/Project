package pom_demoqa;

import java.io.FileNotFoundException;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.ScreenshotUtil;
import gnrl.WebBase;
import gnrl.genCSV;

public class LoginPage extends WebBase {

	ExtentTest test;

    public LoginPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//input[@id='userName']")
    WebElement username;
    @FindBy(xpath = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//button[@id='login']")
    WebElement loginButton;
    @FindBy(xpath = "//button[text()='Log out']")
    WebElement logoutButton;
    
    @FindBy(xpath = "//label[@id='userName-value']")
    WebElement loginName;
   
    // Methods
    public void setUsername(String user) {
    	username.sendKeys(user);
    	test.pass("Username entry is success");
    }

    public void setPassword(String pass) {
    	password.sendKeys(pass);
    	test.pass("Password entry is success");
    }

    public void clickLogin() {
        loginButton.click();
        test.pass("Login click is success");
    }

    public void loginToApplication() throws FileNotFoundException {
    try {
        genCSV r1 = new genCSV();
    	Map<String, String> h1 = r1.csvreader("username", "mvns567@gmail.com"); 
    	String user = h1.get("username");
    	String pass = h1.get("password");
    	viewfinder(username);
        this.setUsername(user);
        this.setPassword(pass);
        this.clickLogin();
        test.pass("Site login with CSV data : username " + user + " and password "+ pass +" is success");
    } catch (Exception e)
        {
       	 String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "loginfail");
       	 test.addScreenCaptureFromPath(screenshotPath);
       	 test.fail("login failed " + e.getMessage());
       	}
    }
    
    public void logincheck() {
         viewfinder(logoutButton);
		 if (loginName.getText().equalsIgnoreCase("mvns567@gmail.com"))
     	{
     		Assert.assertTrue(true, "logged in successfully");
     		test.pass("Login name visibility verified");
     	} else
     	{
     		Assert.assertFalse(false, "login failed");
     		test.fail("Login name not visible");
     	}
    }
}
