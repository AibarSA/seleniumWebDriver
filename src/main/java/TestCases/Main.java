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
    WebDriverWait webDriverWait;
    InboxPage inboxPage;
    Actions make;
    HomePage homePage;
    LoginPage loginPage;


    @BeforeTest(groups = {"Smoke test"})
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();
    }


    @Test(groups = {"Smoke test"}, dataProvider="loginData", dataProviderClass = DataProviderClass.class)
    public void logIn(String email, String password){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);

        webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.loginButton()));
        homePage.loginButton().click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginPage.email()));

        loginPage.email().sendKeys(email);
        loginPage.password().sendKeys(password);
        loginPage.submit().click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(inboxPage.composeButton()));
        String welcomText = inboxPage.welcomText().getText();

        Assert.assertEquals("Добро пожаловать", welcomText);
    }


    @Test(groups = {"Smoke test"}, dataProvider="testDataForMail", dataProviderClass = DataProviderClass.class, dependsOnMethods ={"logIn"} )
    private void createNewMail(String email, String subject, String textContent) throws InterruptedException {
        inboxPage.composeButton().click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(inboxPage.recipient()));

        inboxPage.recipient().sendKeys(email);
        inboxPage.subject().sendKeys(subject);
        driver.switchTo().frame(inboxPage.frame());
        inboxPage.textBox().click();

        make  = new Actions(driver);
        Action kbEvents = make.sendKeys(textContent).build();
        kbEvents.perform();

        webDriverWait.until(ExpectedConditions.textToBePresentInElement(inboxPage.textBox(),textContent));

        driver.switchTo().defaultContent();

        inboxPage.closeButton().click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@ng-bind-html = '$message']"))));
    }


    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail" , dataProviderClass = DataProviderClass.class, dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(String email, String subject, String textContent) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inboxPage.drafts()));
        inboxPage.drafts().click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inboxPage.draftsList().get(0)));

        List<WebElement> list = inboxPage.draftsList();
        for (WebElement webElement : list) {
            if (inboxPage.sendersName().getText().equals(email) && inboxPage.subjectText().getText().equals(subject)){
                webElement.click();

               // webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(inboxPage.frame()));

                WebElement iFrame = inboxPage.frame();
                driver.switchTo().frame(iFrame);

                if (inboxPage.textBox().getText().equals(textContent)){
                    driver.switchTo().defaultContent();
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(inboxPage.send()));

                    inboxPage.send().click();
                    webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@ng-bind-html = '$message']"))));
                }
                break;
            }
        }
    }
    @AfterTest(groups = {"Smoke test"})
    public void closeBrowser(){
        driver.quit();
    }

}
