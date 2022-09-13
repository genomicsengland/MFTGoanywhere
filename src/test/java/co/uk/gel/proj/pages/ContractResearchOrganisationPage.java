package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ContractResearchOrganisationPage {
    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By Forms = By.xpath("//a[normalize-space()='Forms']");
    By ContractResearchOrganisation = By.xpath("//a[normalize-space()='Export from RE: Contract Research Organisation']");
    By UserGroupDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:3:columns:0:dropdown_label']");
    By Browse = By.xpath("(//a[contains(text(),'browse to attach files')])[2]");
    By FileContent =By.xpath("//textarea[@placeholder='Please describe the data in the file(s) requested for export, ibeing sure to fully explain any ambiguous data or columns']");
    By RegistryID = By.xpath("//input[@placeholder='e.g. RR150']");
    By TransferRequestJustification = By.xpath("//textarea[@placeholder='Please explain why you wish to take these data out of the RE, and if you intend to communicate the data to a third party']");
    By TransferDropDown = By.xpath("//div[@data-varname='exportagreement']");
            //By.xpath("//label[@id='secureForm:formPanel:rows:25:columns:0:dropdown_label']");
    By submitButton = By.xpath("//span[normalize-space()='Submit']");
    By successMessage = By.xpath("//span[contains(text(),'Airlock request successfully submitted.')]");


    public ContractResearchOrganisationPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public void setUserGroupValue (String userGroup){
        try {
        seleniumLib.clickOnElement(UserGroupDropDown);
        seleniumLib.sleepInSeconds(2);
        seleniumLib.clickOnElement( By.xpath("//td[normalize-space()='"+userGroup+"']"));
        seleniumLib.sleepInSeconds(10);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setUserGroupValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setUserGroupValue", false);
        }
    }

    public void setParticipantID (String File){
        try {
            seleniumLib.clickOnElement(Browse);
            Thread.sleep(2000); // suspending execution for specified time period
            seleniumLib.upload(Browse,File);
            seleniumLib.sleepInSeconds(10);
            System.out.println("File is Uploaded Successfully");
            seleniumLib.sleepInSeconds(10);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setParticipantID " + exp);
            Assert.assertTrue("EXCEPTION is Found in setParticipantID", false);
        }
    }
    public void setFileContent (String fileContent){
        try {
            seleniumLib.clickOnElement(FileContent);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(FileContent,fileContent);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in fileContent " + exp);
            Assert.assertTrue("EXCEPTION is Found in fileContent", false);
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

    public void setTransferRequestJustification (String transferRequestJustification){
        try {
            seleniumLib.clickOnElement(TransferRequestJustification);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(TransferRequestJustification,transferRequestJustification);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setTransferRequestJustification " + exp);
            Assert.assertTrue("EXCEPTION is Found in setTransferRequestJustification", false);
        }
    }

    public void setTransfer (String transfer){
        try {
            seleniumLib.clickOnElement(TransferDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+transfer+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setTransfer " + exp);
            Assert.assertTrue("EXCEPTION is Found in setTransfer", false);
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

    public void setContractResearchOrganisationForms() {
        try {
            seleniumLib.clickOnElement(ContractResearchOrganisation);
            seleniumLib.sleepInSeconds(10);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setContractResearchOrganisationForms " + exp);
            Assert.assertTrue("EXCEPTION is Found in setContractResearchOrganisationForms", false);
        }
    }

}
