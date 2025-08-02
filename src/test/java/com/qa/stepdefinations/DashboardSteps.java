package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.util.CaptureScreenshot;
import com.qa.util.ReadProperties;
import com.qa.util.WaitMethods;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class DashboardSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	DashboardPage objDashboardPage;

	@Before
	public void startApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^I log in with Admin user and I am at Dashboard Page$")
	public void i_log_in_with_Admin_user_and_I_am_at_Dashboard_Page() throws Throwable {

		scenario.write("Staarting the Orange HRM Application");
		driver = initializeWebDriver();
		WaitMethods.staticWait(5000);
		objLoginPage = new LoginPage(driver, scenario);
		scenario.write("Logging in to ORange HRM Application");
		objLoginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000);

		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I view the Time at work widget at Dashboard Page$")
	public void i_view_the_Time_at_work_widget_at_Dashboard_Page() throws Throwable {

		scenario.write("verify Time at Work Widget Title ");
		objDashboardPage = new DashboardPage(driver, scenario);

		Assert.assertEquals("Time at Work", objDashboardPage.getTimeatWidgetTitle());
		WaitMethods.staticWait(5000);

		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I check below values from the widget showing correct values$")
	public void i_check_below_values_from_the_widget_showing_correct_values(DataTable expectedWidgetValues)
			throws Throwable {

		scenario.write("verifying the data from Time at widget ");
		scenario.write("=============");
		scenario.write(expectedWidgetValues.raw().get(2).get(1));
		scenario.write(objDashboardPage.getTimeWidgetData().get("currentTime"));
		Assert.assertEquals(expectedWidgetValues.raw().get(1).get(1),
				objDashboardPage.getTimeWidgetData().get("punchInStatus"));
		Assert.assertEquals(expectedWidgetValues.raw().get(2).get(1),
				objDashboardPage.getTimeWidgetData().get("currentTime"));

		Assert.assertEquals(expectedWidgetValues.raw().get(3).get(1),
				objDashboardPage.getTimeWidgetData().get("weekDuration"));
		Assert.assertEquals(expectedWidgetValues.raw().get(4).get(1),
				objDashboardPage.getTimeWidgetData().get("TotalHoursinWeek"));
		WaitMethods.staticWait(5000);

		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}
}
