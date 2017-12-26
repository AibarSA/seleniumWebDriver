package testCases;


import objectRepository.HomePage;
import objectRepository.InboxPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MailTest {

    WebDriver driver;
    InboxPage inboxPage;
    HomePage homePage;

    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://protonmail.com/");
        driver.manage().window().maximize();
    }
    
    @Test(groups = {"Smoke test"}, dataProvider="loginData", dataProviderClass = DataProviderClass.class)
    public void logIn(String email, String password) throws InterruptedException {
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        homePage.clickLoginButton().login(email,password);
        Assert.assertEquals("Добро пожаловать", inboxPage.welcomeText());
    }

    @Test(groups = {"Smoke test"}, dataProvider="testDataForMail", dataProviderClass = DataProviderClass.class, dependsOnMethods ={"logIn"} )
    private void createNewMail(objectRepository.Mail mail) throws InterruptedException {
        inboxPage.createNewMail(mail);
    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(objectRepository.Mail mail) throws InterruptedException {
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
