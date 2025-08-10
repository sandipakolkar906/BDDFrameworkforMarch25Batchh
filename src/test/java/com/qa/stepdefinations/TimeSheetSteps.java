package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.LoginPage;
import com.qa.pages.TimePage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;
import com.qa.util.WaitMethods;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class TimeSheetSteps extends Base{
	Scenario scenario;
	LoginPage objLoginPage;
	TimePage objTimePage;
	int actualallDayTotal;
	int expectedallDayTotal;
	
	
	@Before
	public void startApplication(Scenario scenario) {

		this.scenario = scenario;
	}
	
	
	@Given("^Navigate to Time Page after Log in and naviagte to MyTimeSheetPage$")
	public void navigate_to_Time_Page_after_Log_in_and_naviagte_to_MyTimeSheetPage() throws Throwable {
		scenario.write("Staarting the Orange HRM Application");
		driver = initializeWebDriver();
		WaitMethods.staticWait(5000);
		objLoginPage = new LoginPage(driver, scenario);
		scenario.write("Logging in to ORange HRM Application");
		objLoginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000);
	
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		objTimePage= new TimePage(driver,scenario);
		scenario.write("Navigating to my timeSHeetpage");
		objTimePage.navigateToTimePage();
		objTimePage.navigateToMyTimeSheetPage();
		
	}

	@When("^I capture the booked time from all days and do the sum of it$")
	public void i_capture_the_booked_time_from_all_days_and_do_the_sum_of_it() throws Throwable {
		
		actualallDayTotal=objTimePage.calculateTotalbookedTimeforWeek();
		
	}

	@When("^I capture  the total displyed under Total column$")
	public void i_capture_the_total_displyed_under_Total_column() throws Throwable {
		expectedallDayTotal=objTimePage.getTotalSumofWeek();
	}

	@Then("^I verify that the Displayed total is correct$")
	public void i_verify_that_the_Displayed_total_is_correct() throws Throwable {
		scenario.write("Comparing Expected and Actual Totals");
	    Assert.assertEquals(expectedallDayTotal, actualallDayTotal);
	}

	
	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}
}
