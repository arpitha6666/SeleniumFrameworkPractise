package pageobjects;


import Utility.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(css = ".login-btn")
    WebElement loginBtn;

    @FindBy(css = "[class*=flyInOut]")
    WebElement errorMsg;

    public  ProductCatalogue loginApplication(String username,String pw){
        userEmail.sendKeys(username);
        password.sendKeys(pw);
        loginBtn.click();
        return new ProductCatalogue(driver);
    }

    public void getLoginPageUrl(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMsg(){
        waitForVisibilityWebElement(errorMsg);
        return errorMsg.getText();
    }

}
