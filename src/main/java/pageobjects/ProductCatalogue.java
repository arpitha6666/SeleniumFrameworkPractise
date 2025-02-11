package pageobjects;

import Utility.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By productName = By.cssSelector("b");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMs = By.cssSelector("#toastMsgLocator");


    public List<WebElement> getProductList() {
        waitForVisibilityOfElement(productsBy);
        return products;
    }

    public WebElement getProductByName(String searchByProduct){
        return getProductList().stream()
            .filter(s->s.findElement(productName).getText().equals(searchByProduct))
            .findFirst().orElse(null);
    }

    public void addProductToCart(String searchByProduct){
        WebElement prd = getProductByName(searchByProduct);
        prd.findElement(addToCart).click();
        //waitForVisibilityOfElement(toastMs);
        waitForInVisibilityOfElement(spinner);

    }
}
