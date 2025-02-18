package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.*;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefnitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue pc;
    public OrderPage op;
    public CartPage ca;
    public CheckoutPage cp;
    public ConfirmationPage page;
    @Given("I landed on ECommerce page")
    public void I_landed_on_ECommerce_page() throws IOException {
        landingPage=launchApplication();
    }

    @Given("^I logged in with username (.+) and password (.+)$")
    public void I_logged_in_with_username_and_password(String username, String password){
        pc= landingPage.loginApplication(username,password);
    }

    @When("^Add the product (.+) to cart$")
    public void add_the_product_searchByPrd_to_cart(String searchByPrd){
        List<WebElement> products = pc.getProductList();
        pc.addProductToCart(searchByPrd);
    }

    @And("^checkout (.+) and submit the order by selecting the country (.+)$")
    public void checkout_and_submit_the_order(String searchByPrd ,String country){
        ca = pc.goToCartPage();
        Assert.assertTrue(ca.verifyProductDisplay(searchByPrd));
        cp = ca.gotToCheckout();
        cp.selectCountry(country);
        page = cp.submitOrder();
    }

    @Then("{string} message is displayed on the ConfirmationPage")
    public void message_is_displayed_on_the_ConfirmationPage(String verificationMsg){
        System.out.println(page.getConfirmMsgText());
       Assert.assertTrue(page.getConfirmMsgText().equalsIgnoreCase(verificationMsg));
       driver.close();
    }

    @Then("{string} error message is displayed on the ConfirmationPage")
    public void error_message_is_displayed_on_the_ConfirmationPage(String verificationMsg){
        System.out.println(landingPage.getErrorMsg());
        Assert.assertTrue(landingPage.getErrorMsg().equalsIgnoreCase(verificationMsg));
        driver.close();
    }


}
