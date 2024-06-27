package net_ukr_actions.page_object;


import org.openqa.selenium.By;

public class SendEmailPage {

    private final By buttonCreateEmail = By.cssSelector(".button.primary.compose");
    private final By inputReceiver = By.name("toFieldInput");
    private final By inputSubject = By.cssSelector("input.input[name='subject']");
    private final By submitSend = By.xpath("//*[@id=\"screens\"]/div/div[1]/div/button");

    private final By buttonInbox = By.xpath("//*[@id=\"0\"]/span[4]");
    private final By iframe = By.id("mce_0_ifr");
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
