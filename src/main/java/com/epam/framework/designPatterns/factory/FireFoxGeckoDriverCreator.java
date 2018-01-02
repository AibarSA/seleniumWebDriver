package com.epam.framework.designPatterns.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FireFoxGeckoDriverCreator extends WebDriverCreator {
    public WebDriver factoryMethod() {
        GeckoDriverService service = new GeckoDriverService.Builder().usingDriverExecutable(new File("src/main/resources/driverBinaries/geckodriver.exe")).build();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(service, capabilities);
        return driver;
    }
}
