package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility 
{
	public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		// File dest= new File("D:\\new
		// eclipse\\Automation8thJune_Selenium\\screenShot\\"+fileName+timeStamp+".png");
		File dest = new File(System.getProperty("user.dir") + "\\screenShot\\" + fileName + timeStamp + ".png");
		FileHandler.copy(source, dest);
	}

	public static String readDataFromExcel(String excelPath, String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream myFile = new FileInputStream(excelPath);
		String value = WorkbookFactory.create(myFile).getSheet(sheetName).getRow(rowNum).getCell(cellNum)
				.getStringCellValue();
		return value;
	}
	
	public static String readDataFromPropertyFile(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream myFile = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
		properties.load(myFile);
		String value = properties.getProperty(key);
		return value;
	}

}
