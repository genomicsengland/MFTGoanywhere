package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppHomePage {

    private WebDriver driver;
    private SeleniumLib seleniumLib;
    By username = By.xpath("//input[@id='username']");
    By password = By.xpath("//input[@id='value']");
    By loginBtn = By.xpath("//span[contains(text(),'Login')]");


    public void testMethod(){
        Debugger.println("User is in the Application Home Page!");
    }

    public AppHomePage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean navigatesToGoAnywhere() {
        String url = AppConfig.getApp_url();
        driver.get(url);
        seleniumLib.pageLoadTime();
        return true;
    }

    public boolean isCurrentPage() {
        seleniumLib.sleepInSeconds(5);
         return (seleniumLib.isElementPresent(loginBtn));
    }

    public void loginToGoAnywhere() {
        try {
            if (isCurrentPage()) {
                seleniumLib.sleepInSeconds(2);
                seleniumLib.sendValue(username, AppConfig.getApp_username());
                seleniumLib.sendValue(password, AppConfig.getApp_password());
                seleniumLib.clickOnElement(loginBtn);
                seleniumLib.sleepInSeconds(10);
            } else {
                Debugger.println("IT is NOT navigated to Sign IN of GoAnywhere.. Directly logged in GoAnywhere..");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            Debugger.println("EXCEPTION is Found in loginToGoAnywhere " + exp);
            Assert.assertTrue("EXCEPTION is Found in loginToGoAnywhere", false);
        }
    }

    public void logout()
    {
        By accountLink = By.xpath("//a[@id='accountLink']");
        By logOut = By.xpath("//a[@id='logoutLink']");
        try {
            seleniumLib.highLightElement(accountLink);
            seleniumLib.highLightElement(logOut);
            if (!seleniumLib.isElementPresent(logOut)) {
                seleniumLib.sleepInSeconds(2);
                seleniumLib.waitForElementVisible(logOut);
            }
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnElement(logOut);
        } catch (Exception exp) {
            Debugger.println("logout buttton is not available:" +exp);
        }

    }
}
