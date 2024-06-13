package ukr_net.page_object;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    private final WebDriver driver;
    @FindBy(name = "login")
    private WebElement inputLogin;
    @FindBy(name = "password")
    private WebElement inputPassword;
    @FindBy(css = ".Ol0-ktls.jY4tHruE._2yaudugp")
    private WebElement buttonLogin;
    @FindBy(xpath = "/html/head/title")
    private WebElement titlePage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public SendEmailPage loginValidUser(String login, String password) {
        typeLogin(login);
        typePassword(password);
        buttonLogin.click();
        return new SendEmailPage(driver);
    }

    private void typeLogin(String login) {
        inputLogin.clear();
        inputLogin.sendKeys(login);
    }

    private void typePassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }




}

