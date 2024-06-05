package other.alertBox;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class AlertTest {
    static private WebDriver driver;
    static private AlertPage alertPage;
    private TestInfo testInfo;


    @BeforeAll
    public static void setUpAll() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 718));
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        alertPage = new AlertPage(driver);
    }

    @AfterAll
    static void afterAll() {
        driver.close();
        driver.quit();
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @org.junit.jupiter.api.Test
    public void testSimpleAlert() {
        alertPage.simpleAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualResult = alert.getText();
        try {
            Assertions.assertEquals("I am an alert box!", actualResult, "Problem with simple alert !");
        } finally {
            alert.accept();
        }


    }

    @org.junit.jupiter.api.Test
    public void testConfirmAlertCancel() {
        alertPage.confirmAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = driver.switchTo().alert();
        try {
            Assertions.assertEquals("Press a button!", alert.getText(), "Problem with text in Confirm Alert!");
        } finally {
            alert.dismiss();
            Assertions.assertEquals(alertPage.getConfirmText(), "You pressed Cancel!", "Problem with Cancel confirm message !");
        }


    }

    @org.junit.jupiter.api.Test
    public void testConfirmAlertOk() {
        alertPage.confirmAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Press a button!", alert.getText(), "Problem with text in Confirm Alert!");
        alert.accept();
        Assertions.assertEquals(alertPage.getConfirmText(), "You pressed OK!", "Problem with OK confirm message !");

    }

    @org.junit.jupiter.api.Test
    public void testPromptAlert() {
        alertPage.promptAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String bigStr = repeat("qwe", 380);
        System.out.println(bigStr);
        System.out.println(bigStr.length());
        alert.sendKeys(bigStr);
        alert.accept();
        Assertions.assertEquals(alertPage.getPromptText(), "You have entered '%s' !".formatted(bigStr), "Problem with prompt message !");

    }

    @ParameterizedTest(name = "AllertPrompt with value {0} .")
    @ValueSource(strings = {"Selenium", "уукук кекеке Qwewe 1345 23{}|", "____ ____ 23@#"})
    public void testPromptAlertParam(String parametr) throws Exception {
        alertPage.promptAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(parametr);
        alert.accept();
        try {
            Assertions.assertEquals(alertPage.getPromptText(), "You have entered '%s' !33".formatted(parametr), "Problem with prompt message !");
        } catch (Throwable e) {
            makeScreenshot(testInfo.getDisplayName());
        }
    }

    public void makeScreenshot(String nameTest) throws Exception {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/%s.png".formatted(nameTest)));
    }

    String repeat(String source, int countLetter) {
        StringBuilder sb = new StringBuilder();
        while (sb.toString().length() < countLetter) {
            sb.append(source);
        }
        return sb.substring(0, countLetter);
    }
}
