package org.example.ui_testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

public class TotalTest {
    private static final Logger log = LoggerFactory.getLogger(TotalTest.class);
    static public WebDriver driver;
    static public LoginPage loginPage;


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
        //  driver.quit();
    }

    @org.junit.jupiter.api.Test
    public void sendValidEmail() {
        //  loginPage.verifyTitle();
        loginPage.typeLogin(System.getProperty("user"));
        loginPage.typePassword(System.getProperty("password"));
        loginPage.login();
        SendEmailPage sendEmailPage = new SendEmailPage(driver);
        String subject = "test_subject_" + UUID.randomUUID();
        Letter validLetter = new Letter(System.getProperty("receiver"), subject, "message 133333327777456");
        //sendEmailPage.verifyTitle(System.getProperty("user")+"@ukr.net");
        sendEmailPage.sendEmail(validLetter);
        InboxTableLettersPage inboxTableLettersPage = sendEmailPage.toInbox();

        Assertions.assertTrue(inboxTableLettersPage.findLetterInInbox(validLetter));


    }

}
