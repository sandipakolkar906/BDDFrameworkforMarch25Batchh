package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class ClaimsPage {

	// Global Variable

	WebDriver driver;
	Scenario scenario;

	// Page object Repo

	// Claims repo
	@FindBy(xpath = "//span[text()='Claim']")
	WebElement claimsPageLink;

	@FindBy(xpath = "//h6[text()='Claim']")
	WebElement claimsPagetitle;

	@FindBy(xpath = "//a[text()='Submit Claim']")
	WebElement submitClaims;

	@FindBy(xpath = "//label[text()='Event']/following::i[1]")
	WebElement eventDropdownArrow;

	@FindBy(xpath = "//label[text()='Event']/following::i[2]")
	WebElement countryDropdownArrow;

	@FindBy(xpath = "//button[text()=' Create ']")
	WebElement createButton;

	@FindBy(xpath = "//h6[text()='Expenses']/following::button[1]")
	WebElement addExpensesButton;

	@FindBy(xpath = "//label[text()='Expense Type']/following::i[1]")
	WebElement expenseTypeDropdown;

	@FindBy(xpath = "//label[text()='Date']/following::input[1]")
	WebElement expenseDateinputField;

	@FindBy(xpath = "//label[text()='Amount']/following::input[1]")
	WebElement expenseAmountinoutField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveExpenseButton;

	@FindBy(xpath = "//div[@class='orangehrm-bottom-container']/child::p[1]")
	WebElement expenseTotalcalculatedAmount;

	@FindBy(xpath = "//div[@class='oxd-table-body']/child::div[1]/child::div[1]/div[6]/following::button[1]")
	WebElement edidExpensebutton;

	@FindBy(xpath = "//div[@class='oxd-table-body']/child::div[1]/child::div[1]/div[6]/following::button[2]")
	WebElement deleteExpenseButton;

	@FindBy(xpath = "//div[@class='oxd-table-body']/child::div[1]/child::div[1]/div[5]/child::div[1]")
	WebElement editedExpenseAmount;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteConfirmationButton;

	//// Event repo

	@FindBy(xpath = "//span[text()='Configuration ']")
	WebElement configurationMenu;

	@FindBy(xpath = "//a[text()='Events']")
	WebElement eventsOption;

	@FindBy(xpath = " //button[text()=' Add ']")
	WebElement addEventButton;

	@FindBy(xpath = "//label[text()='Event Name']/following::input[1]")
	WebElement eventNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveEventButton;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchEventButton;

	@FindBy(xpath = "//div[@class='oxd-table-body']/child::div/descendant::div[5]/child::div[1]")
	WebElement searchedVenetText;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-pencil-fill']")
	WebElement editEventButton;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteEventButton;

	// Page class constructer

	public ClaimsPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	// Page operation metthods - Claims

	public String navigateToClaimsPage() {
		ElementActions.clickElement(driver, claimsPageLink, scenario);
		return ElementActions.getText(driver, claimsPagetitle, scenario);
	}

	public void navigateToSubmitClaimsPage() {
		ElementActions.clickElement(driver, submitClaims, scenario);
	}

	public void crateClaim() {
		ElementActions.clickElement(driver, eventDropdownArrow, scenario);

		Actions objactions = new Actions(driver);
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);

		ElementActions.clickElement(driver, countryDropdownArrow, scenario);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, createButton, scenario);
	}

	public void addExpense(String Date, String amount) {
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, addExpensesButton, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, expenseTypeDropdown, scenario);
		Actions objactions = new Actions(driver);
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();
		WaitMethods.staticWait(2000);
		ElementActions.sendKeys(driver, expenseDateinputField, scenario, Date);
		ElementActions.sendKeys(driver, expenseAmountinoutField, scenario, amount);
		ElementActions.clickElement(driver, saveExpenseButton, scenario);
		WaitMethods.staticWait(2000);
	}

	public String getTotalAmountText() {
		WaitMethods.staticWait(5000);
		return ElementActions.getText(driver, expenseTotalcalculatedAmount, scenario);
	}

	public void editFirstExpense(String amountValuetoupdate) {

		ElementActions.clickElement(driver, edidExpensebutton, scenario);
		WaitMethods.staticWait(5000);

		Actions objactions = new Actions(driver);
		ElementActions.clickElement(driver, expenseAmountinoutField, scenario);
		WaitMethods.staticWait(2000);
		for (int i = 0; i <= 3; i++) {
			WaitMethods.staticWait(2000);
			objactions.sendKeys(Keys.BACK_SPACE).build().perform();
		}

		ElementActions.sendKeys(driver, expenseAmountinoutField, scenario, amountValuetoupdate);

		ElementActions.clickElement(driver, saveExpenseButton, scenario);
	}

	public void deleteExpense() {
		ElementActions.clickElement(driver, deleteExpenseButton, scenario);
		ElementActions.clickElement(driver, deleteConfirmationButton, scenario);
	}

	// Page operation metthods - Events

	public void navigateToEventPage() {

		ElementActions.clickElement(driver, configurationMenu, scenario);

		ElementActions.clickElement(driver, eventsOption, scenario);

	}

	public void addNewEvent(String eventName) {
		ElementActions.clickElement(driver, addEventButton, scenario);
		ElementActions.sendKeys(driver, eventNameField, scenario, eventName);
		ElementActions.clickElement(driver, saveEventButton, scenario);

	}

	public String searchNewlyAddedEvent(String eventNameToSearch) {

		ElementActions.sendKeys(driver, eventNameField, scenario, eventNameToSearch);

		ElementActions.clickElement(driver, searchEventButton, scenario);

		return ElementActions.getText(driver, searchedVenetText, scenario);

	}

	public void editEvent(String textToappend) {
		ElementActions.clickElement(driver, editEventButton, scenario);
		ElementActions.sendKeys(driver, eventNameField, scenario, textToappend);
		ElementActions.clickElement(driver, saveEventButton, scenario);
	}

	public void deleteEvent() {
		ElementActions.clickElement(driver, deleteEventButton, scenario);
		ElementActions.clickElement(driver, deleteConfirmationButton, scenario);
	}

}
