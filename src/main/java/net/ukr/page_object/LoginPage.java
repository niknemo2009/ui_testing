package net.ukr.page_object;


import net.ukr.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class LoginPage extends BasePage {

    @FindBy(name = "login")
    private WebElement inputLogin;
    @FindBy(name = "password")
    private WebElement inputPassword;
    @FindBy(css = ".Ol0-ktls.jY4tHruE._2yaudugp")
    private WebElement buttonLogin;
    @FindBy(xpath = "/html/head/title")
    private WebElement titlePage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Sign In any user
     *
     * @param <T>  the generic parameter
     * @param user the instance type User
     * @param page the instance type T
     * @return the instance  type T
     */
    public <T extends BasePage> T signinUser(User user, T page) {
        return typeLogin(user.login()).
                typePassword(user.password()).
                submitLogin(page);

    }

    private LoginPage typeLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(inputLogin));
        inputLogin.clear();
        inputLogin.sendKeys(login);
        return this;
    }

    private LoginPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    private <T> T submitLogin(T page) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        buttonLogin.click();
        return page;
    }
}

