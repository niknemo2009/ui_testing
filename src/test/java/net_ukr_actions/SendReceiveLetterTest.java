package net_ukr_actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net_ukr_actions.actions.ActionsLoginPage;
import net_ukr_actions.actions.ActionsSendEmailPage;
import net_ukr_actions.base.BaseTest;
import net_ukr_actions.model.Letter;
import net_ukr_actions.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SendReceiveLetterTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(SendReceiveLetterTest.class);

    private ActionsLoginPage actionsLoginPage;
    // private InboxTableLettersPage inboxTableLettersPage;
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";


    @BeforeEach
    public void beforeEach() {
        //  driver.get("https://accounts.ukr.net/login");
        actionsLoginPage = new ActionsLoginPage(driver);
        logger.info(ANSI_GREEN + "Before each !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_WHITE);

    }


    @RepeatedTest(10)
    public void sendValidEmail() throws Exception {
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
    @ValueSource(classes = {ChromeDriver.class, FirefoxDriver.class})
    void test(Class<? extends WebDriver> webDriverClass) {
        // Driver management and WebDriver instantiation
        driver = WebDriverManager.getInstance(webDriverClass).create();

        // Exercise
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }


}
