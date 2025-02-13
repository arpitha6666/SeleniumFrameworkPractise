package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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

    public List<HashMap<String,String>> getJsonToMap(String filePath) throws IOException {
        //read JSON to
        String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir") + new File(filePath)), String.valueOf(StandardCharsets.UTF_8));
        //String to map can be done using Jackson-databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.getLoginPageUrl();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
        //driver.quit();
    }
}
