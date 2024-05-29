package org.example.ui_testing;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
public class LoginPage {
    private WebDriver driver= new ChromeDriver();
    public WebElement  inputLogin =driver.findElement(By.name("login"));
    public  WebElement  inputPassword =driver.findElement(By.name("password"));
    public  WebElement  buttonLogin = driver.findElement(By.cssSelector(".compose"));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://accounts.ukr.net/login");
    }

    public void loginValidUser(String login, String password){
       inputLogin.clear();
       inputPassword.clear();
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
    }
}
