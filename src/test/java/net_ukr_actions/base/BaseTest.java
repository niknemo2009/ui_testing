package net_ukr_actions.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected TestInfo testInfo;

    public void init(TestInfo testInfo, int delta, TypeBrowser browser) {
        this.testInfo = testInfo;
        switch (browser) {
            case CHROME -> startChromeDriver(delta);
            case FIREFOX -> startFirefoxDriver(delta);
        }
        driver.manage().window().maximize();

    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.close();
        }
    }

    protected void startFirefoxDriver(int deltaVersion) {
        WebDriverManager wdm = WebDriverManager.firefoxdriver().browserInDocker()
                .dockerDefaultArgs("--disable-gpu,--no-sandbox")
                .browserVersion("latest-" + deltaVersion);
        driver = wdm.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    protected void startChromeDriver(int deltaVersion) {
        WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker()
                .dockerDefaultArgs("--disable-gpu,--no-sandbox")
                .browserVersion("latest-" + deltaVersion);
        driver = wdm.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }


}
