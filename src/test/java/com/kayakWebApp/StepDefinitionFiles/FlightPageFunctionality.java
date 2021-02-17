package com.kayakWebApp.StepDefinitionFiles;

import com.jayway.jsonpath.JsonPath;
import com.kayakWebApp.POKayakFlightFiles.POFlight;
import com.kayakWebApp.UtilityFiles.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

public class FlightPageFunctionality extends TestBase {
POFlight oPOFlight;
    Logger log=Logger.getLogger(getClass().getSimpleName());

    @Given("^User launches kayak page$")
    public void user_launches_kayak_page() throws IOException {

        driverWeb = oSelUtility.launchApp(oComUtility.readPropertiesFile("webPageURL","browser_name"));
        System.out.println("+++++++++++"+sBrowser);
        oPOFlight=new POFlight(driverWeb);
        oSelUtility.createReport("Flight Page");
        oPOFlight.openKayakWeb();

       Assert.assertEquals(driverWeb.getTitle(),oComUtility.readPropertiesFile("webPageURL","kayakPageTitle"));
        log.info("User launches kayak page");

    }

        @Given("Select the Flight option")
    public void select_the_flight_option() {
       oPOFlight.openFlightPage();

        log.info("Select the Flight option");
    }

    @Then("Kayak Flight page is displayed")
    public void kayak_flight_page_is_displayed() throws IOException {
        Assert.assertEquals(driverWeb.getTitle(),oComUtility.readPropertiesFile("webPageURL","kayak_flightPageTitle"));
        log.info("Kayak Flight page is displayed");
    }

    @When("Enter origin Airport {string}")
    public void user_enters_origin(String string) throws IOException, InterruptedException {
        oPOFlight.enterOrigin(string);
        log.info("User enters {string} ");
    }
    @When("Select origin Nearby Airport")
    public void select_origin_nearby_airport() throws InterruptedException, IOException {
        oPOFlight.selectOriginNearByAirports();
        log.info("Origin Nearby airport is selected");
    }
    @When("Enter Destination Airport {string}")
    public void enter_destination_airport(String string) throws IOException {
        oPOFlight.enterDestination(string);
        log.info("User entered the destination Airport{string} ");
    }
    @When("Select Nearby Airports for destination")
    public void select_nearby_airports_for_destination() {
        oPOFlight.selectDestinationNearByAirports();
        log.info(" Nearby Airports for  destination is selected");
    }



    @When("Select  Departure and Return Dates")
    public void select_departure_and_return_dates() {
        oPOFlight.selectFromDate();
        oPOFlight.selectReturnDate();
        log.info("Select  Departure and Return Dates");
    }

    @When("Click Search")
    public void click_search() {
        oPOFlight.clickSearch();
       log.info("Click Search");
    }

    @Then("The flight details for entered the Origin and Destination Details are displayed")
    public void the_flight_details_for_entered_the_origin_and_destination_details_are_displayed() throws InterruptedException {
        oPOFlight.getSearchResult();

        log.info("The flight details for entered the Origin and Destination Details are displayed");
    }

}
