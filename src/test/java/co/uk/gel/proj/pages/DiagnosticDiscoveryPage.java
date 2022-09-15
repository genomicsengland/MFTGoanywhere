package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DiagnosticDiscoveryPage {
    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By Forms = By.xpath("//a[normalize-space()='Forms']");
    By DiagnosticDiscovery = By.xpath("(//a[normalize-space()='Non export form: Contact Clinical Team and/or Report Potential Diagnosis Form'])[1]");
    By UserGroupDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:10:columns:0:dropdown_label']");
    By ContactClinicalTeamDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:14:columns:0:dropdown_label']");
    By PotentialVariantDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:16:columns:0:dropdown_label']");
    By GenomeBuildDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:32:columns:0:dropdown_label']");
    By ExpertiseContent =By.xpath("//textarea[@placeholder='It may also be helpful to include the research you have worked on leading to this finding. If you are a PhD student, you may want to include the name of your PI. ']");
    By RegistryID = By.xpath("//input[@data-varname='rr_number']");
    By ParticipantIDandVariantsTextBox = By.xpath("//textarea[@placeholder='Example: (with fictitious ID and variants): 700000004, CFTR, 7:117530975:G:C 7:117595038:T:TTA']");
    By submitButton = By.xpath("//span[normalize-space()='Submit']");
    By successMessage = By.xpath("//span[contains(text(),'Airlock request successfully submitted.')]");

    public DiagnosticDiscoveryPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public void selectAvailableForms() {
        try {
                seleniumLib.clickOnElement(Forms);
                seleniumLib.sleepInSeconds(10);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in selectAvailableForms " + exp);
            Assert.assertTrue("EXCEPTION is Found in selectAvailableForms", false);
        }
    }

    public void selectDiagnosticDiscoveryForms() {
        try {
            seleniumLib.clickOnElement(DiagnosticDiscovery);
            seleniumLib.sleepInSeconds(2);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in selectDiagnosticDiscoveryForms " + exp);
            Assert.assertTrue("EXCEPTION is Found in selectDiagnosticDiscoveryForms", false);
        }
    }

    public void setUserGroupValue (String userGroup){
        try {
        seleniumLib.clickOnElement(UserGroupDropDown);
        seleniumLib.sleepInSeconds(2);
        seleniumLib.clickOnElement( By.xpath("//td[normalize-space()='"+userGroup+"']"));
        seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setUserGroupValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setUserGroupValue", false);
        }
    }
    public void setRegistryID (String registryID){
        try {
            seleniumLib.clickOnElement(RegistryID);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(RegistryID,registryID);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setRegistryID " + exp);
            Assert.assertTrue("EXCEPTION is Found in setRegistryID", false);
        }
    }

    public void setExpertise (String expertise){
        try {
            seleniumLib.clickOnElement(ExpertiseContent);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(ExpertiseContent,expertise);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setExpertise " + exp);
            Assert.assertTrue("EXCEPTION is Found in setExpertise", false);
        }
    }

    public void setContactClinicalTeamDropDownValue (String clinicalTeamDropDownValue){
        try {
            seleniumLib.clickOnElement(ContactClinicalTeamDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("(//td[contains(text(),'"+clinicalTeamDropDownValue+"')])[1]"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setContactClinicalTeamDropDownValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setContactClinicalTeamDropDownValue", false);
        }
    }

    public void setGenomeBuildDropDownValue (String genomeBuildDropDownValue){
        try {
            seleniumLib.clickOnElement(GenomeBuildDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("(//td[contains(text(),'"+genomeBuildDropDownValue+"')])"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setGenomeBuildDropDownValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setGenomeBuildDropDownValue", false);
        }
    }

    public void setReportPotentialVariantDropDownValue (String potentialVariantDropDownvalue){
        try {
            seleniumLib.clickOnElement(PotentialVariantDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("(//td[contains(text(),'"+potentialVariantDropDownvalue+"')])[2]"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setContactClinicalTeamDropDownValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setContactClinicalTeamDropDownValue", false);
        }
    }

    public void setParticipantIDandVariantsTextBox (String participantIDandVariants){
        try {
            seleniumLib.clickOnElement(ParticipantIDandVariantsTextBox);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(ParticipantIDandVariantsTextBox,participantIDandVariants);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setParticipantIDandVariantsTextBox " + exp);
            Assert.assertTrue("EXCEPTION is Found in setParticipantIDandVariantsTextBox", false);
        }
    }

    public void submitButton (){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-1050)", "");
            seleniumLib.sleepInSeconds(10);
            seleniumLib.waitForElementVisible(submitButton);
            seleniumLib.highLightElement(submitButton);
            seleniumLib.clickOnElement(submitButton);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.waitForElementVisible(successMessage);
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in submitButton " + exp);
            Assert.assertTrue("EXCEPTION is Found in submitButton", false);
        }
    }

}
