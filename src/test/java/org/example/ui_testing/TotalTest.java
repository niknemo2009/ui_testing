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
     driver.manage().window().setSize(new Dimension(681, 718));
     loginPage=new LoginPage(driver);
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void totalFlow() throws InterruptedException {
       loginPage.loginValidUser(System.getProperty("user"),System.getProperty("password"));
       Thread.sleep(3000);
        {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/aside/button"));
            element.click();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.name("toFieldInput")).click();
        driver.findElement(By.name("toFieldInput")).sendKeys("javaoptimist@gmail.com");
        driver.findElement(By.name("subject")).click();
//        {
//            WebElement element = driver.findElement(By.cssSelector("#mceu_7-open > svg"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }
//        {
//            WebElement element = driver.findElement(By.tagName("body"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element, 0, 0).perform();
//        }
//        driver.findElement(By.name("subject")).sendKeys("qwerty33");
//        driver.switchTo().frame(1);
//        driver.findElement(By.cssSelector("div")).click();
//        {
//            WebElement element = driver.findElement(By.id("tinymce"));
//            js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = '<div><span id=\"_mce_caret\" data-mce-bogus=\"true\"><span style=\"font-size: 12pt; line-height: 14pt; font-family: Arial;\" data-mce-style=\"line-height: 14pt; font-family: Arial; font-size: 12pt;\" class=\"customFontStyle\">﻿qwqwqwqwq</span></span><br></div>'}", element);
//        }
//        driver.switchTo().defaultContent();
//        driver.findElement(By.cssSelector(".sendmsg__bottom-controls > .button")).click();
    }

}
