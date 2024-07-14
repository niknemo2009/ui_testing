package net_ukr_actions.page_object;


import com.codeborne.selenide.SelenideElement;
import net_ukr_actions.model.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SendEmailPage {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final SelenideElement buttonCreateEmail;
    private final SelenideElement inputReceiver;
    private final SelenideElement inputSubject;
    private final SelenideElement submitSend;

    private final SelenideElement buttonInbox;
    private final SelenideElement iframe;
    private final SelenideElement bodyLetter;

    private final SelenideElement attachFile;

    public SendEmailPage(WebDriver driver) {
        this.driver = driver;
        this.buttonCreateEmail = $(By.cssSelector(".button.primary.compose"));
        this.inputReceiver = $(By.name("toFieldInput"));
        this.inputSubject = $(By.cssSelector("input.input[name='subject']"));
        this.submitSend = $(By.xpath("//*[@id=\"screens\"]/div/div[1]/div/button"));
        this.buttonInbox = $(By.xpath("//*[@id=\"0\"]/span[4]"));
        this.iframe = $(By.cssSelector("iframe#mce_0_ifr"));
        this.bodyLetter = $(By.id("tinymce"));
        this.attachFile = $(By.cssSelector("button.action.attachments-file-button.button.outline"));
        this.js = (JavascriptExecutor) driver;
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
        inputReceiver.shouldBe(visible).type(emailReceiver);
        return this;
    }

    private SendEmailPage typeSubject(String emailSubject) {
        inputSubject.shouldBe(visible).type(emailSubject);
        return this;
    }

    public InboxTableLettersPage toInbox() {
        buttonInbox.shouldBe(clickable).click();
        return new InboxTableLettersPage(driver);
    }

    public <T> T submitSendClick(T expectedPage) {
        submitSend.shouldBe(clickable).click();
        return expectedPage;
    }


    public SendEmailPage attachFile() {
        attachFile.shouldBe(clickable).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F7);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_F7);
            Thread.sleep(2000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);

        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
