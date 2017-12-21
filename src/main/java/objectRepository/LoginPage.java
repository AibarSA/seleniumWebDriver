package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    private static  final By USER_NAME = By.xpath("//*[@id='username']");
    private static  final By PASSWORD = By.xpath("//*[@id='password']");
    private static  final By SUBMIT = By.xpath("//*[@id='login_btn']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage login(String email, String password){
        waitForElementToBeClickable(USER_NAME);
        getDriver().findElement(USER_NAME).sendKeys(email);
        getDriver().findElement(PASSWORD).sendKeys(password);
        getDriver().findElement(SUBMIT).click();
       return new InboxPage(getDriver());
    }



}
