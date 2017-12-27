package com.epam.framework.pages;

import com.epam.framework.business_objects.User;
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

    public InboxPage login(User user){
        waitForElementToBeClickable(userName);
        userName.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        submit.click();
       return new InboxPage(getDriver());
    }



}
