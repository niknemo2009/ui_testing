package net_ukr_actions;

import net_ukr_actions.actions.ActionsLoginPage;
import net_ukr_actions.actions.ActionsSendEmailPage;
import net_ukr_actions.base.BaseTest;
import net_ukr_actions.base.Color;
import net_ukr_actions.base.TypeBrowser;
import net_ukr_actions.model.Letter;
import net_ukr_actions.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SendReceiveLetterTest extends BaseTest {
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";
    private final String START_URL = "https://accounts.ukr.net/login";
    Logger logger = LoggerFactory.getLogger(SendReceiveLetterTest.class);
    private ActionsLoginPage actionsLoginPage;

    public static Stream<Arguments> matrixBrowsers() {
        return Stream.of(
                arguments(0, TypeBrowser.CHROME),
                arguments(5, TypeBrowser.CHROME),
                arguments(10, TypeBrowser.CHROME),
                arguments(0, TypeBrowser.FIREFOX),
                arguments(5, TypeBrowser.FIREFOX),
                arguments(10, TypeBrowser.FIREFOX)

        );
    }

    private void setUpTest(int delta, TypeBrowser browser) {
        init(testInfo, delta, browser);
        driver.get(START_URL);
        actionsLoginPage = new ActionsLoginPage(driver);
        logger.info(Color.GREEN.value() + "Before each !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Color.RESET.value());
    }

    @RepeatedTest(1)
    public void sendValidEmail() {
        setUpTest(0, TypeBrowser.CHROME);
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver))
                .sendEmail(validLetter, new ActionsSendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }

    @ParameterizedTest
    @MethodSource("matrixBrowsers")
    void test(int delta, TypeBrowser browser) {
        setUpTest(delta, browser);
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver))
                .sendEmail(validLetter, new ActionsSendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();
    }

}
