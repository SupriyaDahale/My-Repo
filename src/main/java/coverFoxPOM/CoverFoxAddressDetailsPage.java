package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetailsPage {

	// variables
	@FindBy(className = "mp-input-text")
	private WebElement pincodeField;
	@FindBy(id = "want-expert")
	private WebElement mobNumField;
	@FindBy(className = "next-btn")
	private WebElement continueButton;
	@FindBy(xpath = "//div[text()=' Please enter a valid pincode ']")
	private WebElement pinErrMsg;
	@FindBy(xpath = "//div[text()=' Please enter a valid mobile no. ']")
	private WebElement mobErrMsg;

	// constructor
	public CoverFoxAddressDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// methods
	public void enterPinCode(String pinCode) {
		Reporter.log("enetring pincode..", true);
		pincodeField.sendKeys(pinCode);
	}

	public void enterMobNum(String mobNum) {
		Reporter.log("enetring mobile number..", true);
		mobNumField.sendKeys(mobNum);
	}

	public void clickOnContinueButton() {
		Reporter.log("clicking on continue button..", true);
		continueButton.click();
	}
	
	public String getPinErrMsg()
	{
		String actualText = pinErrMsg.getText();
		return actualText;
	}
	
	public String getMobNumErrMsg()
	{
		String actualText = mobErrMsg.getText();
		return actualText;
	}

}
