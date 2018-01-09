package com.epam.framework.pages;

import com.epam.framework.designPatterns.decorator.WebelementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[text() = 'LOG IN']")
    private WebElement loginButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginButton(){
        waitForElementToBeClickable(loginButton);
        new WebelementDecorator(loginButton).click();
        return  new LoginPage(getDriver());
    }
}
