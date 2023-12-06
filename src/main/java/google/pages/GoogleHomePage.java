package google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleHomePage {
    private WebDriver driver;

    String googleHomePageUrl = "https://www.google.com/ncr";

    // Locators
    private final By googleSearchBar_textarea = By.xpath("//textarea[@jsname='yZiJbe']");

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }


    // Actions
    public void navigateToGoogleSearchHomePage() {
        driver.navigate().to(googleHomePageUrl);
    }


    public GoogleHomePage googleSearch(String searchData) {
        driver.findElement(googleSearchBar_textarea).sendKeys(searchData, Keys.ENTER);
        return this;
    }

    // Validations
    public GoogleHomePage validateThatWeAreInTheHomePage() {
        Assert.assertEquals(driver.getTitle(), "Google");
        return this;
    }


}
