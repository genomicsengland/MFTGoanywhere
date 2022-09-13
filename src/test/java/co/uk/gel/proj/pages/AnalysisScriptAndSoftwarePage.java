package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class AnalysisScriptAndSoftwarePage {
    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By Forms = By.xpath("//a[normalize-space()='Forms']");
    By ExportfromREAnalysisScriptsAndSoftware = By.xpath("(//a[normalize-space()='Export from RE: Analysis scripts and software'])[1]");
    By UserGroupDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:2:columns:0:dropdown_label']");
    By UserGroupValue = By.xpath("//td[normalize-space()='GeCIP']");
    By Browse = By.xpath("//a[contains(text(),'browse to attach files')]");
    By FileContent =By.xpath("//textarea[@placeholder='Please give a brief description of the software/scripts proposed for export. If any actual data is included, be sure to describe it here']");
    By RegistryID = By.xpath("//input[@placeholder='e.g. RR150']");
    By TransferRequestJustification = By.xpath("//textarea[@placeholder='Please explain why you this to export this software/script. If you intend to publish or otherwise make the software publicly available, please specify that here']");
    By ProgrammingLanguageDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:17:columns:0:dropdown_label']");
    By TransferDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:24:columns:0:dropdown_label']");
    By submitButton = By.xpath("//span[normalize-space()='Submit']");
    By successMessage = By.xpath("//span[contains(text(),'Airlock request successfully submitted.')]");

    public AnalysisScriptAndSoftwarePage(WebDriver driver) {
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

    public void selectAnalysisScriptsAndSoftwareForms() {
        try {
            seleniumLib.clickOnElement(ExportfromREAnalysisScriptsAndSoftware);
            seleniumLib.sleepInSeconds(10);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in selectExportfromREAnalysisScriptsAndSoftware " + exp);
            Assert.assertTrue("EXCEPTION is Found in selectExportfromREAnalysisScriptsAndSoftware", false);
        }
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

    public void setFile (String File){
        try {
            seleniumLib.clickOnElement(Browse);
            Thread.sleep(2000); // suspending execution for specified time period
            seleniumLib.upload(Browse,File);
            seleniumLib.sleepInSeconds(10);
            System.out.println("File is Uploaded Successfully");
            seleniumLib.sleepInSeconds(10);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setUserGroupValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setUserGroupValue", false);
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
    public void setProgrammingLanguage (String programmingLanguage){
        try {
            seleniumLib.clickOnElement(ProgrammingLanguageDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+programmingLanguage+"']//td"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setProgrammingLanguage " + exp);
            Assert.assertTrue("EXCEPTION is Found in setProgrammingLanguage", false);
        }
    }

    public void setTransfer (String transfer){
        try {
            seleniumLib.clickOnElement(TransferDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+transfer+"']//td"));
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

}
