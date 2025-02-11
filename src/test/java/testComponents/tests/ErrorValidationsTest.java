package testComponents.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ProductCatalogue;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest{
    @Test
    public void errorValTest() throws IOException {
        String username = "ars1123@gmail.com";
        String password = "Arsars123";
        String searchByprd= "ZARA COAT 3";

        //Login page actions
        ProductCatalogue pc = landingPage.loginApplication(username,password);
        Assert.assertEquals(landingPage.getErrorMsg(),"Incorrect email or password.");
    }
}
