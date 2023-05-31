package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageBase {
    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).isDisplayed();
        driver.findElement(locator).click();
    }
}
