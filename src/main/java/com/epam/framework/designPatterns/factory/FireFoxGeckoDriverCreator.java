package com.epam.framework.designPatterns.factory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FireFoxGeckoDriverCreator extends WebDriverCreator {
    static Logger logger = LogManager.getLogger(FireFoxGeckoDriverCreator.class);
    public WebDriver factoryMethod() {
        GeckoDriverService service = new GeckoDriverService.Builder().usingDriverExecutable(new File("src/main/resources/driverBinaries/geckodriver.exe")).build();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(service, capabilities);
        logger.info("FirefoxDriver created successfully");
        return driver;
    }
}
