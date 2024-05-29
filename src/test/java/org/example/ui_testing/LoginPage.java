package org.example.ui_testing;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private WebDriver driver;
    public WebElement  inputLogin;
    public  WebElement  inputPassword;
    public  WebElement  buttonLogin ;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://accounts.ukr.net/login");
        inputLogin =driver.findElement(By.name("login"));
        inputPassword =driver.findElement(By.name("password"));
        buttonLogin =driver.findElement(By.cssSelector(".Ol0-ktls.jY4tHruE._2yaudugp"));

    }

    public EmailPage loginValidUser(String login, String password)  {
       inputLogin.clear();
       inputPassword.clear();
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        buttonLogin.click();
     return new EmailPage(driver);
    }
}
