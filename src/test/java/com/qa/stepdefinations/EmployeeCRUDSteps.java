package com.qa.stepdefinations;

import java.util.HashMap;

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
import junit.framework.Assert;

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
		String expectedPIMPageTitle = objAddEmployeePage.navigateToPIMPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals("PIM", expectedPIMPageTitle);

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
		String actualSearchedFNameandMname = objAddEmployeePage.searchEmployeeByname(fName, mName, lName);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		Assert.assertEquals(fName + " " + mName, actualSearchedFNameandMname);
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

	// Employee Reports Steps

	@When("^I add Custom Report with below ReportName as \"([^\"]*)\" and below Display field group and field names$")
	public void i_add_Custom_Report_with_below_ReportName_as_and_below_Display_field_group_and_field_names(
			String reportName, DataTable reportFields) throws Throwable {
		scenario.write("Adding the Report ");
		objAddEmployeePage.navigateToReports();
		objAddEmployeePage.addReport(reportName);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I  verify Report is searched in the Report with ReportName as \"([^\"]*)\"$")
	public void i_verify_Report_is_searched_in_the_Report_with_ReportName_as(String reportName) throws Throwable {

		scenario.write("Searching the Report after navigating to report ");
		objAddEmployeePage.navigateToReports();
		WaitMethods.staticWait(5000);
		Assert.assertEquals(reportName, objAddEmployeePage.searchReport(reportName));
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify the Report is generated with below fields$")
	public void i_verify_the_Report_is_generated_with_below_fields(DataTable ReportFields) throws Throwable {

		scenario.write("Generating the Report");
		HashMap<String, String> actualFieldMap = objAddEmployeePage.generateReport();

		System.out.println(" Actual Values added in map :" + actualFieldMap);

		Assert.assertEquals(ReportFields.raw().get(0).get(1), actualFieldMap.get("field1"));
		Assert.assertEquals(ReportFields.raw().get(1).get(1), actualFieldMap.get("field3"));
		Assert.assertEquals(ReportFields.raw().get(2).get(1), actualFieldMap.get("field2"));
	}

	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}

}
