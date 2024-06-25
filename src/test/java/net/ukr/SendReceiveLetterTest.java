package net.ukr;


import net.ukr.base.BaseTest;
import net.ukr.model.Letter;
import net.ukr.model.User;
import net.ukr.page_object.BasePage;
import net.ukr.page_object.InboxTableLettersPage;
import net.ukr.page_object.LoginPage;
import net.ukr.page_object.SendEmailPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class SendReceiveLetterTest extends BaseTest {
    private LoginPage loginPage;
    private BasePage PageAfterSigninUser;
    private InboxTableLettersPage inboxTableLettersPage;
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";


    @BeforeEach
    public void beforeEach() {
        driver.get("https://accounts.ukr.net/login");
        loginPage = new LoginPage(driver);

    }

    //@org.junit.jupiter.api.Test
    @RepeatedTest(1)
    public void sendValidEmail() {
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        // sendEmailPage = loginPage.loginExistingUser(EXISTING_USER);
        PageAfterSigninUser = loginPage.signinUser(EXISTING_USER, new SendEmailPage(driver));
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        ((SendEmailPage) PageAfterSigninUser).sendEmail(validLetter);
        inboxTableLettersPage = ((SendEmailPage) PageAfterSigninUser).toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> inboxTableLettersPage.findLetterInInbox(validLetter));
        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

}
