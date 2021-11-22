package org.demo.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 *
 * Test Runner Class
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Sanity.feature"}
        ,glue={"org/demo/stepdefinition"}
        ,plugin = {"pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
        //tags="@Test1"
)
public class TestRunner
{

}
