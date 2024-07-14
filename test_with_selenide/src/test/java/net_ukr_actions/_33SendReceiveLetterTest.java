package net_ukr_actions;

import net_ukr_actions.base.BaseTest;
import net_ukr_actions.base.TypeBrowser;
import net_ukr_actions.model.Letter;
import net_ukr_actions.model.User;
import net_ukr_actions.page_object.LoginPage;
import net_ukr_actions.page_object.SendEmailPage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;

import java.time.Duration;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class _33SendReceiveLetterTest extends BaseTest {
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";
    private final String START_URL = "https://accounts.ukr.net/login";
    Logger logger = LoggerFactory.getLogger(_33SendReceiveLetterTest.class);
    private LoginPage loginPage;
    LoggingEventBuilder evb;


    private void setUpTest(int delta, TypeBrowser browser) {
        init(testInfo, delta, browser);
        open(START_URL);
        loginPage = new LoginPage(driver);
    }

    @RepeatedTest(1)
    public void sendValidEmail() {
        setUpTest(0, TypeBrowser.CHROME);
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        $("");
        var expectedActions = loginPage.signInUser(EXISTING_USER, new SendEmailPage(driver))
                .writeEmail(validLetter).submitSendClick(new SendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }

    @Test
    public void sendValidEmailWithAttachedFile() {
        setUpTest(0, TypeBrowser.SAFARI);
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = loginPage.signInUser(EXISTING_USER, new SendEmailPage(driver))
                .writeEmail(validLetter).attachFile().attachFile().submitSendClick(new SendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }

//    @ParameterizedTest
//    @MethodSource("matrixBrowsers")
//    void test(int delta, TypeBrowser browser) throws AWTException {
//        setUpTest(delta, browser);
//       Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
//        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new SendEmailPage(driver))
//                .writeEmail(validLetter).submitSendClick(new SendEmailPage(driver)).
//                toInbox();
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
//        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();
//    }
//
//    public static Stream<Arguments> matrixBrowsers() {
//        return Stream.of(
//                arguments(0, TypeBrowser.CHROME),
//                arguments(0, TypeBrowser.FIREFOX)
//
//        );
//    }


}
