package com.qa.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Sandip
 *
 *
 *         To add the method to capture the screenshot and embed in Cucumber
 *         Report
 */
public class CaptureScreenshot {

	/**
	 * This method will capture the screenshot and retuen it in byte format to
	 * embed in cucumber report
	 * 
	 * @param driver
	 * @return
	 */

	public static byte[] captureImage(WebDriver driver) {

		TakesScreenshot srcShot = (TakesScreenshot) driver;

		byte[] srcFile = srcShot.getScreenshotAs(OutputType.BYTES);

		return srcFile;

	}

}
