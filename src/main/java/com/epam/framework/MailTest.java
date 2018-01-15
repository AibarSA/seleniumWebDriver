package com.epam.framework;


import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import com.epam.framework.dataProvider.DataProviderClass;
import com.epam.framework.exceptions.DraftNotFoundExeption;
import com.epam.framework.pages.HomePage;
import com.epam.framework.pages.InboxPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MailTest extends BaseTest {

    static Logger logger = LogManager.getLogger(MailTest.class);
    InboxPage inboxPage;

    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser() {
        driver = initializeDriver();
        driver.get(properties.getProperty("url"));
        logger.debug("Navigated to: {}", properties.getProperty("url"));
    }

    @Test(groups = {"Smoke test"}, dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void logIn(User user) {
        inboxPage = PageFactory.initElements(driver, HomePage.class).clickLoginButton().login(user);
    }

    @Test(groups = {"Smoke test"}, dependsOnMethods = {"logIn"}, dataProvider = "dataForLetter", dataProviderClass = DataProviderClass.class)
    private void createNewMail(Letter letter) {
        inboxPage.createNewMail(letter);
    }

    @Test(groups = {"Smoke test"}, dataProvider = "dataForLetter", dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Letter letter) throws DraftNotFoundExeption {
        inboxPage.checkDraftAndSend(letter);
    }

    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser() {
        driver.quit();
    }
}
