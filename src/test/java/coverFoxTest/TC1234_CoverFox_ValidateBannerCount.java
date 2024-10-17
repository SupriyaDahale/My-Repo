package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtility.Utility;

public class TC1234_CoverFox_ValidateBannerCount extends Base {
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	String excelpath = System.getProperty("user.dir") + "\\DataSheets\\ExcelTest.xlsx";
	String sheetName = "Sheet5";
	public static Logger logger;
	@BeforeClass
	public void openApplication() throws IOException {
		launchBorwser();
		logger=Logger.getLogger("8th_June_CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("opening application");
}
	@BeforeMethod
	public void enterDetails() throws EncryptedDocumentException, IOException, InterruptedException {
		homePage = new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlanPage(driver);
		memberDetailsPage = new CoverFoxMemberDetailsPage(driver);
		addressDetailsPage = new CoverFoxAddressDetailsPage(driver);
		resultPage = new CoverFoxResultPage(driver);
		homePage.clickOnGenderButton();
		logger.info("Clicking on gender button");
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		memberDetailsPage.handleAgeDropDown(Utility.readDataFromExcel(excelpath, sheetName, 0, 0));
		logger.info("hadeling age drop down");
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		logger.warn("enter valid pin code");
		addressDetailsPage.enterPinCode(Utility.readDataFromExcel(excelpath, sheetName, 0, 1));
		logger.info("entering pin code");
		logger.warn("enter valid mobile number");
		addressDetailsPage.enterMobNum(Utility.readDataFromExcel(excelpath, sheetName, 0, 2));
		logger.info("entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		Thread.sleep(3000);
}
	@Test
	public void valiadatePolicyCount() {
		int textCount = resultPage.getCountFromText();
		int bannerCount = resultPage.getCountFromBanner();
		logger.info("validiting results");
		//Assert.fail();
		Assert.assertEquals(textCount, bannerCount,"text count not matching with banner count, TC failed");
		Reporter.log("Code Changed By Amruta",true);
		Reporter.log("Code changed By Yogendra", true);
		Reporter.log("Code changed By Yogendra1", true);
		Reporter.log("Code changed By Yogendra2", true);
}
	@AfterClass
	public void closeApplication() {
		logger.info("closing browser");
		closeBrowser();
	}
}
