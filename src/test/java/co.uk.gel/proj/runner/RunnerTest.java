package co.uk.gel.proj.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
         plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"},
         glue = {"co.uk.gel.proj.steps"},
         features = {"src/test/resources/features"},
         tags = {"@NTS-3407"}
        )
    public class RunnerTest {

    private static final Logger log = LoggerFactory.getLogger(RunnerTest.class);
        @BeforeClass
        public static void setup() {
            log.info("\n******* RUN STARTS " + new java.util.Date() + " *******************************");
        }
    }//end