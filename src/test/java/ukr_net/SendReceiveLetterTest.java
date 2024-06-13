package ukr_net;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ukr_net.page_object.InboxTableLettersPage;
import ukr_net.page_object.LoginPage;
import ukr_net.page_object.SendEmailPage;
import ukr_net.pojo.Letter;
import java.time.Duration;
import java.util.UUID;

public class SendReceiveLetterTest extends BaseTest {
    private LoginPage loginPage;
    private SendEmailPage sendEmailPage;
    private InboxTableLettersPage inboxTableLettersPage;


    @BeforeEach
    public void beforeEach() {
        driver.get("https://accounts.ukr.net/login");
        loginPage = new LoginPage(driver);

    }

    @org.junit.jupiter.api.Test
    public void sendValidEmail() throws InterruptedException {
        Assertions.assertEquals("Пошта @ ukr.net - українська електронна пошта • Створи емейл", driver.getTitle());
        sendEmailPage = loginPage.loginValidUser(System.getProperty("user"), System.getProperty("password"));
        String subject = "test_subject_" + UUID.randomUUID();
        Letter validLetter = new Letter(System.getProperty("receiver"), subject, "message 133333327777456");
        sendEmailPage.sendEmail(validLetter);
        inboxTableLettersPage = sendEmailPage.toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> inboxTableLettersPage.findLetterInInbox(validLetter));
        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

    @org.junit.jupiter.api.Test
    public void sendValidEmail33() throws InterruptedException {
        Assertions.assertEquals("Пошта @ ukr.net - українська електронна пошта • Створи емейл", driver.getTitle());
        sendEmailPage = loginPage.loginValidUser(System.getProperty("user"), System.getProperty("password"));
        String subject = "test_subject_" + UUID.randomUUID();
        Letter validLetter = new Letter(System.getProperty("receiver"), subject, "message 133333327777456");
        sendEmailPage.sendEmail(validLetter);
        inboxTableLettersPage = sendEmailPage.toInbox();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> inboxTableLettersPage.findLetterInInbox(validLetter));
        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

}
