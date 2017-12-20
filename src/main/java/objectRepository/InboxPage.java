package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage {
    WebDriver driver;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id='pm_latest']/header")
    WebElement welcomText;

    @FindBy(css = ".compose.pm_button.sidebar-btn-compose")
    WebElement composeButton;

    @FindBy(css = "#autocomplete")
    WebElement recipient;

    @FindBy(xpath = "//*[@id='uid1']/div[2]/div[5]/input")
    WebElement subject;

    @FindBy(xpath = "//iframe[@class = 'squireIframe']")
    WebElement frame;

    @FindBy(xpath = "//*[@class='protonmail_signature_block']/preceding-sibling::div[2]")
    WebElement textBox;

    @FindBy(xpath = "//*[@data-original-title = 'Закрыть']")
    WebElement closeButton;

    @FindBy(xpath = "//span[text() = 'Черновики']")
    WebElement drafts;

    @FindBy(xpath = "//*[@class = 'senders-name']")
    WebElement sendersName;

    @FindBy(xpath = "//*[@class = 'subject-text ellipsis']")
    WebElement subjectText;

    @FindBy(xpath = "//*[text()='Отправить']")
    WebElement send;






    public WebElement welcomText(){
        return welcomText;
    }

    public WebElement composeButton(){
        return composeButton;
    }

    public WebElement recipient(){
        return recipient;
    }


    public WebElement subject(){
        return subject;
    }


    public WebElement frame(){
        return frame;
    }


    public WebElement textBox(){
        return textBox;
    }


    public WebElement closeButton(){
        return closeButton;
    }


    public WebElement drafts(){
        return drafts;
    }



    public List<WebElement> draftsList(){
        return driver.findElements(By.xpath("//*[@ng-repeat = 'conversation in conversations track by conversation.ID']"));
    }

    public WebElement sendersName(){
        return sendersName;
    }

    public WebElement subjectText(){
        return subjectText;
    }

    public WebElement send(){
        return send;
    }




}
