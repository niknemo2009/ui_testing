package net_ukr_actions.page_object;


import org.openqa.selenium.By;

public class SendEmailPage {


    //    @FindBy(css = ".button.primary.compose")
//    private WebElement buttonCreateEmail;
    private final By buttonCreateEmail = By.cssSelector(".button.primary.compose");
    //    @FindBy(name = "toFieldInput")
//    private WebElement inputReceiver;
    private final By inputReceiver = By.name("toFieldInput");
    //    @FindBy(css = "input.input[name='subject']")
//    private WebElement inputSubject;
    private final By inputSubject = By.cssSelector("input.input[name='subject']");
    //    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[1]/div/button")
//    private WebElement submitSend;
    private final By submitSend = By.xpath("//*[@id=\"screens\"]/div/div[1]/div/button");

    //    @FindBy(xpath = "//span[text()='Вхідні']")
//    private WebElement buttonInbox;
    private final By buttonInbox = By.xpath("//span[text()='Вхідні']");
    //    @FindBy(id = "mce_0_ifr")
//    WebElement iframe;
    private final By iframe = By.id("mce_0_ifr");
    //    @FindBy(id = "tinymce")
//    WebElement bodyLetter;
    private final By bodyLetter = By.id("tinymce");

    public By buttonCreateEmail() {
        return buttonCreateEmail;
    }

    public By inputReceiver() {
        return inputReceiver;
    }

    public By inputSubject() {
        return inputSubject;
    }

    public By submitSend() {
        return submitSend;
    }

    public By buttonInbox() {
        return buttonInbox;
    }

    public By iframe() {
        return iframe;
    }

    public By bodyLetter() {
        return bodyLetter;
    }
}
