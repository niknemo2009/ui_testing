package other.test;

import TestUtil.ErrorInfo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import other.Page.AlertPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertTest implements ErrorInfo {
    public static final String FILE_SCREENSHOTS = "./Screenshots/%s.png";
    static private WebDriver driver;
    static private AlertPage alertPage;
    private TestInfo testInfo;


    @BeforeAll
    public static void setUpAll() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    @DisplayName("1.1 I should check text in simple Alert ")
    public void testSimpleAlert() throws Exception {
        alertPage.simpleAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String textInAlert = alert.getText();
        alert.accept();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertEquals("I am an alert box!", textInAlert, "Problem with text in simple alert !");

    }


    @org.junit.jupiter.api.Test
    @DisplayName("1.2 I should check the Confirm Alert (Cancel) ")
    public void testConfirmAlertCancel() throws Exception {
        alertPage.confirmAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textInAlert = alert.getText();
        alert.dismiss();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with Confirm Alert (Cancel)!",
                () -> assertEquals("Press a button!", textInAlert, "Problem with text in Confirm Alert!"),
                () -> assertEquals(alertPage.getConfirmText(), "You pressed Cancel!", "Problem with Cancel confirm message on page!")
        );

    }


    @org.junit.jupiter.api.Test
    @DisplayName("1.3 I should check the Confirm Alert (OK)")
    public void testConfirmAlertOk() throws Exception {
        alertPage.confirmAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textInAlert = alert.getText();
        alert.accept();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with Confirm Alert (OK)!",
                () -> assertEquals("Press a button!", textInAlert, "Problem with text in Confirm Alert!"),
                () -> assertEquals(alertPage.getConfirmText(), "You pressed OK!", "Problem with OK confirm message !")
        );

    }

    @org.junit.jupiter.api.Test
    @DisplayName("1.4 I should check the AllertPrompt with value.length=380")
    public void testPromptAlert() {
        alertPage.promptAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String bigStr = repeat("qwe", 380);
        alert.sendKeys(bigStr);
        alert.accept();
        Assertions.assertEquals(alertPage.getPromptText(), "You have entered '%s' !".formatted(bigStr), "Problem with prompt message !");
    }

    @ParameterizedTest(name = "1.5 I should check the AllertPrompt with value {0} .")
    @ValueSource(strings = {"Selenium", "уукук кекеке Qwewe 1345 23{}|", "____ ____ 23@#"})
    public void testPromptAlertParam(String parametr) throws Exception {
        alertPage.promptAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(parametr);
        alert.accept();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertEquals(alertPage.getPromptText(), "You have entered '%s' !".formatted(parametr), "Problem with prompt message !");

    }


    String repeat(String source, int countLetter) {
        StringBuilder sb = new StringBuilder();
        while (sb.toString().length() < countLetter) {
            sb.append(source);
        }
        return sb.substring(0, countLetter);
    }
}
