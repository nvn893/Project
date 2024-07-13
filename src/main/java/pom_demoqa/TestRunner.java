package pom_demoqa;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import gnrl.WebBase;

public class TestRunner extends WebBase {

    LoginPage loginPage;
    TextBoxPage textboxpage;
    RadioButtonPage radiopage;
    ButtonsPage buttonspage;
    WebTablesPage webtablespage;
    LinksPage linkspage;
    CheckBoxPage checkboxpage;
    BrokenElementsPage brokenelementspage;
    UpDownPage updownpage;
    DynPropertiesPage dynpropertiespage;
    
    @BeforeClass
    public void setup() throws IOException {
        launchbrowser();
    }

    @Test
    public void TC001_SiteLogin() throws FileNotFoundException {
    	loginPage = new LoginPage(driver, getTest());
        loginPage.loginToApplication();
        loginPage.logincheck();
    }
    
    @Test
    public void TC002_TextBoxPage() throws InterruptedException, IOException {
    	textboxpage = new TextBoxPage(driver, getTest());
        textboxpage.navigateToTextBoxPage();
        textboxpage.verifyLabels();
        textboxpage.enterValues();
        textboxpage.clickSubmit();
        textboxpage.SubmitCheck();
        textboxpage.validateEmail();
    }
    
     @Test
     public void TC003_CheckBoxPage() throws InterruptedException {
        checkboxpage = new CheckBoxPage(driver, getTest());
        checkboxpage.navigateToCheckBoxPage();
        checkboxpage.expandHeaders();
        checkboxpage.selectionCheck();
      }
      
     @Test
     public void TC004_RadioButton() throws InterruptedException {
        radiopage = new RadioButtonPage(driver, getTest());
        radiopage.navigateToRadioButtonPage();
        radiopage.YesButttonStatus();
        radiopage.ImpressiveButttonStatus();
        radiopage.NoButttonStatus();
    }
    
    @Test
    public void TC005_WebTables() throws InterruptedException, IOException {
    	webtablespage = new WebTablesPage(driver, getTest());
    	webtablespage.navigateToWebTablesPage();
    	webtablespage.addData();
     	webtablespage.checkRecord();
     	webtablespage.editRecord();
    	webtablespage.deleteRecord();
    }
   
    @Test
    public void TC006_Buttons() throws InterruptedException {
    	buttonspage = new ButtonsPage(driver, getTest());
    	buttonspage.navigateToButtonsPage();
    	buttonspage.doubleClick();
    	buttonspage.rightClick();
    	buttonspage.dynamicClick();
    }
        
    @Test
    public void TC007_Links() throws InterruptedException {
    	linkspage = new LinksPage(driver, getTest());
    	linkspage.navigateToLinksPage();
    	linkspage.newTabLinks();
    	linkspage.apiCallLinks();
    }
    
    @Test
    public void TC008_BrokenElements() throws InterruptedException {
    	brokenelementspage = new BrokenElementsPage(driver, getTest());
    	brokenelementspage.navigateToBrokenElementsPage();
    	brokenelementspage.imageCheck();
    	brokenelementspage.linkCheck();
    }
    
    @Test
    public void TC009_UpDownCheck() throws InterruptedException {
    	updownpage = new UpDownPage(driver, getTest());
    	updownpage.navigateToLoadPage();
    	updownpage.downloadCheck();
    	updownpage.uploadCheck();
    }
    
    @Test
    public void TC010_DynamicPropsCheck() throws InterruptedException {
    	dynpropertiespage = new DynPropertiesPage(driver, getTest());
    	dynpropertiespage.navigateToDynPropertiesPage();
    	dynpropertiespage.propertiesCheck();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
