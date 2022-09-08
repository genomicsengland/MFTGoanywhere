package co.uk.gel.proj.pages;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pages {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected Page1 page1;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        page1 = PageFactory.initElements(driver, Page1.class);
    }
}//end class
