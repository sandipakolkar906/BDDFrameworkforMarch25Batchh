package com.qa.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Sandip This is cucumber runner classs to Run feature files and Steps
 *         and generate reports
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.qa.stepdefinations" }, plugin = { "pretty",
		"json:target/cucumber-reports/Cucumber.json" }, monochrome = true)

public class Runner {

}
