package co.uk.gel.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static co.uk.gel.config.BrowserConfig.*;

public class BrowserFactory {

    private static final Logger log = LoggerFactory.getLogger(BrowserFactory.class);

    WebDriver driver;
    public static final String USERNAME = "rexsureshthankas1";
    public static final String AUTOMATE_KEY = "1uFqNqxkLFhYGRcVbVbQ";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String strDate = "NGIS-UI-" + getBrowser() + "-" + getBrowserVersion() + "-" + dateFormat.format(date);
    String resolution = "1920x1080";
    String project = "NGIS UI Automation";
    String applicationType = "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream";

    private final static String ZAP_PROXYHOST = "127.0.0.1";
    private final static int ZAP_PROXYPORT = 9191;
    private final static String ZAP_APIKEY = null;
    //private ScanningProxy zapScanner;
    private String OS = null;

    public WebDriver getDriver() throws MalformedURLException {
        return getDriver(BrowserConfig.getServerType(), BrowserConfig.getBrowser(), true);
    }

    public WebDriver getDriver(String serverType, String browser,
                               boolean javascriptEnabled) throws MalformedURLException {
        BrowserEnum browserEnum = BrowserEnum.valueOf(browser.toUpperCase());
        ServerTypeEnum serverTypeEnum = ServerTypeEnum.valueOf(serverType.toUpperCase());
        if (serverTypeEnum == ServerTypeEnum.LOCAL) {
            switch (browserEnum) {
                case CHROME:
                    WebDriverManager.chromedriver().clearPreferences();
                    WebDriverManager.chromedriver().setup(); // 30-09-2019 - Added WebDriver Manager to get the Chrome Driver version and download
                    driver = getChromeDriver(null, javascriptEnabled);
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().clearPreferences();
                    WebDriverManager.firefoxdriver().setup();
                    driver = getFirefoxDriverLocal(null, javascriptEnabled);
                    break;
                case SAFARI:
                    driver = getSafariDriver(null, javascriptEnabled);
                    break;
                case IE:
                    WebDriverManager.iedriver().clearPreferences();
                    WebDriverManager.iedriver().arch32().setup();
                    driver = getInternetExplorer(null, javascriptEnabled);
                    break;
                case OPERA:
                    WebDriverManager.operadriver().clearPreferences();
                    WebDriverManager.operadriver().setup();
                    driver = getOpera(null, javascriptEnabled);
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().clearPreferences();
                    WebDriverManager.edgedriver().forceDownload().setup();
                    driver = getEdge(null, javascriptEnabled);
                    break;
                case SECUREBROWSER_CHROME:
                    /*try {
                        log.info("Browser: SECURE BROWSER...Initializing ZAPProxyScanner...Host:");
                        try {
                            zapScanner = new ZAProxyScanner(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_APIKEY);
                        } catch (Exception exp1) {
                            log.info("INIT EXCEPTION: " + exp1);
                            Assert.assertTrue(false);
                        }
                        log.info("ZAProxyScanner Initialized......Clearing to start new session...");
                        zapScanner.clear(); //Start a new session
                        //Debugger.println("Cleared...Initializing zapSpider.....");
                        //Default considering Chrome
                        WebDriverManager.chromedriver().clearPreferences();
                        WebDriverManager.chromedriver().setup();
                        log.info("ChromePath: " + WebDriverManager.chromedriver().getBinaryPath());
                        OS = System.getProperty("os.name").toLowerCase();
                        log.info("OS: " + OS);
                        if (OS.indexOf("win") >= 0) {
                            driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), WebDriverManager.chromedriver().getBinaryPath());
                        } else if (OS.indexOf("linux") >= 0) {
                            driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), WebDriverManager.chromedriver().getBinaryPath(), "linux");
                        } else {//Mac
                            driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), WebDriverManager.chromedriver().getBinaryPath());
                        }

                    } catch (Exception exp) {
                        log.info("EXCEPTION: " + exp);
                    }*/
                    break;
                default:
                    log.info("Invalid Browser information");
                    Assert.assertFalse("Browser : " + browser + " is not present in the BrowserEnum", true);
                    break;
            }
        }
        if (serverTypeEnum == ServerTypeEnum.BROWSERSTACK) {
            switch (browserEnum) {
                case CHROME:
                    driver = new RemoteWebDriver(new URL(URL), getChromeOptions(null, javascriptEnabled));
                    break;
                case FIREFOX:
                    driver = new RemoteWebDriver(new URL(URL), getFirefoxOptions(null, javascriptEnabled));
                    break;
                case SAFARI:
                    driver = new RemoteWebDriver(new URL(URL), getsafariOptions(null, javascriptEnabled));
                    break;
                case IE:
                    driver = new RemoteWebDriver(new URL(URL), getInternetExplorerOptions(null, javascriptEnabled));
                    break;
                case OPERA:
                    driver = new RemoteWebDriver(new URL(URL), getOperaOptions(null, javascriptEnabled));
                    break;
                case EDGE:
                    driver = new RemoteWebDriver(new URL(URL), getEdgeOptions(null, javascriptEnabled));
                    break;

                default:
                    log.info("Invalid Browser information");
                    Assert.assertFalse("Browser : " + browser + " is not present in the BrowserEnum", true);
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriver getSafariDriver(String userAgent,
                                      boolean javascriptEnabled) {
        return new SafariDriver(getSafariLocalOptions(userAgent, javascriptEnabled));
    }

    private SafariOptions getSafariLocalOptions(String userAgent, boolean javascriptEnabled) {
        SafariOptions safariLocalOptions = new SafariOptions();
        safariLocalOptions.setCapability("safari.options.dataDir", downloadFilepath());
        return safariLocalOptions;
    }

    private SafariOptions getsafariOptions(String userAgent, boolean javascriptEnabled) {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("project", project);
        safariOptions.setCapability("build", "NGIS UI Browser Stack Safari");
        safariOptions.setCapability("browser", "Safari");
        safariOptions.setCapability("browser_version", getBrowserVersion());
        safariOptions.setCapability("os", getOsName());
        safariOptions.setCapability("os_version", getOsVersion());
        safariOptions.setCapability("resolution", resolution);
        safariOptions.setCapability("name", strDate);
        safariOptions.setCapability("safari.options.dataDir", downloadFilepath());
        return safariOptions;
    }

    private WebDriver getFirefoxDriverLocal(String userAgent,
                                            boolean javascriptEnabled) {
        return new FirefoxDriver(getFirefoxLocalOptions(userAgent, javascriptEnabled));

    }

    private FirefoxOptions getFirefoxLocalOptions(String userAgent,
                                                  boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", applicationType);
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions fireFoxLocalOptions = new FirefoxOptions();
        fireFoxLocalOptions.setProfile(profile);
        return fireFoxLocalOptions;
    }

    private FirefoxOptions getFirefoxOptions(String userAgent,
                                             boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", applicationType);
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("project", project);
        firefoxOptions.setCapability("build", "NGIS UI Browser Stack Firefox");
        firefoxOptions.setCapability("browser", "Firefox");
        firefoxOptions.setCapability("browser_version", getBrowserVersion());
        firefoxOptions.setCapability("os", getOsName());
        firefoxOptions.setCapability("os_version", getOsVersion());
        firefoxOptions.setCapability("resolution", resolution);
        firefoxOptions.setCapability("name", strDate);
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }
    // Added the functions for getChromeDriver for WebDriver Manager  30/09/2019..
    private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
        return new ChromeDriver(getChromeLocalOptions(userAgent, javascriptEnabled));
    }

    private ChromeOptions getChromeLocalOptions(String userAgent,
                                                boolean javascriptEnabled) {
        ChromeOptions chromeLocalOptions = new ChromeOptions();
        if (null != userAgent) {
            chromeLocalOptions.addArguments("user-agent=" + userAgent);
        }
        chromeLocalOptions.setExperimentalOption("prefs", downloadPathsetup());
        if (!javascriptEnabled) {
            chromeLocalOptions.addArguments("disable-javascript");
        }
        return chromeLocalOptions;
    }


    private ChromeOptions getChromeOptions(String userAgent,
                                           boolean javascriptEnabled) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", downloadPathsetup());
        chromeOptions.setCapability("project", project);
        chromeOptions.setCapability("build", "NGIS UI Browser Stack Chrome");
        chromeOptions.setCapability("browser", "Chrome");
        chromeOptions.setCapability("browser_version", getBrowserVersion());
        chromeOptions.setCapability("os", getOsName());
        chromeOptions.setCapability("os_version", getOsVersion());
        chromeOptions.setCapability("resolution", resolution);
        chromeOptions.setCapability("name", strDate);
        return chromeOptions;
    }

    private WebDriver getInternetExplorer(String userAgent, boolean javascriptEnabled) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        internetExplorerOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        internetExplorerOptions.setCapability("unexpectedAlertBehaviour", "accept");
        internetExplorerOptions.setCapability("ignoreProtectedModeSettings", true);
        internetExplorerOptions.setCapability("disable-popup-blocking", true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
        internetExplorerOptions.setCapability(InternetExplorerDriver.EXTRACT_PATH, downloadFilepath());
        internetExplorerOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        return driver = new InternetExplorerDriver(internetExplorerOptions);

    }

    private InternetExplorerOptions getInternetExplorerOptions(String userAgent, boolean javascriptEnabled) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability("project", project);
        internetExplorerOptions.setCapability("build", "NGIS UI Browser Stack IE");
        internetExplorerOptions.setCapability("browser", "IE");
        internetExplorerOptions.setCapability("browser_version", getBrowserVersion());
        internetExplorerOptions.setCapability("os", getOsName());
        internetExplorerOptions.setCapability("os_version", getOsVersion());
        internetExplorerOptions.setCapability("resolution", resolution);
        internetExplorerOptions.setCapability("name", strDate);
        return internetExplorerOptions;
    }

