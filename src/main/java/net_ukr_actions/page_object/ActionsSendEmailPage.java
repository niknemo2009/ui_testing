package net_ukr_actions.page_object;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsSendEmailPage {
    SendEmailPage sendEmailPage;
    WebDriver driver;
    protected WebDriverWait wait;
    JavascriptExecutor js;

    public ActionsSendEmailPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


//    public <T extends BasePage> T sendEmail(Letter letter, T page) {
//        clickInbox().
//                clickCreateEmail().
//                typeReceiver(letter.receiver()).
//                typeSubject(letter.subject());
//        wait.until(ExpectedConditions.visibilityOf(iframe));
//        driver.switchTo().frame(iframe);
//        wait.until(ExpectedConditions.elementToBeClickable(bodyLetter));
//        js.executeScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
//        driver.switchTo().defaultContent();
//        return submitSendClick(page);
//
//    }
//
//    private ActionsSendEmailPage clickCreateEmail() {
//        WebElement element=driver.findElement(sendEmailPage.buttonCreateEmail());
//        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//        return this;
//    }
//
//    private ActionsSendEmailPage  clickInbox() {
//        WebElement element=driver.findElement(sendEmailPage.buttonInbox());
//        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//        return this;
//    }
//
//    private ActionsSendEmailPage  typeReceiver(String emailReceiver) {
//        wait.until(ExpectedConditions.visibilityOf(inputReceiver)).sendKeys(emailReceiver);
//        return this;
//    }
//
//    private ActionsSendEmailPage  typeSubject(String emailSubject) {
//        wait.until(ExpectedConditions.visibilityOf(inputSubject)).sendKeys(emailSubject);
//        return this;
//    }
//
//    public InboxTableLettersPage toInbox() {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(buttonInbox)).click();
//        } catch (StaleElementReferenceException e) {
//            buttonInbox = driver.findElement(By.xpath("//span[text()='Вхідні']"));
//            buttonInbox.click();
//
//        }
//        return new InboxTableLettersPage(driver);
//    }
//
//    private <T extends BasePage> T submitSendClick(T page) {
//        wait.until(ExpectedConditions.elementToBeClickable(submitSend)).click();
//        return page;
//    }
}
