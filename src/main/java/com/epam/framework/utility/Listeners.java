package com.epam.framework.utility;

import com.epam.framework.designPatterns.singleton.ChromeWebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {
    static Logger logger = LogManager.getLogger(Listeners.class);
    public static WebDriver driver = ChromeWebDriver.getInstance();
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.error("'{}' test has failed", iTestResult.getName());
        String methodName = iTestResult.getName().toString().trim();
        takeScreenShot(driver, methodName);

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        logger.info("proton mail tests were started");
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info("proton mail tests were finished");
    }

    public  void takeScreenShot(WebDriver driver, String methodName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE),
                    new File("src/main/resources/protonMailScreenShots/" + methodName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Screenshot for {} taken", methodName);

    }
}
