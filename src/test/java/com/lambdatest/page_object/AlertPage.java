package com.lambdatest.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AlertPage {
    private final WebDriver driver;
    @FindBy(css = "div.mt-30.rounded button")
    List<WebElement> buttons;
    @FindBy(css = "#confirm-demo")
    private WebElement textConfirm;

    @FindBy(id = "prompt-demo")
    private WebElement textPrompt;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void simpleAlert() {
        buttons.get(0).click();
    }

    public void confirmAlert() {
        buttons.get(1).click();
    }

    public void promptAlert() {
        buttons.get(2).click();
    }

    public String getConfirmText() {
        return textConfirm.getText();
    }

    public String getPromptText() {
        return textPrompt.getText();
    }
}
