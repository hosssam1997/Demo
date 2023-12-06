import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTestNgDemoLinearDesign {

    private WebDriver driver;

    String googleHomePageUrl = "https://www.google.com/ncr";
    
    // Element Locators
    private final By googleSearchBar_textarea = By.xpath("//textarea[@jsname='yZiJbe']");
    private final By firstSearchResult_h3 = By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]");

    @Test
    public void testSearch1() {
        googleSearch("Giza Systems");
        Assert.assertTrue(getFirstSearchResultText().contains("Giza"));
        assertOnFirstSearchResult("Giza Systems");
    }

    @Test
    public void testSearch2() {
        googleSearch("Selenium WebDriver");
        assertOnFirstSearchResult("WebDriver");
    }


    /////////////////////////// Configurations

    @BeforeMethod
    public void beforeMethod() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        navigateToGoogleSearchHomePage();
    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }


    //////////////////////////// Actions / Business steps ////////////

    public void navigateToGoogleSearchHomePage() {
        driver.navigate().to(googleHomePageUrl);
    }

    public void googleSearch(String searchData) {
        driver.findElement(googleSearchBar_textarea).sendKeys(searchData, Keys.ENTER);
    }

    public String getFirstSearchResultText() {
        return driver.findElement(firstSearchResult_h3).getText();
    }

    public void assertOnFirstSearchResult(String expectedResult) {
        String firstSearchResultText = driver.findElement(firstSearchResult_h3).getText();
        Assert.assertEquals(firstSearchResultText, expectedResult);
    }


}