    private WebDriver getEdge(String userAgent, boolean javascriptEnabled) {
        EdgeOptions edgeOptions = new EdgeOptions();
        return driver = new EdgeDriver(edgeOptions);

    }

    private EdgeOptions getEdgeLocalOptions(String userAgent, boolean javascriptEnabled) {
        EdgeOptions edgeLocalOptions = new EdgeOptions();
        edgeLocalOptions.setCapability("prefs", downloadPathsetup());
        return edgeLocalOptions;
    }

    private EdgeOptions getEdgeOptions(String userAgent, boolean javascriptEnabled) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("project", project);
        edgeOptions.setCapability("build", "NGIS UI Browser Stack Edge");
        edgeOptions.setCapability("browser", "Edge");
        edgeOptions.setCapability("browser_version", getBrowserVersion());
        edgeOptions.setCapability("os", getOsName());
        edgeOptions.setCapability("os_version", getOsVersion());
        edgeOptions.setCapability("resolution", resolution);
        edgeOptions.setCapability("name", strDate);
        return edgeOptions;
    }

    private WebDriver getOpera(String userAgent, boolean javascriptEnabled) {
        return new OperaDriver(getOperaLocalOptions(userAgent, javascriptEnabled));

    }

    private OperaOptions getOperaLocalOptions(String userAgent, boolean javascriptEnabled) {
        OperaOptions operaLocalOptions = new OperaOptions();
        operaLocalOptions.setCapability("prefs", downloadPathsetup());
        return operaLocalOptions;
    }


    private OperaOptions getOperaOptions(String userAgent, boolean javascriptEnabled) {
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setCapability("project", project);
        operaOptions.setCapability("build", "NGIS UI Browser Stack Opera");
        operaOptions.setCapability("browser", "Opera");
        operaOptions.setCapability("browser_version", getBrowserVersion());
        operaOptions.setCapability("os", getOsName());
        operaOptions.setCapability("os_version", getOsVersion());
        operaOptions.setCapability("resolution", resolution);
        operaOptions.setCapability("name", strDate);
        return operaOptions;
    }

    private HashMap downloadPathsetup() {
        String downloadFilePath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        File location = new File(downloadFilePath);
        if (!location.exists()) {
            location.mkdirs();
        }
        log.info("BROWSER FACTORY:DEFAULT DOWNLOAD PATH:"+downloadFilePath);
        HashMap<String, Object> pathPrefs = new HashMap<String, Object>();
        pathPrefs.put("profile.default_content_settings.popups", 0);
        pathPrefs.put("download.default_directory", downloadFilePath);
        return pathPrefs;
    }

    private String downloadFilepath() {
        //Setting default download path
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        try {
            File download_loc = new File(downloadFilepath);
            if (!download_loc.exists()) {
                download_loc.mkdirs();
            }
        } catch (Exception exp) {
            System.out.println("Exception in creating download directory..." + exp);
        }
        return downloadFilepath;
    }


    private FirefoxOptions getFirefoxSecurityOptions(String userAgent,
                                                     boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
//			profile.setEnableNativeEvents(true);
        profile.shouldLoadNoFocusLib();
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("javascript.enabled", javascriptEnabled);
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        try {
            File download_loc = new File(downloadFilepath);
            if (!download_loc.exists()) {
                download_loc.mkdirs();
            }
        } catch (Exception exp) {
            System.out.println("Exception in creating download directory..." + exp);
        }
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        firefoxOptions.setCapability("marionette", true);
        return firefoxOptions;
    }

    public static WebDriver createProxyDriver(String type, Proxy proxy, String path) {
        return createProxyDriver(type, proxy, path, null);
    }

    public static WebDriver createProxyDriver(String type, Proxy proxy, String path, String typeOfOS) {
        log.info("createProxyDriver............");
        if (typeOfOS != null) {
            System.out.println("Calling typeOfOS = linux");
            if (type.equalsIgnoreCase("Chrome")){
                return createChromeDriver(createProxyCapabilities(proxy), path, "linux");
            }else{//FireFox
                return createFirefoxDriver(createProxyCapabilities(proxy));
            }
        } else {
            log.info("Calling typeOfOS = non-linux");
            if (type.equalsIgnoreCase("Chrome")) {
                return createChromeDriver(createProxyCapabilities(proxy), path);
            }else{//FireFox
                return createFirefoxDriver(createProxyCapabilities(proxy));
            }
        }
    }

    public static WebDriver createChromeDriver(DesiredCapabilities capabilities, String path, String OS) {
        log.info("I am in create createChromeDriver" + "path=" + path + " OS.toString()=");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certificate-errors");
        System.setProperty("webdriver.chrome.driver", path);
        if (OS.equalsIgnoreCase("linux")) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1920,1080");
            // 1920x1080x24
            chromeOptions.addArguments("disable-infobars"); // disabling infobars
            // chromeOptions.addArguments("--disable-extensions"); // disabling extensions
            //   chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
            chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        }
        //  chromeOptions.addArguments("--headless");
        if (capabilities != null) {
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return new ChromeDriver(capabilities);
        } else return new ChromeDriver();
    }

    public static WebDriver createChromeDriver(DesiredCapabilities capabilities, String path) {
        return createChromeDriver(capabilities, path, "non-linux");
    }

    public static WebDriver createFirefoxDriver(DesiredCapabilities capabilities) {
        if (capabilities != null) {
            return new FirefoxDriver(capabilities);
        }
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("WebDriver");
        if (myProfile == null) {
            File ffDir = new File(System.getProperty("user.dir") + File.separator + "ffProfile");
            if (!ffDir.exists()) {
                ffDir.mkdir();
            }
            myProfile = new FirefoxProfile(ffDir);
        }
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(true);
        myProfile.setPreference("webdriver.load.strategy", "unstable");
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        capabilities.setCapability(FirefoxDriver.PROFILE, myProfile);
        return new FirefoxDriver(capabilities);
    }

    public static DesiredCapabilities createProxyCapabilities(Proxy proxy) {
        log.info("createProxyCapabilities.........");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("proxy", proxy);
        return capabilities;
    }

    private static Proxy createZapProxyConfigurationForWebDriver() {
        log.info("createZapProxyConfigurationForWebDriver.........");
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        proxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        return proxy;
    }

    private WebDriver getSafariDriver(Object object,
                                      boolean javascriptEnabled) {
        DesiredCapabilities safariCaps = DesiredCapabilities.safari();
        safariCaps.setCapability("safari.cleanSession", true);
        return new SafariDriver(safariCaps);
    }

    private WebDriver getFirefoxDriver(String userAgent,
                                       boolean javascriptEnabled) {
        return new FirefoxDriver(getFirefoxOptions(userAgent, javascriptEnabled));
    }



    // Added the functions for getChromeDriver for WebDriver Manager  30/09/2019..
//    private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
//        Debugger.println("getChromeDriver.........");
//        return new ChromeDriver(getChromeOptionsSecurity(userAgent, javascriptEnabled));
//    }


    private ChromeOptions getChromeOptionsSecurity(String userAgent,
                                                   boolean javascriptEnabled) {
        log.info("getChromeOptions.........");
        //Setting default download path for chrome browser
        String downloadFilePath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        File location = new File(downloadFilePath);
        if (!location.exists()) {
            location.mkdirs();
        }
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilePath);
        ChromeOptions opts = new ChromeOptions();
        if (null != userAgent) {
            opts.addArguments("user-agent=" + userAgent);
        }
        opts.setExperimentalOption("prefs", chromePrefs);
        if (!javascriptEnabled) {
            opts.addArguments("disable-javascript");
        }
        return opts;
    }
}//end


