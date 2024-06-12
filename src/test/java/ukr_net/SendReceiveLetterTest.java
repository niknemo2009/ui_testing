package ukr_net;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ukr_net.page_object.InboxTableLettersPage;
import ukr_net.page_object.LoginPage;
import ukr_net.page_object.SendEmailPage;
import ukr_net.pojo.Letter;
import java.time.Duration;
import java.util.UUID;

public class SendReceiveLetterTest {
    static private WebDriver driver;
    static private LoginPage loginPage;
    private SendEmailPage sendEmailPage;
    private InboxTableLettersPage inboxTableLettersPage;


    @BeforeAll
    public static void setUpAll() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 718));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://accounts.ukr.net/login");
        loginPage = new LoginPage(driver);

    }

    @AfterAll
    static void afterAll() {
//        driver.close();
//        driver.quit();
    }

    @org.junit.jupiter.api.Test
    public void sendValidEmail() throws InterruptedException {
        Assertions.assertEquals("Пошта @ ukr.net - українська електронна пошта • Створи емейл",driver.getTitle());
        loginPage.typeLogin(System.getProperty("user"));
        loginPage.typePassword(System.getProperty("password"));
        loginPage.login();
        sendEmailPage = new SendEmailPage(driver);
         String subject = "test_subject_" + UUID.randomUUID();
        Letter validLetter = new Letter(System.getProperty("receiver"), subject, "message 133333327777456");
        sendEmailPage.sendEmail(validLetter);
        inboxTableLettersPage = sendEmailPage.toInbox();
       Thread.sleep(5000);
        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

}
