package pageobjects;

import Utility.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> orderTitles;


    public Boolean verifyOrderDisplay(String productName) {
        return orderTitles.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
    }

}
