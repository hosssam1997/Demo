import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumWaitsDemo {

    @Test
    public void testImplicitWaits() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");
        driver.manage().window().maximize();
        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.cssSelector("div#start > button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        String helloMsg = driver.findElement(By.cssSelector("div#finish > h4")).getText();
        Assert.assertEquals(helloMsg, "Hello World!");
    }

    @Test
    public void testExplicitWaits() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");
        driver.manage().window().maximize();
//        driver.findElement(By.partialLinkText("Example 1")).click();
//        driver.findElement(By.cssSelector("div#start > button")).click();
        click(driver, By.partialLinkText("Example 1"));
        click(driver, By.cssSelector("div#start > button"));


        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish > h4")));
        String helloMsg = driver.findElement(By.cssSelector("div#finish > h4")).getText();
        Assert.assertEquals(helloMsg, "Hello World!");
    }


    private void click(WebDriver driver, By elementLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        driver.findElement(elementLocator).click();
    }

}
