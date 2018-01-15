package com.epam.framework.pages;

import com.epam.framework.designPatterns.decorator.WebelementDecorator;
import com.epam.framework.utility.Highlighter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    static Logger logger = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//a[text() = 'LOG IN']")
    private WebElement loginButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginButton(){
        waitForElementToBeClickable(loginButton);
        Highlighter.highlightElement(getDriver(),loginButton);
        new WebelementDecorator(loginButton).click();
        logger.debug("loginButton has clicked");
        return  new LoginPage(getDriver());
    }
}
