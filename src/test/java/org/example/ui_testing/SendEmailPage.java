package org.example.ui_testing;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendEmailPage {
    JavascriptExecutor js;
    private final WebDriver driver;
    @FindBy(css = ".button.primary.compose")
    private WebElement buttonCreateEmail;
    @FindBy(name = "toFieldInput")
    private WebElement inputReceiver;
    @FindBy(css = "input.input[name='subject']")
    private WebElement inputSubject;
    @FindBy(xpath = "//*[@id='screens']/div/div[1]/div/button")
    private WebElement buttonSend;
    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/section[2]/div[2]/label/button")
    private WebElement buttonFileAttach;
    @FindBy(xpath = "/html/head/title")
    private WebElement titlePage;

    @FindBy(xpath = "//*[@id=\"0\"]/span[4]")
    private WebElement buttonInbox;

    public SendEmailPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyTitle(String sendersEmail) {
        String getTitle = titlePage.getText().trim();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ " + getTitle);
        Assertions.assertTrue(getTitle.contains("Вхідні • " + sendersEmail));

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
        buttonSend.click();

    }

    public InboxTableLettersPage toInbox() {
        buttonInbox.click();
        return new InboxTableLettersPage(driver);
    }

}
//todo Command or Builder for dif variants send(simple,with  attach ...) ????????
//todo normalisation all selector !!!!!