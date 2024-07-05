package net_ukr_actions.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    protected TestInfo testInfo;


//    @BeforeAll
//    static void setupClass() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    public void init(TestInfo testInfo) {
        this.testInfo = testInfo;
        startChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            //    driver.close();
            //     driver.quit();
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
        //  WebDriverManager.chromedriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        //   options.setBrowserVersion("75.0");

        //  options.addArguments("--headless");
        WebDriverManager webDriverManager = WebDriverManager.firefoxdriver().browserInDocker().enableVnc().
                capabilities(options);
        //  webDriverManager.avoidBrowserDetection();
        options.setImplicitWaitTimeout(Duration.ofSeconds(1));
        webDriverManager.create();

        startChromeDriver(options);
    }

    protected void startChromeDriver(FirefoxOptions options) {
        //  WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);

    }

    protected void startChromeDriver33() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1280,800");
        options.addArguments("--disable-gpu");
        options.setImplicitWaitTimeout(Duration.ofSeconds(5));
        //  startChromeDriver(options);
    }


}
