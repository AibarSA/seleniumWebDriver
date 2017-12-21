package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class InboxPage extends AbstractPage {
    private static  final By WELCOME_TEXT = By.xpath("//*[@id='pm_latest']/header");
    private static  final By COMPOSE_BUTTON = By.cssSelector(".compose.pm_button.sidebar-btn-compose");
    private static  final By RECIPIENT = By.cssSelector("#autocomplete");
    private static  final By SUBJECT = By.xpath("//*[@id='uid1']/div[2]/div[5]/input");
    private static  final By FRAME = By.xpath("//iframe[@class = 'squireIframe']");
    private static  final By TEXT_BOX = By.xpath("//*[@class='protonmail_signature_block']/preceding-sibling::div[2]");
    private static  final By SAVE_BUTTON = By.xpath("//*[@aria-label='Сохранить']");
    private static  final By DRAFTS = By.xpath("//span[text() = 'Черновики']");
    private static  final By DRAFTS_LIST = By.xpath("//*[@ng-repeat = 'conversation in conversations track by conversation.ID']");
    private static  final By SENDERS_NAME = By.xpath("//*[@class = 'senders-name']");
    private static  final By SUBJECT_TEXT = By.xpath("//*[@class = 'subject-text ellipsis']");
    private static  final By SEND = By.xpath("//*[text()='Отправить']");
    private static  final By MESSAGE_POP_UP = By.xpath("//span[@ng-bind-html = '$message']");

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public String welcomeText(){
        waitForElementToBeClickable(COMPOSE_BUTTON);
        return getDriver().findElement(WELCOME_TEXT).getText();
    }

    public void createNewMail(String email, String subject, String textContent) throws InterruptedException {
        getDriver().findElement(COMPOSE_BUTTON).click();
        waitForElementToBeClickable(RECIPIENT);
        getDriver().findElement(RECIPIENT).sendKeys(email);
        getDriver().findElement(SUBJECT).sendKeys(subject);
        getDriver().switchTo().frame(getDriver().findElement(FRAME));
        getDriver().findElement(TEXT_BOX).click();

        Actions make  = new Actions(getDriver());
        Action kbEvents = make.sendKeys(textContent).build();
        kbEvents.perform();

        getDriver().switchTo().defaultContent();

        getDriver().findElement(SAVE_BUTTON).click();
        waitForVisibilityOfAllElementsLocatedBy(MESSAGE_POP_UP);

    }


    public void checkDraftAndSend(String email, String subject, String textContent) throws InterruptedException {
        waitForElementToBeClickable(DRAFTS);
        getDriver().findElement(DRAFTS).click();
        waitForElementToBeClickable(DRAFTS_LIST);

        List<WebElement> list = getDriver().findElements(DRAFTS_LIST);

        for (WebElement webElement : list) {
            if (getDriver().findElement(SENDERS_NAME).getText().equals(email) && getDriver().findElement(SUBJECT_TEXT).getText().equals(subject)){
                webElement.click();

                WebElement iFrame = getDriver().findElement(FRAME);
                getDriver().switchTo().frame(iFrame);

                if (getDriver().findElement(TEXT_BOX).getText().equals(textContent)){
                    getDriver().switchTo().defaultContent();

                    getDriver().findElement(SEND).click();
                    waitForVisibilityOfAllElementsLocatedBy(MESSAGE_POP_UP);

                }
                break;
            }
        }

    }

}
