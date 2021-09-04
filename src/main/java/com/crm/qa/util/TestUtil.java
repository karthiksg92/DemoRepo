package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	static Workbook workbook;
	static Sheet sheet;
	public final static String EXCEL_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}
	
	public static void switchTodefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	public static Object[][] getTestData(String sheetname) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(EXCEL_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetname);
		int lastRowNum = sheet.getLastRowNum();
		int lastColNum = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRowNum][lastColNum];
		for (int rowNum = 0; rowNum < lastRowNum; rowNum++) {
			for (int colNum = 0; colNum < lastColNum; colNum++) {
				data[rowNum][colNum] = sheet.getRow(rowNum + 1).getCell(colNum).toString();
			}
		}
		return data;
	}

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		FileUtils.copyFile(scrFile,
				new File(".\\reports\\" + screenshotName));
	}

}
