package pageobjects;

import Utility.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;
    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;


    public Boolean verifyProductDisplay(String productName) {
        return productTitles.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage gotToCheckout() {
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

}
