package com.epam.framework.designPatterns.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    public static WebDriver createDriverByType(String type){
        WebDriver driver = null;
        WebDriverCreator creator;

        if (type.equalsIgnoreCase("chrome")){
            creator = new ChromeDriverCreator();
            driver =  creator.factoryMethod();
        }else if (type.equalsIgnoreCase("firefox")){
            creator = new FireFoxGeckoDriverCreator();
            driver = creator.factoryMethod();


        }
        return driver;
    }
}
