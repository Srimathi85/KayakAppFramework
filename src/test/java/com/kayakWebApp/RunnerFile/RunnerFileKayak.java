package com.kayakWebApp.RunnerFile;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/FeaturesFiles/FlightFeatures.feature",glue = {"com.kayakWebApp.StepDefinitionFiles"},monochrome=true,plugin= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"})
public class RunnerFileKayak {

}
