import framework.engine.JsonFileManager;
import framework.engine.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class testProperties {

    static WebDriver driver;
    JsonFileManager testData;

    String googleHomePageUrl = "https://www.google.com/ncr";

    // Element Locators
    private final By googleSearchBar_textarea = By.xpath("//textarea[@jsname='yZiJbe']");
    private final By firstSearchResult_h3 = By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]");


    @Test
    public void testSearch1() {
        googleSearch(testData.getTestData("searchData1"));
        assertOnFirstSearchResult(testData.getTestData("searchData1"));
    }

    @Test
    public void testSearch2() {
        googleSearch(testData.getTestData("test2.searchData2"));
        assertOnFirstSearchResult(testData.getTestData("test2.expectedFirstResult2"));
    }

    //////////////////////// Configurations \\\\\\\\\\\\\\\\\\\\\\\\\\\

    @BeforeClass
    public void beforeClass() {
        PropertiesReader.loadProperties();
        testData = new JsonFileManager("src/test/resources/Test Data/TestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = initDriver();
        navigateToGoogleSearchHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    ///////////////////////////////////////// Steps

    public void navigateToGoogleSearchHomePage() {
        driver.navigate().to(googleHomePageUrl);
    }

    public void googleSearch(String searchData) {
        driver.findElement(googleSearchBar_textarea).sendKeys(searchData, Keys.ENTER);
    }

    public void assertOnFirstSearchResult(String expectedResult) {
        String firstSearchResultText = driver.findElement(firstSearchResult_h3).getText();
        Assert.assertEquals(firstSearchResultText, expectedResult);
    }




    ///////////////// Core
    private static WebDriver initDriver(String browserType, int implicitWait, Boolean maximize, Boolean headless) {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = null;
            if (headless) {
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
            } else {
                driver = new ChromeDriver();
            }
        } else if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions ffOptions = null;
            if (headless){
                ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--headless");
                driver = new FirefoxDriver(ffOptions);
            } else {
                driver = new FirefoxDriver();
            }
        } else if (browserType == "edge") {
            driver = new EdgeDriver();
        } else {
            driver = new SafariDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        if (Boolean.TRUE.equals(maximize)) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver initDriver(String browserType) {
        return initDriver(browserType, 20, true, false);
    }

    public static WebDriver initDriver() {
        return initDriver(
                System.getProperty("browserType"),
                Integer.parseInt(System.getProperty("waits")),
                Boolean.valueOf(System.getProperty("maximizeWindow")),
                Boolean.valueOf(System.getProperty("headlessExecution"))
        );
    }

}
