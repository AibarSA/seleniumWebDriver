package com.epam.framework;


import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import com.epam.framework.designPatterns.factory.WebDriverFactory;
import com.epam.framework.designPatterns.singleton.ChromeWebDriver;
import com.epam.framework.exeptions.DraftNotFoundExeption;
import com.epam.framework.pages.HomePage;
import com.epam.framework.pages.InboxPage;
import com.epam.framework.dataProvider.DataProviderClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class MailTest {

    static Logger logger = LogManager.getLogger(MailTest.class);

    WebDriver driver;
    InboxPage inboxPage;
    HomePage homePage;
    private static final String START_URL = "https://protonmail.com/";

    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){
        logger.info("proton mail tests were started");
        driver = ChromeWebDriver.getInstance();                        // <== singleton
        //driver = WebDriverFactory.createDriverByType("chrome");    // <== factory
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(START_URL);
        logger.debug("Navigated to: {}", START_URL);
        driver.manage().window().maximize();
    }

    @Test(groups = {"Smoke test"}, dataProvider = "loginData" , dataProviderClass = DataProviderClass.class)
    public void logIn(User user) {
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        homePage.clickLoginButton().login(user);
        Assert.assertEquals("Добро пожаловат", inboxPage.welcomeText());
    }

    @Test(groups = {"Smoke test"}, dependsOnMethods ={"logIn"}, dataProvider = "dataForLetter" , dataProviderClass = DataProviderClass.class )
    private void createNewMail(Letter letter) {
        inboxPage.createNewMail(letter);
    }

    @Test(groups = {"Smoke test"}, dataProvider = "dataForLetter" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Letter letter) {
        try {
            inboxPage.checkDraftAndSend(letter);
        } catch (DraftNotFoundExeption draftNotFoundExeption) {
            draftNotFoundExeption.printStackTrace();
        }
    }

    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser(){
        driver.quit();
        logger.info("proton mail tests were finished");
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
