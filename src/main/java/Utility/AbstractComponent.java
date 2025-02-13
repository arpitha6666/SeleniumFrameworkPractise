package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;
import pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="[routerlink*='myorders']")
    WebElement orderheader;
    public AbstractComponent(WebDriver driver){

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    public void waitForVisibilityOfElement(By locator) {
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        eWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForVisibilityWebElement(WebElement locator) {
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        eWait.until(ExpectedConditions.visibilityOf(locator));

    }

    public void waitForInVisibilityOfElement(WebElement locator) {
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        eWait.until(ExpectedConditions.invisibilityOf(locator));

    }

    public CartPage goToCartPage(){
        cartHeader.click();
        return  new CartPage(driver);
    }

    public OrderPage goToOrdersPage(){
        orderheader.click();
        return  new OrderPage(driver);
    }
}
