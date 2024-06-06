package checkBox;

import TestUtil.ErrorInfo;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckBoxTest implements ErrorInfo {
    public static final String FILE_SCREENSHOTS = "./Screenshots/%s.png";
    static private WebDriver driver;
    static private CheckBoxPage checkBoxPage;
    private TestInfo testInfo;


    @BeforeAll
    public static void setUpAll() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 1200));
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        checkBoxPage = new CheckBoxPage(driver);
    }

    @AfterAll
    static void afterAll() {
//        driver.close();
//        driver.quit();
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @Test
    @DisplayName("2.1 I should check single checkBox")
    public void testSingleCheckBox() throws Exception {
        checkBoxPage.singleCheckBox.click();
        boolean isDisplayed1=checkBoxPage.textSingle.isDisplayed();
        String text=checkBoxPage.textSingle.getText();
        checkBoxPage.singleCheckBox.click();
        boolean isDisplayed2=checkBoxPage.textSingle.isDisplayed();
        System.out.println(checkBoxPage.textSingle.getAttribute("style")+" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
        assertAll("Problem with SingleCheckBox!",
                () ->assertEquals(true, isDisplayed1, "Problem with visible textSingle!") ,
                () ->assertEquals(false, isDisplayed2, "Problem with invisible textSingle!") ,
               () ->assertEquals(text, "Checked", "Problem with result text related with singleCheckBox !")
        );
    }



    @Test
    @DisplayName("1.2 I should check the Confirm Alert (Cancel) ")
    public void testConfirmAlertCancel() throws Exception {
        //alertPage.confirmAlert();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textInAlert = alert.getText();
        alert.dismiss();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
//        assertAll("Problem with Confirm Alert (Cancel)!",
//                () ->assertEquals("Press a button!", textInAlert, "Problem with text in Confirm Alert!") ,
//               () ->assertEquals(alertPage.getConfirmText(), "You pressed Cancel!", "Problem with Cancel confirm message on page!")
//        );

        }



}
