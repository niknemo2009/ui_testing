package net.ukr.page_object;


import net.ukr.model.Letter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SendEmailPage extends BasePage {

    private final JavascriptExecutor js;
    @FindBy(css = ".button.primary.compose")
    private WebElement buttonCreateEmail;
    @FindBy(name = "toFieldInput")
    private WebElement inputReceiver;
    @FindBy(css = "input.input[name='subject']")
    private WebElement inputSubject;
    //    @FindBy(xpath = "//button[text()='Надіслати']") ??????????????????????????
//    private List<WebElement> buttonsSend;
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[1]/div/button")
    private WebElement submitSend;
    @FindBy(xpath = "//button[text()='Файл']")
    private WebElement buttonFileAttach;
    @FindBy(xpath = "//span[text()='Вхідні']")
    private WebElement buttonInbox;

    public SendEmailPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;

    }


    public void sendEmail(Letter letter) {
        clickInbox().
                clickCreateEmail().
                typeReceiver(letter.receiver()).
                typeSubject(letter.subject());
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);
        js.executeScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
        driver.switchTo().defaultContent();
        //buttonsSend.get(0).click();
        submitSendClick();

    }

    private SendEmailPage clickCreateEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateEmail)).click();
        return this;
    }

    private SendEmailPage clickInbox() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonInbox));
        return this;
    }

    private SendEmailPage typeReceiver(String emailReceiver) {
        wait.until(ExpectedConditions.visibilityOf(inputReceiver)).sendKeys(emailReceiver);
        return this;
    }

    private SendEmailPage typeSubject(String emailSubject) {
        wait.until(ExpectedConditions.visibilityOf(inputSubject)).sendKeys(emailSubject);
        return this;
    }

    public InboxTableLettersPage toInbox() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(buttonInbox)).click();
        } catch (StaleElementReferenceException e) {
            buttonInbox = driver.findElement(By.xpath("//span[text()='Вхідні']"));
            buttonInbox.click();

        }
        return new InboxTableLettersPage(driver);
    }

    private void submitSendClick() {
        wait.until(ExpectedConditions.elementToBeClickable(submitSend)).click();
    }

}
