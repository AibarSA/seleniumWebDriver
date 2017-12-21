package objectRepositoryPF;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePF extends AbstractPagePF {

    @FindBy(xpath = "//a[text() = 'LOG IN']")
    private WebElement loginButton;


    public HomePagePF(WebDriver driver) {
        super(driver);
    }

    public LoginPagePF clickLoginButton(){
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        return  new LoginPagePF(getDriver());
    }
}
