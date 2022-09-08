package co.uk.gel.lib;

import co.uk.gel.config.BrowserConfig;
import co.uk.gel.utils.TestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class SeleniumLib {

    private static final Logger log = LoggerFactory.getLogger(SeleniumLib.class);

    private static WebDriver driver;
    private static boolean HIGHLIGHT = true;
    private static WebElement webElement = null;
    private static List<WebElement> webElementList = null;

    private String strtext;
    public static String ParentWindowID = null;
    static String defaultSnapshotLocation = System.getProperty("user.dir") + File.separator +"snapshots"+File.separator;
    static String referralFileName = "Referrals.json";

    public SeleniumLib(WebDriver driver) {
        SeleniumLib.driver = driver;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(SeleniumLib.class);

    private static String timeoutErrorMessage(By element) {
        return "Unable to find visibility of element located by " + element + " within " + "10" + " seconds \n "
                + "\nThere several reasons why this may have occurred including: \n" +
                "\n - The page did not finish loading some ui features before the expected timeout (usually related to Ajaxian elements)" +
                "\n - The ui element is legitimately missing from the page due to a bug or feature change " +
                "\n - There was a problem with communication within the Selenium stack" +
                "\nSee image below (if available) for clues.\n If all else fails, RERUN THE TEST MANUALLY! - CPK\n\n";
    }


    public static boolean isClickable(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            final WebElement el = driver.findElement(element);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            log.info("Element is not clickable: " + e);
            return false;
        }
    }

    public static WebElement waitForElementVisible(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
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

    /**
     * @param element
     * @return
     */
    public WebElement getElement(By element) {
        try {
            webElement = waitForElementVisible(element);
            return webElement;
        } catch (NoSuchElementException e) {
            log.info("[Error]" + element.toString() + " Not Found ");
            return null;
            //throw e;
        }
    }


    /**
     * @param element
     */
    public static void elementHighlight(WebElement element) {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: pink; border: 3px solid red;");
        javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

    }

    /**
     * @param element
     */
    public void clickOnElement(By element) {
        WebElement webele = null;
        try {
            webele = getElement(element);
            webele.click();
        } catch (Exception exp) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webele);
            } catch (Exception exp1) {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(element)).click().build().perform();
                //throw exp1;
            }
        }
    }

    public void clickOnWebElement(WebElement webEle) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);//Default waiting
            wait.until(ExpectedConditions.visibilityOf(webEle));
            if(!webEle.isDisplayed()){
                //Waiting for another 30 seconds
                sleepInSeconds(30);
            }
            elementHighlight(webEle);
            webEle.click();
        } catch (Exception exp) {
            try {
                log.info("Clicking Via JavaScript....");
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webEle);

            } catch (Exception exp1) {
                log.info("Clicking Via Action....");
                Actions actions = new Actions(driver);
                actions.moveToElement(webEle).click();
            }
        }
    }

    public List<WebElement> getElements(By ele) {
        try {
            waitForElementVisible(driver.findElement(ele));
            highLightElement(ele);
            return driver.findElements(ele);
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    public void clearValue(By element) {
        try {
            webElement = getWebElement(element);
            webElement.clear();
        } catch (NoSuchElementException e) {
            LOGGER.error("[Error]" + element.toString() + " Not found");
        }
    }

    public void sendValue(By element, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            webElement = getWebElement(element);
            webElement.clear();
            webElement.sendKeys(value);
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        } catch (Exception exp) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.sendKeys(value);
        }
    }
    public void sendValue(WebElement element, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            element.clear();
            element.sendKeys(value);
        } catch (Exception exp) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(value);
        }
    }

    public void focusElement(By element) {
        webElement = getElement(element);
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript("arguments[0].focus();", webElement);
    }

    public void sendKey(By element, Keys key) {
        try {
            webElement = getWebElement(element);
            webElement.sendKeys(key);
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        }
    }

    public boolean selectFromListByText(WebElement element, String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            new Select(element).selectByVisibleText(text);
            return true;
        } catch (NoSuchElementException e) {
            try {
                Select select = new Select(element);
                if (select == null) {
                    return false;
                }
                List<WebElement> options = select.getOptions();
                for (WebElement option : options) {
                    String originalText = text.trim().replace(" ", "").replace(" ", "").toLowerCase();
                    String expectedString = option.getText().trim().replace(" ", "").toLowerCase();
                    //Debugger.println("Original....."+originalText+",Exp.."+expectedString);
                    if (originalText.equalsIgnoreCase(expectedString)) {
                        select.selectByVisibleText(option.getText());
                        //Debugger.println("Yes..got it..........");
                        return true;
                    }
                }
            } catch (Exception exp) {
                return false;
            }
            return false;
        }
    }

    public boolean optionFromListByText(By element, String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            webElementList = driver.findElements(element);
            if (webElementList == null) {
                log.info("element list is null " + webElementList);
                return false;
            }
            for (WebElement actWebelement : webElementList) {
                String actualText = text.trim().replace(" ", "").replace(" ", "").toLowerCase();
                String expectedText = actWebelement.getText().trim().replace(" ", "").toLowerCase();
                if (actualText.equalsIgnoreCase(expectedText)) {
                    actWebelement.click();
                    return true;
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void selectFromListByValue(By element, String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        try {
            webElement = getWebElement(element);
            Select select = new Select(webElement);
            select.selectByValue(text);
        } catch (NoSuchElementException e) {
            log.info("element not found  " + element);
        }
    }

    public static boolean IsDisplayed(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isDisplayed();
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean highLightWebElement(WebElement element) {
        try {
            if (element != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                elementHighlight(element);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean highLightElement(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
                elementHighlight(webElement);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
           return false;
        }
    }

    public boolean isElementPresent(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isDisplayed();

        } catch (Exception e) {
            log.info("[Error]" + element.toString() + "  Not displayed");
            return false;
        }
    }
    public boolean isElementClickable(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isEnabled();

        } catch (Exception e) {
            log.info("[Error]" + element.toString() + "  Not Enabled");
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {

        }
        return false;
    }

    public void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        final WebElement el = element;
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return el.isDisplayed();
            }
        });
    }

    /**
     * @param element
     * @return
     */
    public static WebElement wait(By element) {

        FluentWait<WebDriver> wait = new WebDriverWait(driver, 50);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        } catch (TimeoutException e) {
            throw new TimeoutException(timeoutErrorMessage(element) + e);
        }
        return driver.findElement(element);
    }

    /**
     * @param element
     */
    public boolean JavaScriptClick(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", webElement);
            return true;
        } catch (Exception exp) {
            log.info("Exception: SeleniumLib: Javascript Click.." + exp);
            return false;
        }
    }
    public boolean JavaScriptClick(WebElement element) {
        try {
            elementHighlight(element);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception exp) {
            log.info("Exception: SeleniumLib: Javascript Click.." + exp);
            return false;
        }
    }

    public static WebElement getWebElement(By by) {
        try {
            WebElement element = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception exp) {
            return null;
        }
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }
    /**
     * @param element
     * @return
     */
    public String getText(By element) {
        try {
            webElement = waitForElementVisible(element);
            if (webElement == null) {
                return "";
            }
            elementHighlight(webElement);
            strtext = webElement.getText();
            return "" + strtext;

        } catch (Exception ex) {

            webElement = driver.findElement(element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
            try {
                elementHighlight(webElement);
                strtext = webElement.getText();
            } catch (Exception exp1) {
                return "";
            }
            return strtext;
        }
    }
    public String getText(WebElement element) {
        try {

            elementHighlight(element);
            strtext = element.getText();
            return "" + strtext;

        } catch (Exception ex) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            try {
                elementHighlight(element);
                strtext = element.getText();
            } catch (Exception exp1) {
                return "";
            }
            return strtext;
        }
    }

    /**
     * @param i
     */
    public static void sleep(int i) {
        try {
            Thread.sleep(i * 2000);
        } catch (InterruptedException exp) {

        }
    }

    public static void sleepInSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException exp) {

        }
    }

    public void waitForAjax(int timeoutInSeconds) {
        //Checking active ajax calls by calling jquery.active
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

                for (int i = 0; i < timeoutInSeconds; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;

                        if (n.longValue() == 0L)
                            break;
                    }
                    Thread.sleep(1000);
                }
            } else {
                LOGGER.error("Web driver: " + driver + " cannot execute javascript");
            }
        } catch (InterruptedException e) {
            LOGGER.error("Ajax wait Exception  " + e);
        }
    }

    /**
     *
     */
    public void ChangeWindow() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }
    //File upload logic changed from using Robot script to Selenium option
    public static boolean upload(WebElement element, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                log.info("Specified File does not exist for upload:"+path);
                return false;
            }
            log.info("Uploading the file: "+path);
            element.sendKeys(path);
            log.info("Upload Finished.");
            return true;
        } catch (Exception exp) {
            log.info("Exception from uploading the file: " + exp);
            return false;
        }
    }

    public static boolean isTextPresent(String text) {
        try {
            if (text == null) {
                return false;
            }
            return getVisibleText().contains(text);
        } catch (Exception exp) {
            LOGGER.error("element not found  " + exp);
            return false;
        }
    }

    /**
     * @return
     */
    public static String getVisibleText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception exp) {
            return "";
        }
    }
    public static void dismissAllert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().dismiss();
        }
    }

    static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static void scrollToElement(WebElement element) {
        try {
            if(element == null){
                return;
            }
            Point location = element.getLocation();
            String script = "scroll(" + location.x + "," + (location.y - 120) + ")";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script);
        } catch (Exception e) {

        }
    }
    public static void takeAScreenShot(String filename){
        try{
            if(filename == null || filename.isEmpty()){
                filename = "screenshot";
            }
            if(filename.indexOf(".") == -1){
                filename = filename+".jpg";
            }
            if(!filename.contains("NTS")) {
                String[] today = TestUtils.getCurrentDay();
                if (today != null && today.length == 3) {
                    filename = "T" + today[0] + today[1] + filename;
                }
            }
            log.info("ScreenShotFile:"+filename);
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File(defaultSnapshotLocation+filename));

        }catch(Exception exp){

        }
    }
    public static boolean switchToNewTab(){
        try {
            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            return true;
        }catch(Exception exp){
            log.info("Exception in switching to new Tab: "+exp);
            return false;
        }
    }
    public static boolean closeCurrentWindow(){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.close()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            return true;
        }catch(Exception exp){
            log.info("Could not close current window: "+exp);
            return false;
        }
    }
    public static boolean drawSignature(WebElement drawArea) {
        try {
            Wait.forElementToBeDisplayed(driver, drawArea);
            Click.element(driver, drawArea);
            Actions builder = new Actions(driver);
            Action drawAction = builder.moveToElement(drawArea, 135, 15) //start points x axis and y axis.
                    .clickAndHold()
                    .moveByOffset(80, 80)
                    .moveByOffset(50, 20)
                    .release()
                    .build();
            drawAction.perform();
            Wait.seconds(1);
            return true;
        }catch(Exception exp){
            // Debugger.println("SeleniumLib: Could not draw Signature: "+exp);
            takeAScreenShot("drawSignature.jpg");
            return false;
        }
    }
    //Created new method, where tooltip on mouseMove need to be validated
    public boolean moveAndClickOn(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            return true;
        }catch(Exception exp){
            log.info("Exception in clicking on Element by moving mouse:"+element.toString()+"\n"+exp);
            return false;
        }
    }
    public void moveMouseAndClickOnElement(By element) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(element);
        if(we == null){
            return;
        }
        action.click(we).build().perform();
    }


    public static boolean skipIfBrowserStack(String serverType) {
        return BrowserConfig.getServerType().toUpperCase().equals(serverType);
    }

    public int getNoOfRows(By element) {
        try {
            waitForElementVisible(driver.findElement(element));
            return driver.findElements(element).size();
        } catch (NoSuchElementException exp) {
            return 0;
        }
    }
    public int getNoOfRows(String rowPath){
        By element = By.xpath(rowPath);
        return getNoOfRows(element);
    }
    public int getColumnIndex(By TableHeading, String column_name) {
        List<WebElement> Headings =  getHeadingElements(TableHeading);
        if(Headings == null || Headings.size() == 0){
            return -1;
        }
        String heading_name = "";
        for (int index = 0; index < Headings.size(); index++) {
            heading_name = Headings.get(index).getText();
            if(column_name.equalsIgnoreCase(heading_name)) {
                return index + 1;
            }
        }
        return -1;
    }
    public List<WebElement> getHeadingElements(By element) {
        try {
            waitForElementVisible(driver.findElement(element));
            return driver.findElements(element);
        } catch (NoSuchElementException exp) {
            exp.printStackTrace();
            // DDFREDebugger.println("SeleniumLib: [Error]" + element.toString() + " Not Found ");
            return null;
        }
    }

    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public static String[] getCurrentDay() {
        Calendar today = Calendar.getInstance();
        String year = "";
        String month = "";
        String day = "";
        int iyear = today.get(Calendar.YEAR);
        int imonth = today.get(Calendar.MONTH) + 1;
        int iday = today.get(Calendar.DATE);

        if (imonth < 10) {
            month = "0" + imonth;
        } else {
            month = "" + imonth;
        }
        year = "" + iyear;
        if (iday < 10) {
            day = "0" + iday;
        } else {
            day = "" + iday;
        }
        return new String[]{day, month, year};
    }

    public static void clearAllSnapShots() {
          try {
                File location1 = new File(defaultSnapshotLocation);
                if (!location1.exists()) {//Create the location, if not exists, first time may not be existing.
                    location1.mkdirs();
                    return;
                }
                File[] files = location1.listFiles();
                if (files == null || files.length < 1) {
                    return;
                }
                String[] today = getCurrentDay();
                String prefix = today[0] + today[1];
                for (int i = 0; i < files.length; i++) {
                    if (!(files[i].getName().startsWith(prefix))) {
                        files[i].delete();
                    }
                }
            } catch (Exception e) {
              log.info("Exception in deleting all existing snapshots." + e);
          }
        }

}

