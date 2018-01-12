package com.epam.framework.designPatterns.singleton;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {
    static Logger logger = LogManager.getLogger(ChromeWebDriver.class);

    private static WebDriver driver;

    private ChromeWebDriver(){

    }

    public static WebDriver getInstance(){
        if (driver==null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/driverBinaries/chromedriver.exe");
            driver = new ChromeDriver();
            logger.info("ChromeDriver created successfully");
        }


        return driver;
    }

}
