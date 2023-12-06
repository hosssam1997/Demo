package google.tests;

import framework.engine.DriverFactory;
import google.pages.GoogleHomePage;
import google.pages.GoogleSearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestsConfigurations {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        driver = new EdgeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();

        driver = DriverFactory.initDriver("chrome");
        new GoogleHomePage(driver).navigateToGoogleSearchHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
