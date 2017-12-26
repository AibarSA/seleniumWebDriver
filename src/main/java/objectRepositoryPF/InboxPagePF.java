package objectRepositoryPF;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPagePF extends AbstractPagePF {

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

    @FindBy(xpath = "//span[text()='Все письма']")
    private WebElement incoming;


    @FindBy(xpath = "//*[@class='conversation-wrapper']")
    private WebElement slider;

    @FindBy(xpath = "//*[@data-pt-dropzone-item='archive']")
    private WebElement archive;

    @FindBy(xpath = "//span[text() = 'Черновики']")
    private WebElement drafts;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> draftsList;



    @FindBy(xpath = "//ul[@class='menu sidebarApp-menu']/li")
    private List<WebElement> menuList;

    @FindBy(xpath = "//*[@class = 'senders-name']")
    private WebElement sendersName;

    @FindBy(xpath = "//*[@class = 'subject-text ellipsis']")
    private WebElement subjectText;

    @FindBy(xpath = "//*[text()='Отправить']")
    private WebElement send;

    @FindBy(xpath = "//span[@ng-bind-html = '$message']")
    private WebElement messagePopUp;

    @FindBy(xpath = "//a[text()='Droppable']")
    private WebElement droppableMenu;

    @FindBy(xpath = "//*[@class='demo-frame']")
    private WebElement droppFrame;

    @FindBy(xpath = "//*[@id='draggable']")
    private WebElement draggable;

    @FindBy(xpath = "//*[@id='droppable']")
    private WebElement droppable;













    public InboxPagePF(WebDriver driver) {
        super(driver);
    }

    public String welcomeText(){
        waitForElementToBeClickable(composeButton);
        return welcomeText.getText();
    }

    public void createNewMail(Mail mail) throws InterruptedException {
        composeButton.click();
        waitForElementToBeClickable(recipient);
        recipient.sendKeys(mail.getEmail());
        subject.sendKeys(mail.getSubject());
        getDriver().switchTo().frame(frame);
        textBox.click();

        make  = new Actions(getDriver());
        Action kbEvents = make.sendKeys(mail.getTextContent()).build();
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


    public void dragAndDrop(){
        waitForElementToBeClickable(droppableMenu);
        droppableMenu.click();
        waitForVisibilityOfAllElementsLocatedBy(droppFrame);

        getDriver().switchTo().frame(droppFrame);


        Actions make  = new Actions(getDriver());
        Action kbEvents = make.dragAndDrop(draggable,droppable).build();
        kbEvents.perform();






//        List<WebElement> list = (List<WebElement>) draftsList;
//        List<WebElement> menuL = (List<WebElement>) menuList;
//
//
//        for (WebElement webElement : list) {
//
//            for (WebElement element : menuL) {
//                if (element.getText().equals("Архив")){
//                    Actions make  = new Actions(getDriver());
//                    Action kbEvents = make.dragAndDrop(webElement,element).build();
//                    kbEvents.perform();
//                }
//            }
//
//
//
//        }





    }


}
