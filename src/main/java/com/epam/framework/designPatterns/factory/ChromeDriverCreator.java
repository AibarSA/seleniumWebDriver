package com.epam.framework.designPatterns.factory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class ChromeDriverCreator extends WebDriverCreator{
    static Logger logger = LogManager.getLogger(ChromeDriverCreator.class);
    public WebDriver factoryMethod() {
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/main/resources/driverBinaries/chromedriver.exe")).build();

        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = new ChromeDriver(service);
        logger.info("ChromeDriver created successfully");

        return driver;
    }
}
