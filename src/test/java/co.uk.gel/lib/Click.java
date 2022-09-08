package co.uk.gel.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Click {

	private static final Logger log = LoggerFactory.getLogger(Click.class);
	
	public static void element(WebDriver driver, WebElement element) {

		Wait.forElementToBeClickable(driver, element);
		element.click();

	}

	public static void mouseMoveByLocation (WebDriver driver, int x, int y) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove(x, y);
	}

	public static void clickOnByElement(WebDriver driver,By element) {
		WebElement webele = null;
		try {
			webele = Wait.waitForByElementVisible(driver,element,10);
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

}
