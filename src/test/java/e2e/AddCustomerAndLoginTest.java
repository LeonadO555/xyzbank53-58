package e2e;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.AddCustomerPage;
import pages.CustomerLoginPage;
import pages.HomePage;

public class AddCustomerAndLoginTest extends TestBase {
    Faker faker = new Faker();
    HomePage homePage;
    AddCustomerPage addCustomerPage;
    CustomerLoginPage customerLoginPage;
    AccountPage accountPage;


    @Test
    public void addCustomerAndLogin() {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String postCode = faker.address().zipCode();
        String expectedFirstAndLastName = firstName + " " + lastName;

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

        homePage.clickOnHomeButton();
        homePage.clickOnCustomerLoginButton();

        customerLoginPage = new CustomerLoginPage(app.driver);
        customerLoginPage.selectCustomerName(expectedFirstAndLastName);
        customerLoginPage.clickOnLoginButton();

        accountPage = new AccountPage(app.driver);
        err = "Actual first name and last name is not equal expected";
        Assert.assertEquals(accountPage.getCustomerFirstAndLastName(), expectedFirstAndLastName, err);
    }

    @Test
    public void addCustomerWithInValidData() {
        String firstName = " ";
        String lastName = " ";
        String postCode = " ";

        homePage = new HomePage(app.driver);
        homePage.clickOnBankManagerLoginButton();

        addCustomerPage = new AddCustomerPage(app.driver);
        addCustomerPage.openAddCustomerTab();

        addCustomerPage.fillFirstNameField(firstName);
        addCustomerPage.fillLastNameField(lastName);
        addCustomerPage.fillPostCodeField(postCode);
        addCustomerPage.clickOnAddCustomerButton();

        String expectedUnSuccessfullyAlertText = "Please check the details. Customer may be duplicate.";
        String err = "Actual alert text is not contains expected alert text";
        Assert.assertTrue(addCustomerPage.getAlertText().contains(expectedUnSuccessfullyAlertText), err);
        addCustomerPage.applyAlert();
    }
}
