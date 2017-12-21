package TestCases;

import objectRepository.HomePage;
import objectRepository.InboxPage;
import objectRepository.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    WebDriver driver;
    InboxPage inboxPage;
    HomePage homePage;


    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://protonmail.com/");
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
    private void createNewMail(String email, String subject, String textContent) throws InterruptedException {
        inboxPage.createNewMail(email,subject,textContent);
    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(String email, String subject, String textContent) throws InterruptedException {
        inboxPage.checkDraftAndSend(email, subject, textContent);
    }

    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser(){
        driver.quit();
    }

}
