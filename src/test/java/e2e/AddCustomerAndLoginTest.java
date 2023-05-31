package e2e;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PageBase;

public class AddCustomerAndLoginTest extends TestBase {
    Faker faker = new Faker();
    PageBase pageBase;

    @Test
    public void addCustomerAndLogin() {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String postCode = faker.address().zipCode();
        String expectedFirstAndLastName = firstName + " " + lastName;
        pageBase = new PageBase(app.driver);
        // Click on Bank Manager Login Button
        pageBase.click(By.xpath("//*[@ng-click='manager()']"));
        // Click on Add Customer Button
        pageBase.click(By.cssSelector("[ng-class='btnClass1']"));
        // Fill Add customer form
        app.driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
        app.driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);
        app.driver.findElement(By.xpath("//input[@placeholder='Post Code']")).sendKeys(postCode);
        // Click on Submit Button
        pageBase.click(By.xpath("//button[@type='submit']"));
        // Verify Customer is added successfully (take alert text)
        String actualSuccessfullyAlertText = app.driver.switchTo().alert().getText();
        String expectedSuccessfullyAlertText = "Customer added successfully with customer id";
        String err = "Actual alert text is not contains expected alert text";
        Assert.assertTrue(actualSuccessfullyAlertText.contains(expectedSuccessfullyAlertText), err);
        app.driver.switchTo().alert().accept();
        // Click on Home button
        pageBase.click(By.xpath("//*[@ng-click='home()']"));
        // Click on Customer Login Button
        pageBase.click(By.xpath("//button[@ng-click='customer()']"));
        // Choose customer from the dropdown
        WebElement customerDropdown = app.driver.findElement(By.xpath("//*[@ng-model='custId']"));
        Select select = new Select(customerDropdown);
        select.selectByVisibleText(expectedFirstAndLastName);
        // Click on Login Button
        pageBase.click(By.xpath("//button[@type='submit']"));
        // Verify correct customer is logged in (take text from the page)
        String firstNameAndLastName = app.driver.findElement(By.xpath("//*[@class='fontBig ng-binding']")).getText();
        err = "Actual first name and last name is not equal expected";
        Assert.assertEquals(firstNameAndLastName, expectedFirstAndLastName, err);
    }
}
