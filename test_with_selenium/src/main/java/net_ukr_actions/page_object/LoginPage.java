package net_ukr_actions.page_object;


import org.openqa.selenium.By;


public class LoginPage {

    private final By inputLogin = By.name("login");
    private final By inputPassword = By.name("password");
    private final By buttonLogin = By.xpath("//button[text()='Продовжити']");

    private final By titlePage = By.xpath("/html/head/title");

    public By inputLogin() {
        return inputLogin;
    }

    public By inputPassword() {
        return inputPassword;
    }

    public By buttonLogin() {
        return buttonLogin;
    }

    public By titlePage() {
        return titlePage;
    }
}

