package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;
import com.qa.util.WaitMethods;

import cucumber.api.Scenario;

public class TimePage {

	WebDriver driver;
	Scenario scenario;

	// page object repository

	@FindBy(xpath = "//span[text()='Time']")
	WebElement timePage;

	@FindBy(xpath = "//span[text()='Timesheets ']")
	WebElement timeSheetsMenu;

	@FindBy(xpath = "//a[text()='My Timesheets']")
	WebElement myTimeSheetOption;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[3]")
	WebElement mondayCellvlue;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[4]")
	WebElement tuesdayCellvlue;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[5]")
	WebElement wensdayCellvlue;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[6]")
	WebElement thursdayCellvlue;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[7]")
	WebElement fridayCellvlue;

	@FindBy(xpath = "//table[@class='orangehrm-timesheet-table']/tbody/tr[2]/td[10]")
	WebElement allDayTotalCellvlue;

	public TimePage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	public void navigateToTimePage() {
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, timePage, scenario);

	}

	public void navigateToMyTimeSheetPage() {
		ElementActions.clickElement(driver, timeSheetsMenu, scenario);
		WaitMethods.staticWait(2000);
		ElementActions.clickElement(driver, myTimeSheetOption, scenario);
		WaitMethods.staticWait(5000);

	}

	public int calculateTotalbookedTimeforWeek() {

		return Integer.parseInt(ElementActions.getText(driver, mondayCellvlue, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, tuesdayCellvlue, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, wensdayCellvlue, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, thursdayCellvlue, scenario).split(":")[0])
				+ Integer.parseInt(ElementActions.getText(driver, fridayCellvlue, scenario).split(":")[0]);

	}

	public int getTotalSumofWeek() {
		return Integer.parseInt(ElementActions.getText(driver, allDayTotalCellvlue, scenario).split(":")[0]);
	}
}
