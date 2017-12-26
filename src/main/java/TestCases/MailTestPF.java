package TestCases;


import objectRepositoryPF.HomePagePF;
import objectRepositoryPF.InboxPagePF;
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

public class MailTestPF {

    WebDriver driver;
    InboxPagePF inboxPage;
    HomePagePF homePage;

    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://jqueryui.com/");
        driver.manage().window().maximize();
    }
//    private void initDriver()   {
//        try {
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            driver = new RemoteWebDriver(new URL("http://epkzkarw0270:4444/wd/hub"), capabilities);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://jqueryui.com/");
//        driver.manage().window().maximize();
//    }


//
//    @Test(groups = {"Smoke test"}, dataProvider="loginData", dataProviderClass = DataProviderClass.class)
//    public void logIn(String email, String password) throws InterruptedException {
//        homePage = new HomePagePF(driver);
//        inboxPage = new InboxPagePF(driver);
//        homePage.clickLoginButton().login(email,password);
//        Assert.assertEquals("Добро пожаловать", inboxPage.welcomeText());
//    }
//
//    @Test(groups = {"Smoke test"}, dataProvider="testDataForMail", dataProviderClass = DataProviderClass.class, dependsOnMethods ={"logIn"} )
//    private void createNewMail(objectRepositoryPF.Mail mail) throws InterruptedException {
//        inboxPage.createNewMail(mail);
//    }
//
//    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
//    private void checkingDraftPresence(objectRepositoryPF.Mail mail) throws InterruptedException {
//        inboxPage.checkDraftAndSend(mail);
//    }

    @Test(groups = {"Smoke test"})
    public void fromInboxToArchive(){
        inboxPage = new InboxPagePF(driver);
        inboxPage.dragAndDrop();
    }

    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser(){
        driver.quit();
    }

}
