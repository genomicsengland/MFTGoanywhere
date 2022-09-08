package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page1StepDefs extends Pages {

    private static final Logger log = LoggerFactory.getLogger(Page1StepDefs.class);

    public Page1StepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("application is up and running")
    public void applicationIsUpAndRunning() throws Throwable {
        log.info("Application is Up and Running.");
    }

    @When("the user login to the application with valid credentials")
    public void theUserLoginToTheApplicationWithValidCredentials() throws Throwable {
        log.info("User login to application with valid credentials.");
    }

    @Then("the user should be be navigated to the application home page")
    public void theUserShouldBeBeNavigatedToTheApplicationHomePage() throws Throwable {
        page1.testMethod();
        page1.compareNGISVersion();
    }
}//end
