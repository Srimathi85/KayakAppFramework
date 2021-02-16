package com.kayakWebApp.POKayakFlightFiles;

import com.kayakWebApp.UtilityFiles.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class POFlight extends TestBase {
    WebDriver driver;
    Actions oActions;
    String sOrigin;
    String sDestination;
    //Actions oActions=new Actions(driver);
    public POFlight(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);
        System.out.println("driver--------"+driver);
    }
 @FindBy(xpath ="//div[contains(text(),'Flights')]")
    WebElement flight_tab;
    @FindBy(xpath ="//button[contains(@class,'Button-No-Standard-Style js-remove-selection')]")
    WebElement origin_button;
    @FindBy(xpath = "//input[contains(@id,'-origin-airport')]")
    WebElement origin_input;
    //@FindBy(xpath = "//div[contains(@id,'js-selection-display _')]")
    @FindBy(xpath = "//div[contains(@id,'-origin-airport-display')]")
            WebElement origin_nearby;
    @FindBy(xpath = "//div[contains(@id,'-origin-airport-smarty-content')]")
            WebElement origin_inputNearby;
    @FindBy(xpath = "//div[contains(@id,'-destination-airport-display')]")
            WebElement destination_box;
    @FindBy(xpath = "//input[contains(@id,'-destination-airport')]")
            WebElement destination_input;

    @FindBy(xpath = "//li[contains(@id,'-destination-airport-nearby')]")
            WebElement destination_nearby;
    @FindBy(xpath = "//div[contains(@id,'dateRangeInput-display-start')]//div[@class='_iaf _iEU _iam _iai']")
    WebElement  fromDateBox;
    @FindBy(xpath = "//div[contains(@id,'-jam-cal-defaultView')]")
    WebElement fromDatesCalender;

    @FindBy(xpath = "//div[@aria-label='March 22']//div")
            WebElement from_date;
    @FindBy(xpath = "//button[contains(@id,'-submit')]")
            WebElement search_button;
    @FindBy(xpath = "//div[contains(@id,'-info-leg-0-origin-airport')]//span")
    WebElement search_result;
    @FindBy(xpath = "//div[contains(@id,'info')]//ol")
            List<WebElement> search_results;

    public void openKayakWeb(){
        oSelUtility.goToWebPage("https://www.kayak.com");
    }
    public void openFlightPage(){
        oSelUtility.waitForElementVisible(flight_tab, 5,driver);
        oSelUtility.clickOnElement(flight_tab,"Flight Option");
    }

    public void enterOrigin(String sOrigin_name) throws IOException {
        oActions=new Actions(driver);
        sOrigin=sOrigin_name;
        oSelUtility.waitForElementVisible(origin_button, 5, driver);
        //enter origin Airport
        oSelUtility.clickOnElement(origin_button, "Origin Clear ");
        oSelUtility.sendInput(origin_input, sOrigin_name, "Origin Airport ");
        oActions.pause(1000).doubleClick().build().perform();
    }
    public void selectOriginNearByAirports() throws InterruptedException {
        //Select NearBy Airport Option
        oSelUtility.waitForElementVisible(origin_nearby, 5, driver);
       // driver.findElement(By.xpath("//div[contains(@id,'-origin-airport-display')]")).click();
       oSelUtility.clickOnElement(origin_nearby,"Origin NearBy");
        oSelUtility.waitForElementVisible(origin_inputNearby, 5, driver);
        oSelUtility.clickOnElement(origin_inputNearby,"Origin NearBy Option");
        oActions.pause(1000).doubleClick().build().perform();
    }
    public void enterDestination(String sDestination_name) throws IOException {
        oSelUtility.waitForElementVisible(destination_box, 5, driver);
        sDestination=sDestination_name;
        //enter origin Airport
        oSelUtility.clickOnElement(destination_box, "Destination Clear ");
        oSelUtility.sendInput(destination_input, sDestination_name, "Destination Airport ");
        oActions.pause(1000).doubleClick().build().perform();
    }

    public void selectDestinationNearByAirports(){
        //Select NearBy Airport Option

        oSelUtility.clickOnElement(destination_box,"Origin NearBy");
        oSelUtility.clickOnElement(destination_nearby,"Origin NearBy Option");
        oActions.pause(1000).doubleClick().build().perform();
    }
    //Method to select the from date
    public void selectFromDate(){
        oSelUtility.waitForElementVisible(fromDateBox,5,driver);
        oSelUtility.clickOnElement(fromDateBox,"From Date Option");
        oSelUtility.waitForElementVisible(fromDatesCalender,5,driver);
        oSelUtility.clickOnElement(fromDatesCalender,"From Date Calender");
        oSelUtility.clickOnElement(from_date,"From Date ");
        oActions.doubleClick().build().perform();
    }
    public void clickSearch(){
        oSelUtility.waitForElementVisible(search_button,5,driver);
        oSelUtility.clickOnElement(search_button,"Search Button");

    }
    public void getSearchResult() throws InterruptedException {
        oSelUtility.waitForElementVisible(search_result,5,driver);
      //  for (WebElement element:search_results) {
            if(driver.getPageSource().contains(sOrigin) && driver.getPageSource().contains(sDestination)){
           // if(element.getText().contains(sOrigin)  &&element.getText().contains(sDestination)){
                System.out.println("Search Result is verified for "+sDestination+"    "+sOrigin);
            }else System.out.println("Search Result is  not verified");
       // }
        Thread.sleep(5000);
oSelUtility.closeReport();
oSelUtility.closeBrowser();
    }

}
