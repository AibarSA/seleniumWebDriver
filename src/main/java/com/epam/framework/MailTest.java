package com.epam.framework;


import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import com.epam.framework.designPatterns.factory.WebDriverFactory;
import com.epam.framework.pages.HomePage;
import com.epam.framework.pages.InboxPage;
import com.epam.framework.dataProvider.DataProviderClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class MailTest {

    WebDriver driver;
    InboxPage inboxPage;
    HomePage homePage;

    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){

        //driver = ChromeWebDriver.getInstance();                        // <== singleton
        driver = WebDriverFactory.createDriverByType("chrome");    // <== factory
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://protonmail.com/");
        driver.manage().window().maximize();
    }

    @Test(groups = {"Smoke test"}, dataProvider = "loginData" , dataProviderClass = DataProviderClass.class)
    public void logIn(User user) throws InterruptedException {
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        homePage.clickLoginButton().login(user);
        Assert.assertEquals("Добро пожаловать", inboxPage.welcomeText());
    }

    @Test(groups = {"Smoke test"}, dependsOnMethods ={"logIn"}, dataProvider = "dataForLetter" , dataProviderClass = DataProviderClass.class )
    private void createNewMail(Letter letter) throws InterruptedException {
        inboxPage.createNewMail(letter);
    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(com.epam.framework.pages.Mail mail) throws InterruptedException {
        inboxPage.checkDraftAndSend(mail);
    }

    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser(){
        driver.quit();
    }

    //    private void initDriver()   {
//        try {
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            driver = new RemoteWebDriver(new URL("http://epkzkarw0218:4444/wd/hub"), capabilities);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://protonmail.com");
//        driver.manage().window().maximize();
//    }

}
