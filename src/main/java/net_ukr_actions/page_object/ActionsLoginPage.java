package net_ukr_actions.page_object;

import net.ukr.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsLoginPage {
    LoginPage loginPage;
    WebDriver driver;
    protected WebDriverWait wait;

    public ActionsLoginPage(WebDriver driver) {
        this.loginPage = new LoginPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.driver = driver;
    }

    public <T extends BasePage> T signInUser(User user, T page) {
        return typeLogin(user.login()).
                typePassword(user.password()).
                submitLogin(page);

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

    private <T> T submitLogin(T page) {
        WebElement element = driver.findElement(loginPage.buttonLogin());
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return page;
    }
}



