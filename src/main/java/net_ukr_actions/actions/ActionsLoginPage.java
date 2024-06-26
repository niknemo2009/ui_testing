package net_ukr_actions.actions;


import net_ukr_actions.model.User;
import net_ukr_actions.page_object.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsLoginPage implements Validateble {
    LoginPage loginPage;
    WebDriver driver;
    protected WebDriverWait wait;

    public ActionsLoginPage(WebDriver driver) {
        this.loginPage = new LoginPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.driver = driver;
    }

    public <T> T signInUser(User user, T actions) {
        return typeLogin(user.login()).
                typePassword(user.password()).
                submitLogin(actions);

    }

    private ActionsLoginPage typeLogin(String login) {
        WebElement element = driver.findElement(loginPage.inputLogin());
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(login);
        return this;
    }

    private ActionsLoginPage typePassword(String password) {
        WebElement element = driver.findElement(loginPage.inputPassword());
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(password);
        return this;
    }

    private <T> T submitLogin(T actions) {
        WebElement element = driver.findElement(loginPage.buttonLogin());
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return actions;
    }

    @Override
    public boolean validate() {
        return false;
    }
}



