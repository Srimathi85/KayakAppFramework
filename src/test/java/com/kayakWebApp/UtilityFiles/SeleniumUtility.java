package com.kayakWebApp.UtilityFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.kayakWebApp.WebDriver.WebDriManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumUtility extends  TestBase{
    //public static  WebDriver driver=driverWeb;
    WebDriManager oWebDriManager=new WebDriManager();
    Logger log=Logger.getLogger(getClass().getSimpleName());
    public static ExtentReports extentReports;
    public  static ExtentHtmlReporter htmlReport;
    public  static ExtentTest extentTest=null;
    static {
        String addDate=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        extentReports=new ExtentReports();
        htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\reports\\Kayak_Reports"+addDate+".html");
        extentReports.attachReporter(htmlReport);

    }
    public void createReport(String  sTestName){

        extentTest= extentReports.createTest(sTestName);
    }
    public void closeReport(){

        extentReports.flush();
    }
    public WebDriver launchApp(String sBrowser){
        oWebDriManager.launchApp(sBrowser);
        return oWebDriManager.getWebDriver();
    }
    public  void goToWebPage(String webURL){
        driverWeb=oWebDriManager.getWebDriver();
        System.out.println("-------"+driverWeb);
        driverWeb.get(webURL);
        log.info("Salesforce  is launched");
    }
    public   void closeBrowser(){
        driverWeb.close();
        extentTest.info("Browser is closed");
        log.info("Salesforce  is closed");
    }

    public void waitForElementVisible(WebElement webElement, int iSeconds, WebDriver driverWeb){
        try {
            WebDriverWait eWait = new WebDriverWait(driverWeb, iSeconds);
            eWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
        }
    }
    public void sendInput(WebElement webElement,String textToEnter,String elementName) throws IOException {
        webElement.sendKeys(textToEnter);
        String enteredData=webElement.getAttribute("value");
        System.out.println("Entered data : "+enteredData);
        if (!enteredData.equals(textToEnter)) {
            extentTest.fail(elementName + "  is not entered ");
            extentTest.addScreenCaptureFromPath(addScreenshot());
        } else {
            extentTest.info(elementName+"  is entered");
        }
    }
    public  String addScreenshot() throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) driverWeb;
        String addDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destPath = System.getProperty("user.dir") + "\\reports\\Screenshot\\" + addDate + ".PNG";
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(destPath);
        FileUtils.copyFile(sourceFile, destFile);
        extentTest.info("Screenshot is captured ");
        return destPath;
    }
    public  void clickOnElement(WebElement webElement,String elementName){
        webElement.click();
        extentTest.info(elementName+"  is clicked ");
    }
    public void quitApp(){
        oWebDriManager.quitApp();
    }
}
