package co.uk.gel.proj.steps;

import co.uk.gel.proj.util.Debugger;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber","json:target/cucumber.json",},
        tags="@general",features = {"src/test/Resources/features"})
    public class TestRunner {

        @BeforeClass
        public static void setup() {
            Debugger.println("\n******* RUN STARTS " + new java.util.Date() + " *******************************");
        }

    }//end