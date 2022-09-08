package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Page1 {

    private static final Logger log = LoggerFactory.getLogger(Page1.class);

    WebDriver driver;
    SeleniumLib seleniumLib;

    public Page1(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    public void testMethod() {
        log.info("User is in the Application Home Page!");
    }//end
}