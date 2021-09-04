package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	String sheetName = "contacts";
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		homePage =  loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
	}	
	
	@Test(priority = 1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}
	
	@Test(priority = 2)
	public void selectSingleContactTest() {
		contactsPage.selectContactByName("Naveen Kuntecha");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		return TestUtil.getTestData(sheetName);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
