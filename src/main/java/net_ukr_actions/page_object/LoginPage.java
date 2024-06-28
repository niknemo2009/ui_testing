package net_ukr_actions.page_object;


import org.openqa.selenium.By;


public class LoginPage {

    private final By inputLogin = By.name("login");
    private final By inputPassword = By.name("password");
    private final By buttonLogin = By.xpath("/html/body/div/div/main/div[1]/div[1]/form/div[2]/button");
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

