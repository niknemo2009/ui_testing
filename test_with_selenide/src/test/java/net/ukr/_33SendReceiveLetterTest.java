package net.ukr;

import ch.qos.logback.classic.Level;
import com.codeborne.selenide.Configuration;
import net.ukr.model.Letter;
import net.ukr.model.User;
import net.ukr.page_object.LoginPage;
import net.ukr.page_object.SendEmailPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;


public class _33SendReceiveLetterTest {
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";
    private static final ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(_33SendReceiveLetterTest.class);

    LoginPage loginPage;

    @BeforeAll
    public static void setUpTest() {
        closeWebDriver();
        Configuration.baseUrl = "https://accounts.ukr.net/login";
        logger.setLevel(Level.INFO);
    }

    @BeforeEach
    public void beforeEach() {
        open("/");
        loginPage = new LoginPage();
    }

    @AfterEach
    public void clear() {
        closeWindow();
    }

    @RepeatedTest(3)
    @DisplayName("As user I should send ordinary email ")
    public void sendValidEmail() {
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        logger.error("create letter {}", validLetter.toString());
        var expectedActions = loginPage.signInUser(EXISTING_USER, new SendEmailPage())
                .writeEmail(validLetter).submitSendClick(new SendEmailPage()).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(webdriver().object(), Duration.ofSeconds(1));
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();
    }

    @Test
    public void sendValidEmailWithAttachedFile() {
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        logger.info("create letter {}", validLetter.toString());
        var expectedActions = loginPage.signInUser(EXISTING_USER, new SendEmailPage())
                .writeEmail(validLetter).attachFile().attachFile().submitSendClick(new SendEmailPage()).
                toInbox();
        Wait<WebDriver> wait = new WebDriverWait(webdriver().object(), Duration.ofSeconds(1));
        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
        assertThat(expectedActions.findLetterInInbox(validLetter)).isTrue();


    }


}
