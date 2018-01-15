package com.epam.framework.cucumber.step;


import com.epam.framework.BaseTest;
import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import com.epam.framework.exceptions.DraftNotFoundExeption;
import com.epam.framework.pages.HomePage;
import com.epam.framework.pages.InboxPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class StepDefinition extends BaseTest {


    InboxPage inboxPage;

    @Given("^user navigates to protonmail home page$")
    public void user_navigates_to_protonmail_home_page() {
        driver = initializeDriver();
        driver.get(properties.getProperty("url"));
    }

    @When("^click login button and enters user credentials and submits login form$")
    public void click_login_button_and_enters_user_credentials_and_submits_login_form() {
        inboxPage = PageFactory.initElements(driver, HomePage.class).clickLoginButton().login(User.MAIN_USER);
        Assert.assertEquals("Добро пожаловать", inboxPage.welcomeText());

    }

    @When("^user creates new mail with \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and saves it in draft$")
    public void user_creates_new_mail_with_and_saves_it_in_draft(String recipient, String subject, String textContent) {
        inboxPage.createNewMail(new Letter(recipient, subject, textContent));
    }

    @Then("^check presence draft with same \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and send the message$")
    public void check_presence_draft_with_same_and_send_the_message(String recipient, String subject, String textContent) {
        try {
            inboxPage.checkDraftAndSend(new Letter(recipient, subject, textContent));
        } catch (DraftNotFoundExeption draftNotFoundExeption) {
            draftNotFoundExeption.printStackTrace();
        }

    }
}
