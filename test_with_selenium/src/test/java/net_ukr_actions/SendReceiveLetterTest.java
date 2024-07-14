package net_ukr_actions;

import net_ukr_actions.actions.ActionsLoginPage;
import net_ukr_actions.actions.ActionsSendEmailPage;
import net_ukr_actions.base.BaseTest;
import net_ukr_actions.base.TypeBrowser;
import net_ukr_actions.model.Letter;
import net_ukr_actions.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;

import java.awt.*;
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
    LoggingEventBuilder evb;


    private void setUpTest(int delta, TypeBrowser browser) {
        init(testInfo, delta, browser);
        driver.get(START_URL);
        actionsLoginPage = new ActionsLoginPage(driver);
        //  logger.info(Color.GREEN.value() + "Before each !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Color.RESET.value());
        evb = logger.atInfo().setMessage("Temperature changed.***************************");
    }

    @RepeatedTest(1)
    public void sendValidEmail() {
        setUpTest(0, TypeBrowser.CHROME);
        evb.setMessage("qqqqqqqqqqqqqqqqqqqqqqqqqqqq").addKeyValue("oldT", 55).addKeyValue("newT", 67);
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver))
                .writeEmail(validLetter).submitSendClick(new ActionsSendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }

    @Test
    public void sendValidEmailWithAttachedFile() {
        setUpTest(0, TypeBrowser.SAFARI);
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver))
                .writeEmail(validLetter).attachFile().attachFile().submitSendClick(new ActionsSendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }

    @ParameterizedTest
    @MethodSource("matrixBrowsers")
    void test(int delta, TypeBrowser browser) throws AWTException {
        setUpTest(delta, browser);
        evb.log();
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver))
                .writeEmail(validLetter).submitSendClick(new ActionsSendEmailPage(driver)).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();
    }

    public static Stream<Arguments> matrixBrowsers() {
        return Stream.of(
                arguments(0, TypeBrowser.CHROME),
                arguments(0, TypeBrowser.FIREFOX)

        );
    }


    @Listeners({ScreenShooter.class})
    public static class MainTest {
        @BeforeClass
        public void setUp() {
            Configuration.reportsFolder = "../Screenshots";

        }

        @Test(groups = {"fast"})
        public void aFastTest() {

            Selenide.open("/client/registration");
            Selenide.$(By.name("user.name")).val("johny");
            Selenide.$(By.name("user.gender")).selectRadio("male");
            Selenide.$("#user.preferredLayout").selectOption("plain");
            Selenide.$("#user.securityQuestion").selectOptionByText("What is my first car?");

        }

        @Test(groups = {"slow"})
        public void aSlowTest() {
            System.out.println("Slow test");
        }
    }
}
