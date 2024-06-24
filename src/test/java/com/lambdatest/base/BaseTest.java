package com.lambdatest.base;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
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
            driver.close();
            driver.quit();
        }
    }


    public WebElement getLocatedElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(d -> driver.findElement(by));
    }

    protected FirefoxDriver startFirefoxDriver() {
        return startFirefoxDriver(new FirefoxOptions());
    }

    protected FirefoxDriver startFirefoxDriver(FirefoxOptions options) {
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        driver = new FirefoxDriver(options);
        return (FirefoxDriver) driver;
    }

    protected ChromeDriver startChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        return startChromeDriver(options);
    }

    protected ChromeDriver startChromeDriver(ChromeOptions options) {
        driver = new ChromeDriver(options);
        return (ChromeDriver) driver;
    }

    protected void enableLogging() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.FINE);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.FINE);
        });
    }

  public   String repeat(String source, int countLetter) {
        StringBuilder sb = new StringBuilder();
        while (sb.toString().length() < countLetter) {
            sb.append(source);
        }
        return sb.substring(0, countLetter);
    }

   public  void makeScreenshot(String pathFile, WebDriver driver) throws Exception {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(pathFile));
    }
}
