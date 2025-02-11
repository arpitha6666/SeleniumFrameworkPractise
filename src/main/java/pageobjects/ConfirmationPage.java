package pageobjects;

import Utility.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css=".hero-primary")
    WebElement confirmMsgText;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getConfirmMsgText(){
        return confirmMsgText.getText();
    }

}
