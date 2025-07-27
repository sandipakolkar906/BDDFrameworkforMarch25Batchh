package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.ClaimsPage;
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

public class ClaimsCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	ClaimsPage objClaimsPage;
	double expectedTotal;

	@Before
	public void startApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to Claims after log in with Admin user$")
	public void navigate_to_Claims_after_log_in_with_Admin_user() throws Throwable {

		scenario.write("Staarting the Orange HRM Application");
		driver = initializeWebDriver();
		WaitMethods.staticWait(5000);
		objLoginPage = new LoginPage(driver, scenario);
		scenario.write("Logging in to ORange HRM Application");
		objLoginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write(" Navigate to Claims Page ");
		objClaimsPage = new ClaimsPage(driver, scenario);
		objClaimsPage.navigateToClaimsPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Given("^I navigate to submit claims Page$")
	public void i_navigate_to_submit_claims_Page() throws Throwable {
		scenario.write(" Navigate to submit Claims Page ");
		objClaimsPage.navigateToSubmitClaimsPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I crate claim Request with Event and Currency$")
	public void i_crate_claim_Request_with_Event_and_Currency(DataTable eventDataTable) throws Throwable {
		scenario.write(" Select the EventName " + eventDataTable.raw().get(1).get(0));
		scenario.write(" and Select the Currrency " + eventDataTable.raw().get(1).get(1));
		objClaimsPage.crateClaim();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I add Expenses for the new claim Request with below fields and values$")
	public void i_add_Expenses_for_the_new_claim_Request_with_below_fields_and_values(DataTable dateandAmountTable)
			throws Throwable {
		scenario.write(" I add the expenses for  new claim ");
		objClaimsPage.addExpense(dateandAmountTable.raw().get(2).get(1), dateandAmountTable.raw().get(3).get(1));

		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		expectedTotal = Double.parseDouble(dateandAmountTable.raw().get(3).get(1))
				+ Double.parseDouble(dateandAmountTable.raw().get(3).get(1));
		scenario.write("Expected Total is :" + expectedTotal);
	}

	@Then("^I verify the total amount of all expenses is caculated correctly$")
	public void i_verify_the_total_amount_of_all_expenses_is_caculated_correctly() throws Throwable {

		scenario.write(" Verifying the expected total is equal to actual total ");
		String[] splitArray = objClaimsPage.getTotalAmountText().split(":");

		Double actualTotal = Double.parseDouble(splitArray[1]);

		scenario.write("Actual Total is " + actualTotal);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

		Assert.assertEquals(expectedTotal, actualTotal);

	}

	@When("^I edit the Expense$")
	public void i_edit_the_Expense() throws Throwable {
		scenario.write("Editing the Expense  ");
		objClaimsPage.editFirstExpense("1");
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^Delete one Expense$")
	public void delete_one_Expense() throws Throwable {
		scenario.write("Deleting the Expense  ");
		objClaimsPage.deleteExpense();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}
	// Event Crud Steps

	@When("^I navigate to Configuration menu and Select Event option$")
	public void i_navigate_to_Configuration_menu_and_Select_Event_option() throws Throwable {
		scenario.write("Navigating to Event Page  ");
		objClaimsPage.navigateToEventPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@When("^I add new Event with below event Name$")
	public void i_add_new_Event_with_below_event_Name(DataTable eventDataTable) throws Throwable {
		scenario.write("Adding new Event   ");
		objClaimsPage.addNewEvent(eventDataTable.raw().get(0).get(1));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I Search Event with below eventName$")
	public void i_Search_Event_with_below_eventName(DataTable eventSearchDataTable) throws Throwable {

		scenario.write("Searching  added Event   ");

		Assert.assertEquals(eventSearchDataTable.raw().get(0).get(1),
				objClaimsPage.searchNewlyAddedEvent(eventSearchDataTable.raw().get(0).get(1)));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I edit the Event and change name$")
	public void i_edit_the_Event_and_change_name(DataTable updateEnevetDataTable) throws Throwable {
		scenario.write("Edting  added Event   ");
		objClaimsPage.editEvent(updateEnevetDataTable.raw().get(0).get(1));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I delete the newly added and updated Event$")
	public void i_delete_the_newly_added_and_updated_Event() throws Throwable {
		scenario.write("Deleting  added Event   ");
		objClaimsPage.deleteEvent();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}

}
