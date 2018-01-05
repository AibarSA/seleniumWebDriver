package com.epam.framework.cucumber;

import com.epam.framework.designPatterns.singleton.ChromeWebDriver;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;


@CucumberOptions(strict = true,
                 plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
                 tags = "@smokeTest",
                 features = {"src/main/resources/featureFiles/"},
                 glue = {"com.epam.framework.cucumber.step"})


public class ProtonmailTest extends AbstractTestNGCucumberTests{
    public static WebDriver driver = ChromeWebDriver.getInstance();

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }

}
