package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;

public class Pages {

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected AppHomePage appHomePage;
    protected AnalysisScriptAndSoftwarePage analysisScriptAndSoftwarePage;
    protected ContractResearchOrganisationPage contractResearchOrganisationPage;
    protected REFindingsPage reFindingsPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {
        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        analysisScriptAndSoftwarePage = PageFactory.initElements(driver, AnalysisScriptAndSoftwarePage.class);
        contractResearchOrganisationPage = PageFactory.initElements(driver,ContractResearchOrganisationPage.class);
        reFindingsPage = PageFactory.initElements(driver,REFindingsPage.class);
    }
}//end class
