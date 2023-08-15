package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
@Test
@CucumberOptions(features = "src/test/resources/featureFIles/FetchAnchorSummary.feature", glue = "step.definitions")

public class RunnerClass extends AbstractTestNGCucumberTests {

}
