package co.uk.gel.proj.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"@target/generated-test-sources/rerun.txt"},
        plugin = {"pretty", "json:target/cucumber-parallel/rerun.json"},
        monochrome = false,
        tags = {"@test"},
        glue = {"co.uk.gel.proj.steps"})

public class FailureScenariosRunner {
}
