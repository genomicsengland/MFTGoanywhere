package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppStepDefs extends Pages {

    public AppStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^application is up and running$")
    public void applicationIsUpAndRunning() throws Throwable {
        Debugger.println("Application is Up and Running.");
    }

    @When("^the user login to the application with valid credentials$")
    public void theUserLoginToTheApplicationWithValidCredentials() throws Throwable {
        Debugger.println("User login to application with valid credentials.");
    }

    @Then("^the user should be be navigated to the application home page$")
    public void theUserShouldBeBeNavigatedToTheApplicationHomePage() throws Throwable {
        appHomePage.testMethod();
    }
}//end
