package com.qa.stepdefinations;

import com.qa.base.Base;
import com.qa.pages.BuzzPage;
import com.qa.pages.LoginPage;
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

public class BuzzCrudSteps extends Base {

	Scenario scenario;
	LoginPage objLoginPage;
	BuzzPage objBuzzPage;

	@Before
	public void startApplication(Scenario scenario) {

		this.scenario = scenario;
	}

	@Given("^Navigate to BUzz after log in with Admin user$")
	public void navigate_to_BUzz_after_log_in_with_Admin_user() throws Throwable {
		scenario.write("Staarting the Orange HRM Application");
		driver = initializeWebDriver();
		WaitMethods.staticWait(5000);
		objLoginPage = new LoginPage(driver, scenario);
		scenario.write("Logging in to ORange HRM Application");
		objLoginPage.logintoApplication(ReadProperties.getAppUserName(), ReadProperties.getAppPassword());
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
		scenario.write("Navigating to Buzz Page after Log in ");
		objBuzzPage = new BuzzPage(driver, scenario);
		objBuzzPage.navigatetoBuzzPage();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@When("^Post the comment as \"([^\"]*)\"$")
	public void post_the_comment_as(String commentTextToType) throws Throwable {
		scenario.write("Posting a new comment");
		objBuzzPage.postComment(commentTextToType);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");

	}

	@Then("^verify that comment time user and comment text is posted correctly as \"([^\"]*)\"$")
	public void verify_that_comment_time_user_and_comment_text_is_posted_correctly_as(
			String expectedCommentfromFeatureFile) throws Throwable {
		scenario.write("Verifying expected comment is equal to actual comment");
		Assert.assertEquals(expectedCommentfromFeatureFile, objBuzzPage.getpostedComment());
	}

	@When("^I click on like$")
	public void i_click_on_like() throws Throwable {
		scenario.write("Clicking on like button");
		objBuzzPage.likeComment();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify the like count is updated as Expected count \"([^\"]*)\"$")
	public void i_verify_the_like_count_is_updated_as_Expected_count(String expectedCountfromFeatureFile)
			throws Throwable {
		scenario.write("Veuirfy the expected count is equal to actual count ");
		Assert.assertEquals(expectedCountfromFeatureFile+ " Like", objBuzzPage.getLikeCount());

	}

	@When("^I Edit the post with updated comment\"([^\"]*)\"$")
	public void i_Edit_the_post_with_updated_comment(String featurefilecommettoAppend) throws Throwable {
		scenario.write("Edit the posted comment");
		objBuzzPage.updatePost(featurefilecommettoAppend);
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@Then("^I verify that updated comment\"([^\"]*)\"$")
	public void i_verify_that_updated_comment(String appenedText) throws Throwable {
		scenario.write("Verify that entire commnet has appended text ");
		Assert.assertTrue(objBuzzPage.getpostedComment().contains(appenedText));
	}

	@Then("^I delete the Post$")
	public void i_delete_the_Post() throws Throwable {
		scenario.write("Deleting the added and updated post ");
		objBuzzPage.deletePost();
		WaitMethods.staticWait(5000);
		scenario.embed(CaptureScreenshot.captureImage(driver), "image/png");
	}

	@After
	public void closeApplciation() {
		scenario.write("Closing the Application");
		closeBrowser();
	}

}
