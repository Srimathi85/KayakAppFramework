package com.kayakWebApp.RunnerFile;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/FeaturesFiles/FlightFeatures.feature",glue = {"com.kayakWebApp.StepDefinitionFiles"},plugin = {"pretty","html:reports/sfdc.html"})
public class RunnerFileKayak {

}
