package co.uk.gel.lib;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.*;

public class Actions {

    private static final Logger log = LoggerFactory.getLogger(Actions.class);

    static Random random = new Random();

    public static void clickElement(WebDriver driver, WebElement element) {
        Wait.forElementToBeClickable(driver, element);
        element.click();
    }

    public static boolean selectValueFromDropdown(WebElement dropdownValue, String value) {
        try {
            dropdownValue.findElement(By.xpath("//span[text()='" + value + "']")).click();
            return true;
        } catch (Exception exp) {
            try {
                dropdownValue.findElement(By.xpath("//span[contains(text(),'" + value + "')]")).click();
                return true;
            } catch (Exception exp1) {

                log.info("Dropdown value: " + value + ", not present in the dropdown list. See screen shot DropDownValues.jpg, Element:" + dropdownValue);
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
            }
            return false;
        }

    }

    public static boolean selectByIndexFromDropDown(List<WebElement> dropDownValues, int index) {
        try {
            dropDownValues.get(index).click();
            return true;
        } catch (Exception exp) {
            log.info("Exception in Selecting Dropdown value by index..."+exp);
            SeleniumLib.takeAScreenShot("DropDownValues_SelectByIndex.jpg");
            return false;
        }
    }


    public static void selectRandomValueFromDropdown(List<WebElement> dropdownValues) {
        try {
            int index = random.nextInt(dropdownValues.size() - 1);
            dropdownValues.get(index).click();
        } catch (Exception exp) {
            if (dropdownValues.size() > 1) {
                dropdownValues.get(0).click();
            }
        }
    }

    public static List<String> getValuesFromDropdown(List<WebElement> dropdownValues) {
        try {
            ArrayList<String> values = new ArrayList<String>();
            for (WebElement element : dropdownValues) {
                values.add(Actions.getText(element));
            }
            if (!values.isEmpty()) {
                return values;
            }
            return null;
        } catch (IllegalArgumentException | ElementClickInterceptedException | StaleElementReferenceException exp) {
            return null;
        }
    }

