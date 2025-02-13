package testComponents.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

import java.time.Duration;

public class PageObjectTest {

    @Test
    public void testLoginPage(){
        String username = "ars123@gmail.com";
        String password = "Arsars123";
        String searchByprd= "ZARA COAT 3";

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Login page actions
        LandingPage la = new LandingPage(driver);
        la.getLoginPageUrl();
        ProductCatalogue pc =la.loginApplication(username,password);

        //Product catalague workflow
        pc.getProductList().stream().forEach(s-> System.out.println(s.getText()));
        System.out.println(pc.getProductByName(searchByprd).getText());
        pc.addProductToCart(searchByprd);
        CartPage ca = pc.goToCartPage();

        Assert.assertTrue(ca.verifyProductDisplay(searchByprd));
        CheckoutPage cp = ca.gotToCheckout();
        cp.selectCountry("India");
        ConfirmationPage page = cp.submitOrder();
        page.getConfirmMsgText();

        Assert.assertTrue(page.getConfirmMsgText().equalsIgnoreCase("Thankyou for the order."));

        driver.close();
        driver.quit();

    }
}
