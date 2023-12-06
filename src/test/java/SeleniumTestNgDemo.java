import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SeleniumTestNgDemo {

    WebDriver driver;

    @Test
    public void testSearch1() {
        driver.findElement(By.xpath("//textarea[@jsname='yZiJbe']")).sendKeys("Giza Systems", Keys.ENTER);

        String firstSearchResultText = driver.findElement(By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]")).getText();
        Assert.assertTrue(firstSearchResultText.contains("Giza"));
    }

    @Test
    public void testSearch2() {
        driver.findElement(By.xpath("//textarea[@jsname='yZiJbe']")).sendKeys("Selenium WebDriver", Keys.ENTER);

        String firstSearchResultText = driver.findElement(By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]")).getText();
        Assert.assertEquals(firstSearchResultText, "WebDriver");
    }


    /////////////////////////// Configurations

    @BeforeMethod
    public void beforeMethod() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/ncr");
    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
