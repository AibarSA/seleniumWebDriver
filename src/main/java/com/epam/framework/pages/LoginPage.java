package com.epam.framework.pages;

import com.epam.framework.business_objects.User;
import com.epam.framework.utility.Highlighter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    static Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//*[@id='username']")
    private WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login_btn']")
    private WebElement submit;




    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage login(User user){
        waitForElementToBeClickable(userName);
        Highlighter.highlightElement(getDriver(),userName);
        userName.sendKeys(user.getUserName());
        logger.debug("Entered '{}' to user name field", user.getUserName());
        Highlighter.highlightElement(getDriver(),passwordField);
        passwordField.sendKeys(user.getPassword());
        logger.debug("Entered '{}' to password field", user.getPassword());
        Highlighter.highlightElement(getDriver(),submit);
        submit.click();
        logger.debug("submit has clicked");
       return new InboxPage(getDriver());
    }



}
