package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

/**
 * @author Sandip
 * 
 *         Add Employee page with page object repo and operation methods
 *
 */
public class AddEmployeePage {

	WebDriver driver;
	Scenario scenario;

	// Page object repo

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimPageLink;

	@FindBy(xpath = "//span/child::h6[text()='PIM']")
	WebElement pimPageTitle;

	// Naviga to Add Employee Page

	@FindBy(xpath = "//a[text()='Add Employee']")

	WebElement addEmployeeLink;

	@FindBy(xpath = "//input[@name='firstName']")

	WebElement empFirstNameField;;

	@FindBy(xpath = "//input[@name='middleName']")

	WebElement empMiddleNameField;

	@FindBy(xpath = "//input[@name='lastName']")

	WebElement empLastName;

	@FindBy(xpath = "//button[text()=' Save ']")

	WebElement saveButton;

	@FindBy(xpath = "//a[text()='Employee List']")

	WebElement employeeListlink;

	@FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")

	WebElement searchbyEmpNameField;

	@FindBy(xpath = "//button[text()=' Search ']")

	WebElement SearchButton;

	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/child::div[3]")

	WebElement searhedAmpFnameandMName;

	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/child::div[9]/child::div/button[1]")

	WebElement editEmployeeButton;

	@FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/child::div[9]/child::div/button[2]")

	WebElement deleteButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement confirmDeleteButton;

	// Employee Reports Page Repo

	@FindBy(xpath = "//a[text()='Reports']")
	WebElement reportsLink;

	@FindBy(xpath = "//label[text()='Report Name']/following::input[1]")
	WebElement reportNameField;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addButton;

	@FindBy(xpath = "//label[text()='Select Display Field Group']/following::i[1]")
	WebElement displayFieldDropdownArrow;

	@FindBy(xpath = "//label[text()='Select Display Field']/following::i[1]")
	WebElement selectDisplayFieldDropdown;

	@FindBy(xpath = "//label[text()='Select Display Field']/following::i[@class='oxd-icon bi-plus']")
	WebElement addFieldButton;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveReportButton;

	@FindBy(xpath = "//button/following::i[@class='oxd-icon bi-file-text-fill']")
	WebElement generateReportButton;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/div[1]/child::div[1]")
	WebElement reportField1;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/div[2]/child::div[1]")
	WebElement reportField2;

	@FindBy(xpath = "//div[@class='header-rgRow actual-rgRow']/div[3]/child::div[1]")
	WebElement reportField3;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchReportButton;

	@FindBy(xpath = "//div[@class='oxd-table']/div[@class='oxd-table-body']/descendant::div[6]")
	WebElement searchedReportName;

	// page class constructrer

	public AddEmployeePage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	// Page operation methods

	/**
	 * @return
	 */
	public String navigateToPIMPage() {

		ElementActions.clickElement(driver, pimPageLink, scenario);

		return ElementActions.getText(driver, pimPageTitle, scenario);

	}

	/**
	 * 
	 */
	public void navigateToAddEmployeePage() {
		ElementActions.clickElement(driver, addEmployeeLink, scenario);
	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public void addnewEmployee(String firstName, String middleName, String lastName) {

		ElementActions.sendKeys(driver, empFirstNameField, scenario, firstName);

		ElementActions.sendKeys(driver, empMiddleNameField, scenario, middleName);

		ElementActions.sendKeys(driver, empLastName, scenario, lastName);

		ElementActions.clickElement(driver, saveButton, scenario);
	}

	public void navigateToEmployeeListPage() {
		ElementActions.clickElement(driver, employeeListlink, scenario);

	}

	/**
	 * @param fName
	 * @param mName
	 * @param lName
	 * @return
	 */
	public String searchEmployeeByname(String fName, String mName, String lName) {

		ElementActions.sendKeys(driver, searchbyEmpNameField, scenario, fName + " " + mName + " " + lName);

		ElementActions.clickElement(driver, SearchButton, scenario);

		return ElementActions.getText(driver, searhedAmpFnameandMName, scenario);

	}

	/**
	 * @param fnameAppendValue
	 * @param mNameAppendValue
	 * @param lNameAppendValue
	 */
	public void editEmpinfo(String fnameAppendValue, String mNameAppendValue, String lNameAppendValue) {

		ElementActions.clickElement(driver, editEmployeeButton, scenario);
		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, empFirstNameField, scenario, fnameAppendValue);
		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, empMiddleNameField, scenario, mNameAppendValue);
		WaitMethods.staticWait(5000);
		ElementActions.sendKeys(driver, empLastName, scenario, lNameAppendValue);
		WaitMethods.staticWait(5000);
		ElementActions.clickElement(driver, saveButton, scenario);

	}

	/**
	 *  
	 */
	public void deleteSearhedEmp() {

		ElementActions.clickElement(driver, deleteButton, scenario);
		ElementActions.clickElement(driver, confirmDeleteButton, scenario);

	}

	// Add report page operation methods

	public void navigateToReports() {

		ElementActions.clickElement(driver, reportsLink, scenario);

	}

	public void addReport(String reportName) {

		ElementActions.clickElement(driver, addButton, scenario);
		WaitMethods.staticWait(2000);

		ElementActions.sendKeys(driver, reportNameField, scenario, reportName);
		WaitMethods.staticWait(2000);
		
		ElementActions.clickElement(driver, displayFieldDropdownArrow, scenario);

		Actions objactions = new Actions(driver);

		WaitMethods.staticWait(2000);

		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();

		WaitMethods.staticWait(2000);

		for (int i = 0; i <= 2; i++) {

			ElementActions.clickElement(driver, selectDisplayFieldDropdown, scenario);
			WaitMethods.staticWait(2000);
			objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
			WaitMethods.staticWait(2000);
			objactions.sendKeys(Keys.ENTER).build().perform();
			WaitMethods.staticWait(2000);

			ElementActions.clickElement(driver, addFieldButton, scenario);

		}

		ElementActions.clickElement(driver, saveReportButton, scenario);
		WaitMethods.staticWait(5000);

	}

	public String searchReport(String reportName) {
		
		ElementActions.sendKeys(driver, reportNameField, scenario, reportName);
		Actions objactions = new Actions(driver);

		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ARROW_DOWN).build().perform();
		WaitMethods.staticWait(2000);
		objactions.sendKeys(Keys.ENTER).build().perform();

		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, searchReportButton, scenario);

		WaitMethods.staticWait(2000);

		return ElementActions.getText(driver, searchedReportName, scenario);

	}

	public HashMap<String, String> generateReport() {

		ElementActions.clickElement(driver, generateReportButton, scenario);
		WaitMethods.staticWait(5000);
		HashMap<String, String> objfieldMap = new HashMap<String, String>();

		objfieldMap.put("field1", ElementActions.getText(driver, reportField1, scenario));
		objfieldMap.put("field2", ElementActions.getText(driver, reportField2, scenario));
		objfieldMap.put("field3", ElementActions.getText(driver, reportField3, scenario));
		return objfieldMap;

	}

}
