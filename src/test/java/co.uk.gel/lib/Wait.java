package co.uk.gel.lib;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Wait {

    private static final Logger log = LoggerFactory.getLogger(Wait.class);

    //protected static WebDriverWait wait;
    protected static WebDriver webDriver;

    public Wait(WebDriver driver) {
        webDriver = driver;
    }

    @SuppressWarnings("deprecation")
    public static void forElementToBeDisplayed(WebDriver driver, WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(100, TimeUnit.SECONDS);
        wait.pollingEvery(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /*
	Added this method to verify the element is actually displayed after the specified waiting period.
	 */
    @SuppressWarnings("deprecation")
    public static boolean isElementDisplayed(WebDriver driver, WebElement element,int seconds) {
        try{
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(seconds, TimeUnit.SECONDS);
            wait.pollingEvery(5, TimeUnit.SECONDS);
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }catch (Exception exp){
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public static void forElementToBeDisplayed(WebDriver driver, WebElement element, int timeInSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(timeInSeconds, TimeUnit.SECONDS);
        wait.pollingEvery(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @SuppressWarnings("deprecation")
    public static void forElementToBeClickable(WebDriver driver, WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(50, TimeUnit.SECONDS);
        wait.pollingEvery(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @SuppressWarnings("deprecation")
    public static void forElementToDisappear(WebDriver driver, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(40, TimeUnit.SECONDS);
        wait.pollingEvery(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        wait = new WebDriverWait(driver, 40);
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean forNumberOfElementsToBeGreaterThan(WebDriver driver, By locator, int number) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
            return true;
        }catch (Exception exp){
           log.info("No of elements of locator expected to more than "+number+", but not, even after waiting period. Clicking again on Save and Continue.");
            return false;

        }
    }

    public static void forNumberOfElementsToBeEqualTo(WebDriver driver, By locator, int number) {
        try {
            log.info("Closing Cookies Banner.");
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
        }catch(Exception exp){
            log.info("Exception from closing cookies banner: "+exp);
        }
    }

    public static void forURLToContainSpecificText(WebDriver driver, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.urlContains(text));
    }

    public static void forAlertToBePresent(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void seconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void forPageToBeLoaded(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    public static WebElement waitForByElementVisible(WebDriver driver,By element,int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            final WebElement el = driver.findElement(element);

            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {

                    return el.isDisplayed();
                }
            });
            return el;
        }catch(Exception exp){
            return null;
        }
    }
}