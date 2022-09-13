package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class REFindingsPage {
    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By REFindings = By.xpath("(//a[normalize-space()='Export from RE: Findings'])[1]");
    By UserGroupDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:4:columns:0:dropdown_label']");
    By Browse = By.xpath("//a[contains(text(),'browse to attach files')]");
    By FileContent =By.xpath("//textarea[@placeholder='Describe the data in the file(s) proposed for transfer. Be sure to fully explain any ambiguous or unclear columns or data.']");
    By TransferRequestJustification = By.xpath("//textarea[@placeholder='Please explain why you wish to take this data out of the RE. Make sure to specify where, if anywhere, you plan to publish this data. If requesting to export for external analysis, please justify why that analysis cannot be done inside the RE']");
    By DiseaseTypeDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:16:columns:0:dropdown_label']");
    By AimOfExportDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:17:columns:0:dropdown_label']");
    By TransferDropDown = By.xpath("//div[@data-varname='exportagreement']");

    public REFindingsPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public void selectREFindingsForms() {
        try {
            seleniumLib.clickOnElement(REFindings);
            seleniumLib.sleepInSeconds(10);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in selectREFindingsForms " + exp);
            Assert.assertTrue("EXCEPTION is Found in selectREFindingsForms", false);
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

    public void setDiseaseTypeValue (String diseaseType){
        try {
            seleniumLib.clickOnElement(DiseaseTypeDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//td[normalize-space()='"+diseaseType+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setDiseaseTypeValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setDiseaseTypeValue", false);
        }
    }

    public void setAimOfExport (String aimOfExport){
        try {
            seleniumLib.clickOnElement(AimOfExportDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//td[normalize-space()='"+aimOfExport+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setDiseaseTypeValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setDiseaseTypeValue", false);
        }
    }

    public void setTransfer (String transfer){
        try {
            seleniumLib.clickOnElement(TransferDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@id='secureForm:formPanel:rows:33:columns:0:dropdown_1']"));
            //seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+transfer+"']//td[contains(text(),'"+transfer+"')]"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setTransfer " + exp);
            Assert.assertTrue("EXCEPTION is Found in setTransfer", false);
        }
    }

}
