package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {


    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {//the ITestContext allows this method to access the search-modle.xml file where we can extract the test name

        //BROWSER => chrome/firefox
        //HUB_HOST => localhost / 10.0.1.3 / hostname

        //Default values
        String host ;
        DesiredCapabilities dc;

        //If default values are not to be used we initialize to what the user wants
        if(System.getProperty("BROWSER") != null &&
            System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }
        else {
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null)
        {
            host = System.getProperty("HUB_HOST");
        }
        else
        {
            host = "localhost";
        }

        String testName = ctx.getCurrentXmlTest().getName(); //this line extracts the name of each test name its executing when running search-module.xml file
        String completeURL = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeURL), dc);
    }

    @AfterTest
    public void quitBrowser(){
        driver.quit();
    }
    
}
