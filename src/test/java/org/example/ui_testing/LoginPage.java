package org.example.ui_testing;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import  org.junit.jupiter.api.Assertions;


public class LoginPage {
    private WebDriver driver;
    @FindBy(name = "login")
    private  WebElement inputLogin;
    @FindBy(name = "password")
    private WebElement inputPassword;
    @FindBy(css = ".Ol0-ktls.jY4tHruE._2yaudugp")
    private WebElement buttonLogin;
    @FindBy(xpath = "/html/head/title")
    private WebElement titlePage;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
//        driver.get("https://accounts.ukr.net/login");
        PageFactory.initElements(driver,this);

    }

    public void verifyTitle(){
        String getTitle=titlePage.getText().trim();
        Assertions.assertEquals("Пошта @ ukr.net - українська електронна пошта • Створи емейл",getTitle);

    }
    public SendEmailPage loginValidUser(String login, String password) {
        inputLogin.clear();
        inputPassword.clear();
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        buttonLogin.click();
        return new SendEmailPage(driver);
    }

    public void typeLogin(String login){
        inputLogin.clear();
        inputLogin.sendKeys(login);
    }
    public void typePassword(String password){
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void login(){
        buttonLogin.click();
    }


}

