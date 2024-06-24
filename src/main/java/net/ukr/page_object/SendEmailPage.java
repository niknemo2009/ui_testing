package net.ukr.page_object;



import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import net.ukr.model.Letter;

import java.time.Duration;
import java.util.List;

public class SendEmailPage  {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    @FindBy(css = ".button.primary.compose")
    private WebElement buttonCreateEmail;
    @FindBy(name = "toFieldInput")
    private WebElement inputReceiver;
    @FindBy(css = "input.input[name='subject']")
    private WebElement inputSubject;

    @FindBy(xpath = "//button[text()='Надіслати']")
    private List<WebElement> buttonsSend;
    @FindBy(xpath = "//button[text()='Файл']")
    private WebElement buttonFileAttach;
    @FindBy(xpath = "//span[text()='Вхідні']")
    private WebElement buttonInbox;

    public SendEmailPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }


    public void sendEmail(Letter letter) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button.primary.compose")));
        buttonCreateEmail.click();
        inputReceiver.sendKeys(letter.receiver());
        inputSubject.sendKeys(letter.subject());
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);
        String newBody = letter.generateLettersBody(letter);
        js.executeScript("document.getElementById('tinymce').innerHTML=" + newBody);
        driver.switchTo().defaultContent();
        buttonsSend.get(0).click();

    }

    public InboxTableLettersPage toInbox() {
        try {
            buttonInbox.click();
        } catch (StaleElementReferenceException e) {
            buttonInbox = driver.findElement(By.xpath("//span[text()='Вхідні']"));
            buttonInbox.click();

        }
        return new InboxTableLettersPage(driver);
    }

}
//todo Command or Builder for dif variants send(simple,with  attach ...) ???