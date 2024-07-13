package gnrl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String captureScreenshot (WebDriver driver, String screenshotName) {
	try {
		System.out.println("Taking Screenshots");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String relativePath = "screenshots/" + screenshotName + ".png";
		String destination = System.getProperty("user.dir") + "/screenshots/" + relativePath;
		File finaldestination = new File (destination);
		
		File screenshotDir = new File (System.getProperty("user.dir") + "/screenshots/");
		if (!screenshotDir.exists())
		{
			screenshotDir.mkdirs();
		}
				
		FileUtils.copyFile(source, finaldestination);
		return destination;
	}  catch (IOException e) 
	{
		e.printStackTrace();
		return null;
	}
	}
}
