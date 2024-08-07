package com.lambdatest;

import com.lambdatest.base.BaseTest;
import com.lambdatest.page_object.CheckBoxPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

public class CheckBoxTest extends BaseTest {
    public  final String FILE_SCREENSHOTS = "./Screenshots/%s.png";
    private CheckBoxPage checkBoxPage;

@BeforeEach
    public  void init(TestInfo testInfo) {
        super.init(testInfo);
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        checkBoxPage = new CheckBoxPage(driver);
    }

    @Test
    @DisplayName("2.1 I should check single other.checkBox")
    public void testSingleCheckBox() throws Exception {
        checkBoxPage.singleCheckBox.click();
        boolean isDisplayed1 = checkBoxPage.textSingle.isDisplayed();
        String text = checkBoxPage.textSingle.getText();
        checkBoxPage.singleCheckBox.click();
        boolean isDisplayed2 = checkBoxPage.textSingle.isDisplayed();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with SingleCheckBox!",
                () -> assertTrue(isDisplayed1, "Problem with visible textSingle!"),
                () -> assertFalse(isDisplayed2, "Problem with invisible textSingle!"),
                () -> assertEquals(text, "Checked", "Problem with result text related with singleCheckBox !")
        );
    }

    @Test
    @DisplayName("2.2 I should check Disabled section")
    public void testDisableCheckBox() throws Exception {
        checkBoxPage.getDisabledCheckBox1().click();
        boolean state1 = checkBoxPage.getDisabledCheckBox1().isSelected();
        checkBoxPage.getDisabledCheckBox1().click();
        boolean state11 = checkBoxPage.getDisabledCheckBox1().isSelected();
        checkBoxPage.getDisabledCheckBox2().click();
        boolean state2 = checkBoxPage.getDisabledCheckBox2().isSelected();
        checkBoxPage.getDisabledCheckBox2().click();
        boolean state21 = checkBoxPage.getDisabledCheckBox2().isSelected();
        boolean state3 = checkBoxPage.getDisabledCheckBox3().isEnabled();
        boolean state4 = checkBoxPage.getDisabledCheckBox4().isEnabled();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with DisableCheckBox!",
                () -> assertTrue(state1, "Problem with state1 !"),
                () -> assertFalse(state11, "Problem with state11 !"),
                () -> assertTrue(state2, "Problem with state2!"),
                () -> assertFalse(state21, "Problem with state21!"),
                () -> assertFalse(state3, "Problem with state3!"),
                () -> assertFalse(state4, "Problem with state4!")

        );
    }

    @Test
    @DisplayName("2.3 I should check multi section ")
    public void testMultiCheckBox() throws Exception {
        checkBoxPage.getMultiCheckBox1().click();
        boolean state1 = checkBoxPage.getMultiCheckBox1().isSelected();
        checkBoxPage.getMultiCheckBox1().click();
        boolean state11 = checkBoxPage.getMultiCheckBox1().isSelected();
        checkBoxPage.getMultiCheckBox2().click();
        boolean state2 = checkBoxPage.getMultiCheckBox2().isSelected();
        checkBoxPage.getMultiCheckBox2().click();
        boolean state21 = checkBoxPage.getMultiCheckBox2().isSelected();
        checkBoxPage.getMultiCheckBox3().click();
        boolean state3 = checkBoxPage.getMultiCheckBox3().isSelected();
        checkBoxPage.getMultiCheckBox3().click();
        boolean state31 = checkBoxPage.getMultiCheckBox3().isSelected();
        checkBoxPage.getMultiCheckBox4().click();
        boolean state4 = checkBoxPage.getMultiCheckBox4().isSelected();
        checkBoxPage.getMultiCheckBox4().click();
        boolean state41 = checkBoxPage.getMultiCheckBox4().isSelected();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with MultiCheckBox!",
                () -> assertTrue(state1, "Problem with state1 !"),
                () -> assertFalse(state11, "Problem with state11 !"),
                () -> assertTrue(state2, "Problem with state2!"),
                () -> assertFalse(state21, "Problem with state21!"),
                () -> assertTrue(state3, "Problem with state3!"),
                () -> assertFalse(state31, "Problem with state31 !"),
                () -> assertTrue(state4, "Problem with state4!"),
                () -> assertFalse(state41, "Problem with state41 !")

        );
    }

    @Test
    @DisplayName("2.4 I should check button ")
    public void testButton() throws Exception {
        String textBefore = checkBoxPage.buttonMulti.getAttribute("value");
        checkBoxPage.buttonMulti.click();
        boolean state1 = checkBoxPage.getMultiCheckBox1().isSelected();
        boolean state2 = checkBoxPage.getMultiCheckBox2().isSelected();
        boolean state3 = checkBoxPage.getMultiCheckBox3().isSelected();
        boolean state4 = checkBoxPage.getMultiCheckBox4().isSelected();
        String textAfter = checkBoxPage.buttonMulti.getAttribute("value");
        checkBoxPage.buttonMulti.click();
        boolean state11 = checkBoxPage.getMultiCheckBox1().isSelected();
        boolean state21 = checkBoxPage.getMultiCheckBox2().isSelected();
        boolean state31 = checkBoxPage.getMultiCheckBox3().isSelected();
        boolean state41 = checkBoxPage.getMultiCheckBox4().isSelected();
        makeScreenshot(FILE_SCREENSHOTS.formatted(testInfo.getDisplayName()), driver);
        assertAll("Problem with Button!",
                () -> assertTrue(state1, "Problem with state1 !"),
                () -> assertFalse(state11, "Problem with state11 !"),
                () -> assertTrue(state2, "Problem with state2!"),
                () -> assertFalse(state21, "Problem with state21!"),
                () -> assertTrue(state3, "Problem with state3!"),
                () -> assertFalse(state31, "Problem with state31 !"),
                () -> assertTrue(state4, "Problem with state4!"),
                () -> assertFalse(state41, "Problem with state41 !"),
                () -> assertEquals("Check All", textBefore, "Problem with text textBefore !"),
                () -> assertEquals("Uncheck All", textAfter, "Problem with text textAfter !")

        );
    }

}
