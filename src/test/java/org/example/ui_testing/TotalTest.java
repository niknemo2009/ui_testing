package org.example.ui_testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class TotalTest {
    private static final Logger log = LoggerFactory.getLogger(TotalTest.class);
    static  public WebDriver driver;
    static  public LoginPage   loginPage;
    @BeforeAll
    public static void setUpAll() {
     driver=new ChromeDriver();
     driver.manage().window().setSize(new Dimension(1200, 718));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
     loginPage=new LoginPage(driver);
    }

    @AfterAll
    static void afterAll() {
      //  driver.quit();
    }

    @org.junit.jupiter.api.Test
    public void totalFlow() {
       EmailPage emailPage=loginPage.loginValidUser(System.getProperty("user"),System.getProperty("password"));

      emailPage.sendEmail(System.getProperty("reciver"),"test_subject3377","message 1","");


    }

}
