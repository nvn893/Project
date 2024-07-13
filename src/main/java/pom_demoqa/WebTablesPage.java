package pom_demoqa;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import gnrl.ScreenshotUtil;
import gnrl.WebBase;
import gnrl.genXLS2;

public class WebTablesPage extends WebBase {
	
	ExtentTest test;
	
	public WebTablesPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
		
	// Locators
	@FindBy(xpath = "//div[text()='Elements']")
	WebElement ElemHeader;
	@FindBy(xpath = "//span[text()='Web Tables']")
	WebElement WebTablesHeader;
		
	@FindBy(xpath = "//button[@id='addNewRecordButton']")
	WebElement AddBtn;
	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement SearchBox;
	
	@FindBy(xpath = "//input[@id='firstName']")
	WebElement fname;
	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lname;
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement email;
	@FindBy(xpath = "//input[@id='age']")
	WebElement age;
	@FindBy(xpath = "//input[@id='salary']")
	WebElement salary;
	@FindBy(xpath = "//input[@id='department']")
	WebElement department;
	@FindBy(xpath = "//button[@id='submit']")
	WebElement formsubmit;
	
	@FindBy(xpath = "//div[@class='rt-table']//div[@class='rt-tr-group'][4]//div[@class='rt-td'][1]")
	WebElement subdatafname;
	@FindBy(xpath = "//div[@class='rt-table']//div[@class='rt-tr-group'][4]//div[@class='rt-td'][6]")
	WebElement subdatadep;
	
	@FindBy(xpath = "//div[@class='rt-table']//div[@class='rt-tr-group'][3]//div[@class='rt-td'][1]")
	WebElement editdatafname;
	@FindBy(xpath = "//div[@class='rt-table']//div[@class='rt-tr-group'][3]//div[@class='rt-td'][6]")
	WebElement editdatadep;
	
	@FindBy(xpath = "//span[@id='edit-record-3']")
	WebElement editBtn;
	@FindBy(xpath = "//span[@id='delete-record-4']")
	WebElement deleteBtn;
	
//	@FindBy(xpath = "//div[@Class='rt-resizable-header-content']")
//	WebElement TableHeader;

	// Methods
	public void navigateToWebTablesPage() throws InterruptedException {
	//	ElemHeader.click();
		viewfinder(WebTablesHeader);
		WebTablesHeader.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		viewfinder(AddBtn);
		Thread.sleep(2000);
	}
	
	public void addData() throws IOException, InterruptedException {
		try {
		AddBtn.click();
		genXLS2 r1 = new genXLS2();
		Map<String, String> h1 = r1.xlsreader("John");
		String fnam = h1.get("Fname");
		String lnam = h1.get("Lname");
		String ag = h1.get("Age");
		String mail = h1.get("Email");
		String sal = h1.get("Salary");
		String dep = h1.get("Dept");
		fname.sendKeys(fnam);
		lname.sendKeys(lnam);
		email.sendKeys(mail);
		age.sendKeys(ag);
		salary.sendKeys(sal);
		department.sendKeys(dep);
		formsubmit.click();
		Thread.sleep(3000);
		test.pass("Table Record entered successfully from Excel file");
		} catch (Exception e)
		{
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TableDataEntryfail");
	       	test.addScreenCaptureFromPath(screenshotPath);
	        test.fail("Data Entry failed " + e.getMessage());
		}
	}
	
	public void checkRecord() throws InterruptedException {
		SearchBox.sendKeys("John");
		if ( (subdatafname.isDisplayed()) && (subdatadep.isDisplayed()) )
		{
			Assert.assertTrue(true, "Data Inserted Succesfully");
			test.pass("Data insertion into table successful");
		} else {
			Assert.assertFalse(false, "Data not inserted");
			test.fail("Data insertion failed");
		}
		Thread.sleep(3000);
		SearchBox.clear();
		SearchBox.sendKeys(Keys.SPACE, Keys.BACK_SPACE);
	}
	
	public void editRecord() throws InterruptedException {
		editBtn.click();
		fname.clear();
		fname.sendKeys("Jane");
		department.clear();
		department.sendKeys("Finance");
		Thread.sleep(2000);
		formsubmit.click();
		if ( (editdatafname.isDisplayed()) && (editdatadep.isDisplayed()) )
		{ 
			Assert.assertTrue(true, "Table Data editing action Successful");
			test.pass("Record editing of table data successful");
		} else {
			Assert.assertFalse(false, "Table Data editing action failed");
			test.fail("Record editing of table data failed");
		}
		Thread.sleep(2000);
	}
	
	public void deleteRecord() throws InterruptedException {
		Thread.sleep(3000);
		deleteBtn.click();
		if (subdatafname.isDisplayed())
		{ 
			Assert.assertTrue(true, "Data deleted Successfully");
			test.pass("Record deletion from table successful");
		} else {
			Assert.assertFalse(false, "Data deletion failed");
			test.fail("Record deletion failed");
		}
	}
}
