package objectRepositoryPF;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPagePF extends AbstractPagePF {
    @FindBy(xpath = "//*[@id='username']")
    private WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login_btn']")
    private WebElement submit;




    public LoginPagePF(WebDriver driver) {
        super(driver);
    }

    public InboxPagePF login(String email, String password){
        waitForElementToBeClickable(userName);
        userName.sendKeys(email);
        passwordField.sendKeys(password);
        submit.click();
       return new InboxPagePF(getDriver());
    }



}
