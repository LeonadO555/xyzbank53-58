import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void firstTest() throws InterruptedException {
        WebElement managerLoginButton = driver.findElement(By.xpath("//*[@ng-click='manager()']"));
        managerLoginButton.isDisplayed();
        Thread.sleep(3000);
        managerLoginButton.click();
        Thread.sleep(3000);
        driver.findElement(By.id("notch"));
        driver.findElement(By.tagName("button"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("center"));
        driver.findElement(By.linkText("/user/registration"));
        driver.findElement(By.cssSelector("[ng-click='manager()']"));
        driver.findElement(By.cssSelector("[type='email']"));
        driver.findElement(By.cssSelector("[name='email']"));
        driver.findElement(By.cssSelector("[id='notch']"));
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg"));
        driver.findElement(By.cssSelector("#notch"));
        driver.findElement(By.cssSelector("*"));
        driver.findElement(By.xpath("//*[@class='center']//*[@ng-click='openAccount()']"));
    }

    @AfterMethod
    public void tearDown() {
        //        driver.close(); - закрывает текущую вкладку
        driver.quit(); // - закрывает браузер
    }
}
