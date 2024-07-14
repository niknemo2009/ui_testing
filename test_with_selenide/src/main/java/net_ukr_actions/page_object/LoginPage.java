package net_ukr_actions.page_object;


import com.codeborne.selenide.SelenideElement;
import net_ukr_actions.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    WebDriver driver;
    private final SelenideElement inputLogin;
    private final SelenideElement inputPassword;
    private final SelenideElement buttonLogin;

    private final SelenideElement titlePage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.inputLogin = $(By.name("login"));
        this.inputPassword = $(By.name("password"));
        this.buttonLogin = $(By.xpath("//button[text()='Продовжити']"));
        this.titlePage = $(By.xpath("/html/head/title"));
    }

    public <T> T signInUser(User user, T expectedPage) {
        return typeLogin(user.login()).
                typePassword(user.password()).
                submitLogin(expectedPage);

    }

    private LoginPage typeLogin(String login) {
        inputLogin.shouldBe(visible).sendKeys(login);
        return this;
    }

    private LoginPage typePassword(String password) {
        inputPassword.shouldBe(visible).sendKeys(password);
        return this;
    }

    private <T> T submitLogin(T expectedPage) {
        buttonLogin.shouldBe(clickable).click();
        return expectedPage;
    }
}




