package net_ukr_actions.actions;


import net_ukr_actions.model.User;
import net_ukr_actions.page_object.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActionsLoginPage extends BaseActions {
    private final LoginPage loginPage;


    public ActionsLoginPage(WebDriver driver) {
        super(driver);
        this.loginPage = new LoginPage();
    }

    private static String nameMetod() {
        return new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
    }

    public <T> T signInUser(User user, T actions) {
        return typeLogin(user.login()).
                typePassword(user.password()).
                submitLogin(actions);

    }

    private ActionsLoginPage typeLogin(String login) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.inputLogin()));
        element.clear();
        element.sendKeys(login);
        return this;
    }

    private ActionsLoginPage typePassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.inputPassword()));
        element.clear();
        element.sendKeys(password);
        return this;
    }

    private <T> T submitLogin(T actions) {
        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + "1.png", driver);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginPage.buttonLogin()));
        element.click();
        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + "2.png", driver);
        return actions;
    }
}



