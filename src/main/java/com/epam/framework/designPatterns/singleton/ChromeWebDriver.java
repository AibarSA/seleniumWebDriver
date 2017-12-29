package com.epam.framework.designPatterns.singleton;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {

    private static WebDriver driver;

    private ChromeWebDriver(){

    }

    public static WebDriver getInstance(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driverBinaries/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

}