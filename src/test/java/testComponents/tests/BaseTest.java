package testComponents.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public WebDriverManager manager;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        //properties class
        Properties prop = new Properties();
        FileInputStream fis;

        fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalConfig.properties");
        prop.load(fis);
        String browser =prop.getProperty("browser");

        if(browser.equalsIgnoreCase("firefox")) {
            manager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }else if(browser.equalsIgnoreCase("chrome")){
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.getLoginPageUrl();
        return landingPage;
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        //driver.quit();
    }
}
