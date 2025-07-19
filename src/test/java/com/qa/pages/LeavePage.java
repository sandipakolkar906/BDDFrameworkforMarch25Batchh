package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class LeavePage {

	WebDriver driver;
	Scenario scenario;

	// Page object Repository

	@FindBy(xpath = "//span[text()='Leave']")
	WebElement leavePageLink;

	@FindBy(xpath = "//h6[text()='Leave']")
	WebElement leavePageTitle;

	@FindBy(xpath = "//span[text()='Configure ']")
	WebElement configureMenuButton;

	@FindBy(xpath = "//a[text()='Leave Types']")
	WebElement leaveTypeOption;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addLeaveTypeButton;

	@FindBy(xpath = "//h6[text()='Add Leave Type']/following::input[1]")
	WebElement leaveTypeNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveLeaveTypeButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']/div[1]/div[1]/following-sibling::div[1]/child::div[1]")
	WebElement newlyAddedLeaveType;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-pencil-fill']")
	WebElement leaveTypeEditButton;

	@FindBy(xpath = "//h6[text()='Edit Leave Type']/following::input[1]")
	WebElement editleavetTypeNameField;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement updatedLeaveTypesaveButton;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-table-cell-action-space']/child::i[@class='oxd-icon bi-trash']")
	WebElement deleteLeaveTypeButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteConfirmButton;

	// page class constructer

	public LeavePage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	public String NavigatetoLeavePage() {

		ElementActions.clickElement(driver, leavePageLink, scenario);
		return ElementActions.getText(driver, leavePageTitle, scenario);
	}

	public void navigateToLeaveTypesuPage() {
		ElementActions.clickElement(driver, configureMenuButton, scenario);
		ElementActions.clickElement(driver, leaveTypeOption, scenario);

	}

	public void addnewLeaveType(String leaveTypeNameToadd) {
		ElementActions.clickElement(driver, addLeaveTypeButton, scenario);
		ElementActions.sendKeys(driver, leaveTypeNameField, scenario, leaveTypeNameToadd);
		ElementActions.clickElement(driver, saveLeaveTypeButton, scenario);
	}

	public String getnewlyAddedLeaveTypeFromList() {
		return ElementActions.getText(driver, newlyAddedLeaveType, scenario);
	}

	public void edittheLeaveTypeName(String leavetypevaluetoappend) {
		ElementActions.clickElement(driver, leaveTypeEditButton, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, editleavetTypeNameField, scenario, leavetypevaluetoappend);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, updatedLeaveTypesaveButton, scenario);

	}

	public String getnewlyupdateLeaveTypeFromList() {
		return ElementActions.getText(driver, newlyAddedLeaveType, scenario);
		
	}

	public void deleteNewlyaddedLeaveType() {
		ElementActions.clickElement(driver, deleteLeaveTypeButton, scenario);
		ElementActions.clickElement(driver, deleteConfirmButton, scenario);
	}

}
