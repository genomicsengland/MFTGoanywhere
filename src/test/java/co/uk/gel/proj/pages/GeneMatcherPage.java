package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneMatcherPage {
    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By GeneMatcher = By.xpath("(//a[normalize-space()='Non export form: GeneMatcher request'])[1]");
    By UserGroupDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:5:columns:0:dropdown_label']");
    By Browse = By.xpath("//a[contains(text(),'browse to attach files')]");
    By GeneSymbol = By.xpath("//input[@data-varname='gene_symbol']");
    By ZygosityDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:7:columns:0:dropdown_label']");
    By FunctionDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:8:columns:0:dropdown_label']");
    By InheritanceModelDropDown = By.xpath("//label[@id='secureForm:formPanel:rows:9:columns:0:dropdown_label']");
    By TransferRequestJustification = By.xpath("//input[@data-varname='phenotype_term']");

    By TransferDropDown = By.xpath("//div[@data-varname='exportagreement']");

    public GeneMatcherPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public void selectGeneMatcherForms() {
        try {
            seleniumLib.clickOnElement(GeneMatcher);
            seleniumLib.sleepInSeconds(10);

        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in selectGeneMatcherForms " + exp);
            Assert.assertTrue("EXCEPTION is Found in selectGeneMatcherForms", false);
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
            Thread.sleep(3000); // suspending execution for specified time period
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

    public void setGeneSymbol (String geneSymbol){
        try {
            seleniumLib.clickOnElement(GeneSymbol);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(GeneSymbol,geneSymbol);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setGeneSymbol " + exp);
            Assert.assertTrue("EXCEPTION is Found in setGeneSymbol", false);
        }
    }

    public void setPhenotypeTerm (String phenotypeTerm){
        try {
            seleniumLib.clickOnElement(TransferRequestJustification);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(TransferRequestJustification,phenotypeTerm);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setPhenotypeTerm " + exp);
            Assert.assertTrue("EXCEPTION is Found in setPhenotypeTerm", false);
        }
    }

    public void setZygosityValue (String zygosityValue){
        try {
            seleniumLib.clickOnElement(ZygosityDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+zygosityValue+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setZygosityValue " + exp);
            Assert.assertTrue("EXCEPTION is Found in setZygosityValue", false);
        }
    }

    public void setFunction (String function){
        try {
            seleniumLib.clickOnElement(FunctionDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+function+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setFunction " + exp);
            Assert.assertTrue("EXCEPTION is Found in setFunction", false);
        }
    }

    public void setInheritanceModel (String inheritanceModel){
        try {
            seleniumLib.clickOnElement(InheritanceModelDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("//tr[@data-label='"+inheritanceModel+"']"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setInheritanceModel " + exp);
            Assert.assertTrue("EXCEPTION is Found in setInheritanceModel", false);
        }
    }

    public void setTransfer (String transfer){
        try {
            seleniumLib.clickOnElement(TransferDropDown);
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement( By.xpath("(//td[normalize-space()='"+transfer+"'])[1]"));
            seleniumLib.sleepInSeconds(2);
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in setTransfer " + exp);
            Assert.assertTrue("EXCEPTION is Found in setTransfer", false);
        }
    }

}
