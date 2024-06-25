package net.ukr.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    protected TestInfo testInfo;

    @BeforeEach
  public  void init(TestInfo testInfo) {
        this.testInfo = testInfo;
        startChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    public void quit() {
        if (driver != null) {
//            driver.close();
//            driver.quit();
        }
    }


    protected void startFirefoxDriver() {

        startFirefoxDriver(new FirefoxOptions());
    }

    protected void startFirefoxDriver(FirefoxOptions options) {
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        driver = new FirefoxDriver(options);

    }

    protected void startChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        startChromeDriver(options);
    }

    protected void startChromeDriver(ChromeOptions options) {
        driver = new ChromeDriver(options);

    }


}
