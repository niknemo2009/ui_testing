package net_ukr_actions.actions;


import net_ukr_actions.model.Letter;
import net_ukr_actions.page_object.SendEmailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ActionsSendEmailPage extends BaseActions {
    private final SendEmailPage sendEmailPage;
    private final JavascriptExecutor js;

    public ActionsSendEmailPage(WebDriver driver) {
        super(driver);
        sendEmailPage = new SendEmailPage();
        js = (JavascriptExecutor) driver;
    }

    public ActionsSendEmailPage writeEmail(Letter letter) {

        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + ".png", driver);
        clickInbox().
                clickCreateEmail().
                typeReceiver(letter.receiver()).
                typeSubject(letter.subject());
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(sendEmailPage.iframe()));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailPage.bodyLetter()));
        js.executeScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
        driver.switchTo().defaultContent();
        return this;

    }


    private ActionsSendEmailPage clickCreateEmail() {
        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + ".png", driver);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailPage.buttonCreateEmail())).click();
        return this;
    }

    private ActionsSendEmailPage clickInbox() {
        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + ".png", driver);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailPage.buttonInbox())).click();
        makeScreenshot("clickInbox2.png", driver);
        return this;
    }

    private ActionsSendEmailPage typeReceiver(String emailReceiver) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sendEmailPage.inputReceiver())).sendKeys(emailReceiver);
        return this;
    }

    private ActionsSendEmailPage typeSubject(String emailSubject) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sendEmailPage.inputSubject())).sendKeys(emailSubject);
        return this;
    }

    public ActionsInboxTableLettersPage toInbox() {
        try {
            makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + ".png", driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(sendEmailPage.buttonInbox())).click();
        } catch (StaleElementReferenceException e) {
            toInbox();
        }
        return new ActionsInboxTableLettersPage(driver);
    }

    public <T> T submitSendClick(T actions) {
        makeScreenshot(FILE_SCREENSHOTS + new Throwable().getStackTrace()[0] + "1.png", driver);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailPage.submitSend())).click();
        return actions;
    }


    public ActionsSendEmailPage attachFile() {
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailPage.attachFile())).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F7);
            Thread.sleep(2000);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_F7);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            robot.keyRelease(KeyEvent.VK_ENTER);
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
