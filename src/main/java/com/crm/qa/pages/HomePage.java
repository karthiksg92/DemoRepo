package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase  {
	
	@FindBy(xpath = "//td[contains(text(),'User: Free Chill')]")
	WebElement userNameLabel;
	
	@FindBy(xpath = "//a[@title = 'Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[@title = 'Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[@title = 'Tasks']")
	WebElement tasksLink;
	
	@FindBy(xpath = "//a[@title = 'New Contact']")
	WebElement newContactLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String homePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}	
	
	public DealsPage clickOnDealtsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink() {
		Actions actions = new Actions(driver);
		actions.moveToElement(dealsLink).build().perform();
		actions.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
}
