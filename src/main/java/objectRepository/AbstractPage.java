package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public AbstractPage(WebDriver driver) {

        this.driver = driver;
    }

    public void waitForElementToBeClickable(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForVisibilityOfAllElementsLocatedBy(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
