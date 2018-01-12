package com.epam.framework.pages;

import com.epam.framework.exeptions.DraftNotFoundExeption;
import com.epam.framework.business_objects.Letter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends AbstractPage {

    static Logger logger = LogManager.getLogger(InboxPage.class);

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

    public void createNewMail(Letter letter) {
        composeButton.click();
        logger.debug("composeButton has clicked");
        waitForElementToBeClickable(recipient);
        recipient.sendKeys(letter.getRecipient());
        logger.debug("Entered '{}' to recipient field", letter.getRecipient());
        subject.sendKeys(letter.getSubject());
        logger.debug("Entered '{}' to subject field", letter.getSubject());
        getDriver().switchTo().frame(frame);
        textBox.click();

        make  = new Actions(getDriver());
        Action kbEvents = make.sendKeys(letter.getTextContent()).build();
        kbEvents.perform();
        logger.debug("Entered '{}' to text box", letter.getTextContent());

        getDriver().switchTo().defaultContent();

        saveButton.click();
        logger.debug("saveButton has clicked");
        waitForVisibilityOfAllElementsLocatedBy(messagePopUp);
        logger.debug("Message saved to drafts");
        closeButton.click();

    }


    public void checkDraftAndSend(Letter letter) throws DraftNotFoundExeption {
        waitForElementToBeClickable(drafts);
        drafts.click();
        logger.debug("drafts has clicked");
        waitForListElements(draftsList);
        logger.debug("draftsList opened");



        List<WebElement> list = (List<WebElement>) draftsList;


        for (WebElement webElement : list) {
            logger.info("searching draft with similar recipient and subject started");
            if (sendersName.getText().equals(letter.getRecipient()) && subjectText.getText().equals(letter.getSubject())){
                webElement.click();
                logger.debug("draft with similar recipient and subject was founded and clicked");


                WebElement iFrame = frame;
                getDriver().switchTo().frame(iFrame);

                if (textBox.getText().equals(letter.getTextContent())){
                    getDriver().switchTo().defaultContent();
                    logger.info("Text in the text box:  '{}' was checked for similarity", letter.getTextContent());

                    send.click();
                    logger.debug("send button has clicked");
                    waitForVisibilityOfAllElementsLocatedBy(messagePopUp);
                    logger.debug("Message sent successfully");

                }
                break;
            }else {
                logger.error("Draft with '{}' recipient and '{}' subject was not found", sendersName.getText(), subjectText.getText());
                throw new DraftNotFoundExeption();
            }
        }

    }
}
