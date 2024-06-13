package ukr_net.page_object;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ukr_net.pojo.Letter;

import java.util.List;

public class SendEmailPage {
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
        buttonCreateEmail.click();
        inputReceiver.sendKeys(letter.receiver());
        inputSubject.sendKeys(letter.subject());
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);
        String newBody = """
                '<body id="tinymce" class="mce-content-body " data-id="mce_2" contenteditable="true"><div><span style="font-size: 12pt; line-height: 14pt; font-family: Arial;" data-mce-style="line-height: 14pt; font-family: Arial; font-size: 12pt;" class="customFontStyle">%s</span></body>'    
                    """.formatted(letter.message());
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