package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//*[@id='username']")
    private WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login_btn']")
    private WebElement submit;




    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage login(String email, String password){
        waitForElementToBeClickable(userName);
        userName.sendKeys(email);
        passwordField.sendKeys(password);
        submit.click();
       return new InboxPage(getDriver());
    }



}
