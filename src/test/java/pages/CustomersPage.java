package pages;

import enums.SortDirections;
import enums.SortValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomersPage extends BankManagerLoginPage {
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@ng-model='searchCustomer']")
    WebElement searchCustomerField;

    public Integer countRows() {
        return driver.findElements(By.xpath("//*[@class='table table-bordered table-striped']//*[@class='ng-scope']")).size();
    }

    public WebElement makeCellLocator(String name) {
        return driver.findElement(By.xpath("//*[@class='table table-bordered table-striped']//*[@class='ng-scope']//*[contains(text(), '" + name + "')]"));
    }

    public WebElement makeRowLocator(String name) {
        return makeCellLocator(name).findElement(By.xpath("/ancestor::*[@class='ng-scope']"));
    }

    public void clickOnSortLink(SortValues sortValue) {
        driver.findElement(By.xpath("//*[contains(@ng-click, \"sortType = '" + sortValue + "'\")]")).click();
    }

    public void checkSortDirection(SortDirections direction) {
        driver.findElement(By.xpath("//*[@class='fa fa-caret-" + direction + "']")).isDisplayed();
    }

    public void deleteTableRow(String name) {
        makeRowLocator(name).findElement(By.xpath("//*[@ng-click='deleteCust(cust)']")).click();
    }


    public void filterCustomer(String customer) {
        searchCustomerField.sendKeys(customer);
    }
}
