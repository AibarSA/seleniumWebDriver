package com.epam.framework.cucumber.step;


import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import com.epam.framework.designPatterns.singleton.ChromeWebDriver;
import com.epam.framework.pages.HomePage;
import com.epam.framework.pages.InboxPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class StepDef {

    public static WebDriver driver = ChromeWebDriver.getInstance();
    InboxPage inboxPage;
    HomePage homePage;
    private static final String START_URL = "https://protonmail.com/";

    @Given("^user navigates to protonmail home page$")
    public void user_navigates_to_protonmail_home_page() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(START_URL);
    }

    @When("^click login button and enters user credentials and submits login form$")
    public void click_login_button_and_enters_user_credentials_and_submits_login_form() {
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        homePage.clickLoginButton().login(User.MAIN_USER);
        Assert.assertEquals("Добро пожаловать", inboxPage.welcomeText());

    }

    @When("^user creates new mail with \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and saves it in draft$")
    public void user_creates_new_mail_with_and_saves_it_in_draft(String recipient, String subject, String textContent) {
        inboxPage.createNewMail(new Letter(recipient, subject, textContent));
    }

    @Then("^check presence draft with same \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and send the message$")
    public void check_presence_draft_with_same_and_send_the_message(String recipient, String subject, String textContent) {
        inboxPage.checkDraftAndSend(new Letter(recipient, subject, textContent));

    }
}
