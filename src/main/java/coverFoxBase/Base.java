package coverFoxBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import coverFoxUtility.Utility;



public class Base {
	// driver
	// url
	// launch browser
	// close browser

protected static WebDriver driver;
	public void launchBorwser() throws IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(Utility.readDataFromPropertyFile("url"));
		Reporter.log("launching browser", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		Reporter.log("waiting..", true);

	}
	
	public void closeBrowser()
	{
		Reporter.log("closing browser", true);
		driver.quit();
	}

}
