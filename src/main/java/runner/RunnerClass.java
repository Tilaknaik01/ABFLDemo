package runner;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)//cucumber
@CucumberOptions(features = "src/test/resources/featureFIles/FetchAnchorSummary.feature", glue = "step.definitions")

public class RunnerClass {

}
