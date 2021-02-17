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
    String sFrom_date;

    public POFlight(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[contains(text(),'Flights')]")
    WebElement flight_tab;

    public void openKayakWeb() {
        oSelUtility.goToWebPage("https://www.kayak.com");
    }

    public void openFlightPage() {
        oSelUtility.waitForElementVisible(flight_tab, 5, driver);
        oSelUtility.clickOnElement(flight_tab, "Flight Option");
    }

    @FindBy(xpath = "//button[contains(@class,'Button-No-Standard-Style js-remove-selection')]")
    WebElement origin_button;
    @FindBy(xpath = "//input[contains(@id,'-origin-airport')]")
    WebElement origin_input;

    public void enterOrigin(String sOrigin_name) throws IOException {
        oActions = new Actions(driver);
        sOrigin = sOrigin_name;
        oSelUtility.waitForElementVisible(origin_button, 5, driver);
        //enter origin Airport
        oSelUtility.clickOnElement(origin_button, "Origin Clear ");
        oSelUtility.sendInput(origin_input, sOrigin_name, "Origin Airport ");
        oSelUtility.sendInput(origin_input,"","");
        oActions.pause(1000).doubleClick().build().perform();
    }//div[contains(@id,'-origin-airport-display') and @data-placeholder='From?']

    @FindBy(xpath = "//div[contains(@id,'-origin-airport-display')]")
    WebElement origin_nearby;
    @FindBy(xpath = "//div[contains(@id,'-origin-airport-smarty-content')]")
    WebElement origin_inputNearby;

    public void selectOriginNearByAirports() throws InterruptedException, IOException {
        //Select NearBy Airport Option

        oSelUtility.waitForElementVisible(origin_nearby, 5, driver);
        oSelUtility.clickOnElement(origin_nearby,"Origin nearby");
        oSelUtility.waitForElementVisible(origin_inputNearby,5,driver);
        oSelUtility.clickOnElement(origin_inputNearby, "Origin NearBy");
        oActions.pause(1000).doubleClick().build().perform();
    }

    @FindBy(xpath = "//div[contains(@id,'-destination-airport-display')]")
    WebElement destination_box;
    @FindBy(xpath = "//input[contains(@id,'-destination-airport')]")
    WebElement destination_input;

    public void enterDestination(String sDestination_name) throws IOException {
        oSelUtility.waitForElementVisible(destination_box, 5, driver);
        sDestination = sDestination_name;
        //enter origin Airport
        oSelUtility.clickOnElement(destination_box, "Destination Clear ");
        oSelUtility.sendInput(destination_input, sDestination_name, "Destination Airport ");
        oActions.pause(1000).doubleClick().build().perform();
    }

    @FindBy(xpath = "//li[contains(@id,'-destination-airport-nearby')]")
    WebElement destination_nearby;

    public void selectDestinationNearByAirports() {
        //Select NearBy Airport Option inDestination

        oSelUtility.clickOnElement(destination_box, "Origin NearBy");
        oSelUtility.clickOnElement(destination_nearby, "Origin NearBy Option");
        oActions.pause(1000).doubleClick().build().perform();
    }

    @FindBy(xpath = "//div[contains(@id,'dateRangeInput-display-start')]//div[@class='_iaf _iEU _iam _iai']")
    WebElement fromDateBox;
    @FindBy(xpath = "//div[contains(@id,'-jam-cal-defaultView')]")
    WebElement fromDatesCalender;
    @FindBy(xpath = "//div[@aria-label='March 22']//div")
    WebElement from_date;

    //Method to select the from date
    public void selectFromDate() {
        oSelUtility.waitForElementVisible(fromDateBox, 5, driver);
        oSelUtility.clickOnElement(fromDateBox, "From Date Option");
        oSelUtility.waitForElementVisible(fromDatesCalender, 5, driver);
        oSelUtility.clickOnElement(fromDatesCalender, "From Date Calender");
        oSelUtility.clickOnElement(from_date, "From Date ");
        oActions.doubleClick().build().perform();
    }

    @FindBy(xpath = "//div[contains(@id,'-dateRangeInput-display-end')]//div")
    WebElement return_dateBox;
    @FindBy(xpath = "//div[contains(@id,'jam-cal-displayWrapper')]")
    WebElement return_dateCalendar;
    @FindBy(xpath = "//div[contains(@id,'-jam-cal-nextMonth')]")
    WebElement return_dateNextMonth;
    @FindBy(xpath = "//div[@aria-label='April 21']//div")
    WebElement return_date;

    public void selectReturnDate() {
        oSelUtility.waitForElementVisible(return_dateBox, 5, driver);
        oSelUtility.clickOnElement(return_dateBox, "Return Date Box");
        oSelUtility.waitForElementVisible(return_dateCalendar, 5, driver);
        oSelUtility.clickOnElement(return_dateCalendar, "Return Date Calendar");
        boolean dateIsSelected = true;
        while (dateIsSelected) {

            if (return_date.isDisplayed()) {
                System.out.println("control is in if stmt");
                oSelUtility.waitForElementVisible(return_date, 5, driver);
                oSelUtility.clickOnElement(return_date, "Return Date");
                oActions.doubleClick().build().perform();
                dateIsSelected = false;
            } else {
                oSelUtility.clickOnElement(return_dateNextMonth, "Next Month  icon ");
            }
        }
    }

    @FindBy(xpath = "//button[contains(@id,'-submit')]")
    WebElement search_button;

    public void clickSearch() {
        oSelUtility.waitForElementVisible(search_button, 5, driver);
        oSelUtility.clickOnElement(search_button, "Search Button");

    }

    @FindBy(xpath = "//div[contains(@id,'-info-leg-0-origin-airport')]//span")
    WebElement search_result;
    @FindBy(xpath = "//div[contains(@id,'info')]//ol")
    List<WebElement> search_results;
    @FindBy(xpath = "//div[contains(@id,'-info-leg-0-origin-airport')]//span[@xpath='1']")
    WebElement result_origin;

    public void getSearchResult()  {
        oSelUtility.waitForElementVisible(search_result, 30, driver);
        for (WebElement element : search_results) {
            if(element.getText().contains(sOrigin.substring(sOrigin.length()-3,sOrigin.length()-1))&&element.getText().contains(sDestination.substring(sDestination.length()-3,sDestination.length()-1))){
                System.out.println(" Result is  verified for available services from " + sOrigin + "to " + sDestination);
          break;
            }else
                System.out.println(" Result is not verified for available services from " + sOrigin + "to " + sDestination);
             }
            oSelUtility.closeReport();
            oSelUtility.closeBrowser();
        }

    }

