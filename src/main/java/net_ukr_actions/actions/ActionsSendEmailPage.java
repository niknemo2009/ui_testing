package net_ukr_actions.actions;

import net_ukr_actions.model.Letter;
import net_ukr_actions.page_object.SendEmailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsSendEmailPage implements Validateble {
    SendEmailPage sendEmailPage;
    WebDriver driver;
    protected WebDriverWait wait;
    JavascriptExecutor js;

    public ActionsSendEmailPage(WebDriver driver) {
        sendEmailPage = new SendEmailPage();
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    public <T> T sendEmail(Letter letter, T actions) {
        clickInbox().
                clickCreateEmail().
                typeReceiver(letter.receiver()).
                typeSubject(letter.subject());
        WebElement iframe = driver.findElement(sendEmailPage.iframe());
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        WebElement bodyLetter = driver.findElement(sendEmailPage.bodyLetter());
        wait.until(ExpectedConditions.elementToBeClickable(bodyLetter));
        js.executeScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
        driver.switchTo().defaultContent();
        return submitSendClick(actions);

    }

    private ActionsSendEmailPage clickCreateEmail() {
        WebElement element = driver.findElement(sendEmailPage.buttonCreateEmail());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    private ActionsSendEmailPage clickInbox() {
        WebElement element = driver.findElement(sendEmailPage.buttonInbox());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    private ActionsSendEmailPage typeReceiver(String emailReceiver) {
        WebElement element = driver.findElement(sendEmailPage.inputReceiver());
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(emailReceiver);
        return this;
    }

    private ActionsSendEmailPage typeSubject(String emailSubject) {
        WebElement element = driver.findElement(sendEmailPage.inputSubject());
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(emailSubject);
        return this;
    }

    public ActionsInboxTableLettersPage toInbox() {
        try {
            WebElement element = driver.findElement(sendEmailPage.buttonInbox());
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (StaleElementReferenceException e) {
            toInbox();
        }
        return new ActionsInboxTableLettersPage(driver);
    }

    private <T> T submitSendClick(T actions) {
        WebElement element = driver.findElement(sendEmailPage.submitSend());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return actions;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
