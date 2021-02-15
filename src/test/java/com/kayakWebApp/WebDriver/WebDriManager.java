package com.kayakWebApp.WebDriver;

import com.kayakWebApp.DriverUtility.IDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriManager  implements IDriverManager,IWebDriver{
    public WebDriver driver;
    public void launchApp(String sBrowser) {
        if (sBrowser.toLowerCase().equals("chrome")) {
            System.out.println("Chrome------"+sBrowser);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }else if(sBrowser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else if(sBrowser.equalsIgnoreCase("IE")){
            WebDriverManager.iedriver().setup();
            driver=new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
    }
    public void quitApp() {
        driver.quit();
    }

    public WebDriver getWebDriver() {

        return driver;
    }
}
