package org.example.ui_testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotalTest {
    private static final Logger log = LoggerFactory.getLogger(TotalTest.class);
    static  private WebDriver driver;
    static JavascriptExecutor js;
  static  public LoginPage   loginPage;
    @BeforeAll
    public static void setUpAll() {
     driver=new ChromeDriver();
     driver.manage().window().setSize(new Dimension(1200, 718));
     loginPage=new LoginPage(driver);
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void totalFlow() throws InterruptedException {
       EmailPage emailPage=loginPage.loginValidUser(System.getProperty("user"),System.getProperty("password"));

      emailPage.sendEmail(System.getProperty("reciver"),"test_subject33","message 1","");


    }

}
