package com.epam.framework.pages;

import com.epam.framework.business_objects.Letter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='pm_latest']/header")
    private WebElement welcomeText;

    @FindBy(css = ".compose.pm_button.sidebar-btn-compose")
    private WebElement composeButton;

    @FindBy(css = "#autocomplete")
    private WebElement recipient;

    @FindBy(xpath = "//*[@id='uid1']/div[2]/div[5]/input")
    private WebElement subject;

    @FindBy(xpath = "//iframe[@class = 'squireIframe']")
    private WebElement frame;

    @FindBy(xpath = "//*[@class='protonmail_signature_block']/preceding-sibling::div[2]")
    private WebElement textBox;

    @FindBy(xpath = "//*[@aria-label='Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@aria-label='Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//span[text() = 'Черновики']")
    private WebElement drafts;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> draftsList;

    @FindBy(xpath = "//*[@class = 'senders-name']")
    private WebElement sendersName;

    @FindBy(xpath = "//*[@class = 'subject-text ellipsis']")
    private WebElement subjectText;

    @FindBy(xpath = "//*[text()='Отправить']")
    private WebElement send;

    @FindBy(xpath = "//span[@ng-bind-html = '$message']")
    private WebElement messagePopUp;



    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public String welcomeText(){
        waitForElementToBeClickable(composeButton);
        return welcomeText.getText();
    }

    public void createNewMail(Letter letter) throws InterruptedException {
        composeButton.click();
        waitForElementToBeClickable(recipient);
        recipient.sendKeys(letter.getRecipient());
        subject.sendKeys(letter.getSubject());
        getDriver().switchTo().frame(frame);
        textBox.click();

        make  = new Actions(getDriver());
        Action kbEvents = make.sendKeys(letter.getMessage()).build();
        kbEvents.perform();

        getDriver().switchTo().defaultContent();

        saveButton.click();
        waitForVisibilityOfAllElementsLocatedBy(messagePopUp);
        closeButton.click();

    }


    public void checkDraftAndSend(Mail mail) throws InterruptedException {
        waitForElementToBeClickable(drafts);
        drafts.click();
        waitForListElements(draftsList);



        List<WebElement> list = (List<WebElement>) draftsList;


        for (WebElement webElement : list) {
            if (sendersName.getText().equals(mail.getEmail()) && subjectText.getText().equals(mail.getSubject())){
                webElement.click();

                WebElement iFrame = frame;
                getDriver().switchTo().frame(iFrame);

                if (textBox.getText().equals(mail.getTextContent())){
                    getDriver().switchTo().defaultContent();

                    send.click();
                    waitForVisibilityOfAllElementsLocatedBy(messagePopUp);

                }
                break;
            }
        }

    }
}
