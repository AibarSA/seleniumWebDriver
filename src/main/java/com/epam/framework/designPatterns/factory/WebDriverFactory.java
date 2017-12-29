package com.epam.framework.designPatterns.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    public static WebDriver createDriverByType(String type){
        WebDriver driver = null;

        if (type.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/driverBinaries/chromedriver.exe");
            driver = new ChromeDriver();
        }else if (type.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/driverBinaries/geckodriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver();

        }
        return driver;
    }
}
