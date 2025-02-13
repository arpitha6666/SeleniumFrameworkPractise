package testComponents.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        String username="ars123@gmail.com";
        String password="Arsars123";
        String countrySearch = "india";
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.cssSelector(".login-btn")).click();
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement productExpected = products.stream()
                .filter(s->s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
                .findFirst().orElse(null);
        System.out.println("productExpected "+ productExpected.getText());
        driver.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        eWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //below statemnt was waiting for too long so added webElement
        //eWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //below statement improved performance
        eWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> elements =driver.findElements(By.cssSelector(".cartSection h3"));
        Assert.assertTrue(elements.stream().anyMatch(s->s.getText().equalsIgnoreCase("ZARA COAT 3")));
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(countrySearch);
        eWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
        driver.quit();
   }
}
