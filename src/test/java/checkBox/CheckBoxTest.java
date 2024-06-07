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

import static org.junit.jupiter.api.Assertions.*;

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
       makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
        assertAll("Problem with SingleCheckBox!",
                () ->assertEquals(true, isDisplayed1, "Problem with visible textSingle!") ,
                () ->assertEquals(false, isDisplayed2, "Problem with invisible textSingle!") ,
               () ->assertEquals(text, "Checked", "Problem with result text related with singleCheckBox !")
        );
    }

    @Test
    @DisplayName("2.2 I should check Disabled section")
    public void testDisableCheckBox() throws Exception {
        checkBoxPage.getDisabledCheckBox1().click();
        boolean state1=checkBoxPage.getDisabledCheckBox1().isSelected();
        checkBoxPage.getDisabledCheckBox1().click();
        boolean state11=checkBoxPage.getDisabledCheckBox1().isSelected();
        checkBoxPage.getDisabledCheckBox2().click();
        boolean state2=checkBoxPage.getDisabledCheckBox2().isSelected();
        checkBoxPage.getDisabledCheckBox2().click();
        boolean state21=checkBoxPage.getDisabledCheckBox2().isSelected();
        boolean state3=checkBoxPage.getDisabledCheckBox3().isEnabled();
        boolean state4=checkBoxPage.getDisabledCheckBox4().isEnabled();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
        assertAll("Problem with DisableCheckBox!",
                () ->assertTrue(state1, "Problem with state1 !") ,
                () ->assertTrue(!state11, "Problem with state11 !") ,
                () ->assertTrue(state2, "Problem with state2!") ,
                () ->assertTrue(!state21, "Problem with state21!") ,
                () ->assertTrue(!state3, "Problem with state3!") ,
                () ->assertTrue(!state4, "Problem with state4!")

        );
    }

    @Test
    @DisplayName("2.3 I should check multi section ")
    public void testMultiCheckBox() throws Exception {
        checkBoxPage.getMultiCheckBox1().click();
        boolean state1=checkBoxPage.getMultiCheckBox1().isSelected();
        checkBoxPage.getMultiCheckBox1().click();
        boolean state11=checkBoxPage.getMultiCheckBox1().isSelected();
        checkBoxPage.getMultiCheckBox2().click();
        boolean state2=checkBoxPage.getMultiCheckBox2().isSelected();
        checkBoxPage.getMultiCheckBox2().click();
        boolean state21=checkBoxPage.getMultiCheckBox2().isSelected();
        checkBoxPage.getMultiCheckBox3().click();
        boolean state3=checkBoxPage.getMultiCheckBox3().isSelected();
        checkBoxPage.getMultiCheckBox3().click();
        boolean state31=checkBoxPage.getMultiCheckBox3().isSelected();
        checkBoxPage.getMultiCheckBox4().click();
        boolean state4=checkBoxPage.getMultiCheckBox4().isSelected();
        checkBoxPage.getMultiCheckBox4().click();
        boolean state41=checkBoxPage.getMultiCheckBox4().isSelected();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
        assertAll("Problem with MultiCheckBox!",
                () ->assertTrue(state1, "Problem with state1 !") ,
                () ->assertTrue(!state11, "Problem with state11 !") ,
                () ->assertTrue(state2, "Problem with state2!") ,
                () ->assertTrue(!state21, "Problem with state21!") ,
                () ->assertTrue(state3, "Problem with state3!") ,
                () ->assertTrue(!state31, "Problem with state31 !") ,
                () ->assertTrue(state4, "Problem with state4!"),
                () ->assertTrue(!state41, "Problem with state41 !")

        );
        }

    @Test
    @DisplayName("2.4 I should check button ")
    public void testButton() throws Exception {
        String textBefore=checkBoxPage.buttonMulti.getAttribute("value");
        checkBoxPage.buttonMulti.click();
         boolean state1=checkBoxPage.getMultiCheckBox1().isSelected();
         boolean state2=checkBoxPage.getMultiCheckBox2().isSelected();
         boolean state3=checkBoxPage.getMultiCheckBox3().isSelected();
         boolean state4=checkBoxPage.getMultiCheckBox4().isSelected();
        String textAfter=checkBoxPage.buttonMulti.getAttribute("value");
        checkBoxPage.buttonMulti.click();
        boolean state11=checkBoxPage.getMultiCheckBox1().isSelected();
        boolean state21=checkBoxPage.getMultiCheckBox2().isSelected();
        boolean state31=checkBoxPage.getMultiCheckBox3().isSelected();
        boolean state41=checkBoxPage.getMultiCheckBox4().isSelected();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()),driver);
        assertAll("Problem with Button!",
                () ->assertTrue(state1, "Problem with state1 !") ,
                () ->assertTrue(!state11, "Problem with state11 !") ,
                () ->assertTrue(state2, "Problem with state2!") ,
                () ->assertTrue(!state21, "Problem with state21!") ,
                () ->assertTrue(state3, "Problem with state3!") ,
                () ->assertTrue(!state31, "Problem with state31 !") ,
                () ->assertTrue(state4, "Problem with state4!"),
                () ->assertTrue(!state41, "Problem with state41 !"),
                () ->assertEquals("Check All", textBefore,"Problem with text textBefore !"),
                () ->assertEquals("Uncheck All", textAfter,"Problem with text textAfter !")

        );
    }

}
