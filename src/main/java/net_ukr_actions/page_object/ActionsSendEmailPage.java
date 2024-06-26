package net_ukr_actions.page_object;

import net_ukr_actions.model.Letter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsSendEmailPage {
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


    public <T> T sendEmail(Letter letter, T page) {
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
        return submitSendClick(page);

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

    public InboxTableLettersPage toInbox() {
        try {
            WebElement element = driver.findElement(sendEmailPage.buttonInbox());
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (StaleElementReferenceException e) {
            toInbox();
        }
        return new InboxTableLettersPage(driver);
    }

    private <T> T submitSendClick(T page) {
        WebElement element = driver.findElement(sendEmailPage.submitSend());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return page;
    }
}
