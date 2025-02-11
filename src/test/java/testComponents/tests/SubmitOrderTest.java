package testComponents.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;

public class SubmitOrderTest extends BaseTest{

    @Test
    public void submitOrderTest() throws IOException {
        String username = "ars123@gmail.com";
        String password = "Arsars123";
        String searchByprd= "ZARA COAT 3";

        //Login page actions
        ProductCatalogue pc = landingPage.loginApplication(username,password);

        //Product catalague workflow
        pc.getProductList().stream().forEach(s-> System.out.println(s.getText()));
        System.out.println(pc.getProductByName(searchByprd).getText());
        pc.addProductToCart(searchByprd);
        CartPage ca = pc.gotToCartPage();

        Assert.assertTrue(ca.verifyProductDisplay(searchByprd));
        CheckoutPage cp = ca.gotToCheckout();
        cp.selectCountry("India");
        ConfirmationPage page = cp.submitOrder();
        page.getConfirmMsgText();

        Assert.assertTrue(page.getConfirmMsgText().equalsIgnoreCase("Thankyou for the order."));


    }
}
