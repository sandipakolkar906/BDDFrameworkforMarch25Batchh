package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.ElementActions;

import cucumber.api.Scenario;

public class BuzzPage {

	WebDriver driver;
	Scenario scenario;

	// page object repo

	@FindBy(xpath = "//span[text()='Buzz']")
	WebElement buzPageLink;
	@FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']/child::h6")
	WebElement buzzPageTitle;
	@FindBy(xpath = "//div[@class='oxd-buzz-post oxd-buzz-post--active']/child::textarea[1]")
	WebElement commentField;
	@FindBy(xpath = "//button[text()=' Post ']")
	WebElement postButton;
	@FindBy(xpath = "//div[@class='orangehrm-buzz-post']/following::p[1]")
	WebElement postedCommentText;
	@FindBy(xpath = "//div[@class='orangehrm-buzz-post-actions']/child::div[1]")
	WebElement postLikeButton;
	@FindBy(xpath = "//div[@class='orangehrm-buzz-stats']/child::div[1]/child::p")
	WebElement postCommentCountText;
	@FindBy(xpath = "//div[@class='orangehrm-buzz-post-header-config']/child::li[1]")
	WebElement editandDeleteMenuButton;
	@FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li[2]")
	WebElement editCommentButton;
	@FindBy(xpath = "//p[text()='Edit Post']/following::textarea[1]")
	WebElement editPostField;

	@FindBy(xpath = "//div[@class='orangehrm-buzz-photos']/following::button[text()=' Post ']")
	WebElement editPostPostButton;

	@FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li[1]")
	WebElement deletePostButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement deleteCommentcobfirmButton;

	// page class constructer

	public BuzzPage(WebDriver driver, Scenario scenario) {

		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
	}

	// page operation methods

	/**
	 * @return it will navigate user to buzz page after log in and return the
	 *         page title in string fromat
	 */
	public String navigatetoBuzzPage() {

		ElementActions.clickElement(driver, buzPageLink, scenario);
		return ElementActions.getText(driver, buzzPageTitle, scenario);
	}

	/**
	 * @param commentToPost
	 *            this will type the comment in comment field
	 */
	public void postComment(String commentToPost) {

		ElementActions.sendKeys(driver, commentField, scenario, commentToPost);
		ElementActions.clickElement(driver, postButton, scenario);

	}

	/**
	 * @return this method will get added comment text and retun you
	 */
	public String getpostedComment() {
		return ElementActions.getText(driver, postedCommentText, scenario);
	}

	/**
	 * this method will click on like button
	 */
	public void likeComment() {
		ElementActions.clickElement(driver, postLikeButton, scenario);
	}

	/**
	 * @return
	 * 
	 * 		this will will get the text in which count is there
	 */
	public String getLikeCount() {

		return ElementActions.getText(driver, postCommentCountText, scenario);

	}

	/**
	 * @param commentToUpdate
	 *            This methods will launch edit comment dialog and add type text
	 *            there and click on post button
	 */
	public void updatePost(String commentToUpdate) {
		ElementActions.clickElement(driver, editandDeleteMenuButton, scenario);
		ElementActions.clickElement(driver, editCommentButton, scenario);
		ElementActions.sendKeys(driver, editPostField, scenario, commentToUpdate);
		ElementActions.clickElement(driver, editPostPostButton, scenario);
	}

	/**
	 * This mwethod will delete the post
	 */
	public void deletePost() {
		ElementActions.clickElement(driver, editandDeleteMenuButton, scenario);
		ElementActions.clickElement(driver, deletePostButton, scenario);
		ElementActions.clickElement(driver, deleteCommentcobfirmButton, scenario);

	}

}
