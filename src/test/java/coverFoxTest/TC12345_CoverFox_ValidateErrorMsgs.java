package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class TC12345_CoverFox_ValidateErrorMsgs extends Base {
	
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	String excelpath = System.getProperty("user.dir") + "\\DataSheets\\ExcelTest.xlsx";
	String sheetName = "Sheet5";
	public static Logger logger;
	// open browser/open an application
	

	// gender,next click, age selection, pincode, mobile,nect click
	@BeforeMethod
	public void enterDetails() throws EncryptedDocumentException, IOException, InterruptedException {
		launchBorwser();
		logger=Logger.getLogger("8th_June_CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("opening application");
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
		
	}

	@Test(priority = -1)
	public void validatePinCodeErrorMsg() throws EncryptedDocumentException, IOException {
		logger.warn("enter valid mobile number");
		addressDetailsPage.enterMobNum(Utility.readDataFromExcel(excelpath, sheetName, 0, 2));
		logger.info("entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		String actualPinErrorMsg = addressDetailsPage.getPinErrMsg();
		String expectedPinErrorMsg = Utility.readDataFromExcel(excelpath, sheetName, 0, 3);
	
		Assert.assertEquals(actualPinErrorMsg, expectedPinErrorMsg,"Error msg not matching, TC failed");
	}
	@Test
	public void validateMobileNumErrorMsg() throws EncryptedDocumentException, IOException
	{
		logger.warn("enter valid pin code");
		addressDetailsPage.enterPinCode(Utility.readDataFromExcel(excelpath, sheetName, 0, 1));
		logger.info("entering pin code");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		
		String actualMobErrMsg = addressDetailsPage.getMobNumErrMsg();
		String expectedMobErrMsg = Utility.readDataFromExcel(excelpath, sheetName, 0, 4);
		Assert.assertEquals(actualMobErrMsg, expectedMobErrMsg,"Error msgs are not matching, TC failed");
	}

	// logout From application
	// close Browser/close an application
	@AfterMethod
	public void closeApplication() {
		logger.info("closing browswr");
		closeBrowser();
	}

}
