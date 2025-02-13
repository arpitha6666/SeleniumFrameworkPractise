package testComponents.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String filePath ="/src/test/java/data/PurchaseData.json";

    @Test(dataProvider = "getMapForPurchaseFromJson", groups ="Purchase")
    public void submitOrderTest(HashMap<String,String> input) throws IOException {
        //Login page actions
        ProductCatalogue pc = landingPage.loginApplication(input.get("username"),input.get("password"));

        //Product catalague workflow
        pc.getProductList().stream().forEach(s-> System.out.println(s.getText()));
        System.out.println(pc.getProductByName(input.get("searchByPrd").toString()).getText());
        pc.addProductToCart(input.get("searchByPrd"));
        CartPage ca = pc.goToCartPage();

        Assert.assertTrue(ca.verifyProductDisplay(input.get("searchByPrd")));
        CheckoutPage cp = ca.gotToCheckout();
        cp.selectCountry("India");
        ConfirmationPage page = cp.submitOrder();
        page.getConfirmMsgText();

        Assert.assertTrue(page.getConfirmMsgText().equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void orderHistoryTest(){
        String username = "ars123@gmail.com";
        String password = "Arsars123";
        ProductCatalogue pc = landingPage.loginApplication(username,password);
        OrderPage op = pc.goToOrdersPage();
        Assert.assertTrue(op.verifyOrderDisplay("ZARA COAT 3"));
    }

    @DataProvider
    public Object[][] getDataForPurchase(){
        return new Object[][]{{"ars123@gmail.com","Arsars123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000", "ADIDAS ORIGINAL"}};
    }

    //instead of getData provider, better way to pass data when you have large data is map
    @DataProvider
    public Object[][] getMapForPurchase(){
       HashMap<Object,Object> input = new HashMap<>();
        input.put("username","ars123@gmail.com");
        input.put("password","Arsars123");
        input.put("searchByprd","ZARA COAT 3");
        HashMap<Object,Object> input1 = new HashMap<>();
        input1.put("username","shetty@gmail.com");
        input1.put("password","Iamking@000");
        input1.put("searchByprd","ADIDAS ORIGINAL");

        return new Object[][]{{input},{input1}};
    }

    @DataProvider
    public Object[][] getMapForPurchaseFromJson() throws IOException {
        List<HashMap<String,String>> data =getJsonToMap(filePath);

        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
