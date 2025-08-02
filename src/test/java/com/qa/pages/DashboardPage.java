package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.Base;
import com.qa.util.ElementActions;

import cucumber.api.Scenario;

public class DashboardPage extends Base {

	WebDriver driver;
	Scenario scenario;
	// page object repo

	@FindBy(xpath = "//p[text()='Time at Work']")
	WebElement timeatWorkWidgetTitle;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-state']")
	WebElement punchInStatus;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']")
	WebElement Day;

	@FindBy(xpath = "//div[@class='orangehrm-attendance-card-summary-week']/child::p[2]")
	WebElement weekDuration;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-fulltime']")
	WebElement TotalHoursinWeek;

	// page class constructer

	public DashboardPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	public String getTimeatWidgetTitle() {
		return ElementActions.getText(driver, timeatWorkWidgetTitle, scenario);
	}

	public HashMap<String, String> getTimeWidgetData() {

		HashMap<String, String> objmap = new HashMap<String, String>();

		objmap.put("punchInStatus", ElementActions.getText(driver, punchInStatus, scenario));

		objmap.put("currentTime", ElementActions.getText(driver, Day, scenario));

		objmap.put("weekDuration", ElementActions.getText(driver, weekDuration, scenario));

		objmap.put("TotalHoursinWeek", ElementActions.getText(driver, TotalHoursinWeek, scenario));

		return objmap;

	}

}
