package framework.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {
    static WebDriver driver;
    public static WebDriver initDriver(String browserType, int implicitWait, Boolean maximize, Boolean headless) {
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
