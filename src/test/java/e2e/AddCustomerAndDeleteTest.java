package e2e;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomersPage;
import pages.HomePage;

public class AddCustomerAndDeleteTest extends TestBase {
    Faker faker = new Faker();
    HomePage homePage;
    AddCustomerPage addCustomerPage;
    CustomersPage customersPage;

    @Test
    public void addCustomerAndDeleteTest() {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String postCode = faker.address().zipCode();

        homePage = new HomePage(app.driver);
        homePage.clickOnBankManagerLoginButton();

        addCustomerPage = new AddCustomerPage(app.driver);
        addCustomerPage.openAddCustomerTab();

        addCustomerPage.fillFirstNameField(firstName);
        addCustomerPage.fillLastNameField(lastName);
        addCustomerPage.fillPostCodeField(postCode);
        addCustomerPage.clickOnAddCustomerButton();

        String expectedSuccessfullyAlertText = "Customer added successfully with customer id";
        String err = "Actual alert text is not contains expected alert text";
        Assert.assertTrue(addCustomerPage.getAlertText().contains(expectedSuccessfullyAlertText), err);
        addCustomerPage.applyAlert();

        customersPage = new CustomersPage(app.driver);
        customersPage.openCustomersTab();
        customersPage.filterCustomer(firstName);

        Assert.assertEquals(customersPage.makeCellLocator(firstName).getText(), firstName, "First name is not equals");
        Assert.assertEquals(customersPage.makeCellLocator(lastName).getText(), lastName, "Last name is not equals");
        Assert.assertEquals(customersPage.makeCellLocator(postCode).getText(), postCode, "Post code is not equals");
        Assert.assertEquals(customersPage.countRows(), 1, "Count rows is not equals should be 1 row");
    }
}
