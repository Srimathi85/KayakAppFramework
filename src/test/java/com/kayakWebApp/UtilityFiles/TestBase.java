package com.kayakWebApp.UtilityFiles;

import com.jayway.jsonpath.JsonPath;
import com.kayakWebApp.WebDriver.WebDriManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
public class TestBase {
    public static String sConfig;
    public static String sBrowser;
    public static String sAutomation;
    public static WebDriver driverWeb;
    public static CommonUtility oComUtility=new CommonUtility();
    public static SeleniumUtility oSelUtility=new SeleniumUtility();
    public WebDriManager oWebDriManager=new WebDriManager();
    Logger log=Logger.getLogger(getClass().getSimpleName());
    @Before
    public void manageDependency() throws Exception {
        oComUtility.loadLog4jProperty(System.getProperty("user.dir") + "/testresources/log4j.properties");
        sConfig = CommonUtility.readFileReturnInString(System.getProperty("user.dir") + "/testresources/config.json");
        sAutomation = JsonPath.read(sConfig, "automation");
        BasicConfigurator.configure();
        if (sAutomation.toLowerCase().equals("web")) {
            sBrowser = JsonPath.read(sConfig, "browser");
            System.out.println("Browser$$$$$$$$$$$$$$$$$$$$$"+sBrowser);
            log.info("Browser is read");
        }

    }
    @After
    public void terminateApp() {
        if(sAutomation.toLowerCase().equals("web")){
            oSelUtility.quitApp();
            log.info("App is closed");
        }
    }
}
