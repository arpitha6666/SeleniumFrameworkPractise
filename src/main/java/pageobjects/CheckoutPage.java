package pageobjects;

import Utility.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement countryEle;
    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;
    @FindBy(css=".action__submit")
    WebElement submitBtn;


    By listOfCountries = By.cssSelector(".ta-results");

    public void selectCountry(String countryToBelSelected){
        Actions a = new Actions(driver);
        a.sendKeys(countryEle,countryToBelSelected).build().perform();
        waitForVisibilityOfElement(listOfCountries);
        selectCountry.click();

    }

    public ConfirmationPage submitOrder(){
        submitBtn.click();
       return new ConfirmationPage(driver);
    }
    /*driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(countrySearch);
        eWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
    String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order.")); */


}
