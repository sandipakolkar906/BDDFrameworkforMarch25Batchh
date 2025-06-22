package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.AddEmployeePage;
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

/**
 * @author Sandip
 * 
 *         This class has all implemented glucode for feature file
 *         EmployeeCRUD.feature
 *
 */
public class EmployeeCRUDSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	AddEmployeePage objAddEmployeePage;

	@Before
	public void startApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to PIM after log in with Admin user$")
	public void navigate_to_PIM_after_log_in_with_Admin_user() throws Throwable {
		scenario.write("Staarting the Orange HRM Application");
		driver = initializeWebDriver();
		WaitMethods.staticWait(5000);
		objLoginPage = new LoginPage(driver, scenario);
		scenario.write("Logging in to ORange HRM Application");
		objLoginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("Navigating to PIM Page after Log in ");
		objAddEmployeePage = new AddEmployeePage(driver, scenario);
		objAddEmployeePage.navigateToPIMPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I Add employee with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_Add_employee_with_first_name_as_and_mname_as_and_lName_as(String fName, String mName, String lName)
			throws Throwable {

		scenario.write("Adding new employee by navigating to Ad Employee Page !");

		objAddEmployeePage.navigateToAddEmployeePage();
		objAddEmployeePage.addnewEmployee(fName, mName, lName);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^I  verify employeeAdded in list with  first name as \"([^\"]*)\" and mname as \"([^\"]*)\" and lName as \"([^\"]*)\"$")
	public void i_verify_employeeAdded_in_list_with_first_name_as_and_mname_as_and_lName_as(String fName, String mName,
			String lName) throws Throwable {

		scenario.write("Searching the newly added amp in the list");
		objAddEmployeePage.navigateToEmployeeListPage();
		objAddEmployeePage.searchEmployeeByname(fName, mName, lName);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^I click on Edit button and update below values and save the Data$")
	public void i_click_on_Edit_button_and_update_below_values_and_save_the_Data(DataTable empEditinfoTable)
			throws Throwable {

		scenario.write("Editing the Searched employee Details ");
		WaitMethods.staticWait(10000);
		System.out.println("=====" + empEditinfoTable.raw().get(0).get(1));
		objAddEmployeePage.editEmpinfo(empEditinfoTable.raw().get(0).get(1), empEditinfoTable.raw().get(1).get(1),
				empEditinfoTable.raw().get(2).get(1));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I search the employee and ensure that it is searched using below values$")
	public void i_search_the_employee_and_ensure_that_it_is_searched_using_below_values(
			DataTable searchEditedempinfoTable) throws Throwable {
		scenario.write("Searching theedited amp in the list");
		objAddEmployeePage.navigateToEmployeeListPage();
		objAddEmployeePage.searchEmployeeByname(searchEditedempinfoTable.raw().get(0).get(1),
				searchEditedempinfoTable.raw().get(1).get(1), searchEditedempinfoTable.raw().get(2).get(1));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I select and Delete the Updated Employee and verify employee is not  in search result$")
	public void i_select_and_Delete_the_Updated_Employee_and_verify_employee_is_not_in_search_result()
			throws Throwable {
		
		scenario.write("Deleting the searched employee !");
		objAddEmployeePage.deleteSearhedEmp();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		
	}

	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}

}
