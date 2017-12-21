package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    private static  final By LOGIN_BUTTON = By.xpath("//a[text() = 'LOG IN']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginButton(){
        waitForElementToBeClickable(LOGIN_BUTTON);
        getDriver().findElement(LOGIN_BUTTON).click();
        return  new LoginPage(getDriver());
    }
}
