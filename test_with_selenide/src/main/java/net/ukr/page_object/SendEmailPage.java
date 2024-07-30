package net.ukr.page_object;


import com.codeborne.selenide.SelenideElement;
import net.ukr.model.Letter;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SendEmailPage {
    private final SelenideElement buttonCreateEmail;
    private final SelenideElement inputReceiver;
    private final SelenideElement inputSubject;
    private final SelenideElement submitSend;

    private final SelenideElement buttonInbox;
    private final SelenideElement iframe;
    private final SelenideElement bodyLetter;

    private final SelenideElement attachFile;

    public SendEmailPage() {
        this.buttonCreateEmail = $(By.cssSelector(".button.primary.compose"));
        this.inputReceiver = $(By.name("toFieldInput"));
        this.inputSubject = $(By.cssSelector("input.input[name='subject']"));
        this.submitSend = $(By.xpath("//*[@id=\"screens\"]/div/div[1]/div/button"));
        this.buttonInbox = $(By.xpath("//*[@id=\"0\"]/span[4]"));
        this.iframe = $(By.cssSelector("iframe#mce_0_ifr"));
        this.bodyLetter = $(By.id("tinymce"));
        this.attachFile = $(By.cssSelector("button.action.attachments-file-button.button.outline"));
    }

    public SendEmailPage writeEmail(Letter letter) {
        clickInbox().
                clickCreateEmail().
                typeReceiver(letter.receiver()).
                typeSubject(letter.subject());
        $(iframe).shouldBe(visible);
        switchTo().frame(iframe);
        bodyLetter.shouldBe(clickable);
        executeJavaScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
        switchTo().defaultContent();
        return this;

    }


    private SendEmailPage clickCreateEmail() {
        buttonCreateEmail.shouldBe(clickable).click();
        return this;
    }

    private SendEmailPage clickInbox() {
        buttonInbox.shouldBe(clickable).click();
        return this;
    }

    private SendEmailPage typeReceiver(String emailReceiver) {
        inputReceiver.shouldBe(visible).sendKeys(emailReceiver);
        return this;
    }

    private SendEmailPage typeSubject(String emailSubject) {
        inputSubject.shouldBe(visible).sendKeys(emailSubject);
        return this;
    }

    public InboxTableLettersPage toInbox() {
        buttonInbox.shouldBe(clickable).click();
        return new InboxTableLettersPage();
    }

    public <T> T submitSendClick(T expectedPage) {
        submitSend.shouldBe(clickable).click();
        return expectedPage;
    }


    public SendEmailPage attachFile() {
        attachFile.shouldBe(clickable).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F7);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_F7);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(1000);

        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