    public static String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception exp) {
            log.info("Could not read text from Element: " + element);
            return null;
        }
    }


    public void editTextField(WebDriver driver, WebElement element, String value) {
        Wait.forElementToBeDisplayed(driver, element);
        element.clear();
        element.sendKeys(value);
    }


    public static String getValue(WebElement element) {
        return element.getAttribute("value");
    }

    public static String getClassName(WebElement element) {
        return element.getAttribute("class");
    }


    public static String getAutoCompleteAttribute(WebElement element) {
        return element.getAttribute("autoComplete");
    }

    public static String getPlaceHolderAttribute(WebElement element) {
        return element.getAttribute("placeholder");
    }


    public static void fillInValue(WebElement element, String value) {
        element.sendKeys(value);
    }

    public static void fillInValue(WebDriver driver, WebElement element, String value) {
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(element).click().perform();
        element.sendKeys(value);
    }

    public static void fillInValueOneCharacterAtATimeOnTheDynamicInputField(WebElement element, String strValue) {
        //https://jira.extge.co.uk/browse/NTS-3539 - it's absolutely fine to pass partial values to the dropdown whose values are populated at runtime
        //  if we pass exact full value to this web element at one go, like - Intraneural perineurioma - then there is a chance that dynamic dropdown is not displayed in the UI
        //  As we are waiting for dynamic populated list to appear in selenium , this kind of tests becomes intermittenly fail/pass depends on the application behaviour
        // so the approach is to pass partial values into the web element and wait for the auto-suggest dropdown to appear, and choose the specific/random values from the auto suggest dropdown list
        List<String> charList = Arrays.asList(strValue.split(""));
        for (String character : charList) {
            Actions.fillInValue(element, character);
            Wait.seconds(1);
        }
    }

    public static void clearTextField(WebElement element) {
        element.clear();
    }

    public static void clearInputField(WebElement element) { // Required for deleting text-input for patient search DOB
        while (!getValue(element).isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public static void clearField(WebElement element) {
        element.sendKeys(Keys.BACK_SPACE);
    }

    public static void clearField(WebDriver driver, WebElement element) throws AWTException {
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(element).click().perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1].substring(0, 3);
    }


    public static void switchTab(WebDriver driver) {
        String mainWindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String childWindow = i1.next();
            if (!mainWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                driver.switchTo().activeElement();
            }
        }
    }

    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static void acceptAlert(WebDriver driver) {
        if (isAlertPresent(driver)) {
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Wait.seconds(2);
        }
    }

    public static void dismissAlert(WebDriver driver) {
        if (isAlertPresent(driver)) {
            driver.switchTo().alert().dismiss();
            driver.switchTo().defaultContent();
            Wait.seconds(2);
        }
    }

    public static String getTextOfAlertMessage(WebDriver driver) {
        try {
            return driver.switchTo().alert().getText();
        } catch (Exception exp) {
            log.info("Alert expected, but not present.");
            SeleniumLib.takeAScreenShot("NoAlertMessage.jpg");
            return "";
        }
    }

    public static void refreshBrowser(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void browseBackward(WebDriver driver) {
        driver.navigate().back();
    }

    public static void browseForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public static void closeBrowser(WebDriver driver) {
        driver.close();
    }

    public static void cleanUpSession(WebDriver driver) {
        driver.quit();
    }

    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    /*
     Implemented the method retryClickAndIgnoreElementInterception() fix the intermittent ElementClickInterceptedException
   // org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
   // Element <a class="styles_inline-link__3cAK2" href="/test-order/new-patient">...</a> is not clickable at point (502, 537).
   // Other element would receive the click: <html lang="en">...</html> ...30/10/2019
     */

    public static void retryClickAndIgnoreElementInterception(WebDriver driver, WebElement element) {
        int counter = 0;
        boolean flag = true;
        while (flag) {
            counter++;
            //Debugger.println("Counter is:" + counter);
            try {
                Wait.forElementToBeClickable(driver, element);
                Click.element(driver, element);
                flag = false;
            } catch (ElementClickInterceptedException e) {
                Wait.forElementToBeClickable(driver, element);
                //Debugger.println("Actions: Clicking on Element :" + element);
            } catch (Exception exp) {
                try {
                    log.info("Actions: Clicking on Element Exception :" + exp);
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", element);
                } catch (Exception exp1) {
                    org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                    actions.moveToElement(element).click().build().perform();
                    //throw exp1;
                }
            }
            if (counter == 10)
                break;
        }
    }

    public static void retrySelectRandomValueFromDropDown(List<WebElement> dropDownValues) {
        try {
            boolean flag = false;
            int counter = 5;  // Counter for number of tries
            for (int i = 1; i <= counter; i++) {
                if (dropDownValues.size() > 0) {
                    flag = true;
                    break;
                }
                Wait.seconds(1);
            }
            if (flag) {
                int index = random.nextInt(dropDownValues.size() - 1);
                try {
                    dropDownValues.get(index).click();
                } catch (ElementClickInterceptedException e) {
                    dropDownValues.get(0).click();
                }
            } else {
                log.info("There is no values found in dropdown");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void retrySelectExactValueFromDropDown(WebDriver driver, List<WebElement> dropDownElementValues, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfAllElements(dropDownElementValues));
            List<String> actualDropDownValues = new ArrayList<>();
            for (WebElement dropDownElement : dropDownElementValues) {
                actualDropDownValues.add(dropDownElement.getText().trim());
            }
            int index = actualDropDownValues.indexOf(value);
            dropDownElementValues.get(index).click();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Unable to select value from drop down.");
        }
    }


    public static void reClickDropDownFieldIfLabelErrorIsShown(WebDriver driver, List<WebElement> fieldErrors, WebElement element, WebElement elementLabel, int counter) {
        for (int i = 1; i <= counter; i++) {
            if (fieldErrors.size() > 0) {
                Actions.clickElement(driver, element);
                log.info("Error message triggered on clicking the element " + getText(elementLabel) + ":" + i);
                break;
            }
            Wait.seconds(1);
        }
    }

    public static boolean isTabClickable(WebDriver driver, Integer expectedTabCount, List<WebElement> element) {
        Iterator<WebElement> itr = element.iterator();
        int actualTabCount = 0;
        while (itr.hasNext()) {
            Wait.forElementToBeClickable(driver, itr.next());
            actualTabCount++;
        }
        return actualTabCount == expectedTabCount;
    }

    public static boolean isRadioButtonIsSelected(WebElement element) {
        try {
            String str = element.getAttribute("checked");
            if (str.equalsIgnoreCase("true")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteCookies(WebDriver driver) {
        try {
            log.debug("Clearing Browser cookies...");
            driver.manage().deleteAllCookies();
            log.debug("No cookies found... " + driver.manage().getCookies());
        } catch (Exception exp) {
            log.info("Exception caught while clearing cookies : " + exp);
        }
    }
}
