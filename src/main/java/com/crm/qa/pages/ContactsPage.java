package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(name = "title")
	WebElement titleDropDown;
	
	@FindBy(id = "first_name")
	WebElement firstname;
	
	@FindBy(id = "surname")
	WebElement lastname;
	
	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement company;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}

	public void selectContactByName(String name) {
		driver.findElement(By.xpath("//a[@_name='" + name + 
				"']/parent::td/preceding-sibling::td[1]/input")).click();
	}
	
	public void createNewContact(String title, String fName, String lName, String comp) {
		Select select = new Select(titleDropDown);
		select.selectByVisibleText(title);
		firstname.sendKeys(fName);
		lastname.sendKeys(lName);
		company.sendKeys(comp);
		saveBtn.click();
	}
}
