package co.uk.gel.config;

public class BrowserConfig {

    public static String browser;
    public static String browserVersion;
    public static String serverType;
    public static String osName;
    public static String osVersion;
    public static String rerunOption;

    public static String getBrowser() {
        browser = System.getProperty("browser");
        if(browser == null || browser.isEmpty()){
            browser = "Chrome";
        }
        return browser;
    }
    public static String getServerType() {
        serverType = System.getProperty("serverType");
        if (serverType == null || serverType.isEmpty()) {
            serverType = "Local";
        }
        return serverType;
    }

    public static String getBrowserVersion() {
        browserVersion = System.getProperty("browserVersion");
        if (browserVersion == null || browserVersion.isEmpty()) {
            browserVersion = "68";
        }
        return browserVersion;
    }

    public static String getOsName() {
        osName = System.getProperty("osName");
        if (osName == null || osName.isEmpty()) {
            osName = "Windows";
        }
        return osName;
    }

    public static String getOsVersion() {
        osVersion = System.getProperty("osVersion");
        if (osVersion == null || osVersion.isEmpty()) {
            osVersion = "10";
        }
        return osVersion;
    }

    public static String ifRerunNeeded() {
        rerunOption = System.getProperty("rerunOption");
        if (rerunOption == null || rerunOption.isEmpty()) {
            rerunOption = "No";
        }
        return rerunOption;
    }

}//end
