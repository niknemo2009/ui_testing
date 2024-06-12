package org.example.ui_testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class TotalTest {
    static private WebDriver driver;
    static private LoginPage loginPage;
    private SendEmailPage sendEmailPage;
    private InboxTableLettersPage inboxTableLettersPage;


    @BeforeAll
    public static void setUpAll() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 718));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        //  loginPage.verifyTitle();
        loginPage.typeLogin(System.getProperty("user"));
        loginPage.typePassword(System.getProperty("password"));
        loginPage.login();
        sendEmailPage = new SendEmailPage(driver);
        // sendEmailPage.verifyTitle(System.getProperty("receiver"));
        String subject = "test_subject_" + UUID.randomUUID();
        Letter validLetter = new Letter(System.getProperty("receiver"), subject, "message 133333327777456");
        sendEmailPage.sendEmail(validLetter);
        inboxTableLettersPage = sendEmailPage.toInbox();
       Thread.sleep(5000);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//         wait.until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver input) {
//                return inboxTableLettersPage.findLetterInInbox(validLetter);
//            }
//        });
        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

}
