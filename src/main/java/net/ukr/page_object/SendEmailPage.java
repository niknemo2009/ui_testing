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
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[1]/div/button")
    private WebElement submitSend;

    @FindBy(xpath = "//*[@id=\"0\"]/span[4]")
    private WebElement buttonInbox;
    @FindBy(id = "mce_0_ifr")
    WebElement iframe;
    @FindBy(id = "tinymce")
    WebElement bodyLetter;
    public SendEmailPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;

    }


    public <T extends BasePage> T sendEmail(Letter letter, T page) {
        clickInbox().
                clickCreateEmail().
                typeReceiver(letter.receiver()).
                typeSubject(letter.subject());
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.elementToBeClickable(bodyLetter));
        js.executeScript("document.getElementById('tinymce').innerHTML=" + letter.generateLettersBody());
        driver.switchTo().defaultContent();
        return submitSendClick(page);

    }

    private SendEmailPage clickCreateEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateEmail)).click();
        return this;
    }

    private SendEmailPage clickInbox() {
        wait.until(ExpectedConditions.visibilityOf(buttonInbox)).click();
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

    private <T extends BasePage> T submitSendClick(T page) {
        wait.until(ExpectedConditions.elementToBeClickable(submitSend)).click();
        return page;
    }

}
